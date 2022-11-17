package implementation.cards.specialMinion;

import implementation.cards.minion.Minion;
import implementation.cards.Cards;
import fileio.CardInput;

public final class TheCursedOne extends Cards implements Minion, SpecialMinion {
  private int rowPermission;

  public TheCursedOne(final CardInput card) {
    super(card);
  }

  /**
   * swap between the life of an opposing minion and the same minion's attack.
   * @param card the card that will be affected by the ability
   */
  @Override
  public void ability(final Cards card) {
    int tmp = card.getHealth();
    card.setHealth(card.getAttackDamage());
    card.setAttackDamage(tmp);
  }

  @Override
  public void setRowPermission() {
    rowPermission = 1;
  }
}
