package implementation.cards.minion.specialMinion;

import fileio.CardInput;
import implementation.cards.Cards;
import implementation.cards.minion.Minion;

public final class TheRipper extends Minion implements SpecialMinion {
  public TheRipper(final CardInput card) {
    super(card);
  }

  @Override
  public int rowPermission() {
    return 1;
  }

  /**
   * -2 attack for an opposing minion.
   *
   * @param card the card that will be affected by the ability
   */
  @Override
  public void ability(final Cards card) {
    card.setAttackDamage(card.getAttackDamage() - 2);
    if (card.getAttackDamage() < 0) {
      card.setAttackDamage(0);
    }
  }
}
