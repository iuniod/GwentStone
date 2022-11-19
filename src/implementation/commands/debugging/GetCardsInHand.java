package implementation.commands.debugging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;
import implementation.commands.Command;

import java.util.ArrayList;

public class GetCardsInHand extends Command {

    public GetCardsInHand(final String commandName, final int playerIdx) {
        super(commandName, playerIdx);
    }

  /**
   * Executes the command getCardsInHand.
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   */
  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    out.put("command", getCommandName());
    out.put("playerIdx", getIndex1());
    ArrayNode cards = objectMapper.createArrayNode();
    ArrayList<Cards> cardsInHand = game.getPlayer(getIndex1()).getPlayerHand();
    for (Cards card : cardsInHand) {
      cards.add(card.writeToFile(objectMapper));
    }
    out.put("output", cards);
    output.add(out);
  }
}
