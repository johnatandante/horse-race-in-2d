package it.volta.ts.starhausnicolas.testmultithreading.gui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class HorseView extends JLabel {

	public HorseView(int width, int height, String path) {
		this.setIcon(new ImageIcon(path));
		this.setBounds(width, height, 100, 100);
	}
	
}
