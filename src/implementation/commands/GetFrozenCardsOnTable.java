package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;

public class GetFrozenCardsOnTable extends Command {
  public GetFrozenCardsOnTable(String command) {
    super(command);
  }

  @Override
  public void run(GameSimulation game, ObjectMapper objectMapper, ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    out.put("command", "getFrozenCardsOnTable");
    ArrayNode cards = objectMapper.createArrayNode();

    for (int i = 0; i < 4; i++) {
      for (Cards card : game.getTable().get(i)) {
        if (card.getIsFrozen()) {
          cards.add(card.writeToFile(objectMapper));
        }
      }
    }

    out.set("output", cards);
    output.add(out);
  }
}
