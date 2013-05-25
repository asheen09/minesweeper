package minesweeper;

import java.util.ArrayList;
import java.util.Random;

public class MineLayer {

	private int[][] grid;
	
	public MineLayer(int height, int width, int bombs) {
		
		int row = 0,col = 0,randnumber = 0;
		Random rnd = new Random();
		grid = new int[height][width];
		
		
		ArrayList<Integer> coordinate = new ArrayList<Integer>(height*width);
		for(int i = 0;i<height*width;i++) {
			coordinate.add(i);
		}
		
		for(int i = 0;i<bombs;i++) {
			randnumber = rnd.nextInt(coordinate.size());
			row = coordinate.get(randnumber)/width;
			col = coordinate.get(randnumber)%width;
			grid[row][col] = 9;
			
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
			if(col - 1 >= 0) {
				if(grid[row][col-1] < 9)
				grid[row][col-1] = grid[row][col-1] + 1;
			}
			if(col + 1 < width) {
				if(grid[row][col+1] < 9)
				grid[row-1][col+1] = grid[row-1][col+1] + 1;
			}
			
			coordinate.remove(randnumber);
		}
		coordinate.clear();
	}
}
