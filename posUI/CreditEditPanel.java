package posUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import posPD.Credit;
import posPD.Sale;
import posPD.Session;
import posPD.Store;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.awt.event.ActionEvent;

public class CreditEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBox<String> comboBox;

	/**
	 * Create the panel.
	 */
	public CreditEditPanel(JFrame currentFrame, Store store, Session session, Sale sale) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Amount :");
		lblNewLabel.setBounds(10, 38, 87, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Card Type :");
		lblNewLabel_1.setBounds(10, 63, 87, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Account Num :");
		lblNewLabel_2.setBounds(10, 88, 87, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Expire Date :");
		lblNewLabel_3.setBounds(10, 113, 87, 14);
		add(lblNewLabel_3);
		
		textField = new JTextField();
		textField.setBounds(100, 35, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Credit credit = new Credit();
				
				if(sale.calcTotal().compareTo(new BigDecimal(textField.getText())) > 0)
					credit.setAmount(textField.getText());
				else
					credit.setAmount(sale.calcTotal());
				
				credit.setAmtTendered(textField.getText());
				
				credit.setCardType((String) comboBox.getSelectedItem());
				credit.setAcctNumber(textField_1.getText());
				credit.setExpireDate(LocalDate.parse(textField_2.getText(), DateTimeFormatter.ofPattern("M/d/yyyy")));
				sale.addPayment(credit);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(0, 138, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(100, 138, 89, 23);
		add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Enter Credit Payment");
		lblNewLabel_4.setBounds(42, 11, 108, 14);
		add(lblNewLabel_4);
		
		DefaultComboBoxModel<String> cardList = new DefaultComboBoxModel<String>();
		cardList.addElement("Visa");
		cardList.addElement("MC");
		comboBox = new JComboBox<String>(cardList);
		comboBox.setBounds(100, 60, 86, 20);
		add(comboBox);
		
		textField_1 = new JTextField();
		textField_1.setBounds(100, 85, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(100, 110, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);

	}

}
