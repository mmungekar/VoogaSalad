#### API Review - Jay Doherty (jld60)

netids: jld60, jy183

##### Part 1

*1. What about your API/design is intended to be flexible?*

If you wanted to add a new rule to the game, you can add a new Event or Action. Events are things that can trigger Actions (collisions, timer events, etc) and Actions are the consequences of Events (scoring points, losing a life, etc). If you want to add neew rules, you just extends those classes. Entities are the game objects that get these Events/Actions attached to them, and if you want to add new Entity types, then you can extend that class.

*2. How is your API/design encapsulating your implementation decisions?*

We started by splitting the design into 4 different parts (Game Authoring Environment, Game Engine, Game Player, Game Data) so each of those parts don't have to worry about implementation decisions of the others. Within those parts there are distinctions between front-end and back-end (GameEngine has both back-end model components and a front-end GraphicsEngine class).

*3. How is your part linked to other parts of the project?*

My part connects the Player and Engine. I'm writing code that converts the back-end objects into front-end JavaFX objects to display in the Player.

*4. What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?*

I think the Player should not throw any exceptions. If anything it might be responsible for catching errors thrown by GameData if that was somehow messed up.

*5. Why do you think your API/design is good (also define what your measure of good is)?*

I feel like our design is pretty good because you will be able to make a wide variety of games and set objectives just by modifying the blocks placed in the level.

##### Part 2

*1. What feature/design problem are you most excited to work on?*

Figuring out how to link up the front-end JavaFX components to the back-end Entities. The idea is the back-end guys should be able to tell Mario to jump in the back-end without also communicating that to the front-end (Player). 

*2. What feature/design problem are you most worried about working on?*

I think figuring out the separation between front-end and back-end components is tricky and different people have different ideas for how stuff should work, so its sort of hard to figure out what's best.

*3. What major feature do you plan to implement this weekend?*

\#1

*4. Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?*

My part is mostly drawing representations of what's happening in the back-end so we didn't define that many use cases for it. Hopefully it will work as planned.

*5. Do you have use cases for errors that might occur?*

Hopefully the Player will not cause any errors to occur.




