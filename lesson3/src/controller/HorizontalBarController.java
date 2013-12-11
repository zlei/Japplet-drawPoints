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
		panel = (MyBasicPanel) mainApplet.getPanel();
		model.setGraphProps("model.HorizontalBarGraph");
		panel.repaint();
		return true;
	}
}
