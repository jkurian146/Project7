package view;

import controller.IPlayerListener;
import model.StatusCodes;

/**
 * The `ReversiView` interface represents the view component in a Reversi game.
 * It is responsible for rendering and displaying the game state to the players.
 */
public interface ReversiView {
  /**
   * Renders and displays the current game state to the players.
   * This method is responsible for presenting the visual representation of the game board,
   * and any other relevant game information to the users.
   */
  void render();

  void removeListener(IPlayerListener listener);

  void notifyListener(int x, int y);

  void addListener(IPlayerListener listener);
}
