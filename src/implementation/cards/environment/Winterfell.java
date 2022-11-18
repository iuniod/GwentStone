package implementation.cards.environment;

import fileio.CardInput;
import implementation.cards.Cards;

import java.util.ArrayList;

public class Winterfell extends Environment {

  public Winterfell(final CardInput card) {
    super(card);
  }

  /**
   * Freezes all the cards on the row.
   *
   * @param cards the list of cards on the row
   */
  @Override
  public void action(ArrayList<Cards> cards, ArrayList<Cards> opponentCards) {
    for (Cards card : opponentCards) {
      card.freeze();
    }
  }
}
