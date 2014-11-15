package com.monkeymusicchallenge.astar;

public class AStarMain {

	public static void main(String[] args) {
		AStar aStar = new AStar(getWorld2());
		while (true) {
			String s = aStar.move();
			if (s == null || s.equals("")) {
				System.out.println("End of path");
				break;
			} else {
				System.out.println(s);
			}
		}
	}
	
	private static int[][] getWorld0() {
		int[][] w = {{0, 0, 3, 0, 2},
					 {0, 1, 1, 0, 0},
					 {0, 2, 1, 0, 0},
					 {0, 0, 0, 0, 0},
					 {0, 0, 4, 0, 0}};
		return w;
	}

	private static int[][] getWorld1() {
		int[][] w = {{1, 1, 2, 1, 1},
					 {1, 1, 0, 1, 1},
					 {2, 0, 3, 0, 2},
					 {1, 1, 0, 1, 1},
					 {1, 1, 4, 1, 1}};
		return w;
	}
	
	private static int[][] getWorld2() {
		int[][] w = {{4, 0, 0, 0, 0, 0},
					 {2, 0, 0, 1, 0, 0},
					 {1, 1, 1, 1, 0, 2},
					 {0, 0, 0, 1, 0, 0},
					 {0, 1, 0, 0, 0, 1},
					 {3, 1, 2, 1, 0, 2}};
		return w;
	}
}
