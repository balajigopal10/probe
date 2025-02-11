# Sea Probe Project

## Overview
The Sea Probe Project is designed to simulate a remotely controlled submersible probe exploring the ocean floor. The system consists of a grid-based map where the probe can move, turn, and interact with obstacles. It is built using **Spring Boot** for the backend, and it allows control via REST APIs.

### Key Features:
- **Initialize Probe**: Initializes the probe with starting coordinates and direction.
- **Move Probe**: The probe can move forward and backward.
- **Turn Probe**: The probe can turn left or right.
- **Obstacles**: Add obstacles on the grid, preventing the probe from moving over them.
- **Track Visited Locations**: Keep track of all visited locations by the probe.
- **Direction Control**: The probe can face one of four directions: NORTH, EAST, SOUTH, WEST.

---

## Setup and Installation

### Prerequisites
- **Java 17** or higher
- **Maven** (for building the project)
- **Postman** (for testing the API)
- **Spring Boot** for the backend
- **JUnit** for testing

---

## API Endpoints

###1. Initialize Probe

**POST** `/api/v1/probe/initialize`

Initializes the probe with starting coordinates, grid dimensions, and a facing direction.

### Request Body
```json
{
  "startX": 0,
  "startY": 0,
  "width": 10,
  "height": 10,
  "direction": "NORTH"
}
```

### Response
- **200 Success**: Probe initialized at (0, 0) facing NORTH
- **400 Bad Request**: Invalid direction. Allowed values: NORTH, EAST, SOUTH, WEST.

###2. Execute Commands

**POST** `/api/v1/probe/executeCommands`

Executes a sequence of commands on the probe. 
The available commands are:
F: Move Forward
B: Move Backward
L: Turn Left
R: Turn Right

### Request Body
```json
{
  "commands": ["F", "R", "F", "L"]
}
```
### Response
- **200 Success**: Commands executed successfully
- **400 Bad Request**: Probe not initialized! Please initialize the probe first.


###3. Add Obstacle

**POST** `/api/v1/probe/addObstacle`

Adds an obstacle at a specified location on the grid.

### Request Body
```json
{
  "x": 2,
  "y": 2
}
```

### Response
- **200 Success**: Obstacle added at (2, 2)
- **400 Bad Request**: Probe not initialized! Please initialize the probe first.


###4. Visited Locations

**GET** `/api/v1/probe/visitedLocations`

Retrieves a list of all locations the probe has visited.

### Response
- **200 Success**: A list of visited locations, e.g., ["(0,0)", "(1,0)", "(1,1)"]
- **400 Bad Request**: Probe not initialized! Please initialize the probe first.

###5. Probe Position

**GET** `/api/v1/probe/position`

Retrieves the current position and facing direction of the probe.

### Response
- **200 Success**: Position: (x, y) Facing: DIRECTION
- **400 Bad Request**: Probe not initialized! Please initialize the probe first.

---

## Setup for Testing
### Unit Testing
Unit tests are written using JUnit to test the functionality of different components of the system. For testing the controller endpoints, MockMvc is used.

####1. Test Command Execution
- Test the probe’s response to commands such as forward, backward, turn left, and turn right.

####2. Test Adding Obstacles
- Ensure that obstacles are correctly placed on the grid and that the probe cannot move through them.

---

## Error Handling
- **Probe Not Initialized**: Some actions cannot be performed if the probe is not initialized. In such cases, the response will return an error message stating, "Probe not initialized! Please initialize the probe first."
- **Invalid Direction**: If an invalid direction is provided during initialization, a 400 Bad Request response will be returned with the message: "Invalid direction. Allowed values: NORTH, EAST, SOUTH, WEST."
- **Invalid Commands**: If an invalid or unrecognized command is sent in the executeCommands API, a 400 Bad Request response will be returned.

---