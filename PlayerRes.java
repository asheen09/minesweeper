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
	private int insightC = 20;
	private int excavateC = 10;
	private int maxMP = 50;
	private int maxHP = 100;
	
	private String      currOC    = new String("Excavate");
	private JButton     insight   = new JButton("Insight");
	private JButton     ocSpell   = new JButton(currOC);
	private JLabel      spellbk   = new JLabel("Spell Book");
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
		spellbk.setFont(new Font("Calibri", Font.ITALIC, 30));
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.0001;
		c.weighty = 0.0001;
		c.insets = new Insets(0,85,0,0);
		container.add(spellbk, c);
		
		// Next on-click spell label
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0001;
		c.weighty = 0.0001;
		c.insets = new Insets(0,20,0,0);
		container.add(new JLabel("Next On-click Spell:"), c);
		// Next on-click spell button
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 0.0001;
		c.weighty = 0.0001;
		c.insets = new Insets(0,140,0,0);
		container.add(ocSpell, c);

		// Text area: Description of spells
		c = new GridBagConstraints();
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.gridx = 0;
		c.gridy = 2;
		c.weightx = 0.003;
		c.weighty = 0.003;
		c.insets = new Insets(0,40,0,0);
		spellInfo.setEditable(false);
		container.add(display,c);
		
		/*c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(0,0,0,0);
		c.gridx = 0;
		c.gridy = 1;
		c.weightx = 1;
		c.weighty = 1;
		container.add(new JLabel(insightC + " mana"),c);
		
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 1;
		c.gridy = 1;
		container.add(insight,c);
		*/
	}
	
	public void setUpSpells(){
		ocSpell.addMouseListener(this);
	}
	
	// Mouse event handler
	public void mouseClicked(MouseEvent me) {
		// TODO Auto-generated method stub
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
	}
	public void mouseExited(MouseEvent me) {
		spellInfo.setText("");
	}
	public void mousePressed(MouseEvent me) {
		if(me.getSource().equals(ocSpell)){
			currOC = " ";
			ocSpell.setText(currOC);
		}
	}
	public void mouseReleased(MouseEvent me) {
		// TODO Auto-generated method stub
	}
}
