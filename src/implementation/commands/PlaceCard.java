package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.GameSimulation.Player;
import implementation.cards.Cards;
import implementation.cards.environment.Environment;
import implementation.cards.minion.Minion;

import java.util.List;
import java.util.ArrayList;

public class PlaceCard extends Command {

  public PlaceCard(final String commandName, final int handIdx) {
    super(commandName, handIdx);
  }

  @Override
  public void run(GameSimulation game, ObjectMapper objectMapper, ArrayNode output) {
    int playerIdx = game.getPlayerTurn();
    Player player = game.getPlayer(playerIdx);
    ArrayList<Cards> hand = player.getPlayerHand();
    ArrayList<ArrayList<Cards>> table = game.getTable();
    int handIdx = getIndex1();
    Cards card = hand.get(handIdx);
    setErrorMessage(null);

    if (Environment.isEnvironmentCard(card.getName())) {
      setErrorMessage("Cannot place environment card on table.");
    } else if (card.getMana() > player.getPlayerMana()) {
      setErrorMessage("Not enough mana to place card on table.");
    } else {
      Minion minion = (Minion) card;
      int row = minion.getRowPermission();
      if (playerIdx == 1) {
        row = (row == 0 ? 3 : 2);
      }

      ArrayList<Cards> rowCards = table.get(row);

      if (rowCards.size() == 5) {
        setErrorMessage("Cannot place card on table since row is full.");
      } else {
        rowCards.add(card);
        player.setPlayerMana(-1 * card.getMana());
        hand.remove(handIdx);
      }
    }

    if (getErrorMessage() != null) {
      ObjectNode out = objectMapper.createObjectNode();
      out.put("command", getCommandName());
      out.put("handIdx", handIdx);
      out.put("error", getErrorMessage());
      output.add(out);
    }
  }
}
