package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.GameSimulation.Player;
import implementation.cards.Cards;
import implementation.cards.environment.Environment;

import java.util.ArrayList;
import java.util.List;

public class UseEnvironmentCard extends Command {
  public UseEnvironmentCard(String command, int index, int player) {
    super(command, index, player);
  }

  @Override
  public void run(GameSimulation game, ObjectMapper objectMapper, ArrayNode output) {
    int playerIdx = game.getPlayerTurn();
    int affectedRow = getIndex2();
    int playerRow;
    switch (affectedRow) {
      case 0:
        playerRow = 3;
        break;
      case 1:
        playerRow = 2;
        break;
      case 2:
        playerRow = 1;
        break;
      case 3:
        playerRow = 0;
        break;
      default:
        playerRow = -1;
        break;
    }
    Player player = game.getPlayer(playerIdx);
    Cards cardEnvironment = player.getPlayerHand().get(getIndex1());

    if (cardEnvironment.getIsFrozen()) {
      return;
    }

    if (!Environment.isEnvironmentCard(cardEnvironment.getName())) {
      setErrorMessage("Chosen card is not of type environment.");
    } else if (cardEnvironment.getMana() > player.getPlayerMana()) {
      setErrorMessage("Not enough mana to use environment card.");
    } else if ((playerIdx == 1 && affectedRow >= 2) || (playerIdx == 2 && affectedRow <= 1)) {
      setErrorMessage("Chosen row does not belong to the enemy.");
    } else if (cardEnvironment.getName().equals("Heart Hound") && game.getTable().get(playerRow).size() == 5) {
      setErrorMessage("Cannot steal enemy card since the player's row is full.");
    } else {
      Environment environment = (Environment) cardEnvironment;
      List<ArrayList<Cards>> table = game.getTable();
      environment.action(table.get(playerRow), table.get(affectedRow));
      player.setPlayerMana(-1 * cardEnvironment.getMana());
      player.getPlayerHand().remove(getIndex1());
    }

    if (getErrorMessage() != null) {
      ObjectNode out = objectMapper.createObjectNode();
      out.put("affectedRow", affectedRow);
      out.put("command", getCommandName());
      out.put("error", getErrorMessage());
      out.put("handIdx", getIndex1());

      output.add(out);
    }
  }
}
