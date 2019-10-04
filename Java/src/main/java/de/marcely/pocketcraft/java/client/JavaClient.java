package de.marcely.pocketcraft.java.client;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import de.marcely.pocketcraft.java.network.Connection;
import de.marcely.pocketcraft.java.network.ConnectionInterface;
import de.marcely.pocketcraft.java.network.LoginGoal;
import de.marcely.pocketcraft.java.network.packet.Packet;
import de.marcely.pocketcraft.java.network.packet.PacketListener;
import de.marcely.pocketcraft.java.network.protocol.Protocol;
import de.marcely.pocketcraft.java.network.sequence.ClientLoginInfo;
import de.marcely.pocketcraft.java.network.sequence.ClientLoginResult;
import de.marcely.pocketcraft.java.network.sequence.Sequence;
import de.marcely.pocketcraft.java.network.sequence.SequenceHolder;
import de.marcely.pocketcraft.java.network.sequence.SequenceType;
import de.marcely.pocketcraft.java.network.sequence.type.DeadSequence;
import de.marcely.pocketcraft.utils.scheduler.Scheduler;
import lombok.Getter;

public class JavaClient implements SequenceHolder, ConnectionInterface {
	
	@Getter private final Connection connection;
	@Getter private final Protocol protocol;
	@Getter private final LoginGoal goal;
	
	@Getter private final GameSession session;
	
	@Getter private final List<ClientListener> listeners = new ArrayList<>(4);
	@Getter private final List<PacketListener> packetListeners = new ArrayList<>(4);
	
	@Getter private Sequence sequence;
	
	public JavaClient(Connection conn, Protocol protocol, LoginGoal goal){
		this.connection = conn;
		this.protocol = protocol;
		this.goal = goal;
		
		this.session = new GameSession(this);
		
		this.packetListeners.add(this.sequence = new DeadSequence(this));
		
		conn.setInterface(this);
		protocol.defineIds();
	}

	@Override
	public void setSequence(Sequence seq){
		if(this.sequence.getType() == seq.getType())
			return;
		
		for(int i=0; i<this.packetListeners.size(); i++){
			final PacketListener listener = this.packetListeners.get(i);
			
			if(listener instanceof Sequence){
				this.packetListeners.set(i, seq);
				this.sequence = seq;
				
				try{
					seq.run((Sequence) listener);
				}catch(Exception e){
					e.printStackTrace();
				}catch(Error e){
					e.printStackTrace();
				}
				
				return;
			}
		}
	}

	@Override
	public void sendPacket(Packet packet){
		for(PacketListener listener:this.packetListeners){
			try{
				if(!listener.onSend(packet))
					return;
			}catch(Exception e){
				e.printStackTrace();
			}catch(Error e){
				e.printStackTrace();
			}
		}
		
		this.connection.write(packet);
	}
	
	public void handlePacket(Packet packet){
		System.out.println(packet.getClass().getName());
		
		for(PacketListener listener:this.packetListeners){
			try{
				if(!listener.onReceive(packet))
					return;
			}catch(Exception e){
				e.printStackTrace();
			}catch(Error e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object getLoginInfo(){
		final ClientLoginInfo info = new ClientLoginInfo();
		
		info.protocolVersion = this.protocol.getProtocolVersion();
		info.serverAddress = "127.0.0.1";
		info.serverPort = 25565;
		info.nextState = this.goal;
		info.username = this.session.getUsername();
		
		return info;
	}

	@Override
	public void completeLogin(Object rawResult){
		final ClientLoginResult result = (ClientLoginResult) rawResult;
		
		this.session.setUsername(result.username);
		this.session.setId(result.id);
		
		for(ClientListener listener:this.listeners){
			try{
				listener.onConnect();
			}catch(Exception e){
				e.printStackTrace();
			}catch(Error e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onReady(){
		this.setSequence(this.protocol.newSequenceInstance(SequenceType.HANDSHAKE, this));
		
		for(ClientListener listener:this.listeners){
			try{
				listener.onReady();
			}catch(Exception e){
				e.printStackTrace();
			}catch(Error e){
				e.printStackTrace();
			}
		}
	}

	@Override
	public void onClose(){
		for(ClientListener listener:this.listeners){
			try{
				listener.onDisconnect();
			}catch(Exception e){
				e.printStackTrace();
			}catch(Error e){
				e.printStackTrace();
			}
		}
	}
	
	public boolean isRunning(){
		return !this.connection.isClosed();
	}
	
	private int schedulerId;
	
	public boolean connect() throws IOException {
		if(isRunning())
			return false;
		
		this.connection.open();
		
		return true;
	}
	
	public boolean close(){
		if(!isRunning())
			return false;
		
		Scheduler.cancel(this.schedulerId);
		
		try{
			this.connection.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean registerListener(ClientListener listener){
		if(this.listeners.contains(listener))
			return false;
		
		this.listeners.add(listener);
		
		return true;
	}
	
	public boolean unregisterListener(ClientListener listener){
		return this.listeners.remove(listener);
	}
	
	public boolean registerPacketListener(PacketListener listener){
		if(this.packetListeners.contains(listener))
			return false;
		
		this.packetListeners.add(listener);
		
		return true;
	}
	
	public boolean unregisterPacketListener(PacketListener listener){
		return this.packetListeners.remove(listener);
	}
}