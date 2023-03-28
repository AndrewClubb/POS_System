package posUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTextField;

import posPD.Cashier;
import posPD.Register;
import posPD.Sale;
import posPD.Session;
import posPD.Store;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class POSLogin extends JPanel {
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Create the panel.
	 */
	public POSLogin(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setBounds(204, 11, 39, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cashier Number:");
		lblNewLabel_1.setBounds(45, 73, 113, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Register Number: ");
		lblNewLabel_2.setBounds(45, 98, 113, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Starting Cash:");
		lblNewLabel_3.setBounds(45, 123, 113, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Password: ");
		lblNewLabel_4.setBounds(45, 148, 113, 14);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Password is invalid");
		lblNewLabel_5.setBounds(0, 0, 0, 0);
		add(lblNewLabel_5);
		
		DefaultComboBoxModel<Cashier> cashierList = new DefaultComboBoxModel<Cashier>();
		for(Cashier cashier : store.getCashiers().values())
			cashierList.addElement(cashier);
		JComboBox<Cashier> comboBox = new JComboBox<Cashier>(cashierList);
		comboBox.setBounds(168, 70, 75, 20);
		add(comboBox);
		
		DefaultComboBoxModel<Register> registerList = new DefaultComboBoxModel<Register>();
		for(Register register : store.getRegisters().values())
			registerList.addElement(register);
		JComboBox<Register> comboBox_1 = new JComboBox<Register>(registerList);
		comboBox_1.setBounds(168, 95, 75, 20);
		add(comboBox_1);
		
		textField = new JTextField();
		textField.setBounds(168, 120, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(168, 145, 86, 20);
		add(passwordField);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Cashier cashier = comboBox.getItemAt(comboBox.getSelectedIndex());
				if(cashier.getPassword().contentEquals(passwordField.getText()))
				{
					Session session = new Session();
					session.setCashier((Cashier) comboBox.getSelectedItem());
					session.setRegister((Register) comboBox_1.getSelectedItem());
					session.getRegister().getCashDrawer().setCashAmount(new BigDecimal(textField.getText()));
					
					currentFrame.getContentPane().removeAll();
					currentFrame.getContentPane().add(new POSSale(currentFrame, store, session, new Sale()));
					currentFrame.getContentPane().revalidate();
				}
				else
				{
					lblNewLabel_5.setBounds(154, 36, 113, 14);
					passwordField.setText("");
				}
			}
		});
		btnNewButton.setBounds(99, 220, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(253, 220, 89, 23);
		add(btnNewButton_1);
	}
}
