# Pull Requests Index

&emsp;&emsp;Records of pull requests for master.

### &emsp;Timeline
[#1](https://github.com/jht412ntu/PropertyTycoon/pull/1)   `29/02/2020`  Hayden Banes 198757  
Creater: Hayden  
Comment: Created inital version of Dice class, with a basic test class

[#2](https://github.com/jht412ntu/PropertyTycoon/pull/2)   `03/03/2020`  Hayden banes 198757  
Creater: Hayden  
Comment: Improved logic of the dice class, now will stop the player from rolling again if they did not roll a double.

[#3](https://github.com/jht412ntu/PropertyTycoon/pull/3)   `09/03/2020`  Player class created  
Creater: Haotian  
Comment: None

[#4](https://github.com/jht412ntu/PropertyTycoon/pull/4)   `09/03/2020`  Update - System files deleted  
Creater: Haotian  
Comment: None

[#5](https://github.com/jht412ntu/PropertyTycoon/pull/5)   `12/03/2020`  GitHub Page updated after 12/03/2020 online meeting  
Creater: Haotian   
Comment: None

[#6](https://github.com/jht412ntu/PropertyTycoon/pull/6)   `16/03/2020`  sync from master  
Creater: Haotian  
Comment: None

[#7](https://github.com/jht412ntu/PropertyTycoon/pull/7)   `16/03/2020`  Merge pull request #6 from jht412ntu/master  
Creater: Haotian  
Comment: sync from master

[#12](https://github.com/jht412ntu/PropertyTycoon/pull/12)   `20/03/2020`  Zhenyu tang 215907  
Creater: Hayden  
Comment: None

[#13](https://github.com/jht412ntu/PropertyTycoon/pull/13)   `20/03/2020`  Hayden Banes 198757  
Creater: Hayden  
Comment:  
Started basic development of GUI.
Note: GUI is incomplete and much work is still needed before a game can even be started.

Encountered many issues with class interaction, which required reworking of some class elements and cause delays in development.

[#14](https://github.com/jht412ntu/PropertyTycoon/pull/14)   `20/03/2020`  GUI Version 0.1  
Creater: Hayden  
Comment:   
Updated the GUI to be able to use most functions avaliable in the games current state.

GUI Version 0.1:

The player can choose between full game, and abridged with timer.
The player can enter thier name and select a token to represent themselves within the game. Multiple players can be added by repeating this process.
Rolling the dice displays the current roll value and increases the players position by the same amount
During a players go, the GUI displays various properties about the player including:
Name
Balance
Positon
Roll value (if the player has rolled)
Once a player has rolled and if they cannot roll again, the player can pass control to the next player with the "Next Player" button.
To quit the game, the player can choose file -> exit, or click the red x
Features missing from this version:

On the player setup screen:
There is no check if the entered name has already been used
There is no check if the selected token is already taken.
On the game mode select screen:
*
On the gameplay screen:
The countdown timer only updates as a general GUI update (so only when something else happens)
When the players position reaches 40 (last position on the board) it keeps increasing, instead of returning to 0 to signify a lap of the board and passing go.
The player cannot buy properties currently.
No check for less then two players or more than 6 before starting the game
No check if two players have the same token/name
No linking of player position (int) and board class

[#15](https://github.com/jht412ntu/PropertyTycoon/pull/15)   `22/03/2020`  Bank and Property Ver.1.0.0  
Creater: Haotian  
Comment:   
new file:  
PropertyTycoonGame/src/propertytycoongame/Bank.java "Bank Ver.1.0.0"  
PropertyTycoonGame/src/propertytycoongame/BankException.java "Initialise BankException"  
PropertyTycoonGame/src/propertytycoongame/Property.java "Property Ver.1.0.0"  
PropertyTycoonGame/src/propertytycoongame/PropertyException.java "Initialise PropertyException"

[#16](https://github.com/jht412ntu/PropertyTycoon/pull/16)   `22/03/2020`  Bank and Property Changed by Mingfeng wang 215920  
Creater: Haotian  
Comment: None

[#17](https://github.com/jht412ntu/PropertyTycoon/pull/17)   `22/03/2020`  Small changes to Property and Bank  
Creater: Haotian  
Comment:   
add 'extends Cell' to Property  
add bought property to player's property list  
change owner to be NULL when a property has been sold  
change Cell class's variable 'location' to 'position'  
delete unsuitable Boardtest  
delete unsuitable inner Cell class inside Board

[#18](https://github.com/jht412ntu/PropertyTycoon/pull/18)   `06/04/2020`  Merge Mingfeng wang 215920 to Haotian Jiao 215898  
Creater: Haotian  
Comment: None

[#19](https://github.com/jht412ntu/PropertyTycoon/pull/19)   `07/04/2020`  Haotian Jiao 215898 Sprint 3 Auction features  
Creater: Haotian  
Comment:   
Completed auction method in Bank class  
----Accept offers that between 0 and the max money they have

Added setPassedGoPlayer method in CentralControl to collect those players passed go

Added isPlayerPassedGo method in CentralControl to check if a player in the passed go list

The requirement to check if a player passed go(eligible to bid) should design in GUI:  
----If a player hasn't pass GO, then refuse them to make a bid.

[#20](https://github.com/jht412ntu/PropertyTycoon/pull/20)   `07/04/2020`  Updating member's branch after merging  
Creater: Haotian   
Comment:   
See details here:  
[#19](https://github.com/jht412ntu/PropertyTycoon/pull/19)

[#21](https://github.com/jht412ntu/PropertyTycoon/pull/21)   `07/04/2020`  Updating member's branch after merging  
Creater: Haotian   
Comment:   
See details here:  
[#19](https://github.com/jht412ntu/PropertyTycoon/pull/19)

[#22](https://github.com/jht412ntu/PropertyTycoon/pull/22)   `07/04/2020`  Updating member's branch after merging  
Creater: Haotian   
Comment:   
See details here:  
[#19](https://github.com/jht412ntu/PropertyTycoon/pull/19)

[#23](https://github.com/jht412ntu/PropertyTycoon/pull/23)   `17/04/2020`  Mingfeng wang 215920  
Creater: Mingfeng  
Comment: When pass Go, updating money in bank and player together.

[#24](https://github.com/jht412ntu/PropertyTycoon/pull/24)   `22/04/2020`  Mingfeng wang 215920  
Creater: Mingfeng  
Comment: Payrent update. endGame and raiseMoney function created.

[#25](https://github.com/jht412ntu/PropertyTycoon/pull/25)   `20/04/2020`  Bank Class: Modified the bid method & Player Class: Created the sellPropertyToPlayer method  
Creater: Haotian  
Comment:   
Bank:  
Modified the bid method:  
Keeping two same max offered prices and the players if they offered same price.  
[62a85b2](https://github.com/jht412ntu/PropertyTycoon/commit/62a85b2eb54410912654d948f58137113124707d)

Player:  
Created the sellPropertyToPlayer method:  
Now can sell property between players with their agreed price.  
[4c8ed97](https://github.com/jht412ntu/PropertyTycoon/commit/4c8ed9723ebf5223b29eed9249df1b778c437c61)

To be continued:  
The GUI side:  
1 - allow player bid again when offered two same max price  
2 - discuss agreed selling price between players before two players can sell and buy a property

[#26](https://github.com/jht412ntu/PropertyTycoon/pull/26)   `22/04/2020`  Revert "Mingfeng wang 215920"  
Creater: Hayden  
Comment:   
Reverts [#24](https://github.com/jht412ntu/PropertyTycoon/pull/24)  
Accidently merged branch into master

[#27](https://github.com/jht412ntu/PropertyTycoon/pull/27)   `23/04/2020`  Agent created with fuction "rollDice, autoBuyProperty, autoBuildHouse  
Creater: Haotian on behalf of Mingfeng  
Comment: None

[#28](https://github.com/jht412ntu/PropertyTycoon/pull/28)   `23/04/2020`  Revert "Agent created with fuction "rollDice, autoBuyProperty, autoBuildHouse"  
Creater: Haotian on behalf of Mingfeng  
Comment: Reverts [#27](https://github.com/jht412ntu/PropertyTycoon/pull/27)

[#29](https://github.com/jht412ntu/PropertyTycoon/pull/29)   `23/04/2020`  Agent features: Mingfeng wang 215920  
Creater: Haotian on behalf of Mingfeng  
Comment: None

[#30](https://github.com/jht412ntu/PropertyTycoon/pull/30)   `23/04/2020`  Hayden banes 198757  
Creater: Hayden  
Comment: Adding GUI changes

[#31](https://github.com/jht412ntu/PropertyTycoon/pull/31)   `23/04/2020`  Update Board.java  
Creater: Hayden  
Comment: Fixed bug in board initialisation

[#32](https://github.com/jht412ntu/PropertyTycoon/pull/32)   `24/04/2020`  Critical Classes changes: Agent, Bank, Player, Board, PotluckCard and OpportunityknockCard  
Creater: Haotian  
Comment:  
Agent:  
Added autoPayRent, autoSelectCard and autoAuction.  
Also fixed the run().  
[a7130ef](https://github.com/jht412ntu/PropertyTycoon/commit/a7130efa056b2e73d1a653e0fb2a3c31c5d79dab)

Bank:  
Now the buildHouse function will only allow to build house when the houses on a property in current group has no more than 1 difference.  
Added changePermittedHouses function  
[36b157f](https://github.com/jht412ntu/PropertyTycoon/commit/36b157f25c164dce8d12bd27b2acc97b630d0bf3)

Player:  
Added setLeaveGame function for players to mark themselves as leaved.  
[5aff643](https://github.com/jht412ntu/PropertyTycoon/commit/5aff6437010ae1f0d6a65d90a80ea267d6da173c)

Board:  
Changed getProperty to getCell as we also need to get the Card type cell.  
related changes:  
"getProperty" in GUI

PotluckCard & OpportunityknockCard  
Cards set now has flexible position.  
[0782fc7](https://github.com/jht412ntu/PropertyTycoon/commit/0782fc7147009a2c2046762ac2cc533dfd2e78f8)  
[9305814](https://github.com/jht412ntu/PropertyTycoon/commit/93058141a86f9e7df819a363aa0891b9d6ff7d40)

[#33](https://github.com/jht412ntu/PropertyTycoon/pull/33)   `24/04/2020`  Merge master to Zhenyu-Tang-215907 after pull request #32  
Creater: Haotian on behalf of Zhenyu  
Comment: None

[#34](https://github.com/jht412ntu/PropertyTycoon/pull/34)   `24/04/2020`  Hayden Banes 198757  
Creater: Hayden  
Comment:   
Added some GUI changes  
Fixed missing imports

[#35](https://github.com/jht412ntu/PropertyTycoon/pull/35)   `25/04/2020`  add csvreader ,add totalvalue in player  
Creater: Zhenyu  
Comment: most of board class can delete,because we can fetch data and auto import.

[#36](https://github.com/jht412ntu/PropertyTycoon/pull/36)   `25/04/2020`  Fixed some errors  
Creater: Hayden  
Comment:   
- Made javacsv.jar path relative  
- Added local static variables in CentralControl

[#37](https://github.com/jht412ntu/PropertyTycoon/pull/37)   `25/04/2020`  GitHub Page updated after 24/04/2020 online Seminar Support Session  
Creater: Haotian  
Comment:  
Meetings:  
Added more meeting records  
Audio and Video recordings are now available  

Sprints:  
Added more sprint records  

Pull Requests:  
Created a new page contained all of the detailed pull requests  

Summary:  
Changed the index and deleted the Timeline page  

[#38](https://github.com/jht412ntu/PropertyTycoon/pull/38)   `27/04/2020`  Updated GUI  
Creater: Hayden  
Comment:  
- Added game statistics monitoring (accessed from 'Stats' menu).
- Added 'View Properties' button on the game screen, allowing players to view info about and manage (upgrade and mortgage) thier currently owned properties.
- Added a method in Property class for setting the availability of the current property
- Documentation
- Fixed some errors

[#39](https://github.com/jht412ntu/PropertyTycoon/pull/39)   `29/04/2020`  auto find file with filename now  
Creater: Zhenyu  
Comment: class now take file name as input

[#40](https://github.com/jht412ntu/PropertyTycoon/pull/40)   `30/04/2020`  Mingfeng wang 215920  
Creater: Mingfeng  
Comment:  
Bugs about Player rollDices fixed.  
Some code logic optimisations.  


[#41](https://github.com/jht412ntu/PropertyTycoon/pull/41)   `30/04/2020`  Added getHousePrice  
Creater:  Haotian  
Comment:  Gets the corresponding house price of a property.

[#42](https://github.com/jht412ntu/PropertyTycoon/pull/42)   `30/04/2020`  Hayden banes 198757  
Creater: Hayden  
Comment:  
- Added check if new player is trying to use a token that has already been selected by another player
- Game now requires minimum of two players before starting
- Player must now have a name and a token before being added to the game
- Added exception handeling in the form of popup error/wraning dialogs in the GUI  

[#43](https://github.com/jht412ntu/PropertyTycoon/pull/43)   `04/05/2020`  Mingfeng wang 215920  
Creater: Mingfeng  
Comment: Closed  

[#44](https://github.com/jht412ntu/PropertyTycoon/pull/44)   `08/05/2020`  Mingfeng wang 215920  
Creater: Mingfeng  
Comment: Closed

[#45](https://github.com/jht412ntu/PropertyTycoon/pull/45)   `11/05/2020`  Code documentation for all the classes  
Creater: Haotian  
Comment: Documented all the classes and fixed some small bugs.

[#46](https://github.com/jht412ntu/PropertyTycoon/pull/46)   `12/05/2020`  Requirements testing  
Creater: Haotian  
Comment:  
Sprint 1 tests:  
TC4: Central Control  
TC4-F1: Determine who’s turn it is  

Sprint 2 tests:  
TC2: Buy Properties  
TC2-F1: Player shall be able to buy a property  
TC2-F3: Before a player buys a property, the amount of money the player shall be compared with property price in order to check whether the player has enough money  
TC2-F4: When a player has enough money to buy property, the system shall change the owner of the property.  
TC2-F5: The amount of money for the property shall be transferred from the player’s bank account to the bank.  

Sprint 3 tests:  
TC1: Auctioning  
TC1-F1: Before a player is allowed to make a bid, it shall be checked whether a player has completed one full circuit  
TC1-F2: It should be checked that the player’s bid is a valid number  

Sprint 5 tests:  
TC2: Re-sell properties  
TC2-F2: Bank shall transfer the original value of the property back to the player’s bank account  
TC2-F3: The attribute owner of the property shall be changed and the stored data shall be updated  
TC2-F5: A player shall only be able to re-sell its own properties.  

Sprint 6 tests:  
TC3: Trading mechanics  
TC3-F4: A player should be able to sell the property for more than the original value  
TC3-F5: When buying the property, the buyer shall transfer the money to the seller’s bank account  
TC3-F6: The owner of the property shall be changed  

[#47](https://github.com/jht412ntu/PropertyTycoon/pull/47)   `12/05/2020` Merge master to Mingfeng-Wang-215920 - trying to fix conflicts. 
Creater: Haotian  
Comment: Closed

[#48](https://github.com/jht412ntu/PropertyTycoon/pull/48)   `12/05/2020`   Test3  
Creater: Hayden  
Comment: Closed

[#49](https://github.com/jht412ntu/PropertyTycoon/pull/49)   `12/05/2020` Merge master to Mingfeng-Wang-215920 - Trying to fix conflicts.
Creater: Haotian  
Comment: Closed


[#50](https://github.com/jht412ntu/PropertyTycoon/pull/50)   `12/05/2020` Merging after conflicts fixed  
Creater: Haotian  
Comment: Conflicts fixed.
