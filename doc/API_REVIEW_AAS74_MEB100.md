# API Review
## Matthew Barbano (meb100), Ashka Stephen (aas74)

# Part 1

## What about your API/design is intended to be flexible?
Ashka: Can mix and match different Actions to correspond to different Goals
Matthew: Can mix and match Events that are contained in Entity and Actions that are contained in Event
## How is your API/design encapsulating your implementation decisions?
Ashka: Backend-frontend interface: Frontend calls backend methods, but NOT vice versa
Matthew: Observable DP for input/collisions/time encapsulates the logic for detecting these
## How is your part linked to other parts of the project?
Ashka: Frontend calls backend methods, but NOT vice versa
Matthew: Not sure yet about GAE calling Game Engine, Game Player just instantiates and calls GameLoop's start() method, passing it filename of game (or game folder) and stage
## What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
Ashka: Custom VoogaException, popup window
Matthew: Custom VoogaException, popup window
## Why do you think your API/design is good (also define what your measure of good is)?
Ashka: Decision to make "GameObjects" just a single class rather than an inheritance hierarchy
Matthew: Actions and Events linked to Entities

# Part 2

## What feature/design problem are you most excited to work on?
Ashka: Seeing the finished product come together
Matthew: Graph SelectionGroup data structure for selecting levels (during extensions sprint)
## What feature/design problem are you most worried about working on?
Ashka: Trying to draw the line between flexibility and functionality (ex: GameObject hierarhcy or no?)
Matthew: The interface between the GAE and Game Engine
## What major feature do you plan to implement this weekend?
Ashka: Finish the goals, implement an intelligent search algorithm (chasing)
Matthew: Add a second level
## Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?
Ashka: Yes, appropriately sized and descriptive
Matthew: Yes, but they are too advanced for the basic sprint; more are better for extension sprint
## Do you have use cases for errors that might occur?
Ashka: One 
Matthew: No
Some could be: GAE puts two disallowed blocks on top of each other