package it.volta.ts.starhausnicolas.testmultithreading.events;

import java.util.EventListener;

import javax.swing.JFrame;

public interface WinnerListener extends EventListener {

	void winner(JFrame frame, WinnerEvent event);
	
}
