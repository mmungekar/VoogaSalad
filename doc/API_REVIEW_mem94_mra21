API REVIEW
===============================

* Miguel and I discussed the front-end parts of both our projects. While his team is implementing a tower defense game, mine is a scrolling platformer, introducing some 
concrete differences between the two. One point of discussion was of the level of integration necessary between the two parts of the front-end, the Game Player, and the 
Game Authoring Environment. The two have to share the same user interface basics, meaning that there will need to be some information shared between the two.
Furthermore, if the game needs to be editable while being played, the game authoring environment and game player cannot be too separate from one another.

* It was interesting to note that both of us thought that categorizing all objects as overarching 'Entities' was a good idea. Generalizing objects as much as possible seemed
to be the goal, and with a large entity class, it was possible to dissect all objects in the sense that all had events and actions attached to them (an Event being the catalyst that
triggers an Action)

* It may be worth changing the Game Authoring Environment's workspace. Every child node of the overall environment Workspace requires an instance of the workspace to be 
initialized. It creates a circular reference that may not ultimately be desired; however, it allows the individual panels within the authoring environment to communicate with
other parts of the project. In this sense, the workspace is a controller class that communicates between different parts of the authoring environment and components like Game
Data and the Engine.

* Something worth considering is the handling of errors within the authoring environment. For example, a user should not be able to place two entities in the exact same location. The 
error handling for this needs to take place in the canvas itself. If errors occur in other parts of the authoring environment, public methods need to be made to 
update whichever classes display the errors.