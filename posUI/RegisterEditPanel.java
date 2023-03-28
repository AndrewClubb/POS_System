package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Cashier;
import posPD.Register;
import posPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class RegisterEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public RegisterEditPanel(JFrame currentFrame, Store store, Register register, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Register");
		lblNewLabel.setBounds(180, 38, 86, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Number:");
		lblNewLabel_1.setBounds(90, 82, 57, 14);
		add(lblNewLabel_1);
		
		textField = new JTextField(register.getNumber());
		textField.setBounds(157, 79, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register.setNumber(textField.getText());
				if(isAdd)
					store.addRegister(register);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(90, 220, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(235, 220, 89, 23);
		add(btnNewButton_1);

	}
}
