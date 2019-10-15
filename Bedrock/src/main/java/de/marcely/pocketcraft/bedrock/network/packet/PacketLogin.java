package de.marcely.pocketcraft.bedrock.network.packet;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import de.marcely.pocketcraft.bedrock.component.SkinData;
import de.marcely.pocketcraft.bedrock.util.EByteArrayWriter;
import de.marcely.pocketcraft.bedrock.util.EByteArrayReader;

public class PacketLogin extends PCPacket {
	
	public int protocolVersion;
	
	public UUID identity;
	
	public String username;
	public String xuid;
	public String publicIdentityKey;
	
	public SkinData skin;
	public long randomClientId;
	public int currentInputMode;
	public int defaultInputMode;
	public UUID deviceId;
	public String deviceModel;
	public int deviceOS;
	public String gameVersion;
	public int guiScale;
	public String locale;
	public boolean hasPremiumSkin;
	public String thirdPartyName;
	public int uiProfile;
	
	public PacketLogin(){
		super(PacketType.Login);
	}

	@Override
	public void encode(EByteArrayWriter writer) throws Exception { }

	@Override
	public void decode(EByteArrayReader reader) throws Exception {
		this.protocolVersion = reader.readSignedInt();
		
		final EByteArrayReader reader2 = new EByteArrayReader(reader.readByteArray());
		final Gson gson = new Gson();
		
		// read chain
		{
			final String text = new String(reader2.read(reader2.readLInt()), StandardCharsets.UTF_8);
			
			final Map<String, List<String>> gsonData = gson.fromJson(text,
					new TypeToken<Map<String, List<String>>>(){ }.getType());
			
			for(String rawChain:gsonData.get("chain")){
				final JsonObject obj = decodeToken(rawChain);
				
				if(obj.has("extraData")){
					final JsonObject objExtra = obj.get("extraData").getAsJsonObject();
					
					if(objExtra.has("displayName")) this.username = objExtra.get("displayName").getAsString();
					if(objExtra.has("identity")) this.identity = UUID.fromString(objExtra.get("identity").getAsString());
					if(objExtra.has("XUID")) this.xuid = objExtra.get("XUID").getAsString();
				}
				
				if(obj.has("identityPublicKey")) this.publicIdentityKey = obj.get("identityPublicKey").getAsString();
			}
		}
		
		// read more data
		{
			final JsonObject obj = decodeToken(new String(reader2.read(reader2.readLInt()), StandardCharsets.UTF_8));
			
			// skin
			{
				final String skinId = obj.get("SkinId").getAsString();
				final byte[] skinData = obj.get("SkinData").getAsString().getBytes();
				final byte[] geometryData = obj.get("SkinGeometry").getAsString().getBytes();
				final String geometryName = obj.get("GeometryName").getAsString();
				final byte[] capeData = obj.get("CapeData").getAsString().getBytes();
				
				this.skin = new SkinData(skinId, skinData, capeData, geometryName, geometryData);
			}
			
			// more info
			{
				this.randomClientId = obj.get("ClientRandomId").getAsLong();
				this.currentInputMode = obj.get("CurrentInputMode").getAsInt();
				this.defaultInputMode = obj.get("DefaultInputMode").getAsInt();
				this.deviceId = UUID.fromString(obj.get("DeviceId").getAsString());
				this.deviceModel = obj.get("DeviceModel").getAsString();
				this.deviceOS = obj.get("DeviceOS").getAsInt();
				this.gameVersion = obj.get("GameVersion").getAsString();
				this.guiScale = obj.get("GuiScale").getAsInt();
				this.locale = obj.get("LanguageCode").getAsString(); // e.g.: de_DE
				this.hasPremiumSkin = obj.get("PremiumSkin").getAsBoolean();
				this.thirdPartyName = obj.get("ThirdPartyName").getAsString();
				this.uiProfile = obj.get("UIProfile").getAsInt();
			}
		}
		
		reader2.close();
	}
	
	private JsonObject decodeToken(String token){
		final String[] strs = token.split("\\.");
		
		return new Gson().fromJson(new String(Base64.getDecoder().decode(strs[1]), StandardCharsets.UTF_8), JsonObject.class);
	}
}
