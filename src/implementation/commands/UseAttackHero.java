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

  private void setEndGame(GameSimulation game, ObjectMapper objectMapper, ArrayNode output, int winnerIdx) {
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

  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper, final ArrayNode output) {
    int x = getIndex1();
    int y = getIndex2();
    Cards attacker = game.getTable().get(x).get(y);
    ArrayList<ArrayList<Cards>> table = game.getTable();
    int playerIdx = (x <= 1 ? 2 : 1);
    int opponentIdx = (playerIdx == 1 ? 2 : 1);

    if (attacker.getIsFrozen()) {
      setErrorMessage("Attacker card is frozen.");
    } else if (attacker.getHasAttacked()) {
      setErrorMessage("Attacker card has already attacked this turn.");
    } else {
      if (!Minion.checkTankAttacker(table, "Hero", playerIdx)) {
        setErrorMessage("Attacked card is not of type 'Tank'.");
      }
    }

    if (getErrorMessage() != null) {
      writeError(objectMapper, output);
      return;
    }

    Cards opponentHero = game.getPlayer(opponentIdx).getPlayerHero();
    opponentHero.setHealth(opponentHero.getHealth() - attacker.getAttackDamage());
    attacker.setAttacked();

    if (opponentHero.getHealth() <= 0) {
      setEndGame(game, objectMapper, output, playerIdx);
    }

  }
}
