package controller;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class CartesianController {

	Model model;
	MyBasicPanel panel;

	public CartesianController(Model model) {
		this.model = model;
	}

	public boolean drawCartesian(MainApplet mainApplet) {
		panel = (MyBasicPanel) mainApplet.getPanel();
		model.setGraphProps("model.CartesianGraph");
		panel.repaint();
		return true;
	}
}
