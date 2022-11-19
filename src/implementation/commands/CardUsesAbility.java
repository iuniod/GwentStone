package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;
import implementation.cards.minion.Minion;
import implementation.cards.minion.specialMinion.SpecialMinion;

import java.util.ArrayList;

public class CardUsesAbility extends Command {
  public CardUsesAbility(final String command, final int xAttacker, final int yAttacker,
                         final int xAttacked, final int yAttacked) {
    super(command, xAttacker, yAttacker, xAttacked, yAttacked);
  }

  private void writeError(ObjectMapper objectMapper, ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    ObjectNode coordinates = objectMapper.createObjectNode();
    out.put("command", getCommandName());
    coordinates.put("x", getIndex1());
    coordinates.put("y", getIndex2());
    out.set("cardAttacker", coordinates);
    coordinates = objectMapper.createObjectNode();
    coordinates.put("x", getIndex3());
    coordinates.put("y", getIndex4());
    out.set("cardAttacked", coordinates);
    out.put("error", getErrorMessage());
    output.add(out);
  }

  @Override
  public void run(GameSimulation game, ObjectMapper objectMapper, ArrayNode output) {
    int xAttacker = getIndex1();
    int xAttacked = getIndex3();
    int yAttacker = getIndex2();
    int yAttacked = getIndex4();
    Cards attacker = game.getTable().get(xAttacker).get(yAttacker);
    Cards attacked = game.getTable().get(xAttacked).get(yAttacked);

    if (attacker.getIsFrozen()) {
      setErrorMessage("Attacker card is frozen.");
    } else if (attacker.getHasAttacked()) {
      setErrorMessage("Attacker card has already attacked this turn.");
    }

    if (getErrorMessage() != null) {
      writeError(objectMapper, output);
      return;
    }

    boolean belongsToEnemy = (xAttacker <= 1 && xAttacked > 1) || (xAttacker > 1 && xAttacked <= 1);
    if (attacker.getName().equals("Disciple")) {
      if (belongsToEnemy) {
        setErrorMessage("Attacked card does not belong to the current player.");
      }
    } else if (Minion.isSpecialMinionCard(attacker.getName())) {
      ArrayList<ArrayList<Cards>> table = game.getTable();
      int playerIdx = (xAttacker <= 1 ? 2 : 1);
      if (!belongsToEnemy) {
        setErrorMessage("Attacked card does not belong to the enemy.");
      } else if (!Minion.checkTankAttacker(table, attacked.getName(), playerIdx)) {
        setErrorMessage("Attacked card is not of type 'Tank'.");
      }
    }

    if (getErrorMessage() != null) {
      writeError(objectMapper, output);
      return;
    }

    attacker.setAttacked();
    SpecialMinion minion = (SpecialMinion) attacker;
    minion.ability(attacked);
    if (attacked.getHealth() <= 0) {
      game.getTable().get(xAttacked).remove(yAttacked);
    }
  }
}
