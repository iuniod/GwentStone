package implementation.commands.debugging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;
import implementation.cards.environment.Environment;
import implementation.commands.Command;

public class GetEnvironmentCardsInHand extends Command {
  public GetEnvironmentCardsInHand(final String command, final int playerIdx) {
    super(command, playerIdx);
  }

  /**
   * Executes the command getEnvironmentCardsInHand.
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   */
  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
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

