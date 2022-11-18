package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;
import implementation.cards.environment.Environment;

public class GetEnvironmentCardsInHand extends Command {
  public GetEnvironmentCardsInHand(String command, int playerIdx) {
    super(command, playerIdx);
  }

  @Override
  public void run(GameSimulation game, ObjectMapper objectMapper, ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    ArrayNode cards = objectMapper.createArrayNode();

    out.put("command", getCommandName());
    out.put("playerIdx", getIndex1());
    for (Cards card : game.getPlayer(getIndex1()).getPlayerHand()) {
      if (Environment.isEnvironmentCard(card.getName())) {
        cards.add(card.writeToFile(objectMapper));
      }
    }

    out.set("output", cards);
    output.add(out);
  }
}

