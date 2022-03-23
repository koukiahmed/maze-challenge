import java.util.LinkedList;

public class Maze {
	public int[][] maze;
	public LinkedList<Position> path = new LinkedList<Position>(); //using stack for adding the correct path that found
	public Position start;    
}