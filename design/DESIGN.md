Design Specifications
===========

**Written by:** Elliott Bolzan (eab91), Matthew Barbano (meb100), .

## Introduction

## Overview

On the highest level, our project will include the standard four subgroups, each of which contains several modules:
*	Game Authoring Environment
*	Game Engine
*	Game Player
*	Game Data Manager
The Game Player, which will play a game with a configuration that the user has saved, will communicate with the Game Engine to obtain data for running the game. It will not communicate with any other of the four subgroups. The Game Engine will communicate with both the Game Authoring Environment, which will take care of updating the UI, and the Game Data Manager, which will save games that the user has created. 

The Game Authoring Environment (GAE) and the Game Engine will each be divided into the same set of modules, but each module will have different functionality in the GAE vs. the Engine:

*	Game – The highest-level module, manages the flow of time, organization of levels, and interaction with the Game Player. 
   *	Game Engine: the Game module contains the following classes:
       *	TimingManager - Information about the current time.
       *	PlayerManager - Information about players. Manages their interactions, if multiplayer. See “Player” module below.
       *	GameLoop - Game loop.
       *	LevelManager – Contains a Collection of all existing levels. Responsible for creating/deleting new levels.
       *	Level – Contains classes from Player and Object modules pertaining to that level. Manages interaction between Players and Objects. Will likely be extended into inheritance hierarchy for creation of new types of levels, and each level will be divided into multiple classes. Also contains information about Settings:
            *	Orientation.
            *	Scrolling speed.
            *	Background scrolling speed.
            *	Whether scrolling is determined by character or by game.
   *	GAE: the Game module only contains a counterpart to:
       *	Level – Manages animation relating to JavaFX Objects, and the Settings described above. Adds the appropriate JavaFX objects to a scene for the level. Likely multiple classes.
*   Player – Contains information such a lives left and points earned for the human game player. Does not manage character appearing on screen (allows for games where human can switch between characters).
    *   Game Engine: See above.
    *   GAE: Nothing here.
*	Entity. – Anything drawn on the game screen.
    *	GamerControlledEntity – The character(s) controlled by the human player. Separate from the human player (in case can switch between characters), but interacts with the “Player” module. 
    *	ComputerControlledEntity – Anything appearing on screen not controlled by the human player. This includes wall, blocks, and enemies. Divided between those that move (the user will be able to choose left/right, or up/down and the distance), and those that do not.
    *	Background
    *	Game Engine: For Character and Block, contains inheritance hierarchies for different types. Also attaches Events to each object to handle interactions between Characters and Blocks. Contains a single class for the Background.
    *   GAE: Contains counterparts for all three categories (Character, Block, and Background) for drawing JavaFX objects.
*   Event (attached to Object). Defined as one of the following:
    *	User input.
    *	Collision (each side, to distinguish between possible ones). 
    *	Timer.
    *	Example: Mario hits a block. The block releases a prize. The block has an event; Mario also has an event.  They each have an event from their own point-of-view.
    *	Game Engine: See above. Importantly, attaches an Action to each Event.
    *	GAE: Nothing here. All Event detection handled in the backend by the Game Engine.
*	Action (attached to Event). – The consequence of each Event. Examples are:
    *	Instantiate new object.
    *	Destroy (character or other object).
    *	Win. 
    *	Lose (life or game).
    *	Bounce (move).
    *	Change image.
    *   Power-up.
    *	Next level.
    *	Make sound.
    *	Movement. – This includes the physics of jumping/gravity.
    *  	Game Engine: Contains a class (or a few classes) for each of these listed above.
    *	GAE: Contains counterparts to ALL of the Game Engine classes in this module for updating the level Scene appropriately.

In addition, the GAE will have the following modules (related to the UI outside of the actual game), which DO NOT have a counterpart in the Game Engine:
*	Menu Module – For menus along the top of the UI (example: File, Edit, Help).
*	File Module – For loading and saving user input/output.
*	Edit Module – For editing both Entities (see above) and other aspects of the game. Should have separate UI components editing Entities, Player Settings, Level Settings, etc.
*	Editor Display Module – A picture of the level that is being edited. User can interact with.
*	Play Display Module – For use by the Game Player when the user is testing the game. 
*	Navigation Module – For navigating between levels in the current game.

The Game Player will consist of two modules:
*	I/O Module – For sending user input to the Game Engine and receiving user output from the Game Engine.
*	Play Module – For displaying the game and any auxiliary GUI components.

The Game Data will consist of the following modules:
*	Input Module
*	Output Module

INSERT DIAGRAM RELATING MODULES HERE    //TODO<-------------

## User Interface

## Design Details

## Example Games

## Design Considerations