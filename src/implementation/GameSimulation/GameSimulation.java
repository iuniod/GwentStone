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
  List<ArrayList<Cards>> table = null;
  private int playerTurn = 0;
  private int round = 0;
  private int turn = 0;

  public GameSimulation(final ArrayList<CardInput> playerOneHand, final CardInput playerOneHero,
                        final ArrayList<CardInput> playerTwoHand, final CardInput playerTwoHero,
                        final int playerTurn, final int seed) {
    playerOne = new Player(playerOneHand, playerOneHero, seed, 1);
    playerTwo = new Player(playerTwoHand, playerTwoHero, seed, 2);
    table = new ArrayList<>();
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
    return player == 1 ? playerOne : playerTwo;
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

  public List<ArrayList<Cards>> getTable() {
    table.add(new ArrayList<>(playerTwo.getPlayerTable().get(0)));
    table.add(new ArrayList<>(playerTwo.getPlayerTable().get(1)));
    table.add(new ArrayList<>(playerOne.getPlayerTable().get(1)));
    table.add(new ArrayList<>(playerOne.getPlayerTable().get(0)));

    return table;
  }

  public List<ArrayList<Cards>> getPlayerTable(final int player) {
    if (player == 1) {
      return playerOne.getPlayerTable();
    } else {
      return playerTwo.getPlayerTable();
    }
  }

  public int getTotalGamesPlayed() {
    return playerOne.getPlayerWins() + playerTwo.getPlayerWins();
  }
}
