package controller;

import java.awt.Graphics;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;
import dataset.IGraph;

public class ColumnController {

	Model model;
	MyBasicPanel panel;
	
	public ColumnController(Model model) {
		this.model = model;
	}
	public boolean drawColumn(MainApplet mainApplet) {
		panel = (MyBasicPanel) mainApplet.getPanel();
		model.setGraphProps("model.ColumnGraph");
		panel.repaint();
		return true;
	}
}
