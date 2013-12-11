package controller;

import javax.swing.DefaultListModel;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class RemoveSelectedController {
	Model model;
	MyBasicPanel panel;
	public RemoveSelectedController(Model model) {
		this.model = model;
	}

	/**
	 * Remove selected data points
	 * @param mainApplet
	 * @return
	 */
	public boolean removePoint(MainApplet mainApplet) {
		int[] row = mainApplet.getJList().getSelectedIndices();
		if (row.length == 0) {
			return false;
		}

		DefaultListModel list = (DefaultListModel) mainApplet.getJList().getModel();
		for (int idx = row.length - 1; idx >= 0; idx--) {
			list.remove(row[idx]);
			model.getDataSet().removePoint(row[idx]);
		}

		mainApplet.getXValue().setText("");
		mainApplet.getYValue().setText("");
		
		panel = (MyBasicPanel) mainApplet.getPanel();
		panel.repaint();
		
		return true;
	}
}
