package minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class MineLayer {
	
	/**
	 * creates a grid of size 'height' and 'width'
	 * then populates it with bombs and the corresponding
	 * adjacent numbers
	 * 
	 * @param height - height of grid
	 * @param width - width of grid
	 * @param bombs - number of bombs to be placed
	 */
	public MineLayer(int height, int width, int bombs) {
		this.height = height;
		this.width = width;
		grid = new int[height][width];
		
		Populate(bombs);
	}
	
	/**
	 * returns the current grid setup
	 * 
	 * ??? i think this is a bad use of object oriented programming
	 * ??? try not to use this one
	 * 
	 * @return grid
	 */
	public int[][] Get() {
		return grid;
	}
	
	/**
	 * returns the value at the given x and y coordinates
	 * 
	 * @param xcord - x coordinate value
	 * @param ycord - y coordinate value
	 * @return value at the given position
	 */
	public int get(int xcord, int ycord) {
		return grid[xcord][ycord];
	}
	
	/**
	 * checks if there is a bomb at the give coordinates
	 * 
	 * @param xcord - x coordinate value
	 * @param ycord - y coordinate value
	 * @return boolean
	 */
	public boolean IsBomb(int xcord, int ycord) {
		if(grid[xcord][ycord] == 9)
			return true;
		else return false;
	}
	
	/**
	 * sets all values in the grid to zero
	 */
	public void clear() {
		for(int i=0;i<height;i++) {
			for(int j=0;j<width;j++) {
				grid[i][j] = 0;
			}
		}
	}
	
	/**
	 * populates the grid with the given number of bombs
	 * the bombs are randomly selected positions
	 * also fills in the corresponding adjacent data
	 * 
	 * currently is using psudeo random
	 * 
	 * @param bombs
	 */
	public void Populate(int bombs) {
		int row = 0,col = 0,randnumber = 0;
		Random rnd = new Random();
		
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
			
			UpdateSurround(row,col);
			
			// removes the possible position from array list
			coordinate.remove(randnumber);
		}
			// cleans the left over positions of the array list
			coordinate.clear();
	}
	
	
	// 2d array of the grid data
	private int[][] grid;
	private int height;
	private int width;
	
	/**
	 * only called when bomb is inserted onto grid
	 * updates the surrounding values of a bomb
	 * @param row - x coordinate value
	 * @param col - y coordinate value
	 */
	private void UpdateSurround(int row, int col) {
		System.out.println(row + " " + col);
		
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
				grid[row+1][col] = grid[row+1][col] + 1;
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
				grid[row][col+1] = grid[row][col+1] + 1;
		}
	}
}