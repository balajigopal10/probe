package com.sea.probe.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Direction {

	NORTH(0, 1), EAST(1, 0), SOUTH(0, -1), WEST(-1, 0);

	public final int directionX, directionY;

	Direction(int directionX, int directionY) {
		this.directionX = directionX;
		this.directionY = directionY;
	}

	@JsonValue
	public String toValue() {
		return this.name();
	}

	@JsonCreator
	public static Direction fromValue(String value) {
		try {
			return Direction.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid direction. Allowed values: NORTH, EAST, SOUTH, WEST.");
		}
	}

	public Direction left() {
		return values()[(ordinal() + 3) % 4];
	}

	public Direction right() {
		return values()[(ordinal() + 1) % 4];
	}
}
