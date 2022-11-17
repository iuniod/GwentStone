package implementation.cards.minion;

import fileio.CardInput;
import implementation.cards.Cards;

public final class Sentinel extends Cards implements Minion {
  private int rowPermission;

  public Sentinel(final CardInput card) {
    super(card);
  }

  @Override
  public void setRowPermission() {
    rowPermission = 1;
  }
}
