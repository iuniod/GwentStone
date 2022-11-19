package implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.GameInput;
import fileio.Input;
import implementation.GameSimulation.GameSimulation;
import implementation.commands.*;
import implementation.commands.debugging.*;

public final class Implementation {
  /**
   * The entry point to the implementation. It is called by the checker.
   *
   * @param inputData the input data
   * @param output    the output data
   */
  public void run(final Input inputData, final ArrayNode output, final ObjectMapper objectMapper) {
    /**
     * Iterate through the rounds and play them. The output is written in the output parameter.
     */
    GameSimulation.setPlayerOneWins();
    GameSimulation.setPlayerTwoWins();

    for (GameInput itGame : inputData.getGames()) {
      /**
       * Create a new game simulation. The game simulation will be used to simulate the game.
       */
      GameSimulation game = new GameSimulation(
          inputData.getPlayerOneDecks().getDecks().get(itGame.getStartGame().getPlayerOneDeckIdx()),
          itGame.getStartGame().getPlayerOneHero(),
          inputData.getPlayerTwoDecks().getDecks().get(itGame.getStartGame().getPlayerTwoDeckIdx()),
          itGame.getStartGame().getPlayerTwoHero(),
          itGame.getStartGame().getStartingPlayer(),
          itGame.getStartGame().getShuffleSeed()
      );

      /**
       * Iterate through the actions and play them.
       */
      for (ActionsInput itAction : itGame.getActions()) {
        Command command = null;
        switch (itAction.getCommand()) {
          case "getPlayerDeck":
            command = new GetPlayerDeck(itAction.getCommand(),
                itAction.getPlayerIdx());
            break;
          case "getPlayerHero":
            command = new GetPlayerHero(itAction.getCommand(), itAction.getPlayerIdx());
            break;
          case "endPlayerTurn":
            command = new EndPlayerTurn(itAction.getCommand());
            break;
          case "getPlayerTurn":
            command = new GetPlayerTurn(itAction.getCommand());
            break;
          case "getPlayerMana":
            command = new GetPlayerMana(itAction.getCommand(), itAction.getPlayerIdx());
            break;
          case "getTotalGamesPlayed":
            command = new GetTotalGamesPlayed(itAction.getCommand());
            break;
          case "getPlayerOneWins":
            command = new GetPlayerOneWins(itAction.getCommand());
            break;
          case "getPlayerTwoWins":
            command = new GetPlayerTwoWins(itAction.getCommand());
            break;
          case "placeCard":
            command = new PlaceCard(itAction.getCommand(), itAction.getHandIdx());
            break;
          case "getCardsInHand":
            command = new GetCardsInHand(itAction.getCommand(), itAction.getPlayerIdx());
            break;
          case "getCardsOnTable":
            command = new GetCardsOnTable(itAction.getCommand());
            break;
          case "getEnvironmentCardsInHand":
            command = new GetEnvironmentCardsInHand(itAction.getCommand(), itAction.getPlayerIdx());
            break;
          case "useEnvironmentCard":
            command = new UseEnvironmentCard(itAction.getCommand(), itAction.getHandIdx(),
                itAction.getAffectedRow());
            break;
          case "getCardAtPosition":
            command = new GetCardAtPosition(itAction.getCommand(),
                itAction.getX(), itAction.getY());
            break;
          case "getFrozenCardsOnTable":
            command = new GetFrozenCardsOnTable(itAction.getCommand());
            break;
          case "cardUsesAttack":
            command = new CardUsesAttack(itAction.getCommand(),
                itAction.getCardAttacker().getX(), itAction.getCardAttacker().getY(),
                itAction.getCardAttacked().getX(), itAction.getCardAttacked().getY());
            break;
          case "cardUsesAbility":
            command = new CardUsesAbility(itAction.getCommand(),
                itAction.getCardAttacker().getX(), itAction.getCardAttacker().getY(),
                itAction.getCardAttacked().getX(), itAction.getCardAttacked().getY());
            break;
          case "useAttackHero":
            command = new UseAttackHero(itAction.getCommand(),
                itAction.getCardAttacker().getX(), itAction.getCardAttacker().getY());
            break;
          case "useHeroAbility":
            command = new UseHeroAbility(itAction.getCommand(), itAction.getAffectedRow());
            break;
          default:
            break;
        }
        command.run(game, objectMapper, output);
      }
    }
  }
}
