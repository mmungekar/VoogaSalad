**Written by: Elliott Bolzan (eab91) and Jonathan Rub (jr286).**

## Elliott’s API

**What about your API/design is intended to be flexible?** 

My API / design is intended to be flexible in the following ways:

- Every GUI component inherits from a `View` object.
- Collapsible views all inherit from a `CollapsibleView` object.
- I made use of a `ResourceBundle`.
- I have an implementation of a `TableView` or `List` in which the rows can be edited and deleted: I can reuse this component, and have, many times. This prevents duplicated code.

**How is your API/design encapsulating your implementation decisions?**

My API encapsulates implementation designs the following way:

- The logic for collapsing individual views is held in one `CollapsibleView` abstract class.
- The logic for creating a new `Entity` or editing one is all contained in the `EntityMaker`.
- The `EntityMaker` itself delegates the display and creation of `Events` and `Actions` to other classes.

In essence, my code is structured the way it looks on screen. This, I think, promotes encapsulation: elements only need to know about themselves.

**How is your part linked to other parts of the project?** 

My Authoring Environment interacts with two parts of the project:

1\. **The Engine**: my environment obtains information about available `Entities`, `Events`, and `Actions` from the Engine. 
2\. **The Game Data**: my environment calls upon this module to save the game information to disk.

These two modules are loaded by instatiating the most high-level class in each module. Those modules then make use of the **Chain of Responsibility** design pattern to provide me with the information I need.

**What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**

I am throwing exceptions in my code for the following events:

-  The user seeks to add an `Action`, but no `Event` is selected.
-  The user seeks to add an `Event`, but no `Entity` is selected.
-  The user provide an illegal argument.

**Why do you think your API/design is good (also define what your measure of good is)?** 

I think the following constitutes good design:

- The ability to reuse classes.
- The ability to extend classes and add features easily.
- The lack of duplicated code.

My API follows good design in the following ways:

- It is highly reusable: in fact, I reused most of my SLogo front-end code for this project.
- I can reuse classes and add features easily, because I have made `Editor` and `Picker` abstract classes. That way, I can use the same classes to pick and edit `Actions`, `Events`, and `Entities`.
- Because of this use of vertical design hierarchies, I really believe I have little duplicated code. My use of the **Factory** design pattern certainly helps with this as well!

### Part 2

**What feature/design problem are you most excited to work on?** 

I'm most excited to work on my chat system. I've coded it already, and tested it on my home WPA 2 network, but for some reason, it does not function on Duke's massive `dukeblue`.

**What feature/design problem are you most worried about working on?** 

Likewise, I'm most worried about this networking problem. While most GUI implementations come easily to me, I know little about networking, and this could take me a solid amount of time.

**What major feature do you plan to implement this weekend?**

This weekend, I plan on finishing the part I was assigned: the creation and editing of `Entities`, `Events`, and `Actions`.

**Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?**

So far, the use cases that I had created are appropriate. I wish I had had more diversity: of the 6 I have left, when I will have finished one, I will have finished all of them. In that regard, I believe my use cases might have been too big. 

In the future, I'll strive to make my use cases more manageable in size!

**Do you have use cases for errors that might occur?**

As of now, I have situations in which errors might occur that I'm aware of, but no corresponding use cases for them in Gitlab. Over the weekend, I will add use cases that satisfy this purpose. Errors should be tested, like any of the functionality in a project.

## Jonathan’s API

### Part 1

**What about your API/design is intended to be flexible?** 

The goal is to add new effects to characters easily. The characters use the Composite Design pattern, in order to enhance flexibility. There’s a Utility Factory, that creates buttons and objects in one place, in order to reduce duplicated code. Tabs should also be easy to add using our design.

**How is your API/design encapsulating your implementation decisions?**

Every element in the Authoring Environment is an extension of a GUIComponent. That way, many visual elements are encapsulated in one Java class. Extensions of that class do not have to worry about the precise implementation decisions. The elements in the toolbar are sourced from a ResourseBundle, promoting encapsulation as well.

**How is your part linked to other parts of the project?** 

The Authoring Environment is linked to the other parts of the project in one way: the game information is sent to the XML Writer, to be written to disk. No other modules should be interacting with the Authoring Engine directly.

**What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?**

The following exceptions can take place in the Authoring Environment:

- The user loads a file that is not an image for a sprite.

- An invalid value is entered by the user.

Upon throwing these exceptions, an Alert box is shown to the user to inform him or her of the mistake.

**Why do you think your API/design is good (also define what your measure of good is)?** 

My measure of good design is as follows:

- Easily extensible: adding new components should be easy.
- Reusable: elements should be used several times to reduce code duplication.
- Little duplicated code.

The current API / design is good for the following reasons:

- It’s easily extensible because it makes use of abstract classes for similar objects.

- It’s flexible because it uses a ResourceBundle.

### Part 2

**What feature/design problem are you most excited to work on?** 

I'm most excited to work on the shared editor aspect of the Authoring Environment. Networking interests me, and I look forward to the challenges of learning how it works.

**What feature/design problem are you most worried about working on?** 

I'm most worried about working on getting the toolbar to update correctly based on the characters chosen. This seems like the most difficult feature I currently have to do.

**What major feature do you plan to implement this weekend?**

This weekend, I want to implement the aforementioned toolbar. It seems difficult, and I want to get it out of the way.

**Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?**

The use cases for my part are appropriate, overall. They might be a little big, but I'll work around it.

**Do you have use cases for errors that might occur?**

I do:

- The user uploading a file that is not a picture.
- The user entering incorrect data.