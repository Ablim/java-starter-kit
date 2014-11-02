package com.monkeymusicchallenge.warmup;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.concurrent.ThreadLocalRandom;

import org.json.JSONArray;
import org.json.JSONObject;

public class AI {
	
	private LinkedList<String> path = new LinkedList<String>();
	private byte[][] visited;
	private int[] xOffset = {1, -1, 0, 0};
	private int[] yOffset = {0, 0, 1, -1};
	private String[] dirs = {"down", "up", "right", "left"};
	
	
	public AI(JSONObject gameState) {
		visited = new byte[gameState.getJSONArray("layout").length()][gameState.getJSONArray("layout").getJSONArray(0).length()];
	}

	public String move(JSONObject gameState) {

		int remainingNumberOfTurns = gameState.getInt("turns");
		JSONArray currentLevelLayout = gameState.getJSONArray("layout");
		JSONArray pickedUpMusicItems = gameState.getJSONArray("pickedUp");
		JSONArray currentPositionOfMonkey = gameState.getJSONArray("position");
		
//		String monkey = currentLevelLayout.getJSONArray(
//				currentPositionOfMonkey.getInt(0)).getString(
//				currentPositionOfMonkey.getInt(1));
		
		int mx = currentPositionOfMonkey.getInt(0);
		int my = currentPositionOfMonkey.getInt(1);
		
		if (path.size() == 0) {
			PriorityQueue<Location> queue = new PriorityQueue<Location>();
			queue.add(new Location(mx, my, new LinkedList<String>()));
	
			while (!queue.isEmpty() && path.size() == 0) {
//				System.out.println("Queue size: " + queue.size());
				Location l = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					if (l.getX() + xOffset[i] < visited.length && 
							l.getX() + xOffset[i] >= 0 &&
							l.getY() + yOffset[i] < visited[0].length &&
							l.getY() + yOffset[i] >= 0 &&
							visited[l.getX() + xOffset[i]][l.getY() + yOffset[i]] <= 2) {
						
						visited[l.getX() + xOffset[i]][l.getY() + yOffset[i]]++;
						LinkedList<String> list = (LinkedList<String>)l.getPath().clone();
						list.addLast(dirs[i]);
						
						String tile = currentLevelLayout.getJSONArray(l.getX() + xOffset[i]).getString(l.getY() + yOffset[i]);
						if (tile.equals("song") || tile.equals("album") || tile.equals("playlist") || (tile.equals("user") && pickedUpMusicItems.length() == 5)) {
							path = list;
							break;
						} else if (tile.equals("empty")) {
							queue.add(new Location(l.getX() + xOffset[i], l.getY() + yOffset[i], list));
						}
					}
				}
			}
		}
		String ret = path.poll();
		System.out.println("Sending: " + ret);
		return ret;
	}
}
