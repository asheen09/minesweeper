package minesweeper;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PlayerRes implements MouseListener{
	
	// c = cost
	private int insightC = 15;
	private int excavateC = 10;
	private int maxMP = 50;
	private int maxHP = 30;
	private int curHP = 30;
	private int curMP = 30;
	private int bombs = 0;
	private int maxBombs = 5;
	
	private String      currOC    = new String("Excavate");
	private JButton     insight   = new JButton("Insight [" + insightC + "]");
	private JButton     excavate  = new JButton("Excavate [" + excavateC + "]");
	private JButton     consume   = new JButton("Consume");
	private JButton     ocSpell   = new JButton(currOC);
	private JLabel      bombStrg  = new JLabel("Bombs: " + bombs + " / " + maxBombs);
	private JLabel      hpStat    = new JLabel("HP: " + curHP + " / " + maxHP);
	private JLabel      mpStat    = new JLabel("MP: " + curMP + " / " + maxMP);
	private JLabel      title     = new JLabel("Spell Book");
	private JTextArea   spellInfo = new JTextArea("",5,20);
	private JScrollPane display   = new JScrollPane(spellInfo);
	
	GridBagConstraints c = new GridBagConstraints();
	
	public PlayerRes(JPanel container){
		setUpDisplay(container);
		setUpSpells();
	}

	public void setUpDisplay(JPanel container){
		container.setLayout(new GridBagLayout());
		container.setPreferredSize(new Dimension(300,100));
		
		// Title: Spell Book
		title.setFont(new Font("Calibri", Font.ITALIC, 30));
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.insets = new Insets(0,85,0,0);
		container.add(title, c);
		
		// Next on-click spell label
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,20,0,0);
		container.add(new JLabel("Next On-click Spell:"), c);
		// Next on-click spell button
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.insets = new Insets(0,140,0,0);
		container.add(ocSpell, c);

		// Text area: Description of spells
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 2;
		c.insets = new Insets(20,40,20,0);
		spellInfo.setEditable(false);
		container.add(display,c);
		
		// Insight spell button
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0.005;
		c.weighty = 0.005;
		c.insets = new Insets(0,20,0,0);
		container.add(insight, c);
		
		// Excavate spell button
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 3;
		c.weightx = 0.005;
		c.weighty = 0.005;
		c.insets = new Insets(0,160,0,0);
		container.add(excavate, c);
		
		// Consume spell button
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 4;
		c.weightx = 0.005;
		c.weighty = 0.005;
		c.insets = new Insets(0,20,0,0);
		container.add(consume, c);
		
		// Bomb storage
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 5;
		c.weightx = 0.005;
		c.weighty = 0.005;
		c.insets = new Insets(0,100,0,0);
		container.add(bombStrg, c);
		
		// Health
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 0.09;
		c.weighty = 0.09;
		c.insets = new Insets(0,40,0,0);
		container.add(hpStat, c);
		
		// Mana
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 6;
		c.weightx = 0.09;
		c.weighty = 0.09;
		c.insets = new Insets(0,160,0,0);
		container.add(mpStat, c);
	}
	
	public void setUpSpells(){
		ocSpell.addMouseListener(this);
		insight.addMouseListener(this);
		excavate.addMouseListener(this);
		consume.addMouseListener(this);
	}
	
	// Mouse event handler
	public void mouseClicked(MouseEvent me) {
		if(me.getSource().equals(ocSpell)){
			currOC = " ";
			ocSpell.setText(currOC);
		}
		if(me.getSource().equals(insight)){
			
		}
		if(me.getSource().equals(excavate)){
			
		}
	}
	public void mouseEntered(MouseEvent me) {
		if(me.getSource().equals(ocSpell)){
			if(currOC.equals(" ")){
				spellInfo.setText("No On-click spell is active");
			}
			else{
				spellInfo.setText("Click to clear \"" + currOC + "\" from next click");
			}
		}
		if(me.getSource().equals(insight)){
			spellInfo.setText("[Instant Cast] - " + insightC + " MP\n" +
					          ">Reveals a random non-bomb cell\n" +
					          "on the field");
		}
		if(me.getSource().equals(excavate)){
			spellInfo.setText("[On-click Cast] - " + excavateC + " MP\n" +
					          ">Safely extracts resource from cell\n" + 
					          ">>50% chance to keep bombs");
		}
		if(me.getSource().equals(consume)){
			spellInfo.setText("[Instant Cast] - Free\n" + 
		                      ">Consume a bomb to gain HP and MP");
		}
	}
	public void mouseExited(MouseEvent me) {
		spellInfo.setText("");
	}
	public void mousePressed(MouseEvent me) {
		
	}
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
	}
}
