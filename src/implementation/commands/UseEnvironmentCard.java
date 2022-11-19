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
  public UseEnvironmentCard(final String command, final int index, final int player) {
    super(command, index, player);
  }

  private String findError(final GameSimulation game, final Player player, final Cards card,
                           final int playerRow, final int affectedRow) {
    int playerIdx = game.getPlayerTurn();
    if (!Environment.isEnvironmentCard(card.getName())) {
      return "Chosen card is not of type environment.";
    } else if (card.getMana() > player.getPlayerMana()) {
      return "Not enough mana to use environment card.";
    } else if ((playerIdx == 1 && affectedRow >= 2) || (playerIdx == 2 && affectedRow <= 1)) {
      return "Chosen row does not belong to the enemy.";
    } else if (card.getName().equals("Heart Hound")
                   && game.getTable().get(playerRow).size() == Player.MAX_ROW_SIZE) {
      return "Cannot steal enemy card since the player's row is full.";
    }

    return null;
  }

  /**
   * Executes the command useEnvironmentCard.
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   */
  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
    int affectedRow = getIndex2();
    int playerRow = switch (affectedRow) {
      case 0 -> 3;
      case 1 -> 2;
      case 2 -> 1;
      case 3 -> 0;
      default -> -1;
    };
    Player player = game.getPlayer(game.getPlayerTurn());
    Cards cardEnvironment = player.getPlayerHand().get(getIndex1());

    if (cardEnvironment.getIsFrozen()) {
      return;
    }

    setErrorMessage(findError(game, player, cardEnvironment, playerRow, affectedRow));

    if (getErrorMessage() != null) {
      ObjectNode out = objectMapper.createObjectNode();
      out.put("affectedRow", affectedRow);
      out.put("command", getCommandName());
      out.put("error", getErrorMessage());
      out.put("handIdx", getIndex1());

      output.add(out);
    } else {
      Environment environment = (Environment) cardEnvironment;
      List<ArrayList<Cards>> table = game.getTable();
      environment.action(table.get(playerRow), table.get(affectedRow));
      player.setPlayerMana(-1 * cardEnvironment.getMana());
      player.getPlayerHand().remove(getIndex1());
    }
  }
}
