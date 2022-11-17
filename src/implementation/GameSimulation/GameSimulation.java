package implementation.GameSimulation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import fileio.CardInput;
import implementation.cards.Cards;

/**
 * Class that simulates the game.
 */
public final class GameSimulation {
  private ArrayList<Cards> playerOneHand = new ArrayList<Cards>();
  private ArrayList<Cards> playerTwoHand = new ArrayList<Cards>();

  private ArrayList<ArrayList<Cards>> playerOneTable = null;
  private ArrayList<ArrayList<Cards>> playerTwoTable = null;
  private Cards playerOneHero;
  private Cards playerTwoHero;
  private int playerOneMana = 0;
  private int playerTwoMana = 0;
  private int playerTurn = 0;

  public GameSimulation(final ArrayList<CardInput> playerOneHand, final CardInput playerOneHero,
                        final ArrayList<CardInput> playerTwoHand, final CardInput playerTwoHero,
                        final int playerTurn) {

    for (CardInput itCard : playerOneHand) {
      this.playerOneHand.add(new Cards(itCard));
    }
    this.playerOneHero = new Cards(playerOneHero);

    for (CardInput itCard : playerTwoHand) {
      this.playerTwoHand.add(new Cards(itCard));
    }
    this.playerTwoHero = new Cards(playerTwoHero);

    this.playerTurn = playerTurn;
  }

  /**
   * Method that shuffles the hands of the players.
   * @param seed the seed used to shuffle the hands (in input file)
   */
  public void shuffleHands(final Random seed) {
    Collections.shuffle(playerOneHand, seed);
    Collections.shuffle(playerTwoHand, seed);
  }
}
