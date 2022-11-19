package implementation.GameSimulation;

import fileio.CardInput;
import implementation.cards.Cards;
import implementation.cards.environment.*;
import implementation.cards.hero.*;
import implementation.cards.minion.*;
import implementation.cards.minion.specialMinion.*;

import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

public class Player {
  public final static int MAX_ROW_SIZE = 5;
  private int playerNumber = 0;
  private ArrayList<Cards> playerHand = new ArrayList<Cards>();
  private ArrayList<Cards> playerDeck = new ArrayList<Cards>();

  private Cards playerHero = null;
  private int playerMana = 0;

  public Player(final ArrayList<CardInput> deck, final CardInput hero,
                final int seed, final int playerNumber) {
    this.playerNumber = playerNumber;
    for (CardInput itCard : deck) {
      switch (itCard.getName()) {
        case "Sentinel" -> playerDeck.add(new Sentinel(itCard));
        case "Berserker" -> playerDeck.add(new Berserker(itCard));
        case "Goliath" -> playerDeck.add(new Goliath(itCard));
        case "Warden" -> playerDeck.add(new Warden(itCard));
        case "Miraj" -> playerDeck.add(new Miraj(itCard));
        case "The Ripper" -> playerDeck.add(new TheRipper(itCard));
        case "Disciple" -> playerDeck.add(new Disciple(itCard));
        case "The Cursed One" -> playerDeck.add(new TheCursedOne(itCard));
        case "Firestorm" -> playerDeck.add(new Firestorm(itCard));
        case "Winterfell" -> playerDeck.add(new Winterfell(itCard));
        case "Heart Hound" -> playerDeck.add(new HeartHound(itCard));
        case "Lord Royce" -> playerDeck.add(new LordRoyce(hero));
        case "Empress Thorina" -> playerDeck.add(new EmpressThorina(hero));
        case "King Mudface" -> playerDeck.add(new KingMudface(hero));
        case "General Kocioraw" -> playerDeck.add(new GeneralKocioraw(hero));
        default -> System.out.println("[ERROR] Invalid card name");
      }
    }

    Collections.shuffle(playerDeck, new Random(seed));

    addCardToHand();

    playerHero = switch (hero.getName()) {
      case "Lord Royce" -> new LordRoyce(hero);
      case "Empress Thorina" -> new EmpressThorina(hero);
      case "King Mudface" -> new KingMudface(hero);
      case "General Kocioraw" -> new GeneralKocioraw(hero);
      default -> null;
    };
  }

  /**
   * Method that returns the player's cards in hand.
   *
   * @return The player's cards in hand.
   */
  public ArrayList<Cards> getPlayerHand() {
    return playerHand;
  }

  /**
   * Method that returns the player's deck.
   *
   * @return The player's deck.
   */
  public ArrayList<Cards> getPlayerDeck() {
    return playerDeck;
  }

  /**
   * Method that returns the player's hero.
   *
   * @return The player's hero.
   */
  public Cards getPlayerHero() {
    return playerHero;
  }

  /**
   * Method that returns the player's mana.
   *
   * @return The player's mana.
   */
  public int getPlayerMana() {
    return playerMana;
  }

  /**
   * Method that increases the player's mana with the given amount.
   *
   * @param playerMana The amount of mana to be added (can be negative).
   */
  public void setPlayerMana(final int playerMana) {
    this.playerMana += playerMana;
  }

  /**
   * Method that adds a card to the player's hand if there are cards in the deck.
   */
  public void addCardToHand() {
    if (playerDeck.size() > 0) {
      playerHand.add(playerHand.size(), playerDeck.get(0));
      playerDeck.remove(0);
    }
  }
}
