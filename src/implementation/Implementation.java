package implementation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import fileio.ActionsInput;
import fileio.GameInput;
import fileio.Input;
import implementation.GameSimulation.GameSimulation;
import implementation.commands.*;
import java.util.*;

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
        switch (itAction.getCommand()) {
          case "getPlayerDeck":
            GetPlayerDeck getPlayerDeck = new GetPlayerDeck(itAction.getCommand(),
                itAction.getPlayerIdx());
            getPlayerDeck.run(game, objectMapper, output);
            break;
          case "getPlayerHero":
            GetPlayerHero getPlayerHero = new GetPlayerHero(itAction.getCommand(),
                itAction.getPlayerIdx());
            getPlayerHero.run(game, objectMapper, output);
            break;
          case "endPlayerTurn":
            EndPlayerTurn endPlayerTurn = new EndPlayerTurn(itAction.getCommand());
            endPlayerTurn.run(game, objectMapper, output);
            break;
          case "getPlayerTurn":
            GetPlayerTurn getPlayerTurn = new GetPlayerTurn(itAction.getCommand());
            getPlayerTurn.run(game, objectMapper, output);
            break;
          case "getPlayerMana":
            GetPlayerMana getPlayerMana = new GetPlayerMana(itAction.getCommand(),
                itAction.getPlayerIdx());
            getPlayerMana.run(game, objectMapper, output);
            break;
          case "getTotalGamesPlayed":
            GetTotalGamesPlayed getGamesPlayed = new GetTotalGamesPlayed(itAction.getCommand());
            getGamesPlayed.run(game, objectMapper, output);
            break;
          case "getPlayerOneWins":
            GetPlayerOneWins getPlayerOneWins = new GetPlayerOneWins(itAction.getCommand());
            getPlayerOneWins.run(game, objectMapper, output);
            break;
          case "getPlayerTwoWins":
            GetPlayerTwoWins getPlayerTwoWins = new GetPlayerTwoWins(itAction.getCommand());
            getPlayerTwoWins.run(game, objectMapper, output);
            break;
          case "placeCard":
            PlaceCard placeCard = new PlaceCard(itAction.getCommand(), itAction.getHandIdx());
            placeCard.run(game, objectMapper, output);
            break;
          case "getCardsInHand":
            GetCardsInHand getCardsInHand = new GetCardsInHand(itAction.getCommand(),
                itAction.getPlayerIdx());
            getCardsInHand.run(game, objectMapper, output);
            break;
          case "getCardsOnTable":
            GetCardsOnTable getCardsOnTable = new GetCardsOnTable(itAction.getCommand());
            getCardsOnTable.run(game, objectMapper, output);
            break;
          case "getEnvironmentCardsInHand":
            GetEnvironmentCardsInHand getEnvironmentCardsInHand = new GetEnvironmentCardsInHand(
                itAction.getCommand(), itAction.getPlayerIdx());
            getEnvironmentCardsInHand.run(game, objectMapper, output);
            break;
          case "useEnvironmentCard":
            UseEnvironmentCard useEnvironmentCard = new UseEnvironmentCard(itAction.getCommand(),
                itAction.getHandIdx(), itAction.getAffectedRow());
            useEnvironmentCard.run(game, objectMapper, output);
            break;
          case "getCardAtPosition":
            GetCardAtPosition getCardAtPosition = new GetCardAtPosition(itAction.getCommand(),
                itAction.getX(), itAction.getY());
            getCardAtPosition.run(game, objectMapper, output);
            break;
          case "getFrozenCardsOnTable":
            GetFrozenCardsOnTable getFrozenCardsOnTable = new GetFrozenCardsOnTable(
                itAction.getCommand());
            getFrozenCardsOnTable.run(game, objectMapper, output);
            break;
          default:
            break;
        }
      }
    }
  }
}
