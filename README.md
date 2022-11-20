

# GwentStone

---

#### Assignment Link: [https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/tema](https://ocw.cs.pub.ro/courses/poo-ca-cd/teme/tema) (in romanian)

---

## Skel Structure

* src/
  * checker/ - checker files
  * fileio/ - contains classes used to read data from the json files
  * main/
      * Main - the Main class runs the checker on your implementation. Add the entry point to your implementation in it. Run Main to test your implementation from the IDE or from command line.
      * Test - run the main method from Test class with the name of the input file from the command line and the result will be written
        to the out.txt file. Thus, you can compare this result with ref.
  *  implementation/
      * Implementation - entry point to my implementation of the game
      * GameSimulation/
          * GameSimulation - class that stores players data, cards on table and game statistics such as number of each player's wins
          * Player - class that stores one player's data (deck, hand, mana, player number)
      * cards/ 
          * Cards - basic card object that has all the necessary data about a card
          * minion/ , environment/ hero/ - contains classes that represent each card with their abilities
      * commands/
          * Command - class used for running each particular command (stores command's parameters and its error message if it has error message, otherwise null)
          * CardUsesAbility, PlaceCard, UseEnvironmentCard, CardUsesAttack, EndPlayerTurn, UseAttackHero, UseHeroAbility - commands' classes
          * debugging/ - classes for debugging commands
* input/ - contains the tests in JSON format
* ref/ - contains all reference output for the tests in JSON format

---

## Implementation info
I created an Implementation class that helps with the game's logic.

It has a GameSimulation object that stores all the data about the game (players, cards on table, game statistics) that is processed from the input file in fileio package.

It also has a method that runs the game, which is called from the Main class.

The game is run in a while loop that runs until the game is over. In each iteration of the loop, the game runs one command from the list of commands according to the command's type and parameters.

Each command has a specific class that extends the Command abstract class and implements the run method. The run method is called in the loop, and it processes the command's logic.

For example, the PlaceCard command places a card on the table, and it has a run method that checks if the card can be placed on the table, and if it can, it places it on the table and removes it from the player's hand.

For storing a card's data, I created a Cards class that has all the necessary data about a card. It is extended by the minion, environment and hero abstract classes that represent each card type. Each card type has its own class that extends the minion, environment or hero class and implements the card's ability (each abstract class has an abstract method that is implemented in the child classes).

---

## How to run the game

1. Open the project in IntelliJ IDEA
2. Run the Main class
4. The output will be written to the result package
5. To run the game with a specific input file, run the Test class with the name of the input file as a parameter

---

## Tests

1. test01_game_start - 3p
2. test02_place_card - 4p
3. test03_place_card_invalid - 4p
4. test04_use_env_card - 4p
5. test05_use_env_card_invalid - 4p
6. test06_attack_card - 4p
7. test07_attack_card_invalid - 4p
8. test08_use_card_ability - 4p
9. test09_use_card_ability_invalid -4p
10. test10_attack_hero - 4p
11. test11_attack_hero_invalid - 4p
12. test12_use_hero_ability_1 - 4p
13. test13_use_hero_ability_2 - 4p
14. test14_use_hero_ability_1_invalid - 4p
15. test15_use_hero_ability_2_invalid - 4p
16. test16_multiple_games_valid - 5p
17. test17_multiple_games_invalid - 6p
18. test18_big_game - 10p