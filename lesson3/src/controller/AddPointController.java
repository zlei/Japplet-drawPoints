package controller;

import javax.swing.DefaultListModel;

import model.Model;
import view.MainApplet;

public class AddPointController {
	Model model;

	public AddPointController(Model model) {
		this.model = model;
	}

	public boolean addPoint(MainApplet mainApplet) {
		String pointXValue = mainApplet.getXValue().getText();
		String pointYValue = mainApplet.getYValue().getText();

		mainApplet.getXValue().setText("");
		mainApplet.getYValue().setText("");

		if (pointXValue.length() == 0 || pointYValue.length() == 0) {
			return false;
		}

		model.getPointFromInput(pointXValue, pointYValue);

				DefaultListModel list = (DefaultListModel) mainApplet.getDataList()
				.getModel();
		int idx = list.getSize();
		list.add(idx, model.printPoint());
		return true;
	}
}
