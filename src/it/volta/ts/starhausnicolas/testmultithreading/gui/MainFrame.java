package it.volta.ts.starhausnicolas.testmultithreading.gui;

import javax.swing.*;

import it.volta.ts.starhausnicolas.testmultithreading.bean.Horse;
import it.volta.ts.starhausnicolas.testmultithreading.business.Match;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import java.util.List;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {

	private List<Horse> horses;
	private Match            match;
	
	private MainScene mainScene;
	
	private static List<HorseView> uIhorses = new ArrayList<>();
	
	public MainFrame() {
		super("Starhaus - Corsa dei cavalli");
		
		horses = new ArrayList<>();
		
		this.match = new Match(horses);
		
		this.setSize(1000, 570);
		this.setResizable(false);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		gameInit(this);
	}
	
	/**
	 * Metodo destinato all'installazione temporanea di font custom
	 */
	private void customFontsInstallation() {
		try {
		     GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		     ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("resources/fonts/Glossy Sheen Shine DEMO.ttf")));
		} catch (IOException | FontFormatException e) {}
	}
	
	private void gameInit(JFrame frame) {
		uIhorses.clear();
		customFontsInstallation();
		
		frame.getContentPane().setLayout(new BorderLayout());
		
		mainScene = new MainScene("resources/images/background.png");
		mainScene.setLayout(null);
		
		frame.getContentPane().add(mainScene);
		float scale = 0.8f;
		int startLanePosition = 100;
		int distance = 50;
		int horseCounter = 4;
		HorseView redTeamHorse = new HorseView(20, (int)(startLanePosition + (distance * horseCounter--)/scale), "resources/images/horseIcon_redTeam.png");
		uIhorses.add(redTeamHorse);
		
		HorseView greenTeamHorse = new HorseView(20, (int)(startLanePosition + (distance * horseCounter--)/scale), "resources/images/horseIcon_greenTeam.png");
		uIhorses.add(greenTeamHorse);
		
		HorseView blueTeamHorse = new HorseView(20, (int) (startLanePosition + (distance * horseCounter--)/scale), "resources/images/horseIcon_blueTeam.png");
		uIhorses.add(blueTeamHorse);
		
		HorseView yellowTeamHorse = new HorseView(20, (int) (startLanePosition + (distance * horseCounter--)/scale), "resources/images/horseIcon_yellowTeam.png");
		uIhorses.add(yellowTeamHorse);

		mainScene.add(redTeamHorse);
		mainScene.add(greenTeamHorse);
		mainScene.add(blueTeamHorse);
		mainScene.add(yellowTeamHorse);
		
		JLabel finishLine = new JLabel();
		finishLine.setIcon(new ImageIcon("resources/images/finishLine.png"));
		finishLine.setBounds(730, 0, 50, 570);
		
		mainScene.add(finishLine);
		
		JButton startButton = new JButton(new ImageIcon("resources/images/startButton.png"));
		startButton.setBounds(20, 20, 225, 50);
		startButton.setOpaque(false);
		startButton.setContentAreaFilled(false);
		startButton.setBorderPainted(false);
		startButton.addActionListener(startGame);
		mainScene.add(startButton);
		
		frame.setVisible(true);
	}
	
	private void playMusic() {
		InputStream music;
		
		try {
			music = new FileInputStream(new File("resources/images/"));
//			AudioStream audio = new AudioStream(music);
//			AudioPlayer.players.start(audio);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	private void initializeHorses() {
		horses.add(new Horse("Range R. Over", 100));
		horses.add(new Horse("Rect Angle", 100));
		horses.add(new Horse("Math Ew", 100));
		horses.add(new Horse("Micky Cat", 100));
	}
	
	private void addGUIHorseNames() {
		for(int idx = 0; idx < 4; idx++) {
			JLabel label = new JLabel(horses.get(idx).getHorseName());
			label.setVerticalAlignment(SwingConstants.CENTER);
			label.setFont(new Font("Glossy Sheen Shine", Font.PLAIN, 28));
			label.setForeground(Color.WHITE);
			label.setBounds(800, (310 + ((3 - idx) * 45)), 200, 50);
			mainScene.add(label);
		}
	}
	
	public void execute() {
		initializeHorses();
		
		this.setVisible(false);
		addGUIHorseNames();
		this.setVisible(true);
		
		for(Horse horse : horses) {
			horse.start();
		}
		
		this.match = new Match(horses);
		
		match.start();
	}
	
	public static List<HorseView> getHorses() {
		return uIhorses;
	}

	
	ActionListener startGame = new ActionListener() { 
		  public void actionPerformed(ActionEvent e) {
			  	JButton button = ((JButton) e.getSource());
			  	
			    execute();

			    button.setIcon(new ImageIcon("resources/images/stopButton.png"));
			    button.removeActionListener(startGame);
			    button.addActionListener(stopGame);
		  } 
	};
	
	ActionListener stopGame = new ActionListener() { 
		public void actionPerformed(ActionEvent e) {
			  	JButton button = ((JButton) e.getSource());
			    
			  	for(Horse horse : horses) {
			  		horse.stop();
			  	}
			  	horses.clear();
			  	
			  	match.stop();
			  	
			  	match = null;
			  	
			    button.setIcon(new ImageIcon("resources/images/resetButton.png"));
			    button.removeActionListener(stopGame);
			    button.addActionListener(resetGame);
		  }
	};
	
	ActionListener resetGame = new ActionListener() { 
		  public void actionPerformed(ActionEvent e) {
			  	JButton button = ((JButton) e.getSource());
			    
			  	for(HorseView horseView : uIhorses) {
			  		horseView.setLocation(20, horseView.getY());
			  	}
			  	
			    button.setIcon(new ImageIcon("resources/images/startButton.png"));
			    button.removeActionListener(resetGame);
			    button.addActionListener(startGame);
		  } 
	};
	
}
