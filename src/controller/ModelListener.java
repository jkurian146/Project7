package controller;

import java.util.HashMap;

import model.StatusCodes;

public class ModelListener implements IModelListener {
  private StatusCodes currStatusCodes = StatusCodes.PRESTART;
  private String currMessage = "";

  @Override
  public void update(StatusCodes sc, String message) {
    this.currStatusCodes = sc;
    this.currMessage = message;
  }
}
