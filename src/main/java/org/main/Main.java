package org.main;

import org.main.controller.GameController;
import org.main.repository.GameHttpRepo;

public class Main {
    public static void main(String[] args) {
        GameHttpRepo gameHttpRepo = new GameHttpRepo();
        GameController gameController = new GameController(gameHttpRepo);
        gameController.run();
    }
}