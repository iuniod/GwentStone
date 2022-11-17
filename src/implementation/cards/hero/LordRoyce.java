package implementation.cards.hero;

import fileio.CardInput;
import implementation.cards.Cards;
import java.util.ArrayList;

public final class LordRoyce extends Cards implements Hero {
  public LordRoyce(final CardInput card) {
    super(card);
  }

  /**
   * freezes the card with the highest attack on the row.
   * @param opponentCards the opponent's cards
   */
  @Override
  public void ability(final ArrayList<Cards> opponentCards) {
    int index = -1;
    int maxAttackDamage = -1;

    for (Cards card : opponentCards) {
      if (card.getAttackDamage() > maxAttackDamage) {
        maxAttackDamage = card.getAttackDamage();
        index = opponentCards.indexOf(card);
      }
    }

    if (index != -1) {
      opponentCards.get(index).freeze();
    }
  }
}
