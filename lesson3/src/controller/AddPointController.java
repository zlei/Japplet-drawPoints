package controller;

import java.awt.Graphics;

import javax.swing.DefaultListModel;

import dataset.IGraph;
import model.Model;
import view.MainApplet;

public class AddPointController {
	Model model;

	public AddPointController(Model model) {
		this.model = model;
	}

	/**
	 * Get point from user, add to JLists
	 * 
	 * @param mainApplet
	 * @return
	 */
	public boolean addPoint(MainApplet mainApplet) {
		String pointXValue = mainApplet.getXValue().getText();
		String pointYValue = mainApplet.getYValue().getText();
		String data = pointXValue + pointYValue;
		
		mainApplet.getXValue().setText("");
		mainApplet.getYValue().setText("");

		if (pointXValue.length() == 0 || pointYValue.length() == 0) {
			return false;
		}

		DefaultListModel list;
		list = (DefaultListModel) mainApplet.getJList().getModel();
		int idx = list.getSize();
		list.add(idx, data);
		model.getDataSet().addPoint(data);
		

		Graphics g = null;
		model.getGraph().draw(g, mainApplet.getPanel());
		
		return true;
	}
}
