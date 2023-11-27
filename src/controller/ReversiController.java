package controller;

import javax.swing.*;

import model.ReversiModel;
import model.StatusCodes;
import player.Player;
import view.ReversiView;

public class ReversiController implements IReversiController{
  private final Player player;
  private ReversiModel model;
  private final ReversiView view;
  private final IModelListener modelListener;
  private final IPlayerListener playerListener;

  public ReversiController(Player player, ReversiModel model, ReversiView view) {
    this.player = player;
    this.model = model;
    this.view = view;
    this.modelListener = new ModelListener();
    this.playerListener = new PlayerListener();
    this.go();
  }

  public void go() {
    this.view.addListener(this.playerListener);
    this.model.subscribe(this.modelListener);
    this.modelListener.getLastStatusCode();


    while(true) {
      int x = this.playerListener.getX();
      int y = this.playerListener.getY();

      if((x >= 0 || y >= 0)) {
        if(this.model.currentTurn() == this.player.getPlayerTurn()) {
          this.model.makeMove(x, y);
          this.view.render();
        } else {

        }
      }
    }

//    String last = this.listener.getLastMessage();

  }

  public void syncViews(ReversiController controller) {
    controller.model = this.model;
    controller.view.render();
  }

}
