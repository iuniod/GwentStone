package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;

public final class GetPlayerDeck extends Command {
  public GetPlayerDeck(final String commandName, final int playerIdx) {
    super(commandName, playerIdx);
  }

  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                     final ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    out.put("command", getCommandName());
    out.put("playerIdx", getIndex1());
    ArrayNode cards = objectMapper.createArrayNode();

    for (Cards card : game.getPlayer(getIndex1()).getPlayerDeck()) {
      cards.add(card.writeToFile(objectMapper));
    }
    out.put("output", cards);
    output.add(out);
  }
}
