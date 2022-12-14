package implementation.cards.minion.specialMinion;

import implementation.cards.Cards;

/**
 * Interface that contains the special minion's ability. (TheRipper, Miraj, Disciple, TheCursedOne)
 */
public interface SpecialMinion {
  /**
   * Method that run the special minion's ability.
   *
   * @param card the card that will be affected by the ability
   */
  void ability(Cards card);
}
