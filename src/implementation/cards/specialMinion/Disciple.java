package implementation.cards.specialMinion;

import fileio.CardInput;
import implementation.cards.Cards;
import implementation.cards.minion.Minion;

public final class Disciple extends Cards implements Minion, SpecialMinion {
  private int rowPermission;

  public Disciple(final CardInput card) {
    super(card);
  }

  /**
   * +2 life for a minion in his camp.
   * @param card the card that will be affected by the ability
   */
  @Override
  public void ability(final Cards card) {
    card.setHealth(card.getHealth() + 2);
  }

  @Override
  public void setRowPermission() {
    rowPermission = 1;
  }
}
