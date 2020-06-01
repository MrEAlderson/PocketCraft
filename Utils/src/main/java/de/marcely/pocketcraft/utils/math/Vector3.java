package de.marcely.pocketcraft.utils.math;

import lombok.Getter;
import lombok.Setter;

public class Vector3 implements Cloneable {
	
	@Getter @Setter private float x, y, z;
	
	public Vector3(){
		this(0, 0, 0);
	}
	
	public Vector3(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getFloorX(){
		return (int) this.x;
	}
	
	public int getFloorY(){
		return (int) this.y;
	}
	
	public int getFloorZ(){
		return (int) this.z;
	}
	
	public Vector3 add(Vector3 vec){
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		
		return this;
	}
	
	public Vector3 subtract(Vector3 vec){
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
		
		return this;
	}
	
	public Vector3 multiply(Vector3 vec){
		this.x *= vec.x;
		this.y *= vec.y;
		this.z *= vec.z;
		
		return this;
	}
	
	public Vector3 divide(Vector3 vec){
		this.x /= vec.x;
		this.y /= vec.y;
		this.z /= vec.z;
		
		return this;
	}
	
	public Vector3 add(float x, float y, float z){
		this.x += x;
		this.y += y;
		this.z += z;
		
		return this;
	}
	
	public Vector3 subtract(float x, float y, float z){
		this.x -= x;
		this.y -= y;
		this.z -= z;
		
		return this;
	}
	
	public Vector3 multiply(float x, float y, float z){
		this.x *= x;
		this.y *= y;
		this.z *= z;
		
		return this;
	}
	
	public Vector3 divide(float x, float y, float z){
		this.x /= x;
		this.y /= y;
		this.z /= z;
		
		return this;
	}
	
	public Vector3 add(float val){
		this.x += val;
		this.y += val;
		this.z += val;
		
		return this;
	}
	
	public Vector3 subtract(float val){
		this.x -= val;
		this.y -= val;
		this.z -= val;
		
		return this;
	}
	
	public Vector3 multiply(float val){
		this.x *= val;
		this.y *= val;
		this.z *= val;
		
		return this;
	}
	
	public Vector3 divide(float val){
		this.x /= val;
		this.y /= val;
		this.z /= val;
		
		return this;
	}
	
	public Vector3 copy(Vector3 vec){
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
		
		return this;
	}
	
	public Vector3 zero(){
		this.x = 0;
		this.y = 0;
		this.z = 0;
		
		return this;
	}
	
	public Vector3 set(float x, float y, float z){
		this.x = x;
		this.y = y;
		this.z = z;
		
		return this;
	}
	
	@Override
    public String toString() {
        return this.x + "," + this.y + "," + this.z;
    }
	
	@Override
	public Vector3 clone(){
		try{
			return (Vector3) super.clone();
		}catch(CloneNotSupportedException e){
			e.printStackTrace();
		}
		
		return null;
	}
}