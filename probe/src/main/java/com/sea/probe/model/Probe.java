package com.sea.probe.model;

import com.sea.probe.constants.Direction;

public class Probe {

	private int x, y;
	private Direction direction;
	private final Grid grid;

	public Probe(int startX, int startY, Direction startDirection, Grid grid) {
		this.x = startX;
		this.y = startY;
		this.direction = startDirection;
		this.grid = grid;
	}

	public void moveForward() {
		int newX = x + direction.directionX;
		int newY = y + direction.directionY;

		if (grid.isWithinBounds(newX, newY) && !grid.hasObstacle(newX, newY)) {
			x = newX;
			y = newY;
		}
	}

	public void moveBackward() {
		int newX = x - direction.directionX;
		int newY = y - direction.directionY;

		if (grid.isWithinBounds(newX, newY) && !grid.hasObstacle(newX, newY)) {
			x = newX;
			y = newY;
		}
	}

	public void turnLeft() {
		direction = direction.left();
	}

	public void turnRight() {
		direction = direction.right();
	}
	
	public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getDirection() {
        return direction;
    }
}
