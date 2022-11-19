package implementation.cards.minion.specialMinion;

import implementation.cards.Cards;
import implementation.cards.minion.Minion;
import fileio.CardInput;

public final class Miraj extends Minion implements SpecialMinion {
  private int rowPermission;

  public Miraj(final CardInput card) {
    super(card);
  }

  /**
   * swap between his life and the life of an opposing minion.
   *
   * @param card the card that will be affected by the ability
   */
  @Override
  public void ability(final Cards card) {
    int tmp = getHealth();
    setHealth(card.getHealth());
    card.setHealth(tmp);
  }

  @Override
  public int RowPermission() {
    return  1;
  }
}
