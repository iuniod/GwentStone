package implementation.cards.hero;

import implementation.cards.Cards;
import fileio.CardInput;

import java.util.ArrayList;

public class EmpressThorina extends Hero {
  public EmpressThorina(final CardInput cardInput) {
    super(cardInput);
  }

  /**
   * destroys the card with the highest life in a row.
   *
   * @param opponentCards the opponent's cards
   */
  @Override
  public void ability(final ArrayList<Cards> opponentCards) {
    int index = -1;
    int maxHealth = -1;

    for (Cards card : opponentCards) {
      if (card.getHealth() > maxHealth) {
        maxHealth = card.getHealth();
        index = opponentCards.indexOf(card);
      }
    }

    if (index != -1) {
      opponentCards.remove(index);
    }
  }
}
