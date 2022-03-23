import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MazeSolver {


	//0 = wall
	//1 = path
	//2 = destination
	
	//game logic is to find 2 passing by all the possibilities if it's valid go on by trying next way but if it's not then
	//will deleted from the list and try another way up ,down ,right or left
	
	public static void main(String[] args) throws FileNotFoundException {

		ArrayList<Maze> mazes = new ArrayList<Maze>(); //collection of multi mazes but we have just one in mazes.txt
		
		Maze m = new Maze();
		
		Scanner in = new Scanner(new File("mazes.txt"));
		int rows = Integer.parseInt(in.nextLine());
		m.maze = new int[rows][];
		//read first line from mazes.txt file and take number of rows to prepare the matrix in maze class
		
		for(int i = 0; i < rows; i++) {
			String line = in.nextLine();
			m.maze[i] = Arrays.stream(line.split(", ")).mapToInt(Integer::parseInt).toArray();
		}
		//read the other lines from mazes.txt file to convert matrix draw to real matrix in maze class with numbers 
		
		m.start = new Position(Integer.parseInt(in.nextLine()), Integer.parseInt(in.nextLine())); 
		//take the position under matrix draw from mazes.txt and converted to an object position class  
		
		mazes.add(m); //adding the maze to mazes collection
		
		int i = 0;
		while(i < mazes.size()) {
			if(solveMaze(mazes.get(i))) {
				System.out.println("You won!");
			} else {
				System.out.println("No path");
			}
			i++;
		}
		//check return value of solve Maze method after maze end with stack data structure in maze class
	}
	
	private static boolean solveMaze(Maze m) {

		Position p = m.start; 
		m.path.push(p); 
		//take postion to start and added to stack path
		
		while(true) {
			int y = m.path.peek().y;
			int x = m.path.peek().x;
			 //take the value of y and x 
			
			m.maze[y][x] = 0;

			//moving down
			if(isValid(y+1, x, m)) { //check if the current position of x or y are not negative or not over maze length 
				if(m.maze[y+1][x] == 2) { //check if the current position is same position of the destination
					System.out.println("Moved down");
					return true;
				} else if(m.maze[y+1][x] == 1) { //check if current position in maze is equal to 1 (path)
					System.out.println("Moved down");
					m.path.push(new Position(y+1, x)); //adding the position to stack path is valid
					continue;
				}
			}

			//moving left
			if(isValid(y, x-1, m)) {
				if(m.maze[y][x-1] == 2) {
					System.out.println("Moved left");
					return true;
				} else if(m.maze[y][x-1] == 1) {
					System.out.println("Moved left");
					m.path.push(new Position(y, x-1));
					continue;
				}
			}
			
			//moving up
			if(isValid(y-1, x, m)) {
				if(m.maze[y-1][x] == 2) {
					System.out.println("Moved up");
					return true;
				} else if(m.maze[y-1][x] == 1) {
					System.out.println("Moved up");
					m.path.push(new Position(y-1, x));
					continue;
				}
			}

			//moving right
			if(isValid(y, x+1, m)) {
				if(m.maze[y][x+1] == 2) {
					System.out.println("Moved right");
					return true;
				} else if(m.maze[y][x+1] == 1) {
					System.out.println("Moved right");
					m.path.push(new Position(y, x+1));
					continue;
				}
			}
			
			m.path.pop(); //update stack path of the current position value equal to 0 (wall) by removing it from path
			System.out.println("Moved back");
			if(m.path.size() <= 0) {   //check the size of stack path if is empty
				return false;
			}
		}
	}

	public static boolean isValid(int y, int x, Maze m) {
		if(y < 0 || 
			y >= m.maze.length ||
			x < 0 ||
			x >= m.maze[y].length
		 ) {
			return false;
		}
		return true;
	}
	//check if the current position of x or y are not negative or not over maze length 

}