package implementation.cards.specialMinion;

import fileio.CardInput;
import implementation.cards.Cards;
import implementation.cards.minion.Minion;

public final class TheRipper extends Minion implements SpecialMinion {
  private int rowPermission;

  public TheRipper(final CardInput card) {
    super(card);
  }

  /**
   * -2 attack for an opposing minion.
   *
   * @param card the card that will be affected by the ability
   */
  @Override
  public void ability(final Cards card) {
    card.setAttackDamage(card.getAttackDamage() - 2);
  }

  @Override
  public int RowPermission() {
    return 1;
  }
}
