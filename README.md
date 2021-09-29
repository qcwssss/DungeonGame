# Dungeon Escape
A rouge-like dungeon game which will randomly generate a dungeon map when a new game start.
Below are screenshots I captured.

<img width="956" alt="dungeon3" src="https://user-images.githubusercontent.com/83999326/135181305-c1c196c6-d937-4539-a9c0-ad066ac9f985.png">
<img width="956" alt="dungeon" src="https://user-images.githubusercontent.com/83999326/135181306-9b0ab4e8-c5a8-42b7-bfb7-c840a8452486.png">


 
## Description
Dungeon Escape is a rouge-like game implemented with Model-View-Controller designing pattern in Java. The View of the game developed based on a tile engine which can generate all kinds of tiles according to the grid given.

This map app has several features:
  - 1. Player can interact with the avatar player through keyboard, and get helpful information when the cursor points to tiles.
  - 2. The game is visualized with a GUI using StdDraw library with a TO-DO bar displaying instructions.
  - 3. Supported real-time saving and loading of the game status.



## Classes and Data Structures
### MapGenerator
This class provides constructor that is able to generate a random dungeon map along with `AVATAR` which the player controls, `LOCKED_DOOR` and `KEY` in initial position. This class is the core of the randomness of the game.

* Instance Variables
  -  `mapGrid` - a two dimensional grid stores all tile info the dungeon map.
  -  `avatarPos` - the position of the initial avatar tile.
  -  `lockedRoomPos` - the position of the locked room tile.
  -  `keyPos` - the position of the key tile.  


### Position
Represents a tile position of the dungeon map.

* Instance Variables
  -  `xPos` - the x-axis coordinate of a tile.
  -  `yPos` - the y-axis coordinate of a tile.

### Room
Represents a rectangular room enclosed by wall tiles.

* Instance Variables
  -  `width` - the width of the room.
  -  `height` - the height of the room.  
  -  `lowerLeft` - the position of the lower left point of the room.  
  -  `height` - the center position of the room.  

### WorldGraph
A world graph is the key part of connecting random generated rooms with hallways to make the map have various route from one room to any other rooms. This graph stores all room information in the same map and how they are connected.

* Instance Variables
  -  `rNodeMap` - a HashMap stores all room instances with a given id.
  -  `neighbors` - a HashMap stores all of the neighbor rooms of each single room id.

## Engine
Controller of the game, enables interactivity of avatar character through keyboard input from players.
Implemented helper UI and ending message using StdDraw libaray.

## Algorithms

### MapGenerator
The idea of generating random dungeon maps are composed of two phases. First, create numbers of random rooms and then connect them with hallways, which are implemented by the two methods below.
- `createRandomRooms`
  - The `createRandomRooms` method is used to generate random rooms using `Random` instances. It runs a limit times of iteration, each time it will create a room instance randomly if `isOverLap` method doesn't dectect overlaping with existing room objects.
- `connectRooms`
  - The `connectRooms` method takes in a `WorldGraph` instance and build hall ways accoding to instance variable `neighbors`. For two rooms which are neighbors, method `buildHallWays` will draw hallways according to the `center` position.

### WorldGraph
- `connectNeighbors`
  - The `connectNeighbors` method takes in a room id and a distance, then scan all the room instances within the scope and set them as neighbors of the given room id. The distance of two rooms are measured by `estimateDistance` method, which calculate the distance according to the two centers. It compares the range with the estimated distance to determine whether to set the two rooms as neighbors.
  Since I want to make the map neither too dense or sparse, so I set the range argument to a moderate value.


## Persistence
The Dungeon Escape game support real-time saving and loading using `savedString` instance variables in the controller class `Engine`.


## Acknowledgements
Adapted from Project3: BYOW of UCB CS61B-Data Structures course, taught by Josh Hug.

My solutions for other projects, labs and homeworks of this course can be found at [CS61B_20Fall_Assignments](https://github.com/qcwssss/CS61B_20Fall). 

