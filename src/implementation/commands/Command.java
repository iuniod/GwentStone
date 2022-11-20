package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import implementation.GameSimulation.GameSimulation;

public abstract class Command {
  private String commandName = null;
  private String errorMessage = null;
  private int index1 = -1;
  private int index2 = -1;
  private int index3 = -1;
  private int index4 = -1;

  public Command(final String commandName) {
    this.commandName = commandName;
  }

  public Command(final String commandName, final int index1) {
    this.commandName = commandName;
    this.index1 = index1;
  }

  public Command(final String commandName, final int index1, final int index2) {
    this.commandName = commandName;
    this.index1 = index1;
    this.index2 = index2;
  }

  public Command(final String commandName, final int index1, final int index2,
                 final int index3, final int index4) {
    this.commandName = commandName;
    this.index1 = index1;
    this.index2 = index2;
    this.index3 = index3;
    this.index4 = index4;
  }

  /**
   * Method that runs the specific command.
   *
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   * @return
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

  /**
   * Method that sets the error message of the command.
   *
   * @param errorMessage The error message.
   */
  public void setErrorMessage(final String errorMessage) {
    this.errorMessage = errorMessage;
  }

  /**
   * Method that returns the index1.
   *
   * @return The index1.
   */
  public int getIndex1() {
    return index1;
  }

  /**
   * Method that returns the index2.
   *
   * @return The index2.
   */
  public int getIndex2() {
    return index2;
  }

  /**
   * Method that returns the index3.
   *
   * @return The index3.
   */
  public int getIndex3() {
    return index3;
  }

  /**
   * Method that returns the index4.
   *
   * @return The index4.
   */
  public int getIndex4() {
    return index4;
  }
}
