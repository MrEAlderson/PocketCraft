package de.marcely.pocketcraft.java.client;

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
import de.marcely.pocketcraft.java.network.sequence.type.DeadSequence;
import lombok.Getter;

public class JavaClient implements SequenceHolder, ConnectionInterface {
	
	@Getter private final Connection connection;
	@Getter private final Protocol protocol;
	@Getter private final LoginGoal goal;
	
	@Getter private final GameSession session;
	
	@Getter private final List<ClientListener> listeners = new ArrayList<>(4);
	@Getter private final List<PacketListener> packetListeners = new ArrayList<>(4);
	
	public JavaClient(Connection conn, Protocol protocol, LoginGoal goal){
		this.connection = conn;
		this.protocol = protocol;
		this.goal = goal;
		
		this.session = new GameSession(this);
		
		this.packetListeners.add(new DeadSequence(this));
	}

	@Override
	public void setSequence(Sequence seq){
		for(int i=0; i<this.packetListeners.size(); i++){
			final PacketListener listener = this.packetListeners.get(i);
			
			if(listener instanceof Sequence){
				if(seq == listener)
					return;
				
				this.packetListeners.set(i, seq);
				
				seq.run((Sequence) listener);
				return;
			}
		}
	}

	@Override
	public Sequence getSequence(){
		for(int i=0; i<this.packetListeners.size(); i++){
			final PacketListener listener = this.packetListeners.get(i);
			
			if(listener instanceof Sequence)
				return (Sequence) listener;
		}
		
		return null;
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