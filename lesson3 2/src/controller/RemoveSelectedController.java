package controller;

import javax.swing.DefaultListModel;

import model.Model;
import view.MainApplet;

public class RemoveSelectedController {
	Model model;

	public RemoveSelectedController(Model model) {
		this.model = model;
	}

	/**
	 * Remove selected data points
	 * @param mainApplet
	 * @return
	 */
	public boolean removePoint(MainApplet mainApplet) {
		int[] row = mainApplet.getDataList().getSelectedIndices();
		if (row.length == 0) {
			return false;
		}

		DefaultListModel list = (DefaultListModel) mainApplet.getDataList()
				.getModel();
		for (int idx = row.length - 1; idx >= 0; idx--) {
			list.remove(row[idx]);
			model.removePoint(row[idx]);
		}

		mainApplet.getXValue().setText("");
		mainApplet.getYValue().setText("");

		return true;
	}
}
