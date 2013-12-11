package controller;

import java.io.IOException;

import model.Model;

public class SaveFileController {

	Model model;

	public SaveFileController(Model model) {
		this.model = model;
	}

	public boolean saveFile(String filepath) {
		try {
			model.saveDataset(filepath);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}
}