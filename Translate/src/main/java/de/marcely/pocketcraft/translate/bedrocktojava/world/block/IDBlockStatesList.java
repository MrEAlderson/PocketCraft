package de.marcely.pocketcraft.translate.bedrocktojava.world.block;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import de.marcely.pocketcraft.bedrock.component.BlockMapping;
import de.marcely.pocketcraft.utils.Application;
import de.marcely.pocketcraft.utils.logger.Logger;

public class IDBlockStatesList {
	
	private final BlockState[][] states;
	
	public IDBlockStatesList(BlockState[][] states){
		this.states = states;
	}
	
	public @Nullable BlockState get(int id, int data){
		if(id < 0 || id >= this.states.length || data < 0)
			return null;
		
		final BlockState[] node = this.states[id];
		
		if(node == null || data >= node.length)
			return null;
		
		return node[data];
	}
	
	
	
	public static IDBlockStatesList load(JsonObject root) throws Exception {
		final List<BlockCollision> collisionInstances = new ArrayList<>();
		final Map<String, BlockCollision> collisionBaseList = new HashMap<>();
		final Map<String, Integer> idsMap = BlockMapping.loadIdsMap();
		
		// helper
		final Function<Object, BlockCollision> collisionSupplier = (element) -> {
			if(element instanceof String){
				final BlockCollision coll = collisionBaseList.get((String) element);
				
				if(coll == null)
					Logger.get(Application.TRANSLATE, "Block States List").warn("Failed to find collision " + (String) element);
				
				return coll;
			
			}else if(element instanceof BlockCollision){
				for(BlockCollision coll:collisionInstances){
					if(coll.equals(element))
						return coll;
				}
				
				return (BlockCollision) element;
				
			}else
				throw new IllegalArgumentException("Invalid argument type");
		};
		
		// load collisions
		{
			final JsonObject collisionsRoot = root.getAsJsonObject("collisions");
			
			for(String id:collisionsRoot.keySet()){
				final JsonArray rawCubes = collisionsRoot.get(id).getAsJsonArray();
				final BlockCollision.Cube[] cubes = new BlockCollision.Cube[rawCubes.size()];
				
				for(int i=0; i<rawCubes.size(); i++){
					final JsonArray rawCube = rawCubes.get(i).getAsJsonArray();
					final BlockCollision.Cube cube = new BlockCollision.Cube();
					
					cube.x = rawCube.get(0).getAsFloat();
					cube.y = rawCube.get(1).getAsFloat();
					cube.z = rawCube.get(2).getAsFloat();
					cube.widthX = rawCube.get(3).getAsFloat();
					cube.height = rawCube.get(4).getAsFloat();
					cube.widthZ = rawCube.get(5).getAsFloat();
					
					cubes[i] = cube;
				}
				
				final BlockCollision coll = new BlockCollision(cubes);
				
				collisionBaseList.put(id, coll);
				collisionInstances.add(coll);
			}
		}
		
		final Map<Integer, BlockState[]> statesMap = new HashMap<>();
		
		// load states
		{
			final JsonObject listRoot = root.getAsJsonObject("list");
		
			for(String rawId:listRoot.keySet()){
				final int id = Integer.parseInt(rawId);
				final JsonObject entry = listRoot.getAsJsonObject(rawId);
				Integer[] bedrockIds = null;
				int[] bedrockDatas = null;
				BlockCollision[] collisions = null;
				
				// get bedrock ids
				{
					final JsonElement bedrockIdEl = entry.get("bedrock_id");
					
					if(bedrockIdEl.isJsonPrimitive()){
						bedrockIds = new Integer[]{ idsMap.get(bedrockIdEl.getAsString()) };
						
						if(bedrockIds[0] == null){
							Logger.get(Application.TRANSLATE, "Block States List").warn("Failed to find bedrock_id " + entry.get("bedrock_id").getAsString());
							continue;
						}
						
					}else{
						final JsonObject bedrockIdObj = bedrockIdEl.getAsJsonObject();
						
						bedrockIds = new Integer[bedrockIdObj.size()];
						
						for(String bRawId:bedrockIdObj.keySet()){
							final int bId = Integer.parseInt(bRawId);
							final String bValue = bedrockIdObj.get(bRawId).getAsString();
							
							if(bId >= bedrockIds.length){
								Logger.get(Application.TRANSLATE, "Block States List").warn("bedrock_id data id " + bId + " is higher than the total entries amount");
								continue;
							}
							
							bedrockIds[bId] = idsMap.get(bValue);
							
							if(bedrockIds[bId] == null)
								Logger.get(Application.TRANSLATE, "Block States List").warn("Failed to find bedrock_id " + bValue);
						}
					}
				}
				
				// get bedrock datas
				{
					final JsonElement bedrockDataElement = entry.get("bedrock_data");
					
					if(bedrockDataElement != null){
						if(bedrockDataElement.isJsonPrimitive()){
							bedrockDatas = new int[]{ bedrockDataElement.getAsInt() };
						
						}else{
							final JsonObject bedrockDataObj = bedrockDataElement.getAsJsonObject();			
							final JsonElement sameObj = bedrockDataObj.get("same");
							
							if(sameObj.isJsonNull()){
								bedrockDatas = new int[bedrockDataObj.size()];
								
								for(String bRawData:bedrockDataObj.keySet()){
									final int bData = Integer.parseInt(bRawData);
									final int bValue = bedrockDataObj.get(bRawData).getAsInt();
									
									if(bData >= bedrockDatas.length){
										Logger.get(Application.TRANSLATE, "Block States List").warn("bedrock_id data id " + bData + " is higher than the total entries amount");
										continue;
									}
									
									bedrockDatas[bData] = bValue;
								}
								
							}else{
								final int entriesAmount = sameObj.getAsInt();
								
								bedrockDatas = new int[entriesAmount+1];
								
								for(int i=0; i<=entriesAmount; i++)
									bedrockDatas[i] = i;
							}
						}
					}
				}
				
				// get collision
				{
					final JsonElement collisionElement = entry.get("collision");
					
					if(collisionElement != null){
						if(collisionElement.isJsonPrimitive()){
							collisions = new BlockCollision[]{ collisionSupplier.apply(collisionElement.getAsString()) };
							
							if(collisions[0] == null){
								Logger.get(Application.TRANSLATE, "Block States List").warn("Failed to find collision " + collisionElement.getAsString());
								continue;
							}
						
						}else{
							final JsonObject collisionObj = collisionElement.getAsJsonObject();
							final BlockCollision coll = collisionSupplier.apply(collisionObj.get("id").getAsString());
							
							if(coll == null){
								Logger.get(Application.TRANSLATE, "Block States List").warn("Failed to find collision " + collisionObj.get("id").getAsString());
								continue;
							}
							
							collisions = new BlockCollision[bedrockDatas.length];
							
							for(String bRawData:collisionObj.keySet()){
								if(bRawData.equals("id"))
									continue;
								
								final int bData = Integer.parseInt(bRawData);
								
								if(bData >= collisions.length){
									Logger.get(Application.TRANSLATE, "Block States List").warn("collision data " + bData + " is higher than the total entries amount");
									continue;
								}
								
								final JsonObject bValue = collisionObj.get(bRawData).getAsJsonObject();
								final JsonElement multiplyWidthX = bValue.get("multiply_width_x");
								final JsonElement multiplyHeight = bValue.get("multiply_height");
								final JsonElement multiplyWidthZ = bValue.get("multiply_width_z");
								final JsonElement setX = bValue.get("set_x");
								final JsonElement setY = bValue.get("set_y");
								final JsonElement setZ = bValue.get("set_z");
								final JsonElement setWidthX = bValue.get("set_width_x");
								final JsonElement setHeight = bValue.get("set_height");
								final JsonElement setWidthZ = bValue.get("set_width_z");
								
								final BlockCollision newColl = coll.clone();
								
								if(multiplyWidthX != null){
									Arrays.stream(newColl.entries).forEach(cube -> cube.widthX *= multiplyWidthX.getAsFloat());
								}
								
								if(multiplyHeight != null){
									Arrays.stream(newColl.entries).forEach(cube -> cube.height *= multiplyHeight.getAsFloat());
								}
								
								if(multiplyWidthZ != null){
									Arrays.stream(newColl.entries).forEach(cube -> cube.widthZ *= multiplyWidthZ.getAsFloat());
								}
								
								if(setX != null){
									Arrays.stream(newColl.entries).forEach(cube -> cube.x = setX.getAsFloat());
								}
								
								if(setY != null){
									Arrays.stream(newColl.entries).forEach(cube -> cube.y = setY.getAsFloat());
								}
								
								if(setZ != null){
									Arrays.stream(newColl.entries).forEach(cube -> cube.z = setZ.getAsFloat());
								}
								
								if(setWidthX != null){
									Arrays.stream(newColl.entries).forEach(cube -> cube.widthX = setWidthX.getAsFloat());
								}
								
								if(setHeight != null){
									Arrays.stream(newColl.entries).forEach(cube -> cube.height = setHeight.getAsFloat());
								}
								
								if(setWidthZ != null){
									Arrays.stream(newColl.entries).forEach(cube -> cube.widthZ = setWidthZ.getAsFloat());
								}
								
								collisions[bData] = collisionSupplier.apply(newColl);
							}
						}
					}
				}
				
				// put data states together
				final BlockState[] dataStates = new BlockState[bedrockDatas != null ? bedrockDatas.length : 1];
				
				if(bedrockDatas == null){
					
					dataStates[0] = new BlockState(bedrockIds[0], 0, collisions != null && collisions.length >= 1 ? collisions[0] : null);
					
				}else{
					for(int i=0; i<dataStates.length; i++){
						final int bedrockId = i < bedrockIds.length ? bedrockIds[i] : bedrockIds[0];
						final int bedrockData = bedrockDatas[i];
						final BlockCollision coll = collisions != null ? (i < collisions.length ? collisions[i] : collisions[0]) : null;
						
						dataStates[i] = new BlockState(bedrockId, bedrockData, coll);
					}
				}
				
				statesMap.put(id, dataStates);
			}
		}
		
		// put states together
		final BlockState[][] states = new BlockState[statesMap.keySet().stream().max((a, b) -> Integer.compare(a, b)).get() + 1][];
		
		statesMap.entrySet().forEach(e -> states[e.getKey()] = e.getValue());
		
		return new IDBlockStatesList(states);
	}
}