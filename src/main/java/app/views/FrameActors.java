package app.views;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import app.controllers.ActorController;
import app.models.Actor;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class FrameActors extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableActors;

	private String[] nameColumns = {"id","first name","last name","last update"};
	private Object[][] dataColumns;

	public FrameActors() {
		loadData();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 582, 622);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(64, 84, 454, 408);
		contentPane.add(scrollPane);
		
		tableActors = new JTable();
		scrollPane.setViewportView(tableActors);
		tableActors.setModel(new DefaultTableModel(
			dataColumns,
			nameColumns
		));
	}

	public void loadData(){
		ActorController actorController = new ActorController();
		ArrayList<Actor> actors = actorController.getAll();
		int sizeActors = actors.size();
		Object[][] dataColumns = new Object[sizeActors][4];
		for (int i=0;i<actors.size();i++){
			dataColumns[i][0] = actors.get(i).getId();
			dataColumns[i][1] = actors.get(i).getFirstName();
			dataColumns[i][2] = actors.get(i).getLastName();
			dataColumns[i][3] = actors.get(i).getLastUpdate();
		}
		this.dataColumns = dataColumns;
	}
}
