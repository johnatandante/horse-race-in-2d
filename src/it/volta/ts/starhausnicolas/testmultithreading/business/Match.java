package it.volta.ts.starhausnicolas.testmultithreading.business;

import java.util.List;

import javax.swing.JFrame;

import it.volta.ts.starhausnicolas.testmultithreading.bean.Horse;
import it.volta.ts.starhausnicolas.testmultithreading.events.UIWinnerListener;
import it.volta.ts.starhausnicolas.testmultithreading.events.WinnerEvent;
import it.volta.ts.starhausnicolas.testmultithreading.gui.HorseView;
import it.volta.ts.starhausnicolas.testmultithreading.gui.MainFrame;

public class Match extends Thread {

	private List<Horse> horses;
	private boolean          hasAWinner;
	
	public Match(List<Horse> horses) {
		this.hasAWinner = false;
		this.horses     = horses;
	}

	public boolean getHasAWinner() {
		return hasAWinner;
	}
	
	public void run() {
		
		List<HorseView> uIhorses = MainFrame.getHorses();
		
		while(!hasAWinner) {
			for(int idx = 0; idx < horses.size(); idx++) {
				
				Horse currentHorse = horses.get(idx);

				HorseView currentHorseView = uIhorses.get(idx);
				currentHorseView.setLocation((currentHorse.getMeters()) * 35 + 20, currentHorseView.getY());
				
				if(currentHorse.getMeters() >= 20) {
					hasAWinner = true;
					new UIWinnerListener()
						.winner((JFrame)(MainFrame.getFrames()[MainFrame.getFrames().length - 1]), 
							new WinnerEvent(currentHorse.getHorseName()));
					break;
				}
			}
			
			System.out.print("\n");
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				System.out.println("Sleep ends for " + e.getMessage());
			} catch (IllegalArgumentException iae) {
				System.out.println("Sleep ends for " + iae.getMessage());
			}
		}
		
		for(Horse horse : horses) {
			horse.stop();
		}
	}
	
}
