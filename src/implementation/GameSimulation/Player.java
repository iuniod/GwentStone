package implementation.GameSimulation;

import fileio.CardInput;
import implementation.cards.Cards;
import implementation.cards.environment.Firestorm;
import implementation.cards.environment.HeartHound;
import implementation.cards.environment.Winterfell;
import implementation.cards.hero.*;
import implementation.cards.minion.*;
import implementation.cards.specialMinion.Disciple;
import implementation.cards.specialMinion.Miraj;
import implementation.cards.specialMinion.TheCursedOne;
import implementation.cards.specialMinion.TheRipper;

import java.util.Collections;
import java.util.Random;
import java.util.ArrayList;

public class Player {
  private ArrayList<Cards> playerHand = new ArrayList<Cards>();
  private ArrayList<Cards> playerDeck = new ArrayList<Cards>();

  private ArrayList<ArrayList<Cards>> playerTable = null;
  private Cards playerHero = null;
  private int playerMana = 0;
  private int playerWins = 0;

  public Player(final ArrayList<CardInput> deck, final CardInput hero, final int seed) {
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
        default -> System.out.println("Invalid card name");
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

    playerTable = new ArrayList<ArrayList<Cards>>();
    playerTable.add(new ArrayList<Cards>());
    playerTable.add(new ArrayList<Cards>());
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
   * Method that returns the player's table.
   *
   * @return The player's table.
   */
  public ArrayList<ArrayList<Cards>> getPlayerTable() {
    return playerTable;
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
   * Method that sets the player's mana.
   *
   * @return The player's mana.
   */
  public void setPlayerMana(final int playerMana) {
    this.playerMana += playerMana;
  }

  /**
   * Method that returns the player's wins.
   *
   * @return The player's wins.
   */
  public int getPlayerWins() {
    return playerWins;
  }

  /**
   * Method that sets the player's wins.
   *
   * @return The player's wins.
   */
  public void setPlayerWins(final int playerWins) {
    this.playerWins = playerWins;
  }

  public void addCardToHand() {
    if (playerDeck.size() > 0) {
      playerHand.add(playerHand.size(), playerDeck.get(0));
      playerDeck.remove(0);
    }
  }
}
