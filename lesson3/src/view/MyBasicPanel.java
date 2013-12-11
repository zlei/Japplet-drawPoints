package view;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import model.DrawBasicGraph;
import model.Model;
import dataset.IGraph;

public class MyBasicPanel extends JPanel {

	Model model;

	int type;

	//default gg
	IGraph gg = null;
	/*
	 * public MyBasicPanel(Model model, int type) { this.model = model;
	 * this.type = type; }
	 */
	public MyBasicPanel(Model model) {
		this.model = model;
	}

	public boolean drawGraph() {
		String graphType = model.getProps().getProperty("GraphType");
		try {
			Class clazz = Class.forName(graphType);
			gg = (IGraph) clazz.newInstance();
			gg.setDataSet(model.getDataSet());
			gg.setProperties(model.getProps());
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * Draws the defined function.
	 * 
	 * @param g
	 *            the components graphics context.
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		drawGraph();
		// setType(type);
		Graphics2D g2D = (Graphics2D) g;
		gg.draw(g2D, this);
	}
	
	public Model getModel(){
		return model;
	}
}