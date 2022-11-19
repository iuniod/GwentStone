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
  ArrayList<ArrayList<Cards>> table = null;
  private int playerTurn = 0;
  private int round = 0;
  private int turn = 0;

  public GameSimulation(final ArrayList<CardInput> playerOneHand, final CardInput playerOneHero,
                        final ArrayList<CardInput> playerTwoHand, final CardInput playerTwoHero,
                        final int playerTurn, final int seed) {
    playerOne = new Player(playerOneHand, playerOneHero, seed, 1);
    playerTwo = new Player(playerTwoHand, playerTwoHero, seed, 2);
    table = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      table.add(new ArrayList<>());
    }
    this.playerTurn = playerTurn;
    this.round = 1;
    this.turn = 0;
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

  public void setPlayerTurn() {
    if (playerTurn == 1) {
      playerTurn = 2;
    } else {
      playerTurn = 1;
    }
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

  public void addRound() {
    round++;
  }

  public ArrayList<ArrayList<Cards>> getTable() {
    return table;
  }

  public int getTotalGamesPlayed() {
    return playerOneWins + playerTwoWins;
  }

  public int getPlayerOneWins() {
    return playerOneWins;
  }

  public int getPlayerTwoWins() {
    return playerTwoWins;
  }

  public void addPlayerOneWins() {
    playerOneWins++;
  }

  public void addPlayerTwoWins() {
    playerTwoWins++;
  }

  public static void setPlayerOneWins() {
    playerOneWins = 0;
  }

  public static void setPlayerTwoWins() {
    playerTwoWins = 0;
  }
}
