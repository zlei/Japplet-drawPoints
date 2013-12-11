package controller;

import javax.swing.DefaultListModel;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class EditPointController {
	Model model;
	private int idx;
	DefaultListModel list;
	MyBasicPanel panel;
	
	public EditPointController(Model model) {
		this.model = model;
	}

	public boolean setEditable(MainApplet mainApplet) {
		idx = mainApplet.getJList().getSelectedIndex();
		list = (DefaultListModel) mainApplet.getJList().getModel();

		if (idx < 0)
			return false;

		String data = (String) list.getElementAt(idx);
		String[] parts = data.split(",");
		String x = parts[0];
		String y = parts[1];

		mainApplet.getXValue().setText(x);
		mainApplet.getYValue().setText(y);

		return true;
	}

	public boolean editPoint(MainApplet mainApplet) {
		String pointXValue = mainApplet.getXValue().getText();
		String pointYValue = mainApplet.getYValue().getText();
		String point = pointXValue + " , " + pointYValue;

		list = (DefaultListModel) mainApplet.getJList().getModel();
		list.set(idx, point);
		model.getDataSet().editPoint(idx, point);

		panel = (MyBasicPanel) mainApplet.getPanel();
		panel.repaint();

		return true;
	}
}
