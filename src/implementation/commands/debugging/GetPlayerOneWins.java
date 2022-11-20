package implementation.commands.debugging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.commands.Command;

public class GetPlayerOneWins extends Command {

  public GetPlayerOneWins(final String commandName) {
    super(commandName);
  }

  /**
   * Executes the command getPlayerOneWins.
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   */
  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                     final ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    out.put("command", getCommandName());
    out.put("output", game.getPlayerOneWins());
    output.add(out);
  }
}
