package controller;

import java.io.IOException;

import javax.swing.JFileChooser;

import view.MainApplet;
import model.Model;

public class SaveFileController {

	Model model;

	public SaveFileController(Model model) {
		this.model = model;
	}

	public boolean saveFile(MainApplet main) {
		JFileChooser fc = new JFileChooser();
		if (fc.showSaveDialog(main) != JFileChooser.APPROVE_OPTION) {
			return false;
		}
		
		String FILENAME; 
		FILENAME = fc.getSelectedFile().toString(); 
		
		try {
			model.getDataSet().saveDataset(FILENAME);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}