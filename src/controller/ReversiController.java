package controller;

import model.GameState;
import model.ReversiHexModel;
import model.ReversiModel;
import player.Player;
import player.PlayerTurn;
import view.ReversiGUI;
import view.ReversiView;

interface ControllerListener {
    void handleEvent();
}
public class ReversiController {

    private final ReversiHexModel reversiModel;
    private final ReversiGUI reversiView;

    private final Player player;

    private ModelListener modelListener;
    private PlayerListener playerListener;


    public ReversiController(ReversiHexModel reversiModel, ReversiGUI reversiView, Player player) {
        this.reversiModel = reversiModel;
        this.reversiView = reversiView;
        this.player = player;
        this.modelListener = new ModelListener();
        this.reversiModel.addListener(this.modelListener);
        this.playerListener = new PlayerListener();
        this.reversiView.addListener(this.playerListener);
        listen();
    }

    public void listen() {
        PlayerTurn controllersPlayerTurn = this.player.getPlayerTurn();
        PlayerTurn modelsPlayerTurn = this.reversiModel.currentTurn();
        ModelEvent modelEvent = this.modelListener.getMostRecentEvent();
        if (modelEvent.getModelEventType() ==  ModelEventType.PLAYER1TURN) {
            if (modelsPlayerTurn == PlayerTurn.PLAYER1 && controllersPlayerTurn == PlayerTurn.PLAYER1) {
                // listen for a PlayerListener move
                PlayerEvent playerEvent = getNextPlayerAction();
                if (playerEvent.getExecutingPlayer() == PlayerTurn.PLAYER1) {
                    handlePlayerEvent(playerEvent);
                    this.reversiView.render();
                    System.out.println("Controller for "
                            + this.player.getPlayerTurn() + " "
                            + this.modelListener.getMostRecentEvent().getModelEventType());
                }
                else {
                    // display pane illegal move not your turn
                    this.reversiView.showPopup(modelEvent.getMessage());
                }
            } else {
                // display pane for illegal move: not your turn
                this.reversiView.showPopup(modelEvent.getMessage());
            }
        }
        else if (modelEvent.getModelEventType() == ModelEventType.PLAYER2TURN) {
            if (modelsPlayerTurn == PlayerTurn.PLAYER2 && controllersPlayerTurn == PlayerTurn.PLAYER2) {
                // listen for a PlayerListener move
                PlayerEvent playerEvent = getNextPlayerAction();
                if (playerEvent.getExecutingPlayer() == PlayerTurn.PLAYER1) {
                    handlePlayerEvent(playerEvent);
                }
                else {
                    this.reversiView.showPopup(modelEvent.getMessage());
                }
            } else {
                this.reversiView.showPopup(modelEvent.getMessage());
            }
        }
        else if (modelEvent.getModelEventType() == ModelEventType.TIE) {
            // display tie pane to both controllers views
            this.reversiView.showPopup(modelEvent.getMessage());
        } else if (modelEvent.getModelEventType() == ModelEventType.ILLEGALMOVE) {
            // display pane to this controllers view
        } else if (modelEvent.getModelEventType() == ModelEventType.PLAYER1WON) {
            // display pane to both
            this.reversiView.showPopup(modelEvent.getMessage());
        } else if (modelEvent.getModelEventType() == ModelEventType.PLAYER2WON) {
            // display pane to both
            this.reversiView.showPopup(modelEvent.getMessage());
        }
    }
    private PlayerEvent getNextPlayerAction() {
        PlayerEvent nextPlayerAction = null;
        while (nextPlayerAction == null) {
            nextPlayerAction = this.playerListener.getMostRecentEvent();
        }
        this.playerListener.resetPlayerActions();
        return nextPlayerAction;
    }

    private void handlePlayerEvent(PlayerEvent playerEvent) {
        if (playerEvent.getPlayerEventType() == PlayerEventType.PASS) {
            this.reversiModel.pass();
            this.reversiView.showPopup(this.player.getPlayerTurn() + " passed");
        } else {
            String[] coordinates = playerEvent.getDescription().split(" ");
            this.reversiModel.makeMove(Integer.parseInt(coordinates[0]),
                    Integer.parseInt(coordinates[1]));
            this.reversiView.showPopup("made a move for " + this.player.getPlayerTurn());
        }
    }
}
