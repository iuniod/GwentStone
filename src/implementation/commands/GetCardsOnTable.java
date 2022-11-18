package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;

import java.util.ArrayList;

public class GetCardsOnTable extends Command {

  public GetCardsOnTable(final String commandName) {
    super(commandName);
  }

  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    out.put("command", getCommandName());
    ArrayNode cards = objectMapper.createArrayNode();
    ArrayNode row = objectMapper.createArrayNode();
    // Add the cards on the table to the output, starting with player 2.
    ArrayList<ArrayList<Cards>> table = game.getPlayer(2).getPlayerTable();
    if (table != null) {
      // Add the rows in this order: 0, 1;
      for (Cards card : table.get(0)) {
        row.add(card.writeToFile(objectMapper));
      }
      cards.add(row);

      row = objectMapper.createArrayNode();
      for (Cards card : table.get(1)) {
        row.add(card.writeToFile(objectMapper));
      }
      cards.add(row);
    }

    // Add player 1 cards to the output.
    table = game.getPlayer(1).getPlayerTable();
    if (table != null) {
      // Add the rows in this order: 1, 0;
      row = objectMapper.createArrayNode();
      for (Cards card : table.get(1)) {
        row.add(card.writeToFile(objectMapper));
      }
      cards.add(row);

      row = objectMapper.createArrayNode();
      for (Cards card : table.get(0)) {
        row.add(card.writeToFile(objectMapper));
      }
      cards.add(row);
    }
    out.set("output", cards);
    output.add(out);
  }
}

