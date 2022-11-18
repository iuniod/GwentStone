package implementation.cards.minion;

import fileio.CardInput;

public final class Warden extends Minion {
  public Warden(final CardInput card) {
    super(card);
  }

  @Override
  public int RowPermission() {
    return  1;
  }


}
