package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Model;
import controller.AddPointController;
import controller.BasicGraphController;
import controller.CartesianController;
import controller.ColumnController;
import controller.EditPointController;
import controller.LoadFileController;
import controller.RemoveSelectedController;
import controller.SaveFileController;
import controller.ShowAxisController;
import controller.ShowGridController;
import controller.TrendLineController;

public class MainApplet extends JApplet {
	Model model;
	private MyBasicPanel panel;
	private JScrollPane scrollPane;
	private JList list_data;
	private JButton btn_load_data;
	private JButton btn_save_data;
	private JButton btn_delete_data;
	private JButton btn_edit_data;
	private JLabel label_x;
	private JTextField text_xValue;
	private JLabel label_y;
	private JTextField text_yValue;
	private JButton btn_add_data;
	private JRadioButton rdbtn_CartesianPlot;
	private JRadioButton rdbtn_ColumnPlot;
	private JCheckBox chckbx_trend_line;
	private JCheckBox chckbx_grid;
	private JCheckBox chckbx_Axis;

	private String selectedRow;
	private static int basic_plot = 0;
	private static int cartesian_plot = 1;
	private static int column_plot = 2;

	// private String FILENAME =
	// "/home/zlei/public_html/cs509/lesson3/dataset.txt";
	// private String FILENAME =
	// "/Users/zhenhao/Documents/workspace/cs509/Japplet-drawPoints/lesson3/data.txt";

	private String FILENAME = "/Users/zhenhao/Documents/workspace/cs509/Japplet-drawPoints/lesson3/dataset.txt";

	// private JFileChooser fc = new JFileChooser(FILENAME);

