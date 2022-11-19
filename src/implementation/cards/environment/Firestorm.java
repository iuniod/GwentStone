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
    ArrayList<Integer> indexes = new ArrayList<Integer>();
    for (Cards card : opponentCards) {
      card.setHealth(card.getHealth() - 1);
      if (card.getHealth() <= 0) {
        indexes.add(opponentCards.indexOf(card));
      }
    }

    if (indexes.size() > 0) {
      for (int i = indexes.size() - 1; i >= 0; i--) {
        opponentCards.remove(indexes.get(i).intValue());
      }
    }
  }
}
