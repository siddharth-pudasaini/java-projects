/*
* Name: Siddhartha Pudasaini
* Assignment #: 10
* Student ID: 1222261339
* Class : T Th 4:30
* Class description: The LinkedList class consists a linked list of reserved tables at a restaurant.
*                    The class also contains data about the total number of values in a linked list.
*                    The LinkedList class provides methods add, remove and get position of a node in the linked list
* */

public class LinkedList {
    Table first;
    int size;
    public LinkedList(){
        first = null;
        size = 0;
    }

    //Method to add a node to the list
    public void add(int numberOfGuests, String name){
        Table newGuest = new Table(numberOfGuests, name);
        if(first == null){
            first = newGuest;
        }
        else{
            Table pointer = first;
            while(pointer.next != null)
                pointer = pointer.next;

            pointer.next = newGuest;
        }
        size++;
    }

    //Remove first node form the list
    public Table removeFirst(){
        if(this.size==0) return new Table.EmptyTable();
        else{
            Table pointer=first;
            first=pointer.next;
            size--;
            return pointer;
        }
    }

    //Remove node with the given name
    public Table removeGuest(String name){
        Table pointer=first;
        int position=getPosition(name);
        if(position==-1){
            return new Table.EmptyTable();
        }
        else if(position==0){
            first=pointer.next;
            size--;
            return pointer;
        }
        else if(position==size-1){
            Table reservation=removeLast();
            return reservation;
        }
        else {
            Table reservation=removeMiddle(name);
            return reservation;
        }

    }

    //Removes a middle element form the linked list
    public Table removeMiddle(String name){
        Table pointer=first;
        int position=getPosition(name);
        for(int i=0;i<position-1;i++){
            pointer=pointer.next;
        }
        Table next=pointer.next;
        pointer.next=next.next;
        size--;
        return next;
    }

    //Removes last element form the list (when number of items in a list is unknown)
//    public Table removeLast(){
//        Table currentPointer=first;
//        Table nextPointer=first.next;
//        while(nextPointer.next!=null){
//            currentPointer=currentPointer.next;
//            nextPointer=nextPointer.next;
//        }
//        currentPointer.next=null;
//        size--;
//        return nextPointer;
//
//    }

    //Removes last element form the list (when number of items in a list is known)
    public Table removeLast(){
        Table pointer=first;
        for(int i=0;i<size-2;i++){
            pointer=pointer.next;
        }
        Table returnPointer=pointer.next;
        pointer.next=null;
        size--;
        return returnPointer;
    }

    //Returns position of a node in a list
    public int getPosition(String name){
        int position=0;
        Table pointer=first;
        while(pointer!=null){
            if (pointer.name.equals(name)) return position;
            pointer=pointer.next;
            position++;
        }
        return -1;
    }


    //Returns the total number of nodes having a specific guest number
    public int getNumberOfParties(int numGuests){
        int numParties=0;
        Table pointer=first;
        while(pointer!=null){
            if (pointer.guests==numGuests) numParties++;
            pointer=pointer.next;
        }
        return numParties;

    }

    //List all reservations from the list
    public String listReservations(){
        String list="";
        int total=0;
        if(this.size==0) return "No reservations in queue at this time.\n";
        else {
            Table pointer=first;
            while (pointer!=null){
                list+=pointer.toString();
                pointer=pointer.next;
                total++;
            }
        }
        return list+"\nTotal reservations: "+total+".\n";
    }

}
