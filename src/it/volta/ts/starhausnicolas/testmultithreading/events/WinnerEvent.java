package it.volta.ts.starhausnicolas.testmultithreading.events;

import java.util.EventObject;

@SuppressWarnings("serial")
public class WinnerEvent extends EventObject  {

	public WinnerEvent(Object obj) {
		super(obj);
	}
	
}
