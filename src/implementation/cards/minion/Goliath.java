package implementation.cards.minion;

import fileio.CardInput;

public final class Goliath extends Minion {
  public Goliath(final CardInput card) {
    super(card);
  }

  @Override
  public int rowPermission() {
    return 1;
  }
}
