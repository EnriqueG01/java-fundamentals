package cinema;
import java.util.Scanner;
import java.util.Arrays;

public class Cinema {
    public static int getTotalIncome(int rows, int seats) {
        int generalTicketPrice = 10;
        int ticketFirstPrice = 10;
        int ticketSecondPrice = 8;
        int totalSeats = rows * seats;
        int seatsFirstHalf = 0;
        int totalIncome = 0;
        
        if (totalSeats > 60) {
            seatsFirstHalf = rows / 2;
            totalIncome = (ticketFirstPrice * seatsFirstHalf * seats) + (ticketSecondPrice * (rows - seatsFirstHalf) * seats);
        } else {
            totalIncome = generalTicketPrice * rows * seats;
        }
        return totalIncome;
    }
    
    public static String[][] clearSeats(int rows, int seats) {
        String[][] arrayAllSeats = new String[rows + 1][seats + 1];
        
        for (String ch[] : arrayAllSeats) {
            Arrays.fill(ch, "S");
        }
        for (int i = 0; i <= seats; i++) {
            if (i == 0) {
                arrayAllSeats[0][i] = " ";
            } else {
                arrayAllSeats[0][i] = String.valueOf(i);
            }
        }
        for (int i = 0; i <= rows; i++) {
            if (i == 0) {
                arrayAllSeats[i][0] = " ";
            } else {
                arrayAllSeats[i][0] = String.valueOf(i);
            }
        }
        //showSeats(rows, seats, arrayAllSeats);
        return arrayAllSeats;
    }
    
    public static void showSeats(int rows, int seats, String[][] seatsArray) {
        System.out.print("Cinema:");
        for (int i = 0; i <= rows; i++) {
            for (int j = 0; j <= seats; j++){
                System.out.print(seatsArray[i][j] + " ");
            }
            System.out.print("\n");
        }
    }
    
    public static boolean validData(int rows, int seats, int seatRow, int seatNumber, String[][] seatsArray) {
        if (seatRow > 0 && seatRow <= rows && seatNumber > 0 && seatNumber <= seats) {
            if (seatsArray[seatRow][seatNumber] == "S") {
                return true;
            } else {
                System.out.println("That ticket has already been purchased!");
            }
        } else {
            System.out.println("Wrong input!");
        }
        return false;
    }
    
    public static String[][] buyTicket(String[][] seatsArray, int seatRow, int seatNumber) {
        seatsArray[seatRow][seatNumber] = "B";
        return seatsArray;
    }
    
    public static int getTicketPrice(int rows, int seats, int seatRow, int seatNumber) {
        int generalTicketPrice = 10;
        int ticketFirstPrice = 10;
        int ticketSecondPrice = 8;
        int totalSeats = rows * seats;
        int seatsFirstHalf = 0;
        int selectedTicketPrice = 0;
        
        if (totalSeats > 60) {
            seatsFirstHalf = rows / 2;
            if (seatRow <= seatsFirstHalf) {
                selectedTicketPrice = ticketFirstPrice;
            } else {
                selectedTicketPrice = ticketSecondPrice;
            }
        } else {
            selectedTicketPrice = generalTicketPrice;
        }
        System.out.println("Ticket price: $" + selectedTicketPrice);
        return selectedTicketPrice;
    }
    
    public static float getBoughtTicketsPercentage(int totalTickets, int boughtTickets) {
        float result = 100 * ((float) boughtTickets / (float) totalTickets);
        return result;
    }
    
    public static void statistics(int boughtTickets, float boughtTicketsPercentage, int currentIncome, int totalIncome) {
        System.out.println("Number of purchased tickets: " + boughtTickets);
        System.out.format("Percentage: %.2f%%", boughtTicketsPercentage);
        System.out.println("\nCurrent income: $" + currentIncome);
        System.out.println("Total income: $" + totalIncome);
    }

    public static void main(String[] args) {
        // Write your code here
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the number of rows:");
        int rows = sc.nextInt();
        System.out.println("Enter the number of seats in each row:");
        int seats = sc.nextInt();
        int seatRow = 0;
        int seatNumber = 0;
        int menuOption = 0;
        int totalTickets = rows * seats;
        int boughtTickets = 0;
        int currentIncome = 0;
        int totalIncome = getTotalIncome(rows, seats);
        float boughtTicketsPercentage;
        String[][] seatsArray = clearSeats(rows, seats);
        
        while (true) {
            System.out.println("\n1. Show the seats");
            System.out.println("2. Buy a ticket");
            System.out.println("3. Statistics");
            System.out.println("0. Exit");
            menuOption = sc.nextInt();
            
            switch(menuOption) {
                case 1:
                    showSeats(rows, seats, seatsArray);
                    break;
                case 2:
                    while (true) {
                        System.out.println("\nEnter a row number:");
                        seatRow = sc.nextInt();
                        System.out.println("Enter a seat number in that row:");
                        seatNumber = sc.nextInt();
                        if (validData(rows, seats, seatRow, seatNumber, seatsArray)) {
                            seatsArray = buyTicket(seatsArray, seatRow, seatNumber);
                            currentIncome += getTicketPrice(rows, seats, seatRow, seatNumber);
                            ++boughtTickets;
                            break;
                        }
                    }
                    break;
                case 3:
                    boughtTicketsPercentage = getBoughtTicketsPercentage(totalTickets, boughtTickets);
                    statistics(boughtTickets, boughtTicketsPercentage, currentIncome, totalIncome);
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid input!");
                    break;
            }
        }

    }
}