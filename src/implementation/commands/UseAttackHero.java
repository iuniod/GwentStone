package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;
import implementation.cards.minion.Minion;

import java.util.ArrayList;

public class UseAttackHero extends Command {
  public UseAttackHero(final String command, final int x, final int y) {
    super(command, x, y);
  }

  private String findError(final Cards attacker, final ArrayList<ArrayList<Cards>> table,
                           final int playerIdx) {
    if (attacker.getIsFrozen()) {
      return "Attacker card is frozen.";
    } else if (attacker.getHasAttacked()) {
      return "Attacker card has already attacked this turn.";
    } else {
      if (!Minion.checkTankAttacker(table, "Hero", playerIdx)) {
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
    out.put("error", getErrorMessage());
    output.add(out);
  }

  private void setEndGame(final GameSimulation game, final ObjectMapper objectMapper,
                          final ArrayNode output, final int winnerIdx) {
    ObjectNode out = objectMapper.createObjectNode();
    if (winnerIdx == 1) {
      out.put("gameEnded", "Player one killed the enemy hero.");
      game.addPlayerOneWins();
    } else {
      out.put("gameEnded", "Player two killed the enemy hero.");
      game.addPlayerTwoWins();
    }

    output.add(out);
  }

  /**
   * Method that executes the command useAttackHero.
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   */
  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
    Cards attacker = game.getTable().get(getIndex1()).get(getIndex2());
    int playerIdx = (getIndex1() <= 1 ? 2 : 1);
    int opponentIdx = (playerIdx == 1 ? 2 : 1);

    setErrorMessage(findError(attacker, game.getTable(), playerIdx));

    if (getErrorMessage() != null) {
      writeError(objectMapper, output);
    } else {
      Cards opponentHero = game.getPlayer(opponentIdx).getPlayerHero();
      opponentHero.setHealth(opponentHero.getHealth() - attacker.getAttackDamage());
      attacker.setAttacked();

      if (opponentHero.getHealth() <= 0) {
        setEndGame(game, objectMapper, output, playerIdx);
      }
    }
  }
}
