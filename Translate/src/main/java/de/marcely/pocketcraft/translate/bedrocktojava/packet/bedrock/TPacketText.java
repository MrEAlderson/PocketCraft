package de.marcely.pocketcraft.translate.bedrocktojava.packet.bedrock;

import de.marcely.pocketcraft.bedrock.component.BTextFormat;
import de.marcely.pocketcraft.bedrock.component.world.BParticleType;
import de.marcely.pocketcraft.bedrock.component.world.entity.EntityType;
import de.marcely.pocketcraft.bedrock.network.packet.PacketText;
import de.marcely.pocketcraft.java.network.packet.play.v8d9.V8D9PacketPlayClientChatMessage;
import de.marcely.pocketcraft.translate.bedrocktojava.BedrockPacketTranslator;
import de.marcely.pocketcraft.translate.bedrocktojava.EntityDebug;
import de.marcely.pocketcraft.translate.bedrocktojava.packet.java.TV8D9PacketPlayBlockBreakAnimation;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Chunk;
import de.marcely.pocketcraft.translate.bedrocktojava.world.Player;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockCollision.Cube;
import de.marcely.pocketcraft.translate.bedrocktojava.world.block.BlockState;
import de.marcely.pocketcraft.utils.scheduler.Scheduler;

public class TPacketText extends BedrockPacketTranslator<PacketText> {
	
	@Override
	public void handle(PacketText packet, Player player){
		if(packet.type != PacketText.TYPE_CHAT)
			return;
		
		// debug
		if(packet.message.startsWith("p!")){
			final String[] args = packet.message.split(" ");
			final String command = args[0].replace("p!", "");
			
			if(command.equals("entity")){
				EntityDebug.INSTANCE = new EntityDebug(player, Byte.parseByte(args[1]), EntityType.valueOf(args[2].toUpperCase()), Float.parseFloat(args[3]));
				player.sendChatMessage(BTextFormat.GREEN + "Ok. Enabled entity debug");
				
			}else if(command.equals("stop")){
				EntityDebug.INSTANCE = null;
				player.sendChatMessage(BTextFormat.GREEN + "Ok. Disabled debug");
				
			}else if(command.equals("blockinfo")){
				final int x = (int) player.getEntity().getX();
				final int y = (int) player.getEntity().getY() - 1;
				final int z = (int) player.getEntity().getZ();
				
				player.sendChatMessage(BTextFormat.AQUA + "X" + x + " Y" + y + " Z" + z);
				player.sendChatMessage("" + BTextFormat.GREEN + BTextFormat.BOLD + "State: " + BTextFormat.GREEN + player.getWorld().getBlockState(x, y, z));
				
			}else if(command.equals("resendchunks")){
				for(Chunk c:player.getWorld().getChunks())
					c.setSent(false);
				
				player.sendChatMessage(BTextFormat.GREEN + "" + player.getWorld().getChunks().size() + " chunks were marked as \"not sent\"");
				
			}else if(command.equals("blockcollision")){
				Scheduler.runRepeated(() -> {
					final int range = 1;
					
					for(int ix=-range; ix<=range; ix++){
						for(int iy=-range; iy<=range; iy++){
							for(int iz=-range; iz<=range; iz++){
								final BlockState state = player.getWorld().getBlockState((int) player.getX() + ix, (int) player.getY() + iy, (int) player.getZ() + iz);
								
								if(state == null || state.getCollision() == null)
									continue;
								
								for(Cube cube:state.getCollision().entries)
									drawCube(player, cube, (int) player.getX() + ix, (int) player.getY() + iy, (int) player.getZ() + iz);
							}
						}
					}
				}, 0, 500);
				
				player.sendChatMessage(BTextFormat.GREEN + "Now sending block collisions");
			}else{
				player.sendChatMessage(BTextFormat.YELLOW + "Debug Commands:");
				player.sendChatMessage(" p!entity <mode [0=event, 1=flag1, 2=flag2, 3=flag_palyer, 4=animate, 5=armor, 6=meta]> <entity type> <data>");
				player.sendChatMessage(" p!stop");
				player.sendChatMessage(" p!blockinfo (displays block info of the block below you)");
				player.sendChatMessage(" p!resendchunks");
				player.sendChatMessage(" p!blockcollision");
			}
			
			return;
		}
		
		TV8D9PacketPlayBlockBreakAnimation.asd = Double.parseDouble(packet.message);
		/*{
			final PacketWorldEvent out = new PacketWorldEvent();
			final String[] parts = packet.message.split(" ");
			
			out.x = (int) player.getX() + 1;
			out.y = (int) player.getY();
			out.z = (int) player.getZ();
			out.type = Integer.parseInt(parts[0]);
			out.data = Integer.parseInt(parts[1]);
			
			player.sendPacket(out);
		}*/
		
		// send to java
		{
			final V8D9PacketPlayClientChatMessage out = new V8D9PacketPlayClientChatMessage();
			
			out.message = packet.message;
			
			player.sendPacket(out);
		}
	}
	
	private static void drawCube(Player player, Cube cube, int x, int y, int z){
		final float minX = x + cube.getX();
		final float minY = y + cube.getY();
		final float minZ = z + cube.getZ();
		final float maxX = minX + cube.getWidthX();
		final float maxY = minY + cube.getHeight();
		final float maxZ = minZ + cube.getWidthZ();
		
		drawFace(player, minX, minY, minZ, maxX, maxY, minZ);
		drawFace(player, minX, minY, minZ, minX, maxY, maxZ);
		drawFace(player, maxX, minY, minZ, maxX, maxY, maxZ);
		drawFace(player, minX, minY, maxZ, maxX, maxY, maxZ);
		drawFace(player, minX, minY, minZ, maxX, minY, maxZ);
		drawFace(player, minX, maxY, minZ, maxX, maxY, maxZ);
	}
	
	private static void drawFace(Player player, float minX, float minY, float minZ, float maxX, float maxY, float maxZ){
		final float accuracy = 0.3F;
		
		for(float ix=minX; ix<=maxX; ix += accuracy){
			for(float iy=minY; iy<=maxY; iy += accuracy){
				for(float iz=minZ; iz<=maxZ; iz += accuracy){
					player.playParticle(ix, iy, iz, BParticleType.DUST, (0xFF << 24) | (((int) (1 * 255F) & 0xFF) << 16) | (((int) (0 * 255F) & 0xFF) << 8) | ((int) (0 * 255F) & 0xFF));
				}
			}
		}
	}
}
