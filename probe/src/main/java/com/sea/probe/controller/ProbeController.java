package com.sea.probe.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sea.probe.controller.dto.InitializeRequest;
import com.sea.probe.controller.dto.ObstacleRequest;
import com.sea.probe.controller.dto.ProbeCommandRequest;
import com.sea.probe.exception.ProbeException;
import com.sea.probe.response.ApiResponse;
import com.sea.probe.service.ProbeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/probe")
public class ProbeController {

	@Autowired
	private ProbeService probeService;

	@PostMapping("/initialize")
	public ResponseEntity<ApiResponse<String>> initializeProbe(@RequestBody @Valid InitializeRequest request) {
		try {
			ApiResponse<String> response = probeService.initializeProbe(request);
			return ResponseEntity.ok(response);
		} catch (ProbeException e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
		}
	}

	@PostMapping("/executeCommands")
	public ResponseEntity<ApiResponse<String>> executeCommands(@RequestBody @Valid ProbeCommandRequest request) {
		try {
			ApiResponse<String> response = probeService.executeCommands(request);
			return ResponseEntity.ok(response);
		} catch (ProbeException e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
		}
	}

	@PostMapping("/addObstacle")
	public ResponseEntity<ApiResponse<String>> addObstacle(@RequestBody ObstacleRequest request) {
		try {
			ApiResponse<String> response = probeService.addObstacle(request);
			return ResponseEntity.ok(response);
		} catch (ProbeException e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
		}
	}

	@GetMapping("/visitedLocations")
	public ResponseEntity<ApiResponse<String>> getVisitedLocations() {
		try {
			ApiResponse<String> response = probeService.getVisitedLocations();
			return ResponseEntity.ok(response);
		} catch (ProbeException e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
		}
	}

	@GetMapping("/position")
	public ResponseEntity<ApiResponse<String>> getPosition() {
		try {
			ApiResponse<String> response = probeService.getPosition();
			return ResponseEntity.ok(response);
		} catch (ProbeException e) {
			return ResponseEntity.badRequest().body(new ApiResponse<>(false, e.getMessage(), null));
		}
	}
}
