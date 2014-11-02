package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;

public class Location implements Comparable<Location> {

	private int x;
	private int y;
	private LinkedList<String> path;
	
	public Location(int x, int y, LinkedList<String> path) {
		this.x = x;
		this.y = y;
		this.path = path;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public LinkedList<String> getPath() {
		return path;
	}

	@Override
	public int compareTo(Location l) {
		return path.size() - l.getPath().size();
	}
}
