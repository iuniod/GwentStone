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

  public PlaceCard(final String commandName, final int handIdx, final int player) {
    super(commandName, handIdx, player);
  }

  @Override
  public void run(GameSimulation game, ObjectMapper objectMapper, ArrayNode output) {
    Player player = game.getPlayer(getPlayer());
    ArrayList<Cards> hand = player.getPlayerHand();
    List<ArrayList<Cards>> table = game.getPlayerTable(getPlayer());
    int handIdx = getIndex();

    setErrorMessage(null);
    if (Environment.isEnvironmentCard(hand.get(handIdx).getName())) {
      setErrorMessage("Cannot place environment card on table.");
    } else if (hand.get(handIdx).getMana() > player.getPlayerMana()) {
      setErrorMessage("Not enough mana to place card on table.");
    } else {
      Minion minion = (Minion) hand.get(handIdx);
      int row = minion.getRowPermission();
      ArrayList<Cards> rowCards = table.get(row);

      if (rowCards.size() == 5) {
        setErrorMessage("Cannot place card on table since row is full.");
      } else {
        System.out.println("Placing card on table.");
        System.out.println("Card: " + hand.get(handIdx).getName() + " for player: " + getPlayer() + " on row: " + row);
        rowCards.add(hand.get(handIdx));
        player.setPlayerMana(-1 * hand.get(handIdx).getMana());
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
