package implementation.commands.debugging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.commands.Command;

public class GetPlayerTwoWins extends Command {

  public GetPlayerTwoWins(final String commandName) {
    super(commandName);
  }

  /**
   * Executes the command getPlayerTwoWins.
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   */
  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                     final ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    out.put("command", getCommandName());
    out.put("output", game.getPlayerTwoWins());
    output.add(out);
  }
}
