package implementation.commands.debugging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;
import implementation.commands.Command;

public class GetFrozenCardsOnTable extends Command {
  public GetFrozenCardsOnTable(final String command) {
    super(command);
  }

  /**
   * Executes the command getFrozenCardsOnTable.
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   */
  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
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
