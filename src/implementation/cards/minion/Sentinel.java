package implementation.cards.minion;

import fileio.CardInput;

public final class Sentinel extends Minion {
  public Sentinel(final CardInput card) {
    super(card);
  }

  @Override
  public int rowPermission() {
    return 0;
  }
}
