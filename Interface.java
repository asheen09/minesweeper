package minesweeper;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	int height = 0;
	int width  = 0;
	int bombs  = 0;
	
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
		if(newGame.equals(evt.getSource())){
			if(verifyData()){
				System.out.println(height + " by " + width);
				generateField();
				grid.setVisible(true);
			}
		}
		else if(evt.getSource() instanceof JButton){
			System.out.println(evt.getActionCommand());
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
	
	public static void main(String args[]){	
		Interface mineSweeper = new Interface();
		mineSweeper.init();
	}
}
