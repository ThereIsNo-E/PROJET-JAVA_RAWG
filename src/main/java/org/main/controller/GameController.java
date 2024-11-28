package org.main.controller;

import org.main.models.Game;
import org.main.models.UserRequest;
import org.main.services.GameService;
import org.main.utils.GenreInfo;

import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class GameController {
    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    private void addGameName(String name, UserRequest userRequest) {
        userRequest.setName(name);
    }

    private void showGames(UserRequest userRequest) {
        Game game = gameService.fetchGames(userRequest).getFirst();
        if(game != null) {
            System.out.println(game);
        }
        else {
            System.out.println("Aucuns jeux correspondants");
        }
    }

    private void addGameFilter(String option, UserRequest userRequest, Scanner scanner) {
        switch(option) {
            case "1":
                System.out.println("Choisissez une plateforme");
                break;
            case "2":
                AtomicInteger count = new AtomicInteger(1);
                List<GenreInfo> genres = gameService.fetchGenres();
                genres.forEach(genre -> System.out.println(count.getAndIncrement() + ". "
                        + genre.getName()));
                System.out.println("Choisissez un genre");
                int choice = scanner.nextInt();
                userRequest.addGenre(genres.get(choice-1));
                break;
            case "3":
                System.out.println("Choisissez un magasin");
                break;
            default:
                System.out.println("Option invalide, veuillez réessayer");
        }
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Bienvenu dans l'application de recherche de jeux vidéos");
        UserRequest userRequest = new UserRequest();
        while(true) {
            System.out.println("Quelle action souhaitez-vous exécuter ?");
            System.out.println("1. Ajouter un nom");
            System.out.println("2. Ajouter un filtre");
            System.out.println("3. Envoyer la requête");
            System.out.println("4. Quitter");
            String option = scanner.nextLine().trim();

            switch(option) {
                case "1": // Ajout d'un nom à la recherche
                    System.out.print("Entrez un nom: ");
                    String name = scanner.nextLine().trim();
                    addGameName(name, userRequest);
                    break;
                case "2": // Choix du filtre à ajouter
                    System.out.println("Choisissez un filtre");
                    System.out.println("1. Plateforme");
                    System.out.println("2. Genre");
                    System.out.println("3. Magasin");
                    String filterOption = scanner.nextLine().trim();
                    addGameFilter(filterOption, userRequest, scanner);
                    break;
                case "3":
                    System.out.println("Envoi de la requête...");
                    showGames(userRequest);
                    userRequest.reset();
                    break;
                case "4":
                    System.out.println("Au revoir...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Option invalide, veuillez réessayer");
            }
        }
    }
}
