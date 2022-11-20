package implementation.cards.minion;

import fileio.CardInput;
import implementation.cards.Cards;

import java.util.ArrayList;

/**
 * The interface Minion. This interface is used to implement the methods that are common to all the
 * minions (Goliath, Warden, Berserker, Sentinel).
 */
public abstract class Minion extends Cards {
  private static final ArrayList<String> SPECIALMINIONCARDS = new ArrayList<String>() {{
    add("The Ripper");
    add("Miraj");
    add("The Cursed One");
    add("Disciple");
  }};
  private static final ArrayList<String> TANKCARDS = new ArrayList<String>() {{
    add("Goliath");
    add("Warden");
  }};

  /**
   * Verify if the card is a special minion.
   * @param cardName the card name
   * @return true if the card is a special minion, false otherwise
   */
  public static boolean isSpecialMinionCard(final String cardName) {
    return SPECIALMINIONCARDS.contains(cardName);
  }

  /**
   * Verify if the card is a tank minion.
   * @param cardName the card name
   * @return true if the card is a tank minion, false otherwise
   */
  public static boolean isTankCard(final String cardName) {
    return TANKCARDS.contains(cardName);
  }

  private static ArrayList<Cards> getFrontRow(final ArrayList<ArrayList<Cards>> table,
                                              final int player) {
    return (player == 1 ? table.get(1) : table.get(2));
  }

  /**
   * Method that verifies if a player can attack opponent's card. The minion can attack if
   * the opponent's card is a tank minion or if there is no tank minion on the opponent's table.
   * @param table the table
   * @param cardName the attacked minion's name
   * @param playerIdx the player which is attacking
   * @return true if the player can attack, false otherwise
   */
  public static boolean checkTankAttacker(final ArrayList<ArrayList<Cards>> table,
                                          final String cardName, final int playerIdx) {
    if (Minion.isTankCard(cardName)) {
      return true;
    }
    ArrayList<Cards> frontRow = getFrontRow(table, playerIdx);
    for (Cards card : frontRow) {
      if (Minion.isTankCard(card.getName())) {
        return false;
      }
    }

    return true;
  }

  public Minion(final CardInput card) {
    super(card);
  }

  /**
   * Method that sets the row permission of the minion. The row permission is used to determine
   * where the minion can be placed on the table (rox 0 or 1).
   */
  public abstract int rowPermission();

  /**
   * Method that sets on which row the minion can be placed on the table.
   * @return the row where the minion can be placed
   */
  public int getRowPermission() {
    return rowPermission();
  }
}
