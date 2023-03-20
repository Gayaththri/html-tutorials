public class Ticket {
    private int row;
    private int seat;
    private double price;
    private Person person;

    public Ticket(int row, int seat, double price, Person person){
        this.row = row;
        this.seat = seat;
        this.price = price;
        this.person = person;

    }

    // Getters and setters for row, seat, price, and person
    public int getRow(){
        return row;
    }

    public void setRow(int row){
        this.row = row;
    }

    public int getSeat(){
        return seat;
    }

    public void setSeat(int seat){
        this.seat = seat;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public Person getPerson(){
        return person;
    }

    public void setPerson(Person person){
        this.person = person;
    }

    // Method to print the ticket information
    public void print(){
        System.out.println("Name : " + getPerson().getName());
        System.out.println("Surname : " + getPerson().getSurname());
        System.out.println("Email : " + getPerson().getEmail());
        System.out.println("Row number : " + getRow());
        System.out.println("Seat number : " + getSeat());
        System.out.println("Price : " + getPrice());
        System.out.println();
    }
}
