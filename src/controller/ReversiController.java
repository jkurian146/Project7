package controller;

import java.util.ArrayList;
import java.util.List;

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
  private List<IReversiController> observers;

  public ReversiController(Player player, ReversiModel model, ReversiView view) {
    this.player = player;
    this.model = model;
    this.view = view;
    this.modelListener = new ModelListener();
    this.playerListener = new PlayerListener();
    this.observers = new ArrayList<>();
  }

  public void go() {
    this.view.addListener(this.playerListener);
    this.model.subscribe(this.modelListener);
    this.modelListener.getLastStatusCode();

    while(!model.isGameOver()) { // Check if the game is over
      int x = this.playerListener.getX();
      int y = this.playerListener.getY();

      if((x >= 0 || y >= 0)) {
        if(this.model.currentTurn() == this.player.getPlayerTurn()) {
          this.model.makeMove(x, y);
          System.out.println(x);
          System.out.println(y);
          this.view.render();
          this.notifyListener(this);
        } else {
        }
      }
    }
  }


  @Override
  public void addListener(IReversiController controller) {
    this.observers.add(controller);
  }

  @Override
  public void removeListener(IReversiController controller) {
    this.observers.remove(controller);
  }

  @Override
  public void notifyListener(IReversiController controller) {
    for(IReversiController c : this.observers) {
      c.displayView(controller);
    }
  }

  @Override
  public void displayView(IReversiController controller) {
    this.view.render();
  }
}
