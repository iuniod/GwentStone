package implementation.cards.hero;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;
import implementation.cards.Cards;

import java.util.ArrayList;


public abstract class Hero extends Cards {
  private static final int MAX_HP = 30;

  public Hero(final CardInput card) {
    super(card);
    setHealth(MAX_HP);
  }

  /**
   * Method that contains the hero's special ability. It is used on the entire row.
   */
  public abstract void ability(ArrayList<Cards> cards);

  /**
   * Method that returns the Hero card as a ObjectNode.
   *
   * @param objectMapper object mapper
   * @return the ObjectNode that contains the hero's details
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
    card.put("health", getHealth());

    return card;
  }
}
