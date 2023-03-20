import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Theatre {

    //Initialize scanner & to read input
    static Scanner scanner = new Scanner(System.in);

    // Declare & initialize 3 arrays, one for each row.
    static int[] row_1 = new int[12];
    static int[] row_2 = new int[16];
    static int[] row_3 = new int[20];

    // Initialize an ArrayList to hold all the tickets
    static ArrayList<Ticket> tickets = new ArrayList<>();

    //Main method
    public static void main(String[] args) {
        System.out.println("Welcome to the New Theatre!"); //Prints a Welcome message to the console

        while (true){
            System.out.println("------------------------------------------------------------------");
            System.out.println("Please select an option: ");
            System.out.println(" 1) Buy a ticket ");
            System.out.println(" 2) Print seating area ");
            System.out.println(" 3) Cancel ticket");
            System.out.println(" 4) List available seats");
            System.out.println(" 5) Save to file");
            System.out.println(" 6) Load from file");
            System.out.println(" 7) Print ticket information and total price");
            System.out.println(" 8) Sort tickets by price");
            System.out.println("   0) Quit");
            System.out.println("----------------------------------------------------------");

            System.out.print("Enter option : ");
            int option = scanner.nextInt(); //Reads the user's input from the console and store it in the option variable.

            switch (option){
                case 1:
                    buy_ticket();
                    break;
                case 2:
                    print_seating_area();
                    break;
                case 3:
                    cancel_ticket();
                    break;
                case 4:
                    show_available();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    load();
                    break;
                case 7:
                    show_tickets();
                    break;
                case 8:
                    sort_tickets_info();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option selected.");
            }
        }
    }

    public static void buy_ticket() { //Method to buy tickets

        //Ask the user for the row and seat numbers
        System.out.println("Enter the row number[1-3]: ");
        int row_num = scanner.nextInt(); //reads the user's input

        //Check weather the seat is available
        while(row_num < 1 || row_num > 3) {
            System.out.println("Invalid row number.Please enter again.");
            row_num = scanner.nextInt();
        }

        System.out.println("Enter the seat number: ");
        int seat_num = scanner.nextInt();


        double price = 0;
        switch (row_num) {
            case 1 :
                while (seat_num > row_1.length || seat_num < 1) {
                    System.out.println("Invalid seat number.Please enter again.");
                    seat_num = scanner.nextInt();
                }
                row_1[seat_num - 1] = 1;
                price = 30;
                break;

            case 2 :
                while (seat_num > row_2.length || seat_num < 1) {
                    System.out.println("Invalid seat number.Please enter again.");
                    seat_num = scanner.nextInt();
                }
                row_2[seat_num - 1] = 1;
                price = 20;
                break;

            case 3 :
                while (seat_num > row_3.length || seat_num < 1) {
                    System.out.println("Invalid seat number.Please enter again.");
                    seat_num = scanner.nextInt();
                }
                row_3[seat_num - 1] = 1;
                price = 10;
                break;
        }
        //Getting details of person
        System.out.println("Enter first name: ");
        String firstName = scanner.next();
        System.out.println("Enter surname: ");
        String lastName = scanner.next();
        System.out.println("Enter email: ");
        String email = scanner.next();

        //Creating objects, person and ticket
        Person user = new Person(firstName, lastName, email);
        Ticket ticket1 = new Ticket(row_num,seat_num,price, user);
        tickets.add(ticket1);
        System.out.println(tickets.size());
    }

    public static void print_seating_area(){  //Method to show the seats that are sold and seats that are still available.
        System.out.println("     ***********");
        System.out.println();
        System.out.println("     *  STAGE  *");
        System.out.println();
        System.out.println("     ***********");
        System.out.print("    ");
        for (int i = 0; i < (row_1.length); i++){
            if (i == 6){
                System.out.print(" ");
            }
            if (row_1[i] == 0){
                System.out.print('O');
            }
            else{
                System.out.print('X');
            }
        }
        System.out.println();
        System.out.print("  ");
        for (int j = 0; j < (row_2.length); j++){
            if (j == 8){
                System.out.print(" ");
            }
            if (row_2[j] == 0){
                System.out.print('O');
            }
            else{
                System.out.print('X');
            }
        }
        System.out.println();
        for(int k = 0; k < (row_3.length); k++){
            if (k == 10){
                System.out.print(" ");
            }
            if (row_3[k] == 0){
                System.out.print('O');
            }
            else{
                System.out.print('X');
            }
        }
        System.out.println();

    }
    public static void cancel_ticket() {    //Method to cancel tickets
        System.out.println("Enter the row number to cancel: ");
        int cancel_row = scanner.nextInt();
        while (cancel_row < 1 || cancel_row > 3) {
            System.out.println("Invalid row number. Please enter the correct row number: ");
            cancel_row = scanner.nextInt();
        }

        System.out.println("Enter the seat number to cancel: ");
        int cancel_seat = scanner.nextInt();
        System.out.println("Your cancellation is successful.");


        switch (cancel_row) {
            case 1:
                while (cancel_seat > row_1.length || cancel_seat < 1) {
                    System.out.println("Invalid seat number.Please enter the correct seat number: ");
                    cancel_seat = scanner.nextInt();
                }
                row_1[cancel_seat - 1] = 0;
            case 2:
                while (cancel_seat > row_2.length || cancel_seat < 1) {
                    System.out.println("Invalid seat number. Please enter the correct seat number: ");
                    cancel_seat = scanner.nextInt();
                }
                row_2[cancel_seat - 1] = 0;
            case 3:
                while (cancel_seat > row_3.length || cancel_seat < 1) {
                    System.out.println("Invalid seat number.Please enter the correct seat number: ");
                    cancel_seat = scanner.nextInt();
                }
                row_3[cancel_seat - 1] = 0;
        }
        for(Ticket ticket: tickets){
            if(ticket.getRow() == cancel_row && ticket.getSeat() == cancel_seat){
                tickets.remove(ticket);
            }
        }
    }

    public static void show_available(){ //Methods to display available  seats in each row
        System.out.println();
        System.out.println("Seats available in row 1: ");
        for (int i = 0; i < row_1.length; i++){
            if (row_1[i] == 0){
                System.out.print(" " + (i+1));
                if (i == row_1.length - 1){
                    System.out.print(".");
                } else {
                    System.out.print(",");
                }
            }
        }
        System.out.println();
        System.out.println("Seats available in row 2: ");
        for (int i = 0; i < row_2.length; i++) {
            if (row_2[i] == 0) {
                System.out.print(" " + (i + 1));
                if (i == row_2.length - 1){
                    System.out.print(".");
                } else {
                    System.out.print(",");
                }
            }
        }
        System.out.println();
        System.out.println("Seats available in row 3: ");
        for (int i = 0; i < row_3.length; i++){
            if (row_3[i] == 0){
                System.out.print(" " + (i+1));
                if ( i == row_3.length - 1){
                    System.out.println(".");
                } else {
                    System.out.print(",");
                }
            }
        }
        System.out.println();
    }
    //Method to save the three arrays with row's information in a file
    public static void save(){
        File tickets = new File("Tickets.txt");
        try{
            FileWriter file_write = new FileWriter(tickets);
            file_write.write("Seats available in row 1: ");
            for (int i = 0; i < row_1.length; i++){
                if (row_1[i] == 0) {
                    file_write.write(" " + (i + 1));
                    if (i == row_1.length - 1){
                        file_write.write(".");
                    } else{
                        file_write.write(",");
                    }
                }
            }

            file_write.write("\n");
            file_write.write("Seats available in row 2: ");
            for (int i = 0; i < row_2.length; i++){
                if (row_2[i] == 0){
                    file_write.write(" " + (i+1));
                    if (i == row_2.length - 1){
                        file_write.write(".");
                    } else {
                        file_write.write(",");
                    }
                }
            }
            file_write.write("\n");
            file_write.write("Seats available in row 3: ");
            for (int i = 0; i < row_3.length; i++){
                if (row_3[i] == 0){
                    file_write.write(" " + (i+1));
                    if (i == row_3.length - 1){
                        file_write.write(".");
                    } else {
                        file_write.write(",");
                    }
                }
            }
            file_write.close();
        }
        catch (IOException e){
            e.printStackTrace();
            System.out.println("An error occurred.");
        }

    }
    //method to load data from a file
    public static void load(){
        //Create a File object to represent the Tickets.txt file
        File tickets = new File ("Tickets.txt");
        try{
            Scanner read_file = new Scanner(tickets);
            //Read the file line by line
            while (read_file.hasNextLine()){
                String data = read_file.nextLine();
                System.out.println(data); //Print each line of data to the console
            }
        }
        catch(FileNotFoundException e){
            System.out.println("An error occurred");
        }
    }
    public static void show_tickets_info(){
        double total = 0;
        //iterate through the tickets array and print each tickets information
        for (Ticket ticket : tickets){
            ticket.print();
            total = total + ticket.getPrice(); //calculate the total price of all the ticket
        }
        System.out.println("Your total is: " + total); //print the total price to the console
    }

    public static void sort_tickets(){ //Method to return a new list of tickets sorted by ticket in ascending order
        Collections.sort(tickets, new Comparator<Ticket>(){
            public int compare(Ticket o1, Ticket o2){
                return Double.compare(o1.getPrice(), o2.getPrice());
            }
        });
        for (Ticket ticket: tickets){
            ticket.print();
        }
    }
}



