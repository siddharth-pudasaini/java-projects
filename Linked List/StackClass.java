import java.util.Locale;
import java.util.Scanner;
import java.util.Stack;


public class StackClass  {

    public static void main(String [] args){
        userInput();
    }

    public static void userInput(){
        Scanner scnr=new Scanner(System.in);
        Stack SStack = new Stack<String>();
        boolean loop=true;
        while (loop){
            String letter=scnr.next();
            String e="";
            String n="";
            String d="";
            SStack.push(letter);

            if(SStack.peek()=="d"||SStack.peek()=="D"){
                d="d";
                n=(String) SStack.peek();
                e=(String) SStack.peek();
                n=n.toLowerCase();
                e=e.toLowerCase();
                System.out.print(e+n+d);

                String command=e+n+d;
                if(command.equals("end")){
                    SStack.pop();
                    SStack.pop();
                    SStack.pop();
                    loop=false;
                }
            }
        }
        while (!SStack.isEmpty()) {
            System.out.print(SStack.pop());
        }

    }



}
