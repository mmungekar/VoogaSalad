Use Cases
===========

**Written by:** Elliott Bolzan (eab91), Jesse Yue (jty4), Jay Doherty (jld60)

Jesse Yue
* Starting a New Game
* Pausing the Game
* Exit the Game
* Loading a Game

Mina Mungekar
** User sets rule—if character runs into a specific block, he/she is transported to another level:**
The user selects an object and a list of possible events from a drop-down menu. The game authoring environment initializes a custom block object. The option on the drop-down menu is mapped to a specific action the block will take (i.e., the level transport) when it is collided with. This preference is sent to the game engine as well.
**User creates enemy character and sets its speed:** 
Via reflection, a custom character object is created extending the abstract character “enemy” class. The speed attribute is modified in the Game Authoring environment, under an "Options" tab. These changes are then
reported to the Game Engine.
** User creates two different layouts and designates one as level 1 and the other as level 2**
A generic scene object is generated twice. Each scene object will contain a number attribute listing which order it needs to come in. When the user sets one scene as level 1, that particular layout stores a “1”; similarly, the other layout stores a “2”. Both are passed to the game player; the scene objects and information about their location is sent to the game engine.
** User wishes to change the appearance of an existing entity ** 
The Entity Editing panel will open, providing the user with several options for images and displays.
The user will click the existing entity, and select a "Change Image" option. Alternatively, there will be a "Set Color"/"Set Background" option as well. After hitting the "Save" button, the information for the entity will be updated in the Game Authoring Environment, and, after the user is done with his/her authoring session, all the visual changes will be reported to the Game Player.


