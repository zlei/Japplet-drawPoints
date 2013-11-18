package controller;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class BasicGraphController {
	Model model;
	MyBasicPanel panel;
//	private DrawBasicGraph drawBasic;
	public BasicGraphController(Model model) {
		this.model = model;
	}
	
	public MyBasicPanel drawBasic(MainApplet mainApplet){
		panel = new MyBasicPanel(model);
//		panel.setType(0);
//		panel.repaint();
		return panel;
	}
}
