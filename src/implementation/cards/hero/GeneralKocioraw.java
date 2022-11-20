package implementation.cards.hero;

import implementation.cards.Cards;
import fileio.CardInput;

import java.util.ArrayList;

public class GeneralKocioraw extends Hero {
  public GeneralKocioraw(final CardInput card) {
    super(card);
  }

  /**
   * +1 attack for all cards on the row.
   *
   * @param cards the cards on the row
   */
  @Override
  public void ability(final ArrayList<Cards> cards) {
    for (Cards card : cards) {
      card.setAttackDamage(card.getAttackDamage() + 1);
    }
  }
}
