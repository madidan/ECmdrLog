package app;

import java.awt.EventQueue;

import app.gui.LWindow;
import app.gui.RWindow;

public class Main {

	public static void main(String[] args) {
		EventQueue.invokeLater(() ->  new LWindow() );
		EventQueue.invokeLater(() ->  new RWindow() );
	}

}
