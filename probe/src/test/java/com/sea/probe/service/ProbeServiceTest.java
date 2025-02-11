package com.sea.probe.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.sea.probe.constants.Command;
import com.sea.probe.controller.dto.InitializeRequest;
import com.sea.probe.controller.dto.ObstacleRequest;
import com.sea.probe.controller.dto.ProbeCommandRequest;
import com.sea.probe.exception.ProbeException;
import com.sea.probe.response.ApiResponse;

@WebMvcTest(ProbeService.class)
public class ProbeServiceTest {

	@InjectMocks
	private ProbeService probeService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testInitializeProbe_Success() {
		InitializeRequest request = new InitializeRequest(0, 0, 10, 10, "NORTH");

		ApiResponse response = probeService.initializeProbe(request);

		assertTrue(response.isSuccess());
		assertEquals("Probe initialized successfully.", response.getMessage());
	}

	@Test
	void testInitializeProbe_InvalidDirection_ShouldThrowException() {
		InitializeRequest request = new InitializeRequest(0, 0, 10, 10, "INVALID_DIRECTION");

		Exception exception = assertThrows(ProbeException.class, () -> probeService.initializeProbe(request));
		assertEquals("Invalid direction. Allowed values: NORTH, EAST, SOUTH, WEST.", exception.getMessage());
	}

	@Test
	void testInitializeProbe_EmptyDirection_ShouldThrowException() {
		InitializeRequest request = new InitializeRequest(0, 0, 10, 10, "");

		Exception exception = assertThrows(ProbeException.class, () -> probeService.initializeProbe(request));
		assertEquals("Direction cannot be null or empty.", exception.getMessage());
	}

	@Test
	void testExecuteCommands_Success() {
		InitializeRequest initRequest = new InitializeRequest(0, 0, 10, 10, "NORTH");
		probeService.initializeProbe(initRequest);

		ProbeCommandRequest commandRequest = new ProbeCommandRequest(List.of(Command.F, Command.L, Command.R));
		ApiResponse response = probeService.executeCommands(commandRequest);

		assertTrue(response.isSuccess());
		assertEquals("Commands executed successfully", response.getMessage());
	}

	@Test
	void testExecuteCommands_WithoutInitialization_ShouldThrowException() {
		ProbeCommandRequest commandRequest = new ProbeCommandRequest(List.of(Command.F, Command.L, Command.R));

		Exception exception = assertThrows(ProbeException.class, () -> probeService.executeCommands(commandRequest));
		assertEquals("Error: Probe not initialized! Please initialize the probe first.", exception.getMessage());
	}

	@Test
	void testAddObstacle_Success() {
		InitializeRequest initRequest = new InitializeRequest(0, 0, 10, 10, "NORTH");
		probeService.initializeProbe(initRequest);

		ObstacleRequest request = new ObstacleRequest(2, 2);
		ApiResponse response = probeService.addObstacle(request);

		assertTrue(response.isSuccess());
		assertEquals("Obstacle added successfully", response.getMessage());
	}

	@Test
	void testGetPosition_Success() {
		InitializeRequest initRequest = new InitializeRequest(5, 5, 10, 10, "EAST");
		probeService.initializeProbe(initRequest);

		ApiResponse response = probeService.getPosition();

		assertTrue(response.isSuccess());
		assertEquals("Position: (5, 5) Facing: EAST", response.getMessage());
	}

	@Test
	void testGetPosition_WithoutInitialization_ShouldThrowException() {
		Exception exception = assertThrows(ProbeException.class, () -> probeService.getPosition());
		assertEquals("Error: Probe not initialized! Please initialize the probe first.", exception.getMessage());
	}

}
