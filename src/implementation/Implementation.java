package implementation;

import com.fasterxml.jackson.databind.node.ArrayNode;
import java.util.Random;
import fileio.Input;
import fileio.GameInput;
import implementation.GameSimulation.GameSimulation;

public final class Implementation {
  /**
   * The entry point to the implementation. It is called by the checker.
   * @param inputData the input data
   * @param output the output data
   */
  public void run(final Input inputData, final ArrayNode output) {
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
          itGame.getStartGame().getStartingPlayer()
      );

      game.shuffleHands(new Random(itGame.getStartGame().getShuffleSeed()));
    }
  }
}
