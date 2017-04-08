### Part 1

* What about your API/design is intended to be flexible?

My API is intended to be flexible for adding new actions and events that can
occur for character because the actions and events are each part of a
hierarchy so new actions and events do not require altering existing actions
and events.

* How is your API/design encapsulating your implementation decisions?

My API/design is encapsulating the implementation by using the observable design
pattern to allows entities to receive changes from the observable subclasses. For example,
CollisionObservable checks all the collisions in a current state and updates the list
of collisions used by events within all entities

* How is your part linked to other parts of the project?

My part is linked to the authoring environment by allowing a user to associate
specific actions with specific events so when an event occurs in the game player, these actions 
happen. Additionally, events can be associated with specific entities so
that these entities check for these events to occur. 

* What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

Exceptions that might occur in my part include a user trying to associate a character related action
to a non-character entity, such as making a block lose a life. This error will be handled throwing an exception from 
the game engine that explains that a certain action is only valid for certain types of entities.

* Why do you think your API/design is good (also define what your measure of good is)?

The API/design is good because it makes use of many strong design principles from class:
open-closed principle, observable design pattern, and good class heirarchy.

### Part 2

* What feature/design problem are you most excited to work on?

I am most excited to work on the different possible actions that can occur for
entities because this will be the primary method to allow the user to create a
variety of different games based on how the user chooses to combine events and 
actions for specific entities. Creating good interaction between the user and the entities
on the screen is the primary purpose of a game engine.

* What feature/design problem are you most worried about working on?

I am most worried about working on the refactoring of the code becasue it is
already beginning to become somewhat difficult to follow as we continue to add
new features to the game. Ideally, the connections between different parts of the
engine should be easy to find and understand, moreso than they are right now.

* What major feature do you plan to implement this weekend?

The major feature I plan to implement this weekend is allowing the user to
complete a level and move on to the next level. This first requires providing the main
character the ability to move around the screen and interact with other entities, such
as blocks or enemies, properly. Next, it requires providing an collision event with a 
corresponding action that ends the current level. Lastly, it requires the loading of 
the next level to allow the user to continue playing the game.

* Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?

The use cases I created for my pieces were mostly related to the interaction of entities. They were reasonably
descriptive but they may have been too large because they each required multiple features to be implemented
before they could be completed. Ideally, they should have been broken into smaller tasks. For example, the collision
between the main character should have been broken down into creating a main character entity, creating an enemy
entity, checking collisions between entities, and creating an action to make the main character lose a life when the
collision occurs. 

* Do you have use cases for errors that might occur?

I did not consider that use cases should be created for errors.

