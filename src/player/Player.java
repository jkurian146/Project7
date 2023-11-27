package player;

import java.util.HashMap;
import java.util.Map;

import discs.DiscColor;
import model.ReversiModel;
import strategy.IStrategy;

/**
 * A 'Player' represents a player it can either be an AI or regular player.
 * Overloaded constructors are used to represent the condition.
 */
public class Player {
  private final PlayerTurn playerTurn;
  private IStrategy iStrategy;
  private final Map<PlayerTurn, DiscColor> playerColorMap;
  private final ReversiModel model;

  /**
   * A 'Player' represents a player instantiation without a strategy (non-ai).
   */
  public Player(PlayerTurn playerTurn, ReversiModel model) {
    this.model = model;
    this.playerTurn = playerTurn;
    this.playerColorMap = setUpColorMap();

  }

  /**
   * A 'Player' represents a player instantiation with a strategy (ai).
   */
  public Player(PlayerTurn playerTurn, IStrategy iStrategy, ReversiModel model) {
    this.model = model;
    this.playerTurn = playerTurn;
    this.iStrategy = iStrategy;
    this.playerColorMap = setUpColorMap();
  }

  private Map<PlayerTurn, DiscColor> setUpColorMap() {
    HashMap<PlayerTurn, DiscColor> temp = new HashMap<>();
    temp.put(PlayerTurn.PLAYER1, DiscColor.BLACK);
    temp.put(PlayerTurn.PLAYER2, DiscColor.WHITE);
    return temp;
  }

  public PlayerTurn getPlayerTurn() {
    return this.playerTurn;
  }

  public DiscColor getPlayerColor() {
    return this.playerColorMap.get(this.getPlayerTurn());
  }

  public IStrategy getIStrategy() {
    return this.iStrategy;
  }
}
