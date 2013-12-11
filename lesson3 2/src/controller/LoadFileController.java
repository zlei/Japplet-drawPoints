package controller;

import java.io.FileNotFoundException;

import model.Model;
import view.MainApplet;
import view.MyBasicPanel;

public class LoadFileController {

	Model model;
	MyBasicPanel panel;

	public LoadFileController(Model model) {
		this.model = model;
	}

	public boolean loadFile(String filepath, MainApplet mainApplet) {
		try {
			model.loadDataset(filepath);
		} catch (FileNotFoundException e) {
			e.getMessage();
			e.printStackTrace();
		}
		this.panel = mainApplet.getMyPanel();
//		panel.repaint();
		return true;
	}
}