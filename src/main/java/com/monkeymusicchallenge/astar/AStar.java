package com.monkeymusicchallenge.astar;

import java.util.LinkedList;
import java.util.PriorityQueue;

import com.monkeymusicchallenge.warmup.Location;

public class AStar {
	
	private int[][] world;
	private PriorityQueue<Location> queue = new PriorityQueue<Location>();
	private int itemCounter = 0;
	private int[] rowOffset = {1, -1, 0, 0};
	private int[] colOffset = {0, 0, 1, -1};
	private String[] dirs = {"down", "up", "right", "left"};
	private LinkedList<String> path;
	private int largestQueueSize = 0;
	
	private final int EMPTY = 0;
	private final int WALL = 1;
	private final int ITEM = 2;
	private final int START = 3;
	private final int GOAL = 4;
	
	/**
	 * Takes an int array with the following structure:
	 * 0 = empty tile, 1 = wall, 2 = item, 3 = start, 4 = goal.
	 * There can only be one start and one goal.
	 * @param world
	 */
	public AStar(int[][] world) {
		this.world = new int[world.length][world[0].length];
		boolean[][] visited = new boolean[world.length][world[0].length];
		
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				int x = world[i][j];
				this.world[i][j] = x;
				switch (x) {
				case START: 
					visited[i][j] = true;
					queue.add(new Location(i, j, 0, new LinkedList<String>(), visited));
					break;
				case ITEM:
					itemCounter++;
				default:
					break;
				}
			}
		}
		
		path = solve();
		System.out.println("Largest queue size: " + largestQueueSize);
	}
	
	@SuppressWarnings("unchecked")
	private LinkedList<String> solve() {
		
		while (queue.size() > 0) {
			if (queue.size() > largestQueueSize) {
				largestQueueSize = queue.size();
			}
			
			Location l = queue.poll();
			
			for (int i = 0; i < 4; i++) {
				int row = l.getRow() + rowOffset[i];
				int col = l.getColumn() + colOffset[i];
				
				if (row < world.length && row >= 0 && col < world[0].length && col >= 0 && l.getVisited()[row][col] == false && world[row][col] != WALL) {
					
					LinkedList<String> list = (LinkedList<String>)l.getPath().clone();
					list.addLast(dirs[i]);
					int c = l.getItemCounter();
					
					int tile = world[row][col];
					if (tile == GOAL && itemCounter == l.getItemCounter()) {
						return list;
					} else if (tile == ITEM) {
						c++;
						boolean[][] vs = new boolean[l.getVisited().length][l.getVisited()[0].length];
						vs[row][col] = true;
						queue.add(new Location(row, col, c, list, vs));
					} else if (tile == EMPTY || tile == START) {
						boolean[][] vs = l.getVisited().clone();
						vs[row][col] = true;
						queue.add(new Location(row, col, c, list, vs));
					} 
				}
			}
		}
		return new LinkedList<String>();
	}
	
	public String move() {
		String s = path.poll();
//		System.out.println("Sending: " + s);
		return s;
	}
	
	public LinkedList<String> getPath() {
		return path;
	}
}
