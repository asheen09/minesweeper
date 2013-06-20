package minesweeper;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerRes implements ActionListener{
	
	// c = cost
	private int insightC = 20;
	private int excavateC = 10;
	private int maxMP = 50;
	private int maxHP = 100;
	
	private JButton insight = new JButton("Insight");
	private JLabel  spellbk = new JLabel("Spell Book");
	GridBagConstraints c = new GridBagConstraints();
	
	public PlayerRes(JPanel container){
		container.setLayout(new GridBagLayout());
		container.setPreferredSize(new Dimension(300,100));
		
		insight.addActionListener(this);
		
		spellbk.setFont(new Font("Calibri", Font.ITALIC, 30));
		c.anchor = GridBagConstraints.FIRST_LINE_START;
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;
		c.weightx = 0.01;
		c.weighty = 0.01;
		c.insets = new Insets(0,85,0,0);
		container.add(spellbk, c);
		
		c.anchor = GridBagConstraints.FIRST_LINE_START;
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
		
	}

	public void actionPerformed(ActionEvent evt) {
		if(insight.equals(evt.getSource())){
			System.out.println("HELLO");
		}
		
	}

}
