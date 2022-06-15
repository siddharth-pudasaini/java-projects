/*
* Name: Siddhartha Pudasaini
* ASU ID: 1222261339
* Assignment #: 11
* Class time: T, Th 4:30-5:45
* Description: Maze solver class contains data about the structure of the maze that is stored in the form of 2D array.
* 				The class also contains other information about the total height of a maze, width of a maze and total number of eggs found.
* 				The class has a depthFirstSearch method that implements depth first search algorithm. The method finds the total number of eggs that
* 				the bunny can find before getting stuck.
* */



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class MazeSolver {
	private Stack<Node> stack=new Stack();
	private char[][] maze;
	private int eggFound;
	private int mHeight;
	private int mWidth;


	// ************************************************************************************
	// ************** Utility method to read maze from user input *************************
	// ************************************************************************************
	public void readMaze() {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			System.out.println("Height of the maze: ");
			String line = reader.readLine();
			mHeight = Integer.parseInt(line);
			System.out.println("Width of the maze: ");
			line = reader.readLine();
			mWidth = Integer.parseInt(line);
			maze = new char[mHeight][mWidth];
			for (int i = 0; i < mHeight; i++) {
				line = reader.readLine();
				for (int j = 0; j < mWidth; j++) {
					maze[i][j] = line.charAt(j);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}


	//Prints maze to the screen
	public void printMaze(){
		for (int i = 0; i < mHeight; i++) {
			for (int j = 0; j < mWidth; j++) {
				System.out.printf("%c",maze[i][j]);
			}
			System.out.println("");
		}
	}


	//Check if the tile at given position in maze is wall or not
	private boolean isWall(int x, int y){
		return maze[x][y]=='#';
	}


	//Check if the tile at given position in maze is visited or not
	private boolean isVisited(int x, int y){
		return maze[x][y]=='x';
	}


	//Check if the position is valid or not
	private boolean isInBound(int x, int y){
		boolean xInBound;
		boolean yInBound;
		xInBound=x<mHeight&&x>=0;
		yInBound=y<mWidth&&y>=0;
		return xInBound && yInBound;
	}


	//Get neighbors that can be visited
	private void getValidNeighbors(int x, int y){
		int southX=x+1;
		int southY=y;

		int eastX=x;
		int eastY=y+1;

		int northX=x-1;
		int northY=y;

		int westX=x;
		int westY=y-1;

		int[] xCoordinates={southX,eastX,northX,westX};
		int[] yCoordinates={southY,eastY,northY,westY};
		
		for(int i=0;i<4;i++){
			int xToCheck=xCoordinates[i];
			int yToCheck=yCoordinates[i];
			if(isInBound(xToCheck,yToCheck)){
				boolean isValidPosition=!isVisited(xToCheck,yToCheck) && !isWall(xToCheck,yToCheck);
				if(isValidPosition){
					Node newNode=new Node(xToCheck,yToCheck);
					stack.push(newNode);
				}
			}
		}
	}


	//Search for eggs
	public void depthFirstSearch(){
		stack.push(new Node(0,0));
		while (!stack.isEmpty()){
			Node node=stack.pop();
			int nodeX= node.getX();
			int nodeY=node.getY();
			if(maze[nodeX][nodeY]=='E') ++eggFound;
			maze[nodeX][nodeY]='x';
			getValidNeighbors(nodeX,nodeY);
		}
	}


	//Return number of total eggs found
	public int getEggFound(){
		return eggFound;
	}

}
