package controller;

import model.StatusCodes;

public class PlayerListener implements IPlayerListener {
  private int x;
  private int y;

  public PlayerListener() {
    this.x = - 1;
    this.y = - 1;
  }

  @Override
  public void update(int x, int y) {
    this.x = x;
    this.y = y;
  }

  @Override
  public int getX() {
    return this.x;
  }

  @Override
  public int getY() {
    return this.y;
  }
}