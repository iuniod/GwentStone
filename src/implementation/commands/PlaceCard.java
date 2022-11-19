package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.GameSimulation.Player;
import implementation.cards.Cards;
import implementation.cards.environment.Environment;
import implementation.cards.minion.Minion;

import java.util.ArrayList;

public class PlaceCard extends Command {

  public PlaceCard(final String commandName, final int handIdx) {
    super(commandName, handIdx);
  }

  /**
   * Checks if the card can be placed on the table.
   *
   * @param game      the game
   * @param player    the player who wants to place the card
   * @param playerIdx the index of the player
   * @param card      the card to be placed
   * @return error message if the card cannot be placed on the table, null otherwise
   */
  private String findError(final GameSimulation game, final Player player,
                           final int playerIdx, final Cards card) {
    if (Environment.isEnvironmentCard(card.getName())) {
      return "Cannot place environment card on table.";
    } else if (card.getMana() > player.getPlayerMana()) {
      return "Not enough mana to place card on table.";
    } else {
      int row = ((Minion) card).getRowPermission();
      if (playerIdx == 1) {
        row = (row == 0 ? 3 : 2);
      }

      if (game.getTable().get(row).size() == Player.MAX_ROW_SIZE) {
        return "Cannot place card on table since row is full.";
      }
    }

    return null;
  }

  /**
   * Method that runs the command placeCard.
   *
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   */
  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
    Player player = game.getPlayer(game.getPlayerTurn());
    ArrayList<Cards> hand = player.getPlayerHand();
    int handIdx = getIndex1();
    Cards card = hand.get(handIdx);

    setErrorMessage(findError(game, player, game.getPlayerTurn(), card));

    if (getErrorMessage() != null) {
      ObjectNode out = objectMapper.createObjectNode();
      out.put("command", getCommandName());
      out.put("handIdx", handIdx);
      out.put("error", getErrorMessage());
      output.add(out);
    } else {
      int row = ((Minion) card).getRowPermission();
      if (game.getPlayerTurn() == 1) {
        row = (row == 0 ? 3 : 2);
      }

      game.getTable().get(row).add(card);
      player.setPlayerMana(-card.getMana());
      hand.remove(handIdx);
    }
  }
}
