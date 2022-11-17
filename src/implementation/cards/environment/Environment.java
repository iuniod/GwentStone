package implementation.cards.environment;

import implementation.cards.Cards;

import java.util.ArrayList;

/**
 * Interface that contains the environment's ability.
 */
public interface Environment {
  /**
   * Method that contains the environment's ability.
   * @param cards the list of cards on the row
   * @param opponentCards the list of cards on the opponent's row
   * !!! We use only opoponentCards for Firestorm and Winterfell because
   *                      we modify the opponent's cards, but we also use cards for HeartHound
   *                      because we take the card from the opponent's row and put it on our row.
   */
  void action(ArrayList<Cards> cards, ArrayList<Cards> opponentCards);
}
