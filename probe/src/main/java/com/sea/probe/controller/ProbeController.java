package com.sea.probe.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sea.probe.constants.Direction;
import com.sea.probe.model.Grid;
import com.sea.probe.model.Probe;

@RestController
@RequestMapping("/probe")
public class ProbeController {

	private final Probe probe;

	public ProbeController() {
		this.probe = new Probe(0, 0, Direction.NORTH, new Grid(10, 10));
	}

	@PostMapping("/moveForward")
	public void moveForward() {
		probe.moveForward();
	}

	@PostMapping("/moveBackward")
	public void moveBackward() {
		probe.moveBackward();
	}

	@PostMapping("/turnLeft")
	public void turnLeft() {
		probe.turnLeft();
	}

	@PostMapping("/turnRight")
	public void turnRight() {
		probe.turnRight();
	}

	@GetMapping("/position")
	public String getPosition() {
		return "Position: (" + probe.getX() + ", " + probe.getY() + ") Facing: " + probe.getDirection();
	}
}
