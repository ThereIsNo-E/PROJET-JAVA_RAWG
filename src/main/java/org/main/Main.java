package org.main;

import org.main.controller.GameController;
import org.main.repository.GameDAO;

public class Main {
    public static void main(String[] args) {
        GameDAO gameDAO = new GameDAO();
        GameController gameController = new GameController(gameDAO);
        gameController.run();
    }
}