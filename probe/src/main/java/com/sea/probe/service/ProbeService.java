package com.sea.probe.service;

import org.springframework.stereotype.Service;

import com.sea.probe.constants.Direction;
import com.sea.probe.controller.dto.InitializeRequest;
import com.sea.probe.controller.dto.ObstacleRequest;
import com.sea.probe.controller.dto.ProbeCommandRequest;
import com.sea.probe.exception.ProbeException;
import com.sea.probe.model.Grid;
import com.sea.probe.model.Probe;
import com.sea.probe.response.ApiResponse;

@Service
public class ProbeService {

	private Probe probe;

	public ApiResponse<String> initializeProbe(InitializeRequest request) {
		if (request.getDirection() == null || request.getDirection().trim().isEmpty()) {
			throw new ProbeException("Direction cannot be null or empty.");
		}

		try {
			if (request.getWidth() <= 0 || request.getHeight() <= 0) {
				throw new ProbeException("Grid width and height must be positive values.");
			}

			Grid grid = new Grid(request.getWidth(), request.getHeight());
			probe = new Probe(request.getStartX(), request.getStartY(),
					Direction.valueOf(request.getDirection().trim().toUpperCase()), grid);

			return new ApiResponse<>(true, "Probe initialized successfully.", null);
		} catch (IllegalArgumentException e) {
			throw new ProbeException("Invalid direction. Allowed values: NORTH, EAST, SOUTH, WEST.");
		}
	}

	public ApiResponse<String> executeCommands(ProbeCommandRequest request) {
		if (probe == null) {
			throw new ProbeException("Error: Probe not initialized! Please initialize the probe first.");
		}

		for (com.sea.probe.constants.Command command : request.getCommands()) {
			switch (command) {
			case F -> probe.moveForward();
			case B -> probe.moveBackward();
			case L -> probe.turnLeft();
			case R -> probe.turnRight();
			}
		}

		return new ApiResponse<>(true, "Commands executed successfully", null);
	}

	public ApiResponse<String> addObstacle(ObstacleRequest request) {
		if (probe == null) {
			throw new ProbeException("Error: Probe not initialized! Please initialize the probe first.");
		}
		probe.getGrid().addObstacle(request.getX(), request.getY());
		return new ApiResponse<>(true, "Obstacle added successfully", null);
	}

	public ApiResponse<String> getVisitedLocations() {
		if (probe == null) {
			throw new ProbeException("Error: Probe not initialized! Please initialize the probe first.");
		}
		return new ApiResponse<>(true, "Visited locations: " + probe.getVisitedLocations().toString(), null);
	}

	public ApiResponse<String> getPosition() {
		if (probe == null) {
			throw new ProbeException("Error: Probe not initialized! Please initialize the probe first.");
		}
		return new ApiResponse<>(true,
				"Position: (" + probe.getX() + ", " + probe.getY() + ") Facing: " + probe.getDirection(), null);
	}
}
