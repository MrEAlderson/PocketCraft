package de.marcely.pocketcraft.bedrock.component.permission;

import lombok.Getter;
import lombok.Setter;

public class PlayerPermissions {
	
	private static final short FLAG1_WORLD_IMMUTABLE = 0x001;
	private static final short FLAG1_NO_PVP = 0x002;
	private static final short FLAG1_AUTO_JUMP = 0x020;
	private static final short FLAG1_ALLOW_FLIGHT = 0x040;
	private static final short FLAG1_NO_CLIP = 0x080;
	private static final short FLAG1_WORLD_BUILDER = 0x100;
	private static final short FLAG1_FLYING = 0x200;
	private static final short FLAG1_MUTED = 0x400;
	
	private static final short FLAG2_BUILD_AND_MINE = 0x001;
	private static final short FLAG2_DOORS_AND_SWITCHES = 0x002;
	private static final short FLAG2_OPEN_CONTAINERS = 0x004;
	private static final short FLAG2_ATTACK_PLAYERS = 0x008;
	private static final short FLAG2_ATTACK_MOBS = 0x010;
	private static final short FLAG2_OPERATOR = 0x020;
	private static final short FLAG2_TELEPORT = 0x080;
	
	@Getter @Setter private CommandPermissionLevel commandLevel = CommandPermissionLevel.NORMAL;
	@Getter @Setter private PermissionLevel level = PermissionLevel.VISITOR;
	@Getter @Setter private short flags1 = 0, flags2 = 0;
	
	public void setImmutable(boolean value){
		setFlag1(FLAG1_WORLD_IMMUTABLE, value);
	}
	
	public boolean isImmutable(){
		return hasFlag1(FLAG1_WORLD_IMMUTABLE);
	}
	
	public boolean isPVPBlocked(){
		return hasFlag1(FLAG1_NO_PVP);
	}
	
	public void setPVPBlocked(boolean value){
		setFlag1(FLAG1_NO_PVP, value);
	}
	
	public boolean canAutoJump(){
		return hasFlag1(FLAG1_AUTO_JUMP);
	}
	
	public void setAutoJump(boolean value){
		setFlag1(FLAG1_AUTO_JUMP, value);
	}
	
	public boolean isFlightAllowed(){
		return hasFlag1(FLAG1_ALLOW_FLIGHT);
	}
	
	public void setFlightAllowed(boolean value){
		setFlag1(FLAG1_ALLOW_FLIGHT, value);
	}
	
	public boolean hasNoClip(){
		return hasFlag1(FLAG1_NO_CLIP);
	}
	
	public void setNoClip(boolean value){
		setFlag1(FLAG1_NO_CLIP, value);
	}
	
	public boolean isWorldBuilder(){
		return hasFlag1(FLAG1_WORLD_BUILDER);
	}
	
	public void setWorldBuilder(boolean value){
		setFlag1(FLAG1_WORLD_BUILDER, value);
	}
	
	public boolean isFlying(){
		return hasFlag1(FLAG1_FLYING);
	}
	
	public void setFlying(boolean value){
		setFlag1(FLAG1_FLYING, value);
	}
	
	public boolean isMuted(){
		return hasFlag1(FLAG1_MUTED);
	}
	
	public void setMuted(boolean value){
		setFlag1(FLAG1_MUTED, value);
	}
	
	
	
	
	public boolean canBuildAndMine(){
		return hasFlag2(FLAG2_BUILD_AND_MINE);
	}
	
	public void setBuildAndMineable(boolean value){
		setFlag2(FLAG2_BUILD_AND_MINE, value);
	}
	
	public boolean canInteractDoorsAndSwitches(){
		return hasFlag2(FLAG2_DOORS_AND_SWITCHES);
	}
	
	public void setInteractableDoorsAndSwitches(boolean value){
		setFlag2(FLAG2_DOORS_AND_SWITCHES, value);
	}
	
	public boolean canOpenContainers(){
		return hasFlag2(FLAG2_OPEN_CONTAINERS);
	}
	
	public void setCanOpenContainers(boolean value){
		setFlag2(FLAG2_OPEN_CONTAINERS, value);
	}
	
	public boolean canAttackPlayers(){
		return hasFlag2(FLAG2_ATTACK_PLAYERS);
	}
	
	public void setCanAttackPlayers(boolean value){
		setFlag2(FLAG2_ATTACK_PLAYERS, value);
	}
	
	public boolean canAttackMobs(){
		return hasFlag2(FLAG2_ATTACK_MOBS);
	}
	
	public void setCanAttackMobs(boolean value){
		setFlag2(FLAG2_ATTACK_MOBS, value);
	}
	
	public boolean isOperator(){
		return hasFlag2(FLAG2_OPERATOR);
	}
	
	public void setOperator(boolean value){
		setFlag2(FLAG2_OPERATOR, value);
	}
	
	public boolean canTeleport(){
		return hasFlag2(FLAG2_TELEPORT);
	}
	
	public void setCanTeleport(boolean value){
		setFlag2(FLAG2_TELEPORT, value);
	}
	
	
	
	
	private boolean hasFlag1(short id){
		return (this.flags1 & id) > 0;
	}
	
	private void setFlag1(short id, boolean value){
		if(hasFlag1(id) == value)
			return;
		
		this.flags1 ^= id;
	}
	
	private boolean hasFlag2(short id){
		return (this.flags2 & id) > 0;
	}
	
	private void setFlag2(short id, boolean value){
		if(hasFlag2(id) == value)
			return;
		
		this.flags2 ^= id;
	}
	
	public static PlayerPermissions newDefaultInstance(){
		final PlayerPermissions permissions = new PlayerPermissions();
		
		permissions.flags1 = FLAG1_AUTO_JUMP | FLAG1_WORLD_BUILDER;
		permissions.flags2 = FLAG2_BUILD_AND_MINE | FLAG2_DOORS_AND_SWITCHES | FLAG2_OPEN_CONTAINERS | FLAG2_ATTACK_PLAYERS | FLAG2_ATTACK_MOBS;
		
		return permissions;
	}
}
