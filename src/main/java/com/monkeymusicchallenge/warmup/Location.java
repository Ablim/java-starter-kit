package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;

public class Location implements Comparable<Location> {

	private int row;
	private int column;
	private int itemCounter = 0;
	private LinkedList<String> path;
	private boolean[][] visited;
	
	public Location(int row, int column, LinkedList<String> path) {
		this.row = row;
		this.column = column;
		this.path = path;
	}
	
	public Location(int row, int column, int itemCounter, LinkedList<String> path, boolean[][] visited) {
		this.row = row;
		this.column = column;
		this.itemCounter = itemCounter;
		this.path = path;
		this.visited = visited;
	}
	
	public int getRow() {
		return row;
	}
	
	public int getColumn() {
		return column;
	}
	
	public LinkedList<String> getPath() {
		return path;
	}
	
	public int getItemCounter() {
		return itemCounter;
	}

	public boolean[][] getVisited() {
		return visited;
	}
	
	@Override
	public int compareTo(Location l) {
		return path.size() - l.getPath().size();
	}
}
