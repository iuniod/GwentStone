package implementation.cards.environment;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;
import implementation.cards.Cards;

import java.util.ArrayList;

public abstract class Environment extends Cards {
  private static final ArrayList<String> ENVIRONMENT_CARDS = new ArrayList<String>() {{
    add("Firestorm");
    add("Winterfell");
    add("Heart Hound");
  }};

  /**
   * Verifies if the card is an environment card.
   * @param cardName the name of the card
   * @return true if the card is an environment card, false otherwise
   */
  public static boolean isEnvironmentCard(final String cardName) {
    return ENVIRONMENT_CARDS.contains(cardName);
  }

  public Environment(final CardInput card) {
    super(card);
  }

  /**
   * Method that run the special ability of the card.
   *
   * @param cards         the list of player's cards
   * @param opponentCards the list of opponent's cards
   */
  public abstract void action(ArrayList<Cards> cards, ArrayList<Cards> opponentCards);

  /**
   * Method that returns the Environment card as a ObjectNode.
   *
   * @param objectMapper object mapper
   * @return the ObjectNode that contains the card's details
   */
  public ObjectNode writeToFile(final ObjectMapper objectMapper) {
    ObjectNode card = objectMapper.createObjectNode();
    ArrayNode colors = objectMapper.createArrayNode();

    card.put("mana", getMana());
    card.put("description", getDescription());

    for (String color : getColors()) {
      colors.add(color);
    }
    card.put("colors", colors);
    card.put("name", getName());

    return card;
  }
}
