package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;

import java.util.ArrayList;
import java.util.List;

public class GetCardAtPosition extends Command {

  public GetCardAtPosition(final String commandName, final int x, final int y) {
    super(commandName, x, y);
  }

  @Override
  public void run(GameSimulation game, ObjectMapper objectMapper, ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    out.put("command", getCommandName());
    out.put("x", getIndex1());
    out.put("y", getIndex2());

    ArrayList<ArrayList<Cards>> table = game.getTable();
    if (getIndex2() >= table.get(getIndex1()).size()) {
      out.put("output", "No card available at that position.");
    } else {
      out.put("output", table.get(getIndex1()).get(getIndex2()).writeToFile(objectMapper));
    }

    output.add(out);
  }
}

