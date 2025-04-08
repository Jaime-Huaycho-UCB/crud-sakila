package app.views;

import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import app.controllers.ActorController;
import app.models.Actor;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrameActors extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable tableActors;

	private String[] nameColumns = {"id","first name","last name","last update"};
	private Object[][] dataColumns;
	private JButton btnUpdateActor;
	private JButton btnDeleteActor;

	public FrameActors() {
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
		
		JButton btnAddActor = new JButton("Agregar actor");
		btnAddActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				addActor();
			}
		});
		btnAddActor.setBounds(379, 518, 139, 29);
		contentPane.add(btnAddActor);
		
		btnUpdateActor = new JButton("Actuaizar actor");
		btnUpdateActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				updateActor();
			}
		});
		btnUpdateActor.setBounds(64, 518, 139, 29);
		contentPane.add(btnUpdateActor);
		
		btnDeleteActor = new JButton("Eliminar actor");
		btnDeleteActor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteActor();
			}
		});
		btnDeleteActor.setBounds(234, 518, 117, 29);
		contentPane.add(btnDeleteActor);
		loadData();
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
		tableActors.setModel(new DefaultTableModel(
			this.dataColumns,
			this.nameColumns
		));
	}

	public void addActor(){
		String firstName = JOptionPane.showInputDialog("Ingrese al primero nombre del actor nuevo");
		String lastName = JOptionPane.showInputDialog("Ingresa el apellido del actor nuevo");
		ActorController actorController = new ActorController();
		if (actorController.createActor(firstName, lastName)){
			loadData();
			JOptionPane.showMessageDialog(null, "Se ingreso exitosamente al actor");
		}else{
			JOptionPane.showMessageDialog(null,"Hubo un error al ingresar al actor");
		}
	}

	public void updateActor(){
		int selectedRow = tableActors.getSelectedRow();
		if (selectedRow != -1){
			int id = Integer.parseInt(dataColumns[selectedRow][0].toString());
			String firstName = dataColumns[selectedRow][1].toString();
			String lastName = dataColumns[selectedRow][2].toString();
			String newFirstName = JOptionPane.showInputDialog("Ingresa el primer nombre del actor a actualzar", firstName);
			String newLastName = JOptionPane.showInputDialog("Ingresa el apellido del actor a actualzar", lastName);
			ActorController actorController = new ActorController();
			if (actorController.updateActor(id, newFirstName, newLastName)){
				loadData();
				JOptionPane.showMessageDialog(null, "Se actualizo exitosamente al actor");
			}else{
				JOptionPane.showMessageDialog(null,"Hubo un error al actualizar al actor");
			}
		}else{
			JOptionPane.showMessageDialog(null,"Seleccione un actor a actualizar");
		}
	}

	public void deleteActor(){
		int selectedRow = tableActors.getSelectedRow();
		if (selectedRow != -1){
			int id = Integer.parseInt(dataColumns[selectedRow][0].toString());
			ActorController actorController = new ActorController();
			if (actorController.deleteActor(id)){
				loadData();
				JOptionPane.showMessageDialog(null, "Se elimino exitosamente al actor");
			}else{
				JOptionPane.showMessageDialog(null,"Hubo un error al eliminar al actor");
			}
		}else{
			JOptionPane.showMessageDialog(null,"Seleccione un actor a eliminar");
		}
	}
}
