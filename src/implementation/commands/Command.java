package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import implementation.GameSimulation.GameSimulation;

public abstract class Command {
  private String commandName = null;
  private String errorMessage = null;
  private int index = -1;
  private int player = -1;

  public Command(final String commandName) {
    this.commandName = commandName;
  }

  public Command(final String commandName, final int index) {
    this.commandName = commandName;
    this.index = index;
  }

  public Command(final String commandName, final int index, final int player) {
    this.commandName = commandName;
    this.index = index;
    this.player = player;
  }

  /**
   * Method that runs the specific command.
   *
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   */
  public abstract void run(GameSimulation game, ObjectMapper objectMapper, ArrayNode output);

  /**
   * Method that returns the command name.
   *
   * @return The command name.
   */
  public String getCommandName() {
    return commandName;
  }

  /**
   * Method that returns the error message.
   *
   * @return The error message.
   */
  public String getErrorMessage() {
    return errorMessage;
  }

  public void setErrorMessage(final String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * Method that returns the index.
   *
   * @return The index.
   */
  public int getIndex() {
    return index;
  }

  public int getPlayer() {
    return player;
  }
}
