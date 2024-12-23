package org.main;

import org.main.controller.GameController;
import org.main.repository.GameHttpRepo;
import org.main.services.GameService;

public class Main {
    public static void main(String[] args) {
        GameHttpRepo gameHttpRepo = new GameHttpRepo();
        GameService gameService = new GameService(gameHttpRepo);
        GameController gameController = new GameController(gameService);
        gameController.run();

    }
}