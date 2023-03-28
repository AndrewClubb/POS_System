package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Cashier;
import posPD.Person;
import posPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CashierEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;

	/**
	 * Create the panel.
	 */
	public CashierEditPanel(JFrame currentFrame, Store store, Cashier cashier, Boolean isAdd) {
		setLayout(null);
		Person person;
		if(cashier.getPerson() != null)
			person = cashier.getPerson();
		else
		{
			person = new Person();
			cashier.setPerson(person);
		}
		
		JLabel lblNewLabel = new JLabel("Edit Cashier");
		lblNewLabel.setBounds(184, 29, 71, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Number:");
		lblNewLabel_1.setBounds(35, 68, 56, 14);
		add(lblNewLabel_1);
		
		textField = new JTextField(cashier.getNumber());
		textField.setBounds(101, 65, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Name:");
		lblNewLabel_2.setBounds(35, 93, 46, 14);
		add(lblNewLabel_2);
		
		textField_1 = new JTextField(person.getName());
		textField_1.setBounds(101, 90, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);

		JLabel lblNewLabel_3 = new JLabel("Address:");
		lblNewLabel_3.setBounds(35, 118, 56, 14);
		add(lblNewLabel_3);
		
		textField_2 = new JTextField(person.getAddress());
		textField_2.setBounds(101, 115, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_4 = new JLabel("City:");
		lblNewLabel_4.setBounds(35, 143, 46, 14);
		add(lblNewLabel_4);

		textField_3 = new JTextField(person.getCity());
		textField_3.setBounds(101, 140, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Phone:");
		lblNewLabel_5.setBounds(35, 168, 46, 14);
		add(lblNewLabel_5);
		
		textField_4 = new JTextField(person.getPhone());
		textField_4.setBounds(101, 165, 86, 20);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Password:");
		lblNewLabel_6.setBounds(35, 193, 63, 14);
		add(lblNewLabel_6);
		
		textField_5 = new JTextField(cashier.getPassword());
		textField_5.setBounds(101, 190, 86, 20);
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("SSN:");
		lblNewLabel_7.setBounds(199, 68, 46, 14);
		add(lblNewLabel_7);
		
		textField_6 = new JTextField(person.getSSN());
		textField_6.setBounds(255, 65, 86, 20);
		add(textField_6);
		textField_6.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("State:");
		lblNewLabel_8.setBounds(199, 93, 46, 14);
		add(lblNewLabel_8);
		
		textField_7 = new JTextField(person.getState());
		textField_7.setBounds(255, 90, 86, 20);
		add(textField_7);
		textField_7.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Zip:");
		lblNewLabel_9.setBounds(199, 118, 46, 14);
		add(lblNewLabel_9);
		
		textField_8 = new JTextField(person.getZip());
		textField_8.setBounds(255, 115, 86, 20);
		add(textField_8);
		textField_8.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cashier.setNumber(textField.getText());
				cashier.getPerson().setName(textField_1.getText());
				cashier.getPerson().setAddress(textField_2.getText());
				cashier.getPerson().setCity(textField_3.getText());
				cashier.getPerson().setPhone(textField_4.getText());
				cashier.setPassword(textField_5.getText());
				cashier.getPerson().setSSN(textField_6.getText());
				cashier.getPerson().setState(textField_7.getText());
				cashier.getPerson().setZip(textField_8.getText());
				
				if(isAdd)
				{
					store.addCashier(cashier);
				}
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(80, 234, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(228, 234, 89, 23);
		add(btnNewButton_1);

	}

}
