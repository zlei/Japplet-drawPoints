package controller;

import model.DrawBasicGraph;
import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class ShowGridController {
	Model model;
	MyBasicPanel panel;
	private DrawBasicGraph drawBasic;

	public ShowGridController(Model model) {
		this.model = model;
	}

	public boolean changeGrid(MainApplet mainApplet) {
		this.panel = mainApplet.getMyPanel();
		this.drawBasic = panel.getBasicPanel();
		drawBasic.changeGrid();
//		panel.repaint();
		return true;
	}
}
