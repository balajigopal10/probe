package com.sea.probe.constants;

public enum Direction {

	NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

	public final int directionX, directionY;

	Direction(int directionX, int directionY) {
		this.directionX = directionX;
		this.directionY = directionY;
	}

	public Direction left() {
		return values()[(ordinal() + 3) % 4];
	}
	
	public Direction right() {
		return values()[(ordinal() + 1) % 4];
	}
}
