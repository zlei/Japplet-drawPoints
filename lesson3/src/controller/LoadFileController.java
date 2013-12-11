package controller;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import javax.swing.DefaultListModel;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class LoadFileController {

	Model model;
	MyBasicPanel panel;

	public LoadFileController(Model model) {
		this.model = model;
	}

	public boolean loadFile(MainApplet main) {
		/*
		 * JFileChooser fc = new JFileChooser(); if (fc.showSaveDialog(main) !=
		 * JFileChooser.APPROVE_OPTION) { return false; }
		 * 
		 * String FILENAME = fc.getSelectedFile().toString();
		 */
		String FILENAME = "/Users/zhenhao/Desktop/cs509/lesson4/dataset.txt";
		try {
			model.getDataSet().loadDataset(FILENAME);
		} catch (FileNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}

		DefaultListModel list = (DefaultListModel) main.getJList().getModel();
		ArrayList<String> dataset = model.getDataSet().getDataset();

		list.clear();

		for (int n = 0; n < dataset.size(); n++) {
			list.add(n, dataset.get(n));
		}

		panel = (MyBasicPanel) main.getPanel();
		panel.repaint();

		return true;
	}
}