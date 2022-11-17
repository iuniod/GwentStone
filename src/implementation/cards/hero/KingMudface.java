package implementation.cards.hero;

import implementation.cards.Cards;
import fileio.CardInput;
import java.util.ArrayList;

public class KingMudface extends Cards implements Hero {
  public KingMudface(final CardInput card) {
    super(card);
  }

  /**
   * +1 life for all cards on the row.
   * @param cards the cards on the row
   */
  @Override
  public void ability(final ArrayList<Cards> cards) {
    for (Cards card : cards) {
      card.setHealth(card.getHealth() + 1);
    }
  }
}
