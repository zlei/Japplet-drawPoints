package controller;

import model.DrawBasicGraph;
import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class TrendLineController {
	Model model;
	MyBasicPanel panel;
	DrawBasicGraph drawBasic;

	public TrendLineController(Model model) {
		this.model = model;
	}

	public boolean changeTrendline(MainApplet mainApplet) {
		this.panel = mainApplet.getMyPanel();
		this.drawBasic = panel.getBasicPanel();
		drawBasic.changeTrendline();
//		panel.repaint();
		return true;
	}
}
