package controller;

import model.DrawBasicGraph;
import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class ShowAxisController {
	Model model;
	MyBasicPanel panel;
	private DrawBasicGraph drawBasic;

	public ShowAxisController(Model model) {
		this.model = model;
	}

	public boolean changeAxis(MainApplet mainApplet) {
		this.panel = mainApplet.getMyPanel();
		this.drawBasic = panel.getBasicPanel();
		drawBasic.changeAxis();
//		panel.repaint();
		return true;
	}
}
