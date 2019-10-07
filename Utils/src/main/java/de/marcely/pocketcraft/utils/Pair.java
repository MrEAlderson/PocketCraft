package de.marcely.pocketcraft.utils;

import lombok.Getter;
import lombok.Setter;

public class Pair<T1, T2> {
	
	@Getter @Setter private T1 entry1;
	@Getter @Setter private T2 entry2;
	
	public Pair(T1 e1, T2 e2){
		this.entry1 = e1;
		this.entry2 = e2;
	}
}
