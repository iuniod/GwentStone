package implementation.cards.minion.specialMinion;

import fileio.CardInput;
import implementation.cards.Cards;
import implementation.cards.minion.Minion;

public final class Disciple extends Minion implements SpecialMinion {
  private int rowPermission;

  public Disciple(final CardInput card) {
    super(card);
  }

  @Override
  public int RowPermission() {
    return 0;
  }

  /**
   * +2 life for a minion in his camp.
   *
   * @param card the card that will be affected by the ability
   */
  @Override
  public void ability(final Cards card) {
    card.setHealth(card.getHealth() + 2);
  }
}
