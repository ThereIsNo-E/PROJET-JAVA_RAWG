package org.main.controller;

import org.main.models.Game;
import org.main.repository.GameHttpRepo;

import java.util.Scanner;

public class GameController {
    private final GameHttpRepo gameHttpRepo;

    public GameController(GameHttpRepo gameHttpRepo) {
        this.gameHttpRepo = gameHttpRepo;
    }

    private void searchGameByName(String name) {
        Game game = gameHttpRepo.fetchGamesByName(name).getFirst();
        if(game != null) {
            System.out.println(game);
        }
        else {
            System.out.println("Ce jeu n'existe pas");
        }
    }
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenu dans l'application de recherche de jeux vidéos");

        while(true) {
            System.out.println("Quelle action souhaitez-vous exécuter ?");
            System.out.println("1. Recherche par nom");
            System.out.println("2. Quitter");
            String option = scanner.nextLine().trim();

            switch(option) {
                case "1":
                    System.out.print("Entrez un nom: ");
                    String name = scanner.nextLine();
                    searchGameByName(name);
                    break;
                case "2":
                    System.out.println("Au revoir...");
                    return;

                default:
                    System.out.println("Option invalide, veuillez réessayer");
            }
        }
    }
}
