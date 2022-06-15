/*
 * Name: Siddhartha Pudasaini
 * Student ID: 1222261339
 * Assignment #: 10
 * Class : T Th 4:30
 * Class description: The table class consists of data such as name of the reservation and total number of guest in
 *                     a reservation. Table class also has next element that points to the next element in the list.
 * */

public class Table {
    String name;
    int guests;
    Table next;

    public Table(int n, String name){
        this.name = name;
        guests = n;
        next = null;
    }

    public String toString(){
        return "\nReservation for " + name + ": party of "
                + guests + ".\n";
    }


    public static class EmptyTable extends Table{

        public EmptyTable() {
            super(-1, "");
        }
    }
}
