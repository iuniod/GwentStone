package implementation.cards;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import fileio.CardInput;

import java.util.ArrayList;

public class Cards {
  private int mana;
  private int health;
  private int attackDamage;
  private String name;
  private String description;
  private ArrayList<String> colors;
  private boolean isFrozen;
  private boolean hasAttacked;

  public Cards(final CardInput card) {
    mana = card.getMana();
    health = card.getHealth();
    attackDamage = card.getAttackDamage();
    name = card.getName();
    description = card.getDescription();
    colors = card.getColors();
    isFrozen = false;
    hasAttacked = false;
  }

  public Cards(final Cards card) {
    mana = card.getMana();
    health = card.getHealth();
    attackDamage = card.getAttackDamage();
    name = card.getName();
    description = card.getDescription();
    colors = card.getColors();
    isFrozen = card.getIsFrozen();
    hasAttacked = card.getHasAttacked();
  }

  /**
   * Method that returns the mana cost of the card.
   *
   * @return the mana cost of the card
   */
  public int getMana() {
    return mana;
  }

  /**
   * Method that sets the mana cost of the card.
   *
   * @param mana the new mana cost of the card
   */
  public void setMana(final int mana) {
    this.mana = mana;
  }

  /**
   * Method that returns the health of the card.
   *
   * @return the health of the card
   */
  public int getHealth() {
    return health;
  }

  /**
   * Method that sets the health of the card.
   *
   * @param health the new health of the card
   */
  public void setHealth(final int health) {
    this.health = health;
  }

  /**
   * Method that returns the attackDamage of the card.
   *
   * @return the attack attackDamage of the card
   */
  public int getAttackDamage() {
    return attackDamage;
  }

  /**
   * Method that sets the attackDamage of the card.
   *
   * @param attackDamage the new attackDamage of the card
   */
  public void setAttackDamage(final int attackDamage) {
    this.attackDamage = attackDamage;
  }

  /**
   * Method that returns the name of the card.
   *
   * @return the name of the card
   */
  public String getName() {
    return name;
  }

  /**
   * Method that sets the name of the card.
   *
   * @param name the new name of the card
   */
  public void setName(final String name) {
    this.name = name;
  }

  /**
   * Method that returns the description of the card.
   *
   * @return the description of the card
   */
  public String getDescription() {
    return description;
  }

  /**
   * Method that sets the description of the card.
   *
   * @param description the new description of the card
   */
  public void setDescription(final String description) {
    this.description = description;
  }

  /**
   * Method that returns the colors of the card.
   *
   * @return the colors of the card
   */
  public ArrayList<String> getColors() {
    return colors;
  }

  /**
   * Method that sets the colors of the card.
   *
   * @param colors the new colors of the card
   */
  public void setColors(final ArrayList<String> colors) {
    this.colors = colors;
  }

  /**
   * Method that returns the frozen status of the card.
   *
   * @return the frozen status of the card
   */
  public boolean getIsFrozen() {
    return isFrozen;
  }

  /**
   * Method that freezes the card.
   */
  public void freeze() {
    isFrozen = true;
  }

  /**
   * Method that unfreezes the card.
   */
  public void unfreeze() {
    isFrozen = false;
  }

  /**
   * Method that returns the attacked status of the card.
   *
   * @return the attacked status of the card
   */
  public boolean getHasAttacked() {
    return hasAttacked;
  }

  /**
   * Method that sets the hasAttacked status of the card as true, meaning the card has attacked
   */
  public void setAttacked() {
    hasAttacked = true;
  }

  /**
   * Method that reset the hasAttacked status of the card.
   * This method is called at the end of each turn to reset the hasAttacked status of all the cards.
   */
  public void resetHasAttacked() {
    hasAttacked = false;
  }

  /**
   * Method that returns the card as a ObjectNode.
   *
   * @param objectMapper the object mapper
   * @return the card as a ObjectNode
   */
  public ObjectNode writeToFile(final ObjectMapper objectMapper) {
    ObjectNode card = objectMapper.createObjectNode();
    ArrayNode colorArray = objectMapper.createArrayNode();

    card.put("mana", getMana());
    card.put("attackDamage", getAttackDamage());
    card.put("health", getHealth());
    card.put("description", getDescription());

    for (String color : getColors()) {
      colorArray.add(color);
    }
    card.put("colors", colorArray);
    card.put("name", getName());

    return card;
  }
}
