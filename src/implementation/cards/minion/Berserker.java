package implementation.cards.minion;

import fileio.CardInput;

public final class Berserker extends Minion {
  public Berserker(final CardInput card) {
    super(card);
  }

  @Override
  public int RowPermission() {
    return 0;
  }
}
