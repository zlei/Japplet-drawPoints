package controller;

import java.util.Properties;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class ShowAxisController {
	Model model;
	MyBasicPanel panel;
	public ShowAxisController(Model model) {
		this.model = model;
	}

	public boolean changeAxis(MainApplet mainApplet) {
		Properties pp = model.getProps();
		String status = pp.getProperty("Axis");
		if (status.equals("On"))
			pp.setProperty("Axis", "Off");
		else pp.setProperty("Axis", "On");
		
		panel = (MyBasicPanel) mainApplet.getPanel();
		panel.repaint();
		
		return true;
	}
}
