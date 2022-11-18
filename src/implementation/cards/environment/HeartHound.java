package implementation.cards.environment;

import fileio.CardInput;
import implementation.cards.Cards;

import java.util.ArrayList;

public class HeartHound extends Environment {

  public HeartHound(final CardInput card) {
    super(card);
  }

  /**
   * The opponent's minion with the highest life on the row is stolen
   * and placed on the player's "mirrored" row.
   *
   * @param cards the list of cards on the row
   */
  @Override
  public void action(final ArrayList<Cards> cards, final ArrayList<Cards> opponentCards) {
    int maxHealth = -1;
    int index = -1;
    for (Cards card : opponentCards) {
      if (card.getHealth() > maxHealth) {
        maxHealth = card.getHealth();
        index = opponentCards.indexOf(card);
      }
    }

    if (index != -1) {
      Cards stolenCard = opponentCards.get(index);
      opponentCards.remove(index);
      cards.add(stolenCard);
    }
  }
}
