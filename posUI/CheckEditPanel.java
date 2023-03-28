package posUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import posPD.Check;
import posPD.Sale;
import posPD.Session;
import posPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class CheckEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Create the panel.
	 */
	public CheckEditPanel(JFrame currentFrame, Store store, Session session, Sale sale) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Amount :");
		lblNewLabel.setBounds(10, 37, 94, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Rounting Num :");
		lblNewLabel_1.setBounds(10, 62, 94, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Account Num :");
		lblNewLabel_2.setBounds(10, 87, 94, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Check Num :");
		lblNewLabel_3.setBounds(10, 112, 94, 14);
		add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(102, 34, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(102, 59, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(102, 84, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(102, 109, 86, 20);
		add(textField_3);
		textField_3.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Check check = new Check();
				check.setAmtTendered(textField.getText());
				
				if(sale.calcTotal().compareTo(new BigDecimal(textField.getText())) > 0)
					check.setAmount(textField.getText());
				else
					check.setAmount(sale.calcTotal());
				
				check.setRoutingNumber(textField_1.getText());
				check.setAccountNumber(textField_2.getText());
				check.setCheckNumber(textField_3.getText());
				sale.addPayment(check);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(0, 140, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(99, 140, 89, 23);
		add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Enter Check");
		lblNewLabel_4.setBounds(56, 12, 76, 14);
		add(lblNewLabel_4);

	}

}
