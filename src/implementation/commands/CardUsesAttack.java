package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;

import java.util.ArrayList;

public class CardUsesAttack extends Command {
  private static final ArrayList<String> tankCards = new ArrayList<String>() {{
    add("Goliath");
    add("Warden");
  }};
  public CardUsesAttack(final String command, final int xAttacker, final int yAttacker,
                        final int xAttacked, final int yAttacked) {
    super(command, xAttacker, yAttacker, xAttacked, yAttacked);
  }

  private boolean checkTankAttacker(ArrayList<ArrayList<Cards>> table, String cardName, int playerIdx) {
    if (CardUsesAttack.isTankCard(cardName)) {
      return true;
    }
    if (playerIdx == 1) {
      for (Cards card : table.get(1)) {
          if (CardUsesAttack.isTankCard(card.getName())) {
            return false;
          }
      }
    } else {
      for (Cards card : table.get(2)) {
          if (CardUsesAttack.isTankCard(card.getName())) {
            return false;
          }
      }
    }
    return true;
  }

  private static boolean isTankCard(final String cardName) {
    return tankCards.contains(cardName);
  }

  @Override
  public void run(GameSimulation game, ObjectMapper objectMapper, ArrayNode output) {
    int xAttacker = getIndex1();
    int xAttacked = getIndex3();
    int yAttacker = getIndex2();
    int yAttacked = getIndex4();
    Cards attacker = game.getTable().get(xAttacker).get(yAttacker);
    Cards attacked = game.getTable().get(xAttacked).get(yAttacked);

    if ((xAttacker <= 1 && xAttacked <= 1) || (xAttacker >= 2 && xAttacked >= 2)) {
      setErrorMessage("Attacked card does not belong to the enemy.");
    } else if (attacker.getHasAttacked()) {
      setErrorMessage("Attacker card has already attacked this turn.");
    } else if (attacker.getIsFrozen()) {
      setErrorMessage("Attacker card is frozen.");
    } else {
      ArrayList<ArrayList<Cards>> table = game.getTable();
      int playerIdx = (xAttacker <= 1 ? 2 : 1);
      if (!checkTankAttacker(table, attacked.getName(), playerIdx)) {
        setErrorMessage("Attacked card is not of type 'Tank'.");
      } else {
        attacked.setHealth(attacked.getHealth() - attacker.getAttackDamage());
        attacker.setAttacked();
        if (attacked.getHealth() <= 0) {
          game.getTable().get(xAttacked).remove(yAttacked);
        }
      }
    }

    if (getErrorMessage() != null) {
      ObjectNode out = objectMapper.createObjectNode();
      ObjectNode coordinates = objectMapper.createObjectNode();
      out.put("command", getCommandName());
      coordinates.put("x", xAttacker);
      coordinates.put("y", yAttacker);
      out.set("cardAttacker", coordinates);
      coordinates = objectMapper.createObjectNode();
      coordinates.put("x", xAttacked);
      coordinates.put("y", yAttacked);
      out.set("cardAttacked", coordinates);
      out.put("error", getErrorMessage());
      output.add(out);
    }

  }
}
