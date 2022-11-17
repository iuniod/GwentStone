package implementation.cards.minion;

import fileio.CardInput;
import implementation.cards.Cards;

public final class Berserker extends Cards implements Minion {
  private int rowPermission;

  public Berserker(final CardInput card) {
    super(card);
  }

  @Override
  public void setRowPermission() {
    rowPermission = 1;
  }
}
