package controller;

import model.StatusCodes;

public interface IPlayerListener {
  void update(int x, int y);

 int getX();

  int getY();
}
