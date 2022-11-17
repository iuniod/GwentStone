package implementation.cards.minion;

import fileio.CardInput;
import implementation.cards.Cards;

public final class Warden extends Cards implements Minion {
  private int rowPermission;

  public Warden(final CardInput card) {
    super(card);
  }

  @Override
  public void setRowPermission() {
    rowPermission = 0;
  }
}
