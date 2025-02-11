package com.sea.probe.constants;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Command {
	F, B, L, R;

	@JsonCreator
	public static Command fromValue(String value) {
		try {
			return Command.valueOf(value.toUpperCase());
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid command. Allowed values: F, B, L, R.");
		}
	}

	@JsonValue
	public String toString() {
		return name();
	}

}
