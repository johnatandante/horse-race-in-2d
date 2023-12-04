package it.volta.ts.starhausnicolas.testmultithreading.gui;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainScene extends JPanel {

	 private BufferedImage backgroundImage;
	 
	 public MainScene(String fileName) {
		 try {
			backgroundImage = ImageIO.read(new File(fileName));
		} catch (IOException e) {}
	 }

	  public void paintComponent(Graphics g) {
	    super.paintComponent(g);

	    g.drawImage(backgroundImage, 0, 0, this);
	  }
	
}
