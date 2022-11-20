package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;
import implementation.cards.minion.Minion;
import implementation.cards.minion.specialMinion.SpecialMinion;

public class CardUsesAbility extends Command {
  public CardUsesAbility(final String command, final int xAttacker, final int yAttacker,
                         final int xAttacked, final int yAttacked) {
    super(command, xAttacker, yAttacker, xAttacked, yAttacked);
  }

  private String findError(final GameSimulation game, final Cards attacker, final Cards attacked) {
    int xAttacker = getIndex1();
    int xAttacked = getIndex3();

    if (attacker.getIsFrozen()) {
      return "Attacker card is frozen.";
    } else if (attacker.getHasAttacked()) {
      return "Attacker card has already attacked this turn.";
    } else {
      boolean belongsToEnemy = (xAttacker <= 1 && xAttacked > 1)
                                   || (xAttacker > 1 && xAttacked <= 1);
      if (attacker.getName().equals("Disciple")) {
        if (belongsToEnemy) {
          return "Attacked card does not belong to the current player.";
        }
      } else if (Minion.isSpecialMinionCard(attacker.getName())) {
        int playerIdx = (xAttacker <= 1 ? 2 : 1);
        if (!belongsToEnemy) {
          return "Attacked card does not belong to the enemy.";
        } else if (!Minion.checkTankAttacker(game.getTable(), attacked.getName(), playerIdx)) {
          return "Attacked card is not of type 'Tank'.";
        }
      }
    }

    return null;
  }

  /**
   * Method that writes the command error to the JSON file.
   * @param objectMapper the object mapper
   * @param output the output
   */
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
   * Executes the command cardUsesAbility.
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
      attacker.setAttacked();
      SpecialMinion minion = (SpecialMinion) attacker;
      minion.ability(attacked);

      if (attacked.getHealth() <= 0) {
        game.getTable().get(getIndex3()).remove(getIndex4());
      }
    }
  }
}
