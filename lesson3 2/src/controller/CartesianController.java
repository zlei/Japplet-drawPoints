package controller;

import model.DrawBasicGraph;
import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class CartesianController {

	Model model;
	MyBasicPanel panel;
	public CartesianController(Model model) {
		this.model = model;
	}
	
	public boolean drawCartesian(MainApplet mainApplet){
		this.panel = mainApplet.getMyPanel();
		panel.setType(1);
//		panel.repaint();
		return true;
	}

}
