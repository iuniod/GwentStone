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

  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
    int playerTurn = game.getPlayerTurn();
    List<ArrayList<Cards>> table = game.getPlayerTable(getPlayer());
    if (table != null) {
      for (ArrayList<Cards> row : table) {
        for (Cards card : row) {
          if (card != null) {
            card.unfreeze();
          }
        }
      }
    }

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
