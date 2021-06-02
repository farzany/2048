# 2048
This was the final project for McMaster University's 2AA4 course of Winter 2021.

**The report/documentation is titled "spec" in Design Specifcations.**

**Project Grade: 146.88%**

**Course Grade: 102%**

We were instructed to professionally design and document the game 2048. Bonus points were awarded if we designed it to be playable in the terminal (which I have done). This project was done completely with Java. The images within the documentation were created by me using varying software.

The design portion of this project was completely up to us. The algorithms used are mathematically outlined within the MIS portion of the report. Assumptions and design decisions are also briefly discussed throughout the report. 

- The GameT module (the actual game runner) is specified as an Abstract Data Type rather than an Abstract Object – this was done to provide convenience when starting a new game with an empty board.
- The Controller and Display modules are specified as single Abstract Objects as these modules are shared resources and only one instance is required to control the actions during runtime.
- The setBoard() method was included strictly for testing purposed – it is therefore largely assumed that the developer using it will provide correct inputs. My design has put an emphasis on consistency which has been discussed on page 15 of the report.
- With essentiality in mind, the MIS omits unnecessary access programs which are not used to produce the flow of the 2048 game.
- The algorithms used to transform the board tiles were created with generality in mind – the algorithms transform sequences (Arrays) and do not focus on any unique “board”.
- By definition, my design is not minimal as many different variables are to be set at every stage of a move – such as updating the score within the transform algorithms.

This design implements high cohesion and low coupling. This means that the components of the module are closely related this is expressed by the MVC design pattern that was incorporated. Information hiding is respected within my MIS design as things that are likely to change are hidden. Comprehensive unit testing was done for the GameT module while the other modules were indirectly yet thoroughly manually tested using the controller.
