package implementation.cards.minion;

import fileio.CardInput;
import implementation.cards.Cards;

public final class Goliath extends Cards implements Minion {
  private int rowPermission;

  public Goliath(final CardInput card) {
    super(card);
  }

  @Override
  public void setRowPermission() {
    rowPermission = 0;
  }
}
