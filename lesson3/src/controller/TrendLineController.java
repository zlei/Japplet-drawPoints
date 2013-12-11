package controller;

import java.util.Properties;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class TrendLineController {
	Model model;
	MyBasicPanel panel;
	public TrendLineController(Model model) {
		this.model = model;
	}

	public boolean changeTrendline(MainApplet mainApplet) {
		Properties pp = model.getProps();
		String status = pp.getProperty("TrendLine");
		if (status.equals("On"))
			pp.setProperty("TrendLine", "Off");
		else pp.setProperty("TrendLine", "On");
		
		panel = (MyBasicPanel) mainApplet.getPanel();
		panel.repaint();
		
		return true;
	}
}
