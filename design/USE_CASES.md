Use Cases
===========

**Written by:** Elliott Bolzan (eab91), Jesse Yue (jty4), Jay Doherty (jld60), Mina Mungekar(mem94), Nikita Zemlevskiy (naz7)

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

------------

Nikita Zemlevskiy (naz7)
The following use cases focus on the functionality of the game engine and assume other parts of the project are functioning as expected.

- The user presses a key to throw a moving object in the game.<br>
The `Player` module will handle the user input. Since all key presses will be bound to a `handleKeyPressed` method in the backend, this method will get triggered automatically. This method will identify all entities that have an event corresponding to that key being pressed. Next, those events will be triggered, and their respective actions will `act`. In this case, some kind of new entity will be created, and it's speed will be set accordingly to the action. This way, the moving object will be created and will fly off as expected.


- The moving object thrown goes off of the screen.<br>
When the moving object (such as a turtle on the screen in the case of mario) is moving toward one side, it will fly off of the screen at some point. It will continue to be updated on every call of the game loop (step), even though it is not within the range of the screen. The screen will begin at the position determined by the `Camera` and will end at that position + width (height) of screen.

- User creates a block that releases a powerup on a collision with a character (and the character is reflected to the direction opposite his initial direction of motion).<br>
The game authoring environment will create the appropriate `Entity` object. These include the character entity and the block entity. Next, the user will select an event which corresponds to the desired behavior. In this example, two `CollisionEvent` objects will be created. One will be the collision from the perspective of the block, and the other will be from the perspective of the character. These events will have their respective `Collision` objects, which differentiate the two. Next, the desired behavior will be added by creating the appropriate `Action` objects and attaching them to the `Event` objects that were just created. In this example, these are releasing a powerup for the block, and bouncing back for the character. The events will then be added to the respective `Entity` objects, which completes the use case.

- The above collision happens.<br>
When a collision happens, it will be detected in the game engine. This will set off the appropriate `Event`s in the appropriate entities. In this example, the game engine will detect that the above collision occured, and will check if those entities have an event to respond to that collision. If they do (which they do), these events will be triggered, and the `act` methods of the respective `Action` objects contained in the `Event` objects will be called to complete the actions requested. Here, the character will be reflected to the opposite way (speed will be changed accordingly) and the block will spawn a new `Entity` with the respective `Event` and `Action` objects attached (give the powerup to the character when colliding with the character). This behavior will have to be preset by the user in the authoring environment.

Jimmy Shackford (jas199)<br>
**Add a character to the game and allow them to move**
- The user goes to the `Entity Panel` in the right-side menu of the Authoring Environment.
- The user creates an `Entity` for the character.
- In this same right-panel, the user creates `Action-Event` pairs for each type of movement.
    - 1. `Event` : On left key pressed. `Action`: Character `Entity` moves left for specified duration of time at specified speed.
    - 2. `Event` : On right key pressed. `Action`: Character `Entity` moves right.
    - 3. `Event` : On up key pressed. `Action`: Character `Entity` moves upwards, and moves back down from specified gravity.
- By clicking on the entities in this panel, the user can then add them to the actual game.

**Add a background to the game and specify the speed it moves at**
- The user goes to the `Entity Panel` on the right-side menu of the Authoring Environment.
- The user creates a new `Layer` and titles it
- The user adds a `Background Image` to this layer
- On the side menu, the user specifies how fast the layer scrolls at—the `Background Scroll Speed`

**Add a wall to the game and specify that a user cannot move through it**
- The user goes to the `Entity Panel` on the right-side menu of the Authoring Environment.
- The user creates a wall `Entity`.
- In this same right-panel, the user creates the following `Action-Event` pairs
	- If the wall is a vertical wall: `Action`: On intersection between wall and character. `Event`: Character’s x-velocity is set to 0.
	- If the wall is a horizontal wall (ground): ‘Action’: On intersection between wall and character. ‘Event’: Character’s y-velocity is set to 0.
- By clicking on this entity in the side panel, the user can add it to the actual game my moving the mouse.

**Save a game that was created within the Game Authoring Environment**
- After a level has been created, the user goes to the `Settings Panel` on the left-side menu of the Authoring Environment.
- The user clicks the `Save` button in this panel and specifies the name of the level to be saved.
- This creates a saved file that can be re-loaded at a later time to edit again.
- Note: Alternatively, the game will be saved to the file after each change the user has made to the game. 

