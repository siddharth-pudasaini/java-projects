/*
 * Name: Siddhartha Pudasaini
 * ASU ID: 1222261339
 * Assignment #: 11
 * Class time: T, Th 4:30-5:45
 * Description: Assignment11 has an object of MazeSolver class. The object has methods that create and solve the maze provided by user.
 */


public class Assignment11 {

	public static void main(String[] args) {
		MazeSolver mazeSolver = new MazeSolver();
		mazeSolver.readMaze();

		System.out.println("Original maze: ");
		mazeSolver.printMaze();

		mazeSolver.depthFirstSearch();

		System.out.println("Maze after Easter Bunny traverse: ");
		mazeSolver.printMaze();

		if (mazeSolver.getEggFound() == 0)
			System.out.println("The Bunny was unable to recover the eggs, Easter is cancelled!");
		else
			System.out.println("The Easter Bunny found " + mazeSolver.getEggFound() + " egg(s)!");
	}
}