package controller;

import javax.swing.DefaultListModel;

import model.Model;
import view.MainApplet;

public class EditPointController {
	Model model;
	private int idx;

	public EditPointController(Model model) {
		this.model = model;
	}

	/**
	 * Get data from List, allow user to update data
	 * 
	 * @param mainApplet
	 * @return
	 */
	public boolean setEditable(MainApplet mainApplet) {
		String selectedRow = mainApplet.getSelectedRow();
		idx = mainApplet.getDataList().getSelectedIndex();

		model.createPoint(selectedRow);
		String currentX = Float.toString(model.getCurrentPointX());
		String currentY = Float.toString(model.getCurrentPointY());

		mainApplet.getXValue().setText(currentX);
		mainApplet.getYValue().setText(currentY);

		return true;
	}

	/**
	 * Get data from user input to update data
	 * 
	 * @param mainApplet
	 * @return
	 */
	public boolean updatePoint(MainApplet mainApplet) {
		String pointXValue = mainApplet.getXValue().getText();
		String pointYValue = mainApplet.getYValue().getText();
		idx = mainApplet.getDataList().getSelectedIndex();

		/*
		 * if (pointXValue.length() == 0 || pointYValue.length() == 0) { return
		 * false; }
		 */
		model.getPointFromInput(pointXValue, pointYValue);

		DefaultListModel list = (DefaultListModel) mainApplet.getDataList()
				.getModel();
		list.set(idx, model.printPoint());

		model.updatePoint(idx, model.printPoint());

		return true;
	}
}
