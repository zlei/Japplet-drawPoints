package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import model.Model;
import controller.AddPointController;
import controller.CartesianController;
import controller.ColumnController;
import controller.EditPointController;
import controller.HorizontalBarController;
import controller.LoadFileController;
import controller.RemoveSelectedController;
import controller.SaveFileController;
import controller.ShowAxisController;
import controller.ShowGridController;
import controller.TrendLineController;

public class MainApplet extends JApplet {
	Model model;

	private MyBasicPanel panel;

	private JButton btn_load_data;
	private JButton btn_save_data;
	private JButton btn_delete_data;
	private JButton btn_edit_data;
	private JLabel label_x;
	private JTextField text_xValue;
	private JLabel label_y;
	private JTextField text_yValue;
	private JButton btn_add_data;
	private JCheckBox chckbx_trend_line;
	private JCheckBox chckbx_grid;
	private JCheckBox chckbx_Axis;
	private JComboBox comboBox;
	private String[] graphType = { "Cartesian Plot", "Column Plot",
			"Horizonal Bar Plot", "Multi Line Plot" };

	JScrollPane scrollPane;
	JList list;

	public MainApplet() {

		model = new Model();
		panel = new MyBasicPanel(model);

		scrollPane = new JScrollPane();
		list = new JList();
		list.setModel(new DefaultListModel());
		scrollPane.setViewportView(list);
		list.setFixedCellHeight(30);
		list.setFixedCellWidth(80);
		list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

		btn_load_data = new JButton("Load Data");
		btn_load_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new LoadFileController(MainApplet.this.model)
						.loadFile(MainApplet.this);
			}
		});

		btn_save_data = new JButton("Save Data");
		btn_save_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new SaveFileController(MainApplet.this.model)
						.saveFile(MainApplet.this);
			}
		});

		btn_delete_data = new JButton("Delete Selected Data");
		btn_delete_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RemoveSelectedController(MainApplet.this.model)
						.removePoint(MainApplet.this);
			}
		});

		btn_add_data = new JButton("Add Data");
		btn_add_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new AddPointController(MainApplet.this.model)
						.addPoint(MainApplet.this);

			}
		});

		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if (e.getValueIsAdjusting()) {
					return;
				} else {
					new EditPointController(MainApplet.this.model)
							.setEditable(MainApplet.this);
				}
			}
		});

		btn_edit_data = new JButton("Edit Selected Data");
		btn_edit_data.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditPointController(MainApplet.this.model)
						.editPoint(MainApplet.this);
			}
		});

		label_x = new JLabel("x:");

		text_xValue = new JTextField();
		text_xValue.setColumns(10);

		label_y = new JLabel("y:");

		text_yValue = new JTextField();
		text_yValue.setColumns(10);

		chckbx_trend_line = new JCheckBox("Show Trend Line");
		chckbx_trend_line.setEnabled(false);
		chckbx_trend_line.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new TrendLineController(MainApplet.this.model)
						.changeTrendline(MainApplet.this);
			}

		});

		chckbx_grid = new JCheckBox("Show Horizontal Lines");
		chckbx_grid.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ShowGridController(MainApplet.this.model)
						.changeGrid(MainApplet.this);
			}

		});

		chckbx_Axis = new JCheckBox("Show Axis");
		chckbx_Axis.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				new ShowAxisController(MainApplet.this.model)
						.changeAxis(MainApplet.this);
			}
		});

		comboBox = new JComboBox(graphType);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				chckbx_trend_line.setSelected(false);;
				int pos = comboBox.getSelectedIndex();
				switch (pos) {
				case 0:
					chckbx_trend_line.setEnabled(true);
					new CartesianController(MainApplet.this.model)
							.drawCartesian(MainApplet.this);
					break;
				case 1:
					chckbx_trend_line.setEnabled(false);
					new ColumnController(MainApplet.this.model)
							.drawColumn(MainApplet.this);
					break;
				case 2:
					chckbx_trend_line.setEnabled(false);
					new HorizontalBarController(MainApplet.this.model)
							.drawHorizontalBar(MainApplet.this);
					break;
				case 3:
					chckbx_trend_line.setEnabled(false);
					break;
				}
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
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.LEADING)
														.addComponent(
																panel,
																GroupLayout.PREFERRED_SIZE,
																483,
																GroupLayout.PREFERRED_SIZE)
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				comboBox,
																				GroupLayout.PREFERRED_SIZE,
																				149,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				chckbx_trend_line)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
																		.addComponent(
																				chckbx_Axis,
																				GroupLayout.PREFERRED_SIZE,
																				112,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED,
																				GroupLayout.DEFAULT_SIZE,
																				Short.MAX_VALUE)
																		.addComponent(
																				chckbx_grid,
																				GroupLayout.PREFERRED_SIZE,
																				175,
																				GroupLayout.PREFERRED_SIZE)
																		.addGap(276))
														.addGroup(
																groupLayout
																		.createSequentialGroup()
																		.addComponent(
																				btn_add_data,
																				GroupLayout.PREFERRED_SIZE,
																				107,
																				GroupLayout.PREFERRED_SIZE)
																		.addPreferredGap(
																				ComponentPlacement.RELATED)
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
																				ComponentPlacement.UNRELATED)
																		.addComponent(
																				btn_save_data,
																				GroupLayout.PREFERRED_SIZE,
																				248,
																				GroupLayout.PREFERRED_SIZE)))
										.addContainerGap()));
		groupLayout
				.setVerticalGroup(groupLayout
						.createParallelGroup(Alignment.LEADING)
						.addGroup(
								groupLayout
										.createSequentialGroup()
										.addGap(43)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.TRAILING)
														.addComponent(
																panel,
																GroupLayout.DEFAULT_SIZE,
																323,
																Short.MAX_VALUE)
														.addComponent(
																scrollPane,
																GroupLayout.DEFAULT_SIZE,
																323,
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
																comboBox,
																GroupLayout.PREFERRED_SIZE,
																36,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																chckbx_trend_line)
														.addComponent(
																chckbx_Axis)
														.addComponent(
																chckbx_grid))
										.addPreferredGap(
												ComponentPlacement.UNRELATED)
										.addGroup(
												groupLayout
														.createParallelGroup(
																Alignment.BASELINE)
														.addComponent(label_y)
														.addComponent(
																text_yValue,
																GroupLayout.PREFERRED_SIZE,
																GroupLayout.DEFAULT_SIZE,
																GroupLayout.PREFERRED_SIZE)
														.addComponent(
																btn_add_data)
														.addComponent(
																btn_edit_data)
														.addComponent(
																btn_delete_data)
														.addComponent(
																btn_load_data)
														.addComponent(
																btn_save_data))
										.addGap(19)));

		getContentPane().setLayout(groupLayout);
	}

	public JList getJList() {
		return list;
	}

	public JTextField getXValue() {
		return text_xValue;
	}

	public JTextField getYValue() {
		return text_yValue;
	}

	public JPanel getPanel() {
		return panel;
	}
}
