package org.main.controller;

import org.main.models.Game;
import org.main.models.UserRequest;
import org.main.services.GameService;

import java.util.Scanner;

public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    private void searchGameByName(String name) {
        UserRequest userRequest = new UserRequest();
        userRequest.setName(name);
        Game game = gameService.fetchGames(userRequest).getFirst();
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
            System.out.println("1. Ajouter un nom");
            System.out.println("2. Ajouter un filtre");
            System.out.println("3. Quitter");
            String option = scanner.nextLine().trim();

            switch(option) {
                case "1": // Ajout d'un nom à la recherche
                    System.out.print("Entrez un nom: ");
                    String name = scanner.nextLine();
                    searchGameByName(name);
                    break;
                case "2":

                case "3":
                    System.out.println("Au revoir...");
                    return;

                default:
                    System.out.println("Option invalide, veuillez réessayer");
            }
        }
    }
}
