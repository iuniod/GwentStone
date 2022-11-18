package implementation.cards.environment;

import fileio.CardInput;
import implementation.cards.Cards;

import java.util.ArrayList;

public class Firestorm extends Environment {

  public Firestorm(final CardInput card) {
    super(card);
  }

  /**
   * Decreases the health of all cards on the row by 1.
   *
   * @param cards the list of cards on the row
   */
  @Override
  public void action(final ArrayList<Cards> cards, final ArrayList<Cards> opponentCards) {
    for (Cards card : opponentCards) {
      card.setHealth(card.getHealth() - 1);
    }
  }
}
