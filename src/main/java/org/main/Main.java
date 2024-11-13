package org.main;

import org.main.controller.GameController;
import org.main.repository.GameHttpClient;

public class Main {
    public static void main(String[] args) {
        GameHttpClient gameHttpClient = new GameHttpClient();
        GameController gameController = new GameController(gameHttpClient);
        gameController.run();
    }
}