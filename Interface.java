package minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Interface implements ActionListener{

	JFrame     mainFrame  = new JFrame("Minesweeper");
	JButton    newGame    = new JButton("New Game");
	JPanel     grid       = new JPanel();
	JPanel     settings   = new JPanel(new GridLayout(0,3));
	JLabel     heightL    = new JLabel("Field Height");
	JLabel     widthL     = new JLabel("Field Width");
	JLabel     bombL      = new JLabel("Desired number of bombs");
	JTextField gridHeight = new JTextField("10");
	JTextField gridWidth  = new JTextField("10");
	JTextField gridBombs  = new JTextField("25");
	MineLayer  mLayer;
	
	boolean    gameOver   = false;
	
	int height = 0;
	int width  = 0;
	int bombs  = 0;
	//hidden refers to the number of squares not revealed
	//when hidden = bombs, player wins
	int hidden = 0;
	
	public void init(){
		
		grid.setVisible(false);
		
		// Settings Panel
		settings.add(heightL);
		settings.add(new JLabel());
		settings.add(gridHeight);
		settings.add(widthL);
		settings.add(new JLabel());
		settings.add(gridWidth);
		settings.add(bombL);
		settings.add(new JLabel());
		settings.add(gridBombs);
		settings.add(new JLabel());
		settings.add(newGame);
		
		// Frame initialization
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setPreferredSize(new Dimension(500,500));
		mainFrame.add(grid, BorderLayout.CENTER);
		mainFrame.add(settings, BorderLayout.SOUTH);
		mainFrame.pack();
		mainFrame.setVisible(true);
		
		newGame.addActionListener(this);	
	}
	
	public void actionPerformed(ActionEvent evt){
		//set mineField = array returned from MineLayer object
		if(newGame.equals(evt.getSource())){
			if(verifyData()){
				mLayer = new MineLayer(height, width, bombs);
				hidden = height*width;
				gameOver = false;
				generateField();
				grid.setVisible(true);
			}
		}
		//if a button on the grid pressed, get its position from ActionCommand string
		//disable the pressed button and give it text = to number in mineField array
		//later need to check if game is over
		else if(evt.getSource() instanceof JButton){
			if(!gameOver){
				String position = evt.getActionCommand();
				int row = Integer.parseInt(position.substring(0,position.indexOf("/")));
				int col = Integer.parseInt(position.substring(position.indexOf("/")+1, position.length()));
				JButton pressed = (JButton)evt.getSource();
			
				if(mLayer.IsBomb(row, col)){
					messagePlayer("Game Over");
					gameOver = true;
					pressed.setText("*");
					pressed.setEnabled(false);
				}
				else{
					revealEmpty(row,col);
					if(hidden == bombs){
						messagePlayer("You Win!");
						gameOver = true;
					}
				}
			}
		}
	}
	
	/**
	 * Is only called if verifyData() returns true
	 * Add buttons to the grid panel
	 * 
	 * @param JPanel field
	 */
	public void generateField(){
		mainFrame.remove(grid);
		grid = new JPanel();
		grid.setLayout(new GridLayout(height, width));
		for(int i = 0; i < height; i++){
			for(int j = 0; j < width; j++){
				JButton temp = new JButton();
				temp.setActionCommand(i+"/"+j);
				temp.addActionListener(this);
				//Allows application of mine markers on buttons
				temp.addMouseListener(new MouseAdapter() {
				    public void mouseClicked(MouseEvent evt) {
				    	if(evt.getButton() == MouseEvent.BUTTON3){
				    		JButton flag = (JButton)evt.getSource();
				    		if(flag.isEnabled() && !gameOver){
				    			if(flag.getText().equals("")){
				    				flag.setText("?");
				    			}
				    			else{
				    				flag.setText("");
				    			}
				    		}
				    	}
				    }});
				grid.add(temp);
			}
		}
		mainFrame.add(grid, BorderLayout.CENTER);
		mainFrame.pack();
	}
	
	/**
	 *  Checks that user has entered acceptable values
	 *  for height, width, and bombs
	 *  
	 * @return false
	 */
	public boolean verifyData(){
		
		try{
			height = Integer.parseInt(gridHeight.getText());
			width  = Integer.parseInt(gridWidth.getText());
			bombs  = Integer.parseInt(gridBombs.getText());
			if(bombs > ((height*width)-1)){
				messagePlayer("Please lower the bomb count.\n" +
						      bombs + " bombs makes it impossible to win");
				return false;
			}
			if(bombs <= 0){
				messagePlayer("Why bother playing?");
				return false;
			}
		} catch(NumberFormatException nfe){
			messagePlayer("Please enter integer numbers only.");
			return false;
		}
		return true;
	}
	
	/**
	 * Use this to send pop up messages to the player
	 * 
	 * @param message
	 */
	public void messagePlayer(String message){
		JOptionPane.showMessageDialog(null, message);
	}
	
	/**
	 * Used to recursively reveal empty space on the
	 * (grid) mine field
	 * 
	 * @param row
	 * @param col
	 */
	public void revealEmpty(int row, int col){
		int north;
		int south;
		int east;
		int west;
		
		if(mLayer.get(row,col) != 0){
			JButton temp = (JButton)grid.getComponent(row*width+col);
			temp.setText(Integer.toString(mLayer.get(row,col)));
			temp.setEnabled(false);
			hidden--;
			return;
		}
		else{
			JButton temp = (JButton)grid.getComponent(row*width+col);
			temp.setText(Integer.toString(mLayer.get(row,col)));
			temp.setEnabled(false);
			hidden--;
			
			north = row*width+col - width;
			south = row*width+col + width;
			east  = row*width+col + 1;
			west  = row*width+col - 1;
		}
		//Check north position
		if(north >= 0 && grid.getComponent(north).isEnabled()){
			revealEmpty(row-1,col);
		}
		if(south < height*width && grid.getComponent(south).isEnabled()){
			revealEmpty(row+1,col);
		}
		//The check is done to prevent wrap around reveals
		//i.e. Row 4 Col 0/10 does not wrap around to Row 3 Col 9/10
		if(east < height*width && grid.getComponent(east).isEnabled()){
			if(east/width == row){
				revealEmpty(row,col+1);
			}
		}
		if(west >= 0 && grid.getComponent(west).isEnabled()){
			if(west/width == row){
				revealEmpty(row,col-1);
			}
		}
		return;
	}
	
	public static void main(String args[]){	
		Interface mineSweeper = new Interface();
		mineSweeper.init();
	}
}
