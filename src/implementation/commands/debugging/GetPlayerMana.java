package implementation.commands.debugging;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.commands.Command;

public class GetPlayerMana extends Command {

  public GetPlayerMana(final String commandName, final int playerIdx) {
    super(commandName, playerIdx);
  }

  /**
   * Executes the command getPlayerMana.
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
    out.put("output", game.getPlayer(getIndex1()).getPlayerMana());
    output.add(out);
  }
}
