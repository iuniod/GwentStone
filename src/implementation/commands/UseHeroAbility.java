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

  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                     final ArrayNode output) {
    int playerIdx = game.getPlayerTurn();
    int affectedRow = getIndex1();
    Player player = game.getPlayer(playerIdx);
    Hero hero = (Hero) player.getPlayerHero();

    if (hero.getMana() > player.getPlayerMana()) {
      setErrorMessage("Not enough mana to use hero's ability.");
    } else if (hero.getHasAttacked()) {
      setErrorMessage("Hero has already attacked this turn.");
    } else if (hero.getName().equals("Lord Royce") || hero.getName().equals("Empress Thorina")) {
      if ((playerIdx == 1 && affectedRow > 1) || (playerIdx == 2 && affectedRow < 2)) {
        setErrorMessage("Selected row does not belong to the enemy.");
      }
    } else {
      if ((playerIdx == 1 && affectedRow < 2) || (playerIdx == 2 && affectedRow > 1)) {
        setErrorMessage("Selected row does not belong to the current player.");
      }
    }

    if (getErrorMessage() != null) {
      writeError(objectMapper, output);
    } else {
      hero.setAttacked();
      player.setPlayerMana(-1 * hero.getMana());
      hero.ability(game.getTable().get(affectedRow));
    }
  }
}
