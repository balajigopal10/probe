package com.sea.probe.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sea.probe.constants.Direction;
import com.sea.probe.controller.dto.InitializeRequest;
import com.sea.probe.controller.dto.ObstacleRequest;
import com.sea.probe.model.Grid;
import com.sea.probe.model.Probe;

@RestController
@RequestMapping("/probe")
public class ProbeController {

	private Probe probe;

//	public ProbeController() {
//		this.probe = new Probe(0, 0, Direction.NORTH, new Grid(10, 10));
//	}

	@PostMapping("/initialize")
	public String initializeProbe(@RequestBody InitializeRequest request) {
		Grid grid = new Grid(request.getWidth(), request.getHeight());
		probe = new Probe(request.getStartX(), request.getStartY(),
				Direction.valueOf(request.getDirection().toUpperCase()), grid);
		return "Probe initialized at (" + request.getStartX() + ", " + request.getStartY() + ") facing "
				+ request.getDirection();
	}

	@PostMapping("/executeCommands")
	public String executeCommands(@RequestBody List<String> commands) {
		if (probe == null) {
			return "Error: Probe not initialized! Please initialize the probe first.";
		}

		for (String command : commands) {
			switch (command) {
			case "F":
				probe.moveForward();
				break;
			case "B":
				probe.moveBackward();
				break;
			case "L":
				probe.turnLeft();
				break;
			case "R":
				probe.turnRight();
				break;
			default:
				return "Error: Invalid command: " + command;
			}
		}
		return "Commands executed successfully!";
	}

	@PostMapping("/addObstacle")
	public String addObstacle(@RequestBody ObstacleRequest request) {
		if (probe == null) {
			return "Error: Probe not initialized! Please initialize the probe first.";
		}
		probe.getGrid().addObstacle(request.getX(), request.getY());
		return "Obstacle added at (" + request.getX() + ", " + request.getY() + ")";
	}

	@GetMapping("/visitedLocations")
	public String getVisitedLocations() {
		if (probe == null) {
			return "Error: Probe not initialized! Please initialize the probe first.";
		}
		return probe.getVisitedLocations().toString();
	}

	@GetMapping("/position")
	public String getPosition() {
		return "Position: (" + probe.getX() + ", " + probe.getY() + ") Facing: " + probe.getDirection();
	}
}
