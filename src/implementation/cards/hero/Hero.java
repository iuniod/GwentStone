package implementation.cards.hero;

import implementation.cards.Cards;

import java.util.ArrayList;

/**
 * Interface that represents a hero card.
 */
public interface Hero {
  /**
   * Method that contains the hero's special ability. It is used on the entire row.
   */
  void ability(ArrayList<Cards> cards);
}
