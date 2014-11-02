package com.monkeymusicchallenge.astar;

import org.json.JSONArray;
import org.json.JSONObject;

public class AStarSolver {

	//World
	//Priority queue with paths
	private Tile[][] world;
	
	public AStarSolver(JSONObject gameState) {
		world = new Tile[gameState.getJSONArray("layout").length()][gameState.getJSONArray("layout").getJSONArray(0).length()];
		JSONArray layout = gameState.getJSONArray("layout");
		
		for (int i = 0; i < world.length; i++) {
			for (int j = 0; j < world[0].length; j++) {
				String s = layout.getJSONArray(i).getString(j);
				switch (s) {
				case "empty": world[i][j] = Tile.EMPTY;
					break;
				case "wall": world[i][j] = Tile.WALL;
					break;
				case "monkey": world[i][j] = Tile.MONKEY;
					break;
				case "user": world[i][j] = Tile.USER;
					break;
				case "song": world[i][j] = Tile.SONG;
					break;
				case "album": world[i][j] = Tile.ALBUM;
					break;
				case "playlist": world[i][j] = Tile.PLAYLIST;
					break;
				default:
					break;
				}
			}
		}
	
	}
}
