package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;

import java.util.ArrayList;
import java.util.List;

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
    // Add the cards on the table to the output.
    List<ArrayList<Cards>> table = game.getTable();
    if (table != null) {
      for (ArrayList<Cards> rowCards : table) {
        for (Cards card : rowCards) {
            row.add(card.writeToFile(objectMapper));
        }
        cards.add(row);
        row = objectMapper.createArrayNode();
      }
    }
    out.set("output", cards);
    output.add(out);
  }
}

