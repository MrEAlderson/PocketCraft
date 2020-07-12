package de.marcely.pocketcraft.bedrock.component;

import java.util.UUID;

import org.jetbrains.annotations.Nullable;

import com.google.gson.JsonObject;

import de.marcely.pocketcraft.utils.Util;
import lombok.Getter;

public class BUserInfo {
	
	@Getter private BSkinData skin;
	@Getter private long randomClientId;
	@Getter private int currentInputMode;
	@Getter private int defaultInputMode;
	@Getter private UUID deviceId;
	@Getter private String deviceModel;
	@Getter private int deviceOS;
	@Getter private String gameVersion;
	@Getter private int guiScale;
	@Getter private String locale;
	@Getter private boolean hasPremiumSkin;
	@Getter private String thirdPartyName;
	@Getter private int uiProfile;
	
	public static @Nullable BUserInfo parse(JsonObject obj) throws Exception {
		final BUserInfo info = new BUserInfo();
		
		// skin
		{
			final String skinId = "";//obj.get("SkinId").getAsString();
			final byte[] skinData = new byte[0];//Base64.getDecoder().decode(obj.get("SkinData").getAsString());
			final byte[] geometryData = new byte[0];//Base64.getDecoder().decode(obj.get("SkinGeometry").getAsString());
			final String geometryName = "";// obj.get("SkinGeometryName").getAsString();
			final byte[] capeData = new byte[0];//Base64.getDecoder().decode(obj.get("CapeData").getAsString());
			
			info.skin = new BSkinData(skinId, skinData, capeData, geometryName, geometryData);
		}
		
		// more info
		{
			info.randomClientId = obj.get("ClientRandomId").getAsLong();
			info.currentInputMode = obj.get("CurrentInputMode").getAsInt();
			info.defaultInputMode = obj.get("DefaultInputMode").getAsInt();
			info.deviceId = Util.parseUUID(obj.get("DeviceId").getAsString());
			info.deviceModel = obj.get("DeviceModel").getAsString();
			info.deviceOS = obj.get("DeviceOS").getAsInt();
			info.gameVersion = obj.get("GameVersion").getAsString();
			info.guiScale = obj.get("GuiScale").getAsInt();
			info.locale = obj.get("LanguageCode").getAsString(); // e.g.: de_DE
			info.hasPremiumSkin = obj.get("PremiumSkin").getAsBoolean();
			info.thirdPartyName = obj.get("ThirdPartyName").getAsString();
			info.uiProfile = obj.get("UIProfile").getAsInt();
		}
		
		return info;
	}
}
