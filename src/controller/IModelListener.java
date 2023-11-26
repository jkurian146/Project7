package controller;

import model.StatusCodes;

public interface IModelListener {
  void update(StatusCodes sc, String message);
}
