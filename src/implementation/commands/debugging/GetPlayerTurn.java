package implementation.commands.debugging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.commands.Command;

public final class GetPlayerTurn extends Command {
  public GetPlayerTurn(final String commandName) {
    super(commandName);
  }

  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                     final ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    out.put("command", getCommandName());
    out.put("output", game.getPlayerTurn());
    output.add(out);
  }
}
