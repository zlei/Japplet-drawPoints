package controller;

import java.util.Properties;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class ShowGridController {
	Model model;
	MyBasicPanel panel;

	public ShowGridController(Model model) {
		this.model = model;
	}

	public boolean changeGrid(MainApplet mainApplet) {
		Properties pp = model.getProps();
		String status = pp.getProperty("Grid");
		if (status.equals("On"))
			pp.setProperty("Grid", "Off");
		else
			pp.setProperty("Grid", "On");

		panel = (MyBasicPanel) mainApplet.getPanel();
		panel.repaint();

		return true;
	}
}
