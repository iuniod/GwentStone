package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;
import implementation.cards.minion.Minion;

import java.util.ArrayList;

public class CardUsesAttack extends Command {
  public CardUsesAttack(final String command, final int xAttacker, final int yAttacker,
                        final int xAttacked, final int yAttacked) {
    super(command, xAttacker, yAttacker, xAttacked, yAttacked);
  }

  private String findError(final GameSimulation game, final Cards attacker,
                           final Cards attacked) {
    int xAttacker = getIndex1();
    int xAttacked = getIndex3();
    if ((xAttacker <= 1 && xAttacked <= 1) || (xAttacker >= 2 && xAttacked >= 2)) {
      return "Attacked card does not belong to the enemy.";
    } else if (attacker.getHasAttacked()) {
      return "Attacker card has already attacked this turn.";
    } else if (attacker.getIsFrozen()) {
      return "Attacker card is frozen.";
    } else {
      ArrayList<ArrayList<Cards>> table = game.getTable();
      int playerIdx = (xAttacker <= 1 ? 2 : 1);
      if (!Minion.checkTankAttacker(table, attacked.getName(), playerIdx)) {
        return "Attacked card is not of type 'Tank'.";
      }
    }

    return null;
  }

  private void writeError(final ObjectMapper objectMapper, final ArrayNode output) {
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

  /**
   * Executes the command cardUsesAttack.
   *
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   */
  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
    Cards attacker = game.getTable().get(getIndex1()).get(getIndex2());
    Cards attacked = game.getTable().get(getIndex3()).get(getIndex4());

    setErrorMessage(findError(game, attacker, attacked));

    if (getErrorMessage() != null) {
      writeError(objectMapper, output);
    } else {
      attacked.setHealth(attacked.getHealth() - attacker.getAttackDamage());
      attacker.setAttacked();

      if (attacked.getHealth() <= 0) {
        game.getTable().get(getIndex3()).remove(getIndex4());
      }
    }
  }
}

