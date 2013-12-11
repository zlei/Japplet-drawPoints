package controller;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class HorizontalBarController {

	Model model;
	MyBasicPanel panel;

	public HorizontalBarController(Model model) {
		this.model = model;
	}

	public boolean drawHorizontalBar(MainApplet mainApplet) {
		this.panel = mainApplet.getMyPanel();
		panel.setType(3);
		// panel.repaint();
		return true;
	}
}
