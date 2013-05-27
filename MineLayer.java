package minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class MineLayer {

	private int[][] grid;
	
	/**
	 * creates a grid of size 'height' and 'width'
	 * then populates it with bombs and the corresponding
	 * adjacent numbers
	 * 
	 * @param height
	 * @param width
	 * @param bombs
	 */
	public MineLayer(int height, int width, int bombs) {
		
		int row = 0,col = 0,randnumber = 0;
		Random rnd = new Random();
		grid = new int[height][width];
		
		// array list of every position on grid
		ArrayList<Integer> coordinate = new ArrayList<Integer>(height*width);
		for(int i = 0;i<height*width;i++) {
			coordinate.add(i);
		}
		
		for(int i = 0;i<bombs;i++) {
			// randomly chooses a position to put a bomb in
			randnumber = rnd.nextInt(coordinate.size());
			row = coordinate.get(randnumber)/width;
			col = coordinate.get(randnumber)%width;
			grid[row][col] = 9;
			
			// updates the 3 positions below the bomb
			if(row - 1 >= 0) {
				if(grid[row-1][col] < 9)
					grid[row-1][col] = grid[row-1][col] + 1;
				if(col - 1 >= 0) {
					if(grid[row-1][col-1] < 9)
					grid[row-1][col-1] = grid[row-1][col-1] + 1;
				}
				if(col + 1 < width) {
					if(grid[row-1][col+1] < 9)
					grid[row-1][col+1] = grid[row-1][col+1] + 1;
				}
			}
			// updates the 3 positions above the bomb
			if(row + 1 < height) {
				if(grid[row+1][col] < 9)
				grid[row-1][col] = grid[row-1][col] + 1;
				if(col - 1 >= 0) {
					if(grid[row+1][col-1] <9)
					grid[row+1][col-1] = grid[row+1][col-1] + 1;
				}
				if(col + 1 < width) {
					if(grid[row+1][col+1] < 9)
					grid[row+1][col+1] = grid[row+1][col+1] + 1;
				}
			}
			// updates position to the left
			if(col - 1 >= 0) {
				if(grid[row][col-1] < 9)
				grid[row][col-1] = grid[row][col-1] + 1;
			}
			// updates position to the right
			if(col + 1 < width) {
				if(grid[row][col+1] < 9)
				grid[row-1][col+1] = grid[row-1][col+1] + 1;
			}
			
			// removes the possible position from array list
			coordinate.remove(randnumber);
		}
		// cleans the left over positions of the array list
		coordinate.clear();
	}
	
	/**
	 * returns the current grid setup
	 * 
	 * ??? possibly return individual positions later
	 * 
	 * @return grid
	 */
	public int[][] Get() {
		return grid;
	}
}