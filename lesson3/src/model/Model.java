package model;

import java.io.FileInputStream;
import java.util.Properties;

import dataset.IGraph;

public class Model {
	DataSet dataSet;
	IGraph graph;

	Properties props;

	public Model() {
		this.dataSet = new DataSet();
		props = new Properties();

		try {
			FileInputStream fis = new FileInputStream(
					"/Users/zhenhao/Desktop/cs509/lesson4/properties");
			props.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		props.setProperty("Axis", "Off");
		props.setProperty("Grid", "Off");
		props.setProperty("TrendLine", "Off");
		props.setProperty("GraphType", "model.CartesianGraph");
	}

	public DataSet getDataSet() {
		return dataSet;
	}

	public void setGraph(IGraph g) {
		graph = g;
	}

	public IGraph getGraph() {
		return graph;
	}

	public Properties getProps() {
		return props;
	}

	public boolean setGraphProps(String Type) {
		props.setProperty("GraphType", Type);
		return true;
	}

}
