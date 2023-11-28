package controller;

public interface IReversiController {

  void go();
  void addListener(IReversiController c);
  void removeListener(IReversiController c);
  void notifyListener(IReversiController c);
  void updateView(IReversiController c);

}
