package controller;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class ColumnController {

	Model model;
	MyBasicPanel panel;

	public ColumnController(Model model) {
		this.model = model;
	}

	public boolean drawColumn(MainApplet mainApplet) {
		this.panel = mainApplet.getMyPanel();
		panel.setType(2);
//		panel.repaint();
		return true;
	}
}
