package machine;
import java.util.Scanner;
import java.util.Arrays;

public class CoffeeMachine {
    public static int[] fillMachine(Scanner sc, int[] availableResources){
        System.out.println("Write how many ml of water you want to add: ");
        int addedWater = sc.nextInt();
        System.out.println("Write how many ml of milk you want to add: ");
        int addedMilk = sc.nextInt();
        System.out.println("Write how many grams of coffee beans you want to add: ");
        int addedCoffee = sc.nextInt();
        System.out.println("Write how many disposable cups of coffee you want to add: ");
        int addedDisposableCups = sc.nextInt();

        availableResources[0] += addedWater;
        availableResources[1] += addedMilk;
        availableResources[2] += addedCoffee;
        availableResources[3] += addedDisposableCups;
        return availableResources;
    }
    
    public static void getIngredients(int cups) {
        int waterOneCup = 200;
        int milkOneCup = 50;
        int coffeeOneCup = 15;
        int totalWater = waterOneCup * cups;
        int totalMilk = milkOneCup * cups;
        int totalCoffee = coffeeOneCup * cups;
        
        System.out.printf("For %d cups of coffee you will need:\n", cups);
        System.out.printf("%d ml of water\n", totalWater);
        System.out.printf("%d ml of milk\n", totalMilk);
        System.out.printf("%d g of coffee beans\n", totalCoffee);
    }
    
    public static int getCupsWithAvailableIngredients(int[] resourcesPerCup, int[] availableResources) {
        //how many cups the machine can make
        //if there are more than zero disposable cups, continue
        if (availableResources[3] > 0) {
            int waterOneCup = resourcesPerCup[0];
            int milkOneCup = resourcesPerCup[1];
            int coffeeBeansOneCup = resourcesPerCup[2];
            int cupsByWater = availableResources[0] / waterOneCup;
            int cupsByMilk = milkOneCup == 0 ? 1000 : availableResources[1] / milkOneCup;
            int cupsByCoffee = availableResources[2] / coffeeBeansOneCup;

            if (cupsByWater > 0) {
                if (cupsByMilk > 0) {
                    if (cupsByCoffee > 0) {
                        int[] cupsArray = {cupsByWater, cupsByMilk, cupsByCoffee};
                        Arrays.sort(cupsArray);
                        if (cupsArray[0] <= availableResources[3]) {
                            return cupsArray[0];
                        } else {
                            return availableResources[3];
                        }
                    } else {
                        System.out.println("Sorry, not enough coffee beans!");
                    }
                } else {
                    System.out.println("Sorry, not enough milk!");
                }
            } else {
                System.out.println("Sorry, not enough water!");
            }
        } else {
            System.out.println("Sorry, not enough disposable cups!");
        }
        return 0;
    }
    
    public static void compareWantedVsAvailableCups(int availableCups, int wantedCups) {
        if (availableCups == wantedCups) {
            System.out.println("Yes, I can make that amount of coffee");
        } else if (availableCups > wantedCups) {
            System.out.printf("Yes, I can make that amount of coffee (and even %d more than that)%n", availableCups-wantedCups);
        } else {
            System.out.printf("No, I can make only %d cup(s) of coffee", availableCups);
        }
    }

    public static void printMachineState(int[] availableResources) {
        System.out.println("\nThe coffee machine has:");
        System.out.printf("%d ml of water\n", availableResources[0]);
        System.out.printf("%d ml of milk\n", availableResources[1]);
        System.out.printf("%d g of coffee beans\n", availableResources[2]);
        System.out.printf("%d disposable cups\n", availableResources[3]);
        System.out.printf("$%d of money\n\n", availableResources[4]);
    }

    public static int takeMoney(int money) {
        System.out.printf("I gave you $%d%n", money);
        return 0;
    }

    public static int[] buyCoffee(Scanner sc, int[] availableResources) {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String coffeeType = sc.next();
        int coffeePrice = 0;
        int waterNeededPerCup = 0;
        int milkNeededPerCup = 0;
        int coffeeBeansNeededPerCup = 0;
        boolean makeModifications = true;
        switch (coffeeType) {
            case "1":
                waterNeededPerCup = 250;
                coffeeBeansNeededPerCup = 16;
                coffeePrice = 4;
                break;
            case "2":
                waterNeededPerCup = 350;
                milkNeededPerCup = 75;
                coffeeBeansNeededPerCup = 20;
                coffeePrice = 7;
                break;
            case "3":
                waterNeededPerCup = 200;
                milkNeededPerCup = 100;
                coffeeBeansNeededPerCup = 12;
                coffeePrice = 6;
                break;
            case "back":
                makeModifications = false;
                break;
            default:
                System.out.println("Wrong input!");
                makeModifications = false;
                break;
        }
        if (makeModifications) {
            int[] resourcesPerCup = {waterNeededPerCup, waterNeededPerCup, coffeeBeansNeededPerCup};
            boolean canPrepareCoffee = getCupsWithAvailableIngredients(resourcesPerCup, availableResources) > 0 ? true: false;
            if (canPrepareCoffee) {
                System.out.println("I have enough resources, making you a coffee!");
                availableResources[0] -= waterNeededPerCup;
                availableResources[1] -= milkNeededPerCup;
                availableResources[2] -= coffeeBeansNeededPerCup;
                availableResources[3]--;
                availableResources[4] += coffeePrice;
            }
        }
        return availableResources;
    }
        
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        /*System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");*/
        /*
        //third stage
        int[] availableIngredients = fillMachine(sc);
        int availableCups = getCupsWithAvailableIngredients(availableIngredients[0], availableIngredients[1],availableIngredients[2]);
        System.out.println("Write how many cups of coffee you will need: ");
        int wantedCups = sc.nextInt();
        compareWantedVsAvailableCups(availableCups, wantedCups);
        */
        int[] availableResources = {400, 540, 120, 9, 550}; //water, milk, coffee beans, disposable cups, money
        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String action = sc.next();
            switch (action) {
                case "buy":
                    availableResources = buyCoffee(sc, availableResources);
                    break;
                case "fill":
                    availableResources = fillMachine(sc, availableResources);
                    break;
                case "take":
                    availableResources[4] = takeMoney(availableResources[4]);
                    break;
                case "remaining":
                    printMachineState(availableResources);
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Wrong input! MF");
                    break;
            }
        }
    }
}
