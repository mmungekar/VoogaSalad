Use Cases
===========

**Written by:** Elliott Bolzan (eab91), Jesse Yue (jty4), Jay Doherty (jld60), Mina Mungekar(mem94)

---------------

Jesse Yue
* Starting a Game
To start a new game the user will click the Load Game button in the Game Player. This will lead to a new sub menu where the user can either choose to start a new game or choose a save file.
* Pausing the Game
Within the game there will be a button at the top that will stop the Game Loop when clicked.
* Changing a Setting
Click the Options button in the Game Player and from there change any options for any game. These are tied to the data files of each game.
* Exiting the Game
There will be a button at the top of the Game window to exit the game. Upon clicking it will prompt the user if they want to save their progress. When closed it will return to the Game Player main menu.

-------------

Mina Mungekar

**User sets rule-- if character runs into a specific block, he/she is transported to another level:**

The user selects an object and a list of possible events from a drop-down menu. The game authoring environment initializes a custom block object. The option on the drop-down menu is mapped to a specific action the block will take (i.e., the level transport) when it is collided with. This preference is sent to the game data module as well.

**User creates enemy character and sets its speed:** 

Via reflection, a custom character object is created extending the abstract character enemy class. The speed attribute is modified in the Game Authoring environment, under an "Options" tab. These changes are then
reported to the game data module.

**User creates two different layouts and designates one as level 1 and the other as level 2:**

A generic scene object is generated twice. Each scene object will contain a number attribute listing which order it needs to come in. When the user sets one scene as level 1, that particular layout stores a "1" as one of its attributes; similarly, the other layout stores a "2". Both are passed to the game player; the scene objects and information about their location is sent to the game data module.

**User wishes to change the appearance of an existing entity:** 
The Entity Editing panel will open, providing the user with several options for images and displays.
The user will click the existing entity, and select a "Change Image" option. Alternatively, there will be a "Set Color"/"Set Background" option as well. After hitting the "Save" button, the information for the entity will be updated in the Game Authoring Environment, and, after the user is done with his/her authoring session, all the visual changes will be reported to the Game Player.

------------

Elliott Bolzan (eab91)

**The user adds a game-wide song.**

- The user goes to the `Settings` panel on the left-hand side of his or her Authoring Environment.
- The user clicks the `Set Song` button.
- The user picks an audio file from disk using the `
FileChooser` he or she is presented with.
- The Authoring Environment asks the Game Data module to save the song the user selected. This way, the file originally selected by the user is no longer required. 
- When the user edits or plays the game, the song will now be a part of the experience.

**The user wishes to create the following interaction between objects: a character hits a wall, which is destroyed as the character bounces off.**

- The user navigates to the `Entity Panel` on the right-hand side of the Authoring Environment.
- The user creates two `Actions` using the appropriate subview of the panel:
	- A "bounce" `Action`.
	- A "destroy" `Action`.
- The user creates two `Entities` using the appropriate subview of the panel:
	- A wall.
	- A character.
- The user creates two `Events` using the appropriate subview of the panel:
	- A collision `Event`, owned by the wall. The `Event` is linked to the "destroy" `Action`.
	- A collision `Event`, owned by the character. The `Event` is linked to the "bounce" `Action`.
- The user, by clicking on the two created `Entities`, can add them to his or her game.

**The user wishes to add many instances of a wall to the game.**

- The user creates a wall, using a workflow similar to the one described in the previous use-case.
- The user selects the wall in the `Entity Panel`.
- The user brings the mouse over the `Canvas`. 
- While holding down the `SHIFT` key, the user presses on the mouse and drags it where walls should be created.
- Walls will appear where the user has hovered the mouse.

**The user wishes to consult and edit a list of objects he or she has designed.**

- The user navigates to the `Entity Panel`.
- The user can see the created `Entities`, `Events`, and `Actions` in three corresponding subpanels.
- By double-clicking on individual `Entities`, `Events`, and `Actions` in the corresponding subpanels, the user is brought to a window in which he or she can edit the object.
- Once the object in question is edited and saved, `Entities` that are affected by the change are modified within the game.
- Each of these subpanels also gives the option to create a new object.

------------

Jay Doherty (jld60)

**Player earns points in game**

- The game engine detects that the user does something to earn points
- The game engine calls `Scoreboard.setScore(int points)`
- The `Scoreboard` updates the score to be displayed visually

**Player beats their high score**

- The game engine detects that the game ends, and calls `Scoreboard.addFinalScore()`
- The `Scoreboard` checks the new score against previous scores
- The score gets added to the top of the high score list

**Clearing the high score list**

- The user navigates to the high score menu
- The user presses the Clear button to reset all high scores to zero

**Saving progress for later**

- The player presses the save button within the `GameRunner`
- This calls `ProgressSaver.saveToFile()`
- `ProgressSaver` writes out the state of the game to be loaded later from a file

------------

Michael Li (mxl3)

**Save a particular Entity**

- The Entity will be saved by 'GameSaver'
- saveDefaultEntity(Entity entity) will save the Entity into a serialization

**Loading a game file (gameauthoring environment, collection of objects)**

- The class 'GameLoader' would take in a string of the filepath, from the constructor
- This string will load the collection of Entities, and turn them into a level
- The Level class is returned

**Saving a game into a game folder**

- The Level, which is a collection of Entities, is serialized, and saved into a folder
- All necessary files (images, sound files, etc.) are also saved into the respective folders
- All this is done through 'GameSaver'

**Exporting a zip game folder**

- 'GameSaver' will, given a filepath, compress the game folder into a zip file
- The zip file filepath is returned


