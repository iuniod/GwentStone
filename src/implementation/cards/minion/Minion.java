package implementation.cards.minion;

/**
 * The interface Minion. This interface is used to implement the methods that are common to all the
 * minions (Goliath, Warden, Berserker, Sentinel).
 */
public interface Minion {
  /**
   * Method that sets the row permission of the minion. The row permission is used to determine
   * where the minion can be placed on the table (rox 0 or 1).
   */
  void setRowPermission();
}
