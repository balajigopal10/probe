package com.sea.probe.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sea.probe.constants.Command;
import com.sea.probe.controller.dto.InitializeRequest;
import com.sea.probe.controller.dto.ObstacleRequest;
import com.sea.probe.controller.dto.ProbeCommandRequest;
import com.sea.probe.response.ApiResponse;
import com.sea.probe.service.ProbeService;

@WebMvcTest(ProbeController.class)
class ProbeControllerTest {

	private MockMvc mockMvc;

	@Mock
	private ProbeService probeService;

	@InjectMocks
	private ProbeController probeController;

	private final ObjectMapper objectMapper = new ObjectMapper();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(probeController).build();
	}

	@Test
	void testInitializeProbe_Success() throws Exception {
		InitializeRequest request = new InitializeRequest(0, 0, 10, 10, "NORTH");
		when(probeService.initializeProbe(any(InitializeRequest.class)))
				.thenReturn(new ApiResponse(true, "Probe initialized successfully.", null));

		mockMvc.perform(post("/api/v1/probe/initialize").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.message").value("Probe initialized successfully."))
				.andExpect(jsonPath("$.data").doesNotExist());
	}

	@Test
	void testExecuteCommands_Success() throws Exception {
		ProbeCommandRequest request = new ProbeCommandRequest(List.of(Command.F, Command.L, Command.R));
		when(probeService.executeCommands(any(ProbeCommandRequest.class)))
				.thenReturn(new ApiResponse(true, "Commands executed successfully", null));

		mockMvc.perform(post("/api/v1/probe/executeCommands").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.message").value("Commands executed successfully"))
				.andExpect(jsonPath("$.data").doesNotExist());
	}

	@Test
	void testAddObstacle_Success() throws Exception {
		ObstacleRequest request = new ObstacleRequest(2, 2);
		when(probeService.addObstacle(any(ObstacleRequest.class)))
				.thenReturn(new ApiResponse(true, "Obstacle added successfully", null));

		mockMvc.perform(post("/api/v1/probe/addObstacle").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(request))).andExpect(status().isOk())
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.message").value("Obstacle added successfully"))
				.andExpect(jsonPath("$.data").doesNotExist());
	}

	@Test
	void testGetPosition_Success() throws Exception {
		when(probeService.getPosition()).thenReturn(new ApiResponse(true, "Position: (0, 0) Facing: NORTH", null));

		mockMvc.perform(get("/api/v1/probe/position")).andExpect(status().isOk())
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.message").value("Position: (0, 0) Facing: NORTH"))
				.andExpect(jsonPath("$.data").isEmpty());
	}

	@Test
	void testGetVisitedLocations_Success() throws Exception {
		when(probeService.getVisitedLocations())
				.thenReturn(new ApiResponse(true, "Visited locations: [0,0, 0,1, 0,2, 1,2]", null));

		mockMvc.perform(get("/api/v1/probe/visitedLocations")).andExpect(status().isOk())
				.andExpect(jsonPath("$.success").value(true))
				.andExpect(jsonPath("$.message").value("Visited locations: [0,0, 0,1, 0,2, 1,2]"))
				.andExpect(jsonPath("$.data").isEmpty());
	}
}
