package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.GameSimulation.Player;
import implementation.cards.hero.Hero;

public class UseHeroAbility extends Command {

  public UseHeroAbility(final String commandName, final int playerIdx) {
    super(commandName, playerIdx);
  }

  private void writeError(final ObjectMapper objectMapper, final ArrayNode output) {
    ObjectNode out = objectMapper.createObjectNode();
    out.put("command", getCommandName());
    out.put("affectedRow", getIndex1());
    out.put("error", getErrorMessage());
    output.add(out);
  }

  private String findError(final Player player, final int playerIdx, final Hero hero) {
    int affectedRow = getIndex1();
    if (hero.getMana() > player.getPlayerMana()) {
      return "Not enough mana to use hero's ability.";
    } else if (hero.getHasAttacked()) {
      return "Hero has already attacked this turn.";
    } else if (hero.getName().equals("Lord Royce") || hero.getName().equals("Empress Thorina")) {
      if ((playerIdx == 1 && affectedRow > 1) || (playerIdx == 2 && affectedRow < 2)) {
        return "Selected row does not belong to the enemy.";
      }
    } else {
      if ((playerIdx == 1 && affectedRow < 2) || (playerIdx == 2 && affectedRow > 1)) {
        return "Selected row does not belong to the current player.";
      }
    }

    return null;
  }

  /**
   * Executes the command useHeroAbility.
   * @param game         The game simulation.
   * @param objectMapper The object mapper.
   * @param output       The output.
   */
  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
    int playerIdx = game.getPlayerTurn();
    Player player = game.getPlayer(playerIdx);
    Hero hero = (Hero) player.getPlayerHero();

    setErrorMessage(findError(player, playerIdx, hero));

    if (getErrorMessage() != null) {
      writeError(objectMapper, output);
    } else {
      hero.setAttacked();
      player.setPlayerMana(-hero.getMana());
      hero.ability(game.getTable().get(getIndex1()));
    }
  }
}
