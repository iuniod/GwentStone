package implementation.cards.minion;

import fileio.CardInput;
import implementation.cards.Cards;

/**
 * The interface Minion. This interface is used to implement the methods that are common to all the
 * minions (Goliath, Warden, Berserker, Sentinel).
 */
public abstract class Minion extends Cards {
  private int rowPermission;
  public Minion(final CardInput card) {
    super(card);
  }

  /**
   * Method that sets the row permission of the minion. The row permission is used to determine
   * where the minion can be placed on the table (rox 0 or 1).
   */
  public abstract int RowPermission();

  public int getRowPermission() {
    return RowPermission();
  }
}
