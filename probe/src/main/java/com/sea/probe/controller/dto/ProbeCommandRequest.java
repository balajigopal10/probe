package com.sea.probe.controller.dto;

import java.util.List;

import com.sea.probe.constants.Command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ProbeCommandRequest {

	@NotNull(message = "Commands list cannot be null")
	@NotEmpty(message = "Commands list cannot be empty")
	private List<Command> commands;

	public ProbeCommandRequest(List<Command> commands) {
		this.commands = commands;
	}

	public List<Command> getCommands() {
		return commands;
	}

	public void setCommands(List<Command> commands) {
		this.commands = commands;
	}
}
