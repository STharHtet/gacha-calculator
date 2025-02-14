// import java.util.*;

// public class Main {
//     public static void main(String[] args) {
//         // Load banners from JSON file
//         List<Banner> banners = Banner.loadBannersFromJson("banners.json");

//         // Get player input
//         Scanner scanner = new Scanner(System.in);
//         System.out.print("Enter your current gem count: ");
//         int currentGems = scanner.nextInt();
//         System.out.print("Enter your gems earned per banner period: ");
//         int gemsPerBanner = scanner.nextInt();
//         System.out.print("Enter gems required per pull: ");
//         int gemsPerPull = scanner.nextInt();
//         System.out.print("Enter guarantee pull count: ");
//         int guaranteePullCount = scanner.nextInt();
//         scanner.nextLine(); 

//         System.out.print("Enter your desired characters (comma separated): ");
//         String charactersInput = scanner.nextLine();
//         String[] characterNames = charactersInput.split(",");
//         List<Character> desiredCharacters = new ArrayList<>();
//         for (String name : characterNames) {
//             desiredCharacters.add(new Character(name.trim()));
//         }

//         // Create player object
//         Player player = new Player(currentGems, gemsPerBanner, gemsPerPull, guaranteePullCount, desiredCharacters);
//         int availablePulls = player.calculateAvailablePulls();
//         System.out.println("\nInitial Available Pulls: " + availablePulls);

//         // Build a map to track when each desired character appears
//         Map<String, Integer> characterLastAppearance = new HashMap<>();
//         for (int i = 0; i < banners.size(); i++) {
//             String featuredCharacter = banners.get(i).getFeaturedCharacter().toLowerCase();
//             for (Character desired : desiredCharacters) {
//                 if (featuredCharacter.contains(desired.getName().toLowerCase())) {
//                     characterLastAppearance.put(desired.getName().toLowerCase(), i);
//                 }
//             }
//         }

//         // Process banners and make decisions dynamically
//         for (int i = 0; i < banners.size(); i++) {
//             Banner banner = banners.get(i);
//             System.out.println("\nProcessing " + banner.toString());

//             boolean isDesired = false;
//             String featuredCharacter = banner.getFeaturedCharacter().toLowerCase();
//             for (Character c : desiredCharacters) {
//                 if (featuredCharacter.contains(c.getName().toLowerCase())) {
//                     isDesired = true;
//                     break;
//                 }
//             }

//             String decision = "Skip";

//             if (isDesired) {
//                 // Check if this character appears later
//                 boolean appearsLater = characterLastAppearance.get(featuredCharacter) != null &&
//                         characterLastAppearance.get(featuredCharacter) > i;

//                 if (appearsLater) {
//                     decision = "Skip"; // Character appears later, so we save resources
//                 } else {
//                     if (availablePulls > player.getGuaranteePullCount()) {
//                         decision = "Indulge"; // Excess pulls available
//                         availablePulls -= player.getGuaranteePullCount(); // Spend 90 pulls
//                     } else if (availablePulls >= player.getGuaranteePullCount()) {
//                         decision = "Pull"; // Just enough pulls available
//                         availablePulls -= player.getGuaranteePullCount();
//                     }
//                 }
//             }

//             System.out.println("Decision for " + banner.getFeaturedCharacter() + ": " + decision);
//             availablePulls += (player.getGemsPerBanner() / player.getGemsPerPull()); // Earn new pulls
//             System.out.println("Available Pulls after processing: " + availablePulls);
//         }

//         scanner.close();
//     }

    
// }

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    private static final String JSON_FILE = "banners.json";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Ask user if they want to add a new banner first
        System.out.print("Do you want to add a new banner? (yes/no): ");
        String addBannerChoice = scanner.nextLine().trim().toLowerCase();

        if (addBannerChoice.equals("yes")) {
            Banner.addNewBanner(JSON_FILE);
        }

        // Load banners from JSON file
        List<Banner> banners = Banner.loadBannersFromJson(JSON_FILE);

        // Get player input
        System.out.print("\nEnter your current gem count: ");
        int currentGems = scanner.nextInt();
        System.out.print("Enter your gems earned per banner period: ");
        int gemsPerBanner = scanner.nextInt();
        System.out.print("Enter gems required per pull: ");
        int gemsPerPull = scanner.nextInt();
        System.out.print("Enter guarantee pull count: ");
        int guaranteePullCount = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        System.out.print("Enter your desired characters (comma separated): ");
        String charactersInput = scanner.nextLine();
        String[] characterNames = charactersInput.split(",");
        List<Character> desiredCharacters = new ArrayList<>();
        for (String name : characterNames) {
            desiredCharacters.add(new Character(name.trim()));
        }

        // Create player object
        Player player = new Player(currentGems, gemsPerBanner, gemsPerPull, guaranteePullCount, desiredCharacters);
        int availablePulls = player.calculateAvailablePulls();
        System.out.println("\nInitial Available Pulls: " + availablePulls);

        // Process banners and make decisions dynamically
        for (Banner banner : banners) {
            System.out.println("\nProcessing " + banner.toString());

            boolean isDesired = false;
            for (Character c : desiredCharacters) {
                if (banner.getFeaturedCharacter().toLowerCase().contains(c.getName().toLowerCase())) {
                    isDesired = true;
                    break;
                }
            }

            String decision = "Skip";
            if (isDesired) {
                if (availablePulls > player.getGuaranteePullCount()) {
                    decision = "Indulge";
                    availablePulls -= player.getGuaranteePullCount();
                } else if (availablePulls >= player.getGuaranteePullCount()) {
                    decision = "Pull";
                    availablePulls -= player.getGuaranteePullCount();
                }
            }

            System.out.println("Decision for " + banner.getFeaturedCharacter() + ": " + decision);
            availablePulls += (player.getGemsPerBanner() / player.getGemsPerPull());
            System.out.println("Available Pulls after processing: " + availablePulls);
        }

        scanner.close();
    }
}
