package it.volta.ts.starhausnicolas.testmultithreading.events;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import it.volta.ts.starhausnicolas.testmultithreading.gui.MainFrame;

public class UIWinnerListener implements WinnerListener {

	public void winner(JFrame frame, WinnerEvent event) {
		JPanel mainPanel = (JPanel)(frame.getContentPane().getComponentAt(0, 0));
		
		mainPanel.setVisible(false);
		mainPanel.removeAll();
		
		JLabel winner = new JLabel("The winner is " + event.getSource());
		winner.setHorizontalAlignment(SwingConstants.CENTER);
		winner.setVerticalAlignment(SwingConstants.CENTER);
		winner.setFont(new Font("Glossy Sheen Shine", Font.PLAIN, 40));
		winner.setBounds(90, 410, 800, 50);
		
		mainPanel.add(winner);
		
		JButton resetGame = new JButton(new ImageIcon("resources/images/resetButton.png"));
		resetGame.setFont(new Font("Serif", Font.BOLD, 25));
		resetGame.setBackground(new Color(255, 255, 255, 200));
		resetGame.setOpaque(false);
		resetGame.setContentAreaFilled(false);
		resetGame.setBorderPainted(false);
		resetGame.setBounds(385, 460, 225, 50);
		resetGame.addActionListener(reset);
		
		mainPanel.add(resetGame);
		
		mainPanel.setVisible(true);
	}
	
	ActionListener reset = new ActionListener() { 
		  public void actionPerformed(ActionEvent e) {
			  JFrame currentFrame = (JFrame)(MainFrame.getFrames()[MainFrame.getFrames().length - 1]);
			  currentFrame.dispose();
			  new MainFrame();
		  } 
	};
	
}
