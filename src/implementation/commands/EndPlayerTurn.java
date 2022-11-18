package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import implementation.GameSimulation.GameSimulation;
import implementation.GameSimulation.Player;
import implementation.cards.Cards;

import java.util.ArrayList;
import java.util.List;

public final class EndPlayerTurn extends Command {
  public EndPlayerTurn(final String commandName) {
    super(commandName);
  }

  private void unfreezeCards(final GameSimulation game, final int playerIdx) {
    if (playerIdx == 1) {
      for (int i = 2; i < 4; i++) {
        for (Cards card : game.getTable().get(i)) {
          card.unfreeze();
        }
      }
    } else {
      for (int i = 0; i < 2; i++) {
        for (Cards card : game.getTable().get(i)) {
          card.unfreeze();
        }
      }
    }
  }
  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
    unfreezeCards(game, game.getPlayerTurn());

    game.setPlayerTurn();
    game.setTurn(game.getTurn() + 1);

    if (game.getTurn() == 2) {
      game.addRound();
      if (game.getRound() <= 10) {
        game.getPlayer(1).setPlayerMana(game.getRound());
        game.getPlayer(2).setPlayerMana(game.getRound());
      }

      game.getPlayer(1).addCardToHand();
      game.getPlayer(2).addCardToHand();

      game.setTurn(0);
    }
  }
}
