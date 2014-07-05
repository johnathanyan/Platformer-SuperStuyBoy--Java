Names (Period 1):
- Hubert Puszklewicz
- Johnathan Yan
- Thomas Hlebowicz

Log:
- Created a UML Diagram (all 3 of us)
- Johnathan (6/3) - Created skeleton files (Player, Menu)
- Johnathan (6/6 - 6/8) - Created buffered menu screen and working GUI. (Menu, Background, Levels, Game)
- Johnathan (6/9) - Un-eclipsed all the files, got level selection to work.
- Johnathan (6/10) - Got level selection from menu to work.
- Johnathan (6/12) - Created enemy class w/ animation.
- Johnathan (6/13) - Created scrolling credits screen
- Johnathan (6/14) - Fixed animations and inputted transition effects from level to level.
- Johnathan (6/15) - Got collision detection to mostly work and there were fixes to player movement.
- Johnathan (6/14) - Fixed jumping and falling and flipped animations.
- Hubert (6/7) - TileMap class - reading text file into double array
- Hubert (6/9) - Changed Player mechanics
- Hubert (6/10) - Trying to print sprites based on text map file
- Hubert (6/13) - Added support for a spritesheet, no separate pics for tiles now
- Hubert (6/14) - Tile class and changes in rendering spritesheet
- Hubert (6/15) - Pixelator to help detect when player collides with a tile
		- The 35x24 double array representing sprites -> giant double array of pixels.
- Hubert (6/15) - Collision Detection overhaul, different method from before. Check the corners of the player a couple of pixels
		  ahead and compare it to the double array of pixels. 
- Hubert (6/16) - Enemy, jumping changes, new sprites, level 3, setting up traps
- Thomas (6/3) - Built off of player skeleton file
- Thomas (6/9) - Instantiated a player in Game.java, and passed the player alogn the class hierarchy
- Thomas (6/10) - Polished level switcher, strategically implemented update and draw methods
- Thomas (6/11) - Displayed the player sprite and map in the window, and implemented basic movement and jump methods
- Thomas (6/12) - Added background, worked on coordinate based methods and vars in player and level
- Thomas (6/13) - First attempt at collision detection using coordinate ranges, and instantiated enemies in game.java
- Thomas (6/14) - Second and third attempts at collision detection using TileMap.java and for loops
- Thomas (6/16) - Added functional level switching, multiple enemies with basic AI, player death mechanics, and traps- the spinning disks
			that move with constant velocity and reset to a spawn point when they pass the edge of the level


Proposal:
- A platform style RPG game, similar to Super Meat Boy.

What Works:
- Menu and credits screen
- Level switches
- Player mechanics
- Generating levels based on text file
- Portals
- 50% of Collision Detection
- Enemies colliding with player
- Music
- Transition Effects

What Doesn't:
- Not as smooth as we wanted
- Collision detection is buggy (we wrote it from scratch)

Instructions:
- Run SuperStuyBoy.jar

OR

- Run Window.java
- Use arrow keys to move and space bar to jump.
