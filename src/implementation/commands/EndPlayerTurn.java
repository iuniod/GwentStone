package implementation.commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import implementation.GameSimulation.GameSimulation;
import implementation.cards.Cards;

public final class EndPlayerTurn extends Command {
  public EndPlayerTurn(final String commandName) {
    super(commandName);
  }

  private void resetCards(final GameSimulation game, final int playerIdx) {
    int firstRow = playerIdx == 1 ? 2 : 0;
    int lastRow = playerIdx == 1 ? 4 : 2;

    for (int i = firstRow; i < lastRow; i++) {
      for (Cards card : game.getTable().get(i)) {
        card.unfreeze();
        card.resetHasAttacked();
      }
    }

    Cards hero = game.getPlayer(playerIdx).getPlayerHero();
    hero.resetHasAttacked();
  }

  @Override
  public void run(final GameSimulation game, final ObjectMapper objectMapper,
                  final ArrayNode output) {
    resetCards(game, game.getPlayerTurn());

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
