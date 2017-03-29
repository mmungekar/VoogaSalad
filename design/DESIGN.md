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

The Game Engine will be divided into the following modules:
*	Game Module– The highest-level module, manages the flow of time, organization of levels, and interaction with the Game Player. It will contain the following classes:
   *	TimingManager - Information about the current time.
   *	PlayerManager - Information about players. Manages their interactions, if multiplayer. See “Player” module below.
   *	GameLoop - Game loop.
   *	LevelManager – Contains a Collection of all existing levels. Responsible for creating/deleting new levels.
   *	Level – Contains classes from Player and Object modules pertaining to that level. Manages interaction between Players and Objects. Will likely be extended into inheritance hierarchy for creation of new types of levels, and each level will be divided into multiple classes. Also contains information about Settings:
        *	Orientation.
        *	Scrolling speed.
        *	Background scrolling speed.
        *	Whether scrolling is determined by character or by game.
*   Player Module – Contains information such a lives left and points earned for the human game player. Does not manage character appearing on screen (allows for games where human can switch between characters).
*	Entity Module. – Anything drawn on the game screen.
    *	GamerControlledEntity – The character(s) controlled by the human player. Separate from the human player (in case can switch between characters), but interacts with the “Player” module. 
    *	ComputerControlledEntity – Anything appearing on screen not controlled by the human player. This includes wall, blocks, and enemies. Divided between those that move (the user will be able to choose left/right, or up/down and the distance), and those that do not.
    *	Background.
    *	Note: For GamerControlledEntity and ComputerControlledEntity, contains inheritance hierarchies for different types. Also attaches Events to each object to handle interactions between Characters and Blocks. Contains a single class for the Background.
*   Event Module (attached to Object). Defined as one of the following:
    *	User input.
    *	Collision (each side, to distinguish between possible ones). 
    *	Timer.
    *	Example: Mario hits a block. The block releases a prize. The block has an event; Mario also has an event.  They each have an event from their own point-of-view.
*	Action Module (attached to Event). – The consequence of each Event. Examples are:
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
    *  	Note: Obtains the list of these Actions through reflection.

The GAE will be divided into the following modules (some of which have counterparts in the Game Engine, others of which do not):
* Game Module- counterpart to the Game module in Game Engine
       *	Level – Manages animation relating to JavaFX Objects. Adds the appropriate JavaFX objects to a scene for the level. Likely multiple classes.
       *	More classes/sets of classes will likely be added here.
* Entity Module - counterpart to the Entity module in Game Engine
        *   Contains counterparts for all three categories (Character, Block, and Background) for drawing JavaFX objects.
* Action Module - counterpart to the Action module in the Game Engine
        *	Contains counterparts to ALL of the Game Engine classes in this module for updating the level Scene appropriately.
*	Settings Module – For editing game-wide settings and saving the game to disk.
*	Canvas Module – For visualizing the Entities added to the game and interacting with them. The user should be able to move these Entities around.
*	Entity Module – For creating, displaying, and editing Entities. The user should be able to assign specific Events and corresponding Actions to each created Entitity.
*	Navigation Module – For navigating between levels in the current game. Will most likely be constructed using a tabbed structure.

The Game Player will consist of two modules:
*	I/O Module – For sending user input to the Game Engine and receiving user output from the Game Engine.
*	Play Module – For displaying the game and any auxiliary GUI components.

The Game Data will consist of the following modules:
*	Input Module
*	Output Module

INSERT DIAGRAM RELATING MODULES HERE    //TODO<------------- (want to wait for things to be more finalized before I draw...)

## User Interface

The User Interface for this project will be composed of three primary units:

1. The Starter Window.
2. The Authoring Environment.
3. The Game Player.

These three units are described below:

1\. **The Starter Window**

2\. **The Authoring Environment**

The Authoring Environment will be further subdivided into three parts:

1. The Settings (on the left in the image below).
2. The Canvas (in the center in the image below).
3. The Entity Panel (on the right in the image below).

![](images/authoring.png)

- **The Settings**

The **Settings** panel will harbor all game-wide settings. 

These settings may include: the scrolling platformer's orientation, whether the game scrolls automatically or when prompted by the character, the game's song, etc.

In addition, the Settings will contain a `Save` button, that allows for the saving of the designed game to file. 

Generally speaking, this section will only include "traditional" user interface elements: there should be no surprises in how the user should interact with the Settings.

- **The Canvas**

The **Canvas** is the section in which the constructed game will be visualized. 

The **Canvas** will be tabbed: each tab will correspond to one **Level** in the game. Furthermore, any new **Level** should be created as a duplicate of the current **Level**, in order to minimize the user's design time.

The user will be able to add **Entities** to the **Canvas** by clicking on an **Entity** in the **Entity Panel** and then clicking on the **Canvas**. Once the **Entity** is added to the **Canvas**, the user will be able to move it to its appropriate location by dragging it.

Clicking on an existing **Entity** will open it in the **Entity Panel**'s editor.

The **Canvas** should be able to extend infinitely, either horizontally or vertically. This way, the user can create games of any size. 

- **The Entity Panel**

This component's purpose is the creation, display, and editing of **Entities**, the basic building blocks of games. This component is split into two parts: the created entity display, and the entity editor.

The created entity display will show the user which entities have been created, and provide a way for creating new entities. Upon clicking on an entity, the user will be able to click on the **Canvas** to add that entity to the game. 

The entity editor will allow the user to create a new **Entity**. The user should be able to set the type of this new object; its image; and **Events** to which this **Entity** responds, associated to **Actions**. Most of the game's functionality should be created from this subpanel.

*Errors will be indicated to the user through a JavaFX Dialog. Possible errors include*:

- Incorrect parameters when an **Action** is created.
- Incorrect parameters for game-wide settings.
- Seeking to delete the game's only existing level.

3\. **The Game Player**

## Design Details

## Example Games

## Design Considerations