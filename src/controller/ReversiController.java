package controller;

import model.ReversiModel;
import player.Player;
import view.ReversiView;

public class ReversiController implements IReversiController{
  private final Player player;
  private final ReversiModel model;
  private final ReversiView view;

  public ReversiController(Player player, ReversiModel model, ReversiView view) {
    this.player = player;
    this.model = model;
    this.view = view;
  }

  public void go() {
    //
  }


}
