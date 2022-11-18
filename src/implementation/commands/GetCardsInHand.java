package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;

import java.util.ArrayList;

public class GetCardsInHand extends Command {

    public GetCardsInHand(final String commandName, final int playerIdx) {
        super(commandName, playerIdx);
    }

  @Override
  public void run(GameSimulation game, ObjectMapper objectMapper, ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    out.put("command", getCommandName());
    out.put("playerIdx", getIndex());
    ArrayNode cards = objectMapper.createArrayNode();
    ArrayList<Cards> cardsInHand = game.getPlayer(getIndex()).getPlayerHand();
    for (Cards card : cardsInHand) {
      cards.add(card.writeToFile(objectMapper));
    }
    out.put("output", cards);
    output.add(out);
  }
}
