package implementation.GameSimulation;

import java.util.*;

import fileio.CardInput;
import implementation.cards.Cards;

/**
 * Class that simulates the game.
 */
public final class GameSimulation {
  private Player playerOne = null;
  private Player playerTwo = null;
  private static int playerOneWins = 0;
  private static int playerTwoWins = 0;
  private ArrayList<ArrayList<Cards>> table = null;
  private int playerTurn = 0;
  private int round = 0;
  private int turn = 0;

  public GameSimulation(final ArrayList<CardInput> playerOneHand, final CardInput playerOneHero,
                        final ArrayList<CardInput> playerTwoHand, final CardInput playerTwoHero,
                        final int playerTurn, final int seed) {
    playerOne = new Player(playerOneHand, playerOneHero, seed, 1);
    playerTwo = new Player(playerTwoHand, playerTwoHero, seed, 2);
    table = new ArrayList<>();
    for (int i = 1; i < Player.MAX_ROW_SIZE; i++) {
      table.add(new ArrayList<>());
    }
    this.playerTurn = playerTurn;
    round = 1;
    turn = 0;
    playerOne.setPlayerMana(1);
    playerTwo.setPlayerMana(1);
  }

  /**
   * Method that returns the player according to the index.
   *
   * @param player The index of the player.
   * @return The player.
   */
  public Player getPlayer(final int player) {
    return player == 2 ? playerTwo : playerOne;
  }

  public int getPlayerTurn() {
    return playerTurn;
  }

  /**
   * Method that change the player turn.
   */
  public void setPlayerTurn() {
    playerTurn = (playerTurn == 1 ? 2 : 1);
  }

  public int getTurn() {
    return turn;
  }

  public void setTurn(final int turn) {
    this.turn = turn;
  }

  public int getRound() {
    return round;
  }

  /**
   * Increases the round by one.
   */
  public void addRound() {
    round++;
  }

  public ArrayList<ArrayList<Cards>> getTable() {
    return table;
  }

  public int getTotalGamesPlayed() {
    return playerOneWins + playerTwoWins;
  }

  /**
   * Method that returns the number of wins for the first player.
   *
   * @return The number of wins for the first player.
   */
  public int getPlayerOneWins() {
    return playerOneWins;
  }

  /**
   * Method that returns the number of wins of the second player.
   *
   * @return The number of wins of the second player.
   */
  public int getPlayerTwoWins() {
    return playerTwoWins;
  }

  /**
   * Method that increases the number of wins of player one.
   */
  public void addPlayerOneWins() {
    playerOneWins++;
  }

  /**
   * Method that increases the number of wins of player two.
   */
  public void addPlayerTwoWins() {
    playerTwoWins++;
  }

  /**
   * Method that sets the playerOneWins to 0 at the beginning of games.
   */
  public static void setPlayerOneWins() {
    playerOneWins = 0;
  }

  /**
   * Method that sets the playerTwoWins to 0 at the beginning of games.
   */
  public static void setPlayerTwoWins() {
    playerTwoWins = 0;
  }
}