	public MainApplet() {

		model = new Model();
		// panel = new MyBasicPanel(model);
		panel = new BasicGraphController(MainApplet.this.model)
				.drawBasic(MainApplet.this);
		// panel = new MyBasicPanel(MainApplet.this.model, basic_plot);

		scrollPane = new JScrollPane();

		btn_load_data = new JButton("Load Data");
		btn_load_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*
				 * int returnVal = fc.showOpenDialog(MainApplet.this);
				 * 
				 * if (returnVal == JFileChooser.APPROVE_OPTION) { FILENAME =
				 * fc.getSelectedFile().toString();
				 */
				if (new LoadFileController(MainApplet.this.model).loadFile(
						FILENAME, MainApplet.this)) {
					refreshList();

					panel.repaint();
				}
			}
		});

		btn_save_data = new JButton("Save Data");
		btn_save_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				/*
				 * int returnVal = fc.showOpenDialog(MainApplet.this); if
				 * (returnVal == JFileChooser.APPROVE_OPTION) { FILENAME =
				 * fc.getSelectedFile().toString(); // This is where a real
				 * application would open the file. }
				 */
				new SaveFileController(MainApplet.this.model)
						.saveFile(FILENAME);
				panel.repaint();
			}
		});

		btn_delete_data = new JButton("Delete Selected Data");
		btn_delete_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RemoveSelectedController(MainApplet.this.model)
						.removePoint(MainApplet.this);
				panel.repaint();
			}
		});

		btn_add_data = new JButton("Add Data");
		btn_add_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddPointController(MainApplet.this.model)
						.addPoint(MainApplet.this);
				panel.repaint();
			}
		});

		btn_edit_data = new JButton("Update Selected Data");
		btn_edit_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditPointController(MainApplet.this.model)
						.updatePoint(MainApplet.this);
				panel.repaint();
			}
		});

		label_x = new JLabel("x:");

		text_xValue = new JTextField();
		text_xValue.setColumns(10);

		label_y = new JLabel("y:");

		text_yValue = new JTextField();
		text_yValue.setColumns(10);

		rdbtn_CartesianPlot = new JRadioButton("Cartesian Plot");
		rdbtn_CartesianPlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtn_CartesianPlot.setSelected(true);
				rdbtn_ColumnPlot.setSelected(false);
				if (rdbtn_CartesianPlot.isSelected()) {
					new CartesianController(MainApplet.this.model)
							.drawCartesian(MainApplet.this);
					chckbx_trend_line.setSelected(false);
					chckbx_grid.setSelected(false);
					chckbx_Axis.setSelected(false);

				}

				panel.repaint();
			}
		});
		rdbtn_ColumnPlot = new JRadioButton("Column Plot");
		rdbtn_ColumnPlot.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtn_ColumnPlot.setSelected(true);
				rdbtn_CartesianPlot.setSelected(false);
				if (rdbtn_ColumnPlot.isSelected()) {
					new ColumnController(MainApplet.this.model)
							.drawColumn(MainApplet.this);
					chckbx_trend_line.setSelected(false);
					chckbx_grid.setSelected(false);
					chckbx_Axis.setSelected(false);
				}

				panel.repaint();
			}
		});

		chckbx_trend_line = new JCheckBox("Show Trend Line");
		chckbx_trend_line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TrendLineController(MainApplet.this.model)
						.changeTrendline(MainApplet.this);

				panel.repaint();
			}

		});

		chckbx_grid = new JCheckBox("Show Horizontal Lines");
		chckbx_grid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowGridController(MainApplet.this.model)
						.changeGrid(MainApplet.this);
				panel.repaint();
			}

		});

		chckbx_Axis = new JCheckBox("Show Axis");
		chckbx_Axis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowAxisController(MainApplet.this.model)
						.changeAxis(MainApplet.this);

				panel.repaint();
			}
		});

		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout
				.setHorizontalGroup(groupLayout
						.createParallelGroup(Alignment.TRAILING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addContainerGap()
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGap(6)
																		.addComponent(
																				btn_add_data,
																				GroupLayout.PREFERRED_SIZE,
																				107,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(6)
																										.addComponent(
																												btn_edit_data,
																												GroupLayout.PREFERRED_SIZE,
																												158,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btn_delete_data,
																												GroupLayout.PREFERRED_SIZE,
																												158,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btn_load_data)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												btn_save_data))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(29)
																										.addComponent(
																												chckbx_trend_line)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												chckbx_grid,
																												GroupLayout.PREFERRED_SIZE,
																												175,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												chckbx_Axis,
																												GroupLayout.PREFERRED_SIZE,
																												112,
																												GroupLayout.PREFERRED_SIZE))
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addGap(101)
																										.addComponent(
																												rdbtn_CartesianPlot)
																										.addGap(76)
																										.addComponent(
																												rdbtn_ColumnPlot,
																												GroupLayout.PREFERRED_SIZE,
																												119,
																												GroupLayout.PREFERRED_SIZE))))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.LEADING)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												label_x,
																												GroupLayout.PREFERRED_SIZE,
																												20,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												text_xValue,
																												GroupLayout.PREFERRED_SIZE,
																												65,
																												GroupLayout.PREFERRED_SIZE))
																						.addComponent(
																								scrollPane,
																								Alignment.TRAILING,
																								GroupLayout.PREFERRED_SIZE,
																								91,
																								GroupLayout.PREFERRED_SIZE)
																						.addGroup(
																								groupLayout
																										.createSequentialGroup()
																										.addComponent(
																												label_y,
																												GroupLayout.PREFERRED_SIZE,
																												20,
																												GroupLayout.PREFERRED_SIZE)
																										.addPreferredGap(
																												ComponentPlacement.RELATED)
																										.addComponent(
																												text_yValue,
																												GroupLayout.PREFERRED_SIZE,
																												65,
																												GroupLayout.PREFERRED_SIZE)))
																		.addGap(18)
																		.addComponent(
																				panel,
																				GroupLayout.DEFAULT_SIZE,
																				517,
																				Short.MAX_VALUE)
																		.addGap(39)))
										.addGap(209)));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(19)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																panel,
																GroupLayout.DEFAULT_SIZE,
																230,
																Short.MAX_VALUE)
														.addComponent(
																scrollPane,
																GroupLayout.DEFAULT_SIZE,
																230,
																Short.MAX_VALUE))
										.addGap(12)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(label_x)
														.addComponent(
																text_xValue,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																rdbtn_CartesianPlot)
														.addComponent(
																rdbtn_ColumnPlot))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								chckbx_trend_line)
																						.addComponent(
																								chckbx_grid)
																						.addComponent(
																								chckbx_Axis))
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addGroup(
																				groupLayout
																						.createParallelGroup(
																								Alignment.BASELINE)
																						.addComponent(
																								btn_add_data)
																						.addComponent(
																								btn_edit_data)
																						.addComponent(
																								btn_delete_data)
																						.addComponent(
																								btn_load_data)
																						.addComponent(
																								btn_save_data)))
														.addGroup(
																groupLayout
																		.createParallelGroup(
																				Alignment.BASELINE)
																		.addComponent(
																				label_y)
																		.addComponent(
																				text_yValue,
																				GroupLayout.PREFERRED_SIZE,
																				GroupLayout.DEFAULT_SIZE,
																				GroupLayout.PREFERRED_SIZE)))
										.addGap(19)));

		refreshList();
		getContentPane().setLayout(groupLayout);

	}

	/**
	 * update JList
	 */
	public void refreshList() {
		DefaultListModel defListModel = new DefaultListModel();
		for (String testdata : model.getDataset()) {
			defListModel.addElement(testdata);
		}
		list_data = new JList(defListModel);
		scrollPane.setViewportView(list_data);
		list_data.setFixedCellHeight(30);
		list_data.setFixedCellWidth(80);
		list_data
				.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		list_data.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				} else {
					if (!list_data.isSelectionEmpty()) {
						new EditPointController(MainApplet.this.model)
								.setEditable(MainApplet.this);
						return;
					}
				}
			}
		});
	}

	public JList getDataList() {
		return list_data;
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}

	public JTextField getXValue() {
		return text_xValue;
	}

	public JTextField getYValue() {
		return text_yValue;
	}

	public String getSelectedRow() {
		return list_data.getSelectedValue().toString();
	}

	public MyBasicPanel getMyPanel() {
		return panel;
	}
}
