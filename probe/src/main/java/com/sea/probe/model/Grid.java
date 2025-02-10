package com.sea.probe.model;

public class Grid {

	private final int width;
	private final int height;
	private boolean[][] obstacles;

	public Grid(int width, int height) {
		this.width = width;
		this.height = height;
		this.obstacles = new boolean[width][height];
	}

	public boolean isWithinBounds(int x, int y) {
		return x >= 0 && x < width && y >= 0 && y < height;
	}

	public boolean hasObstacle(int x, int y) {
		return obstacles[x][y];
	}

	public void addObstacle(int x, int y) {
		if (isWithinBounds(x, y)) {
			obstacles[x][y] = true;
		}
	}

}
