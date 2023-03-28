package posUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import posPD.Sale;
import posPD.Session;
import posPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POSPayment extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JPanel cashPanel;
	private JPanel checkPanel;
	private JPanel creditPanel;

	/**
	 * Create the panel.
	 */
	public POSPayment(JFrame currentFrame, Store store, Session session, Sale currentSale) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Payment Due :");
		lblNewLabel.setBounds(10, 34, 85, 14);
		add(lblNewLabel);
		
		textField = new JTextField(currentSale.calcTotal().toString());
		textField.setBounds(9, 59, 86, 20);
		textField.setEditable(false);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Amount Tendered :");
		lblNewLabel_1.setBounds(10, 90, 109, 14);
		add(lblNewLabel_1);
		
		textField_1 = new JTextField(currentSale.calcAmtTendered().toString());
		textField_1.setBounds(10, 115, 86, 20);
		textField_1.setEditable(false);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Cash");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cashPanel = new CashEditPanel(currentFrame, store, session, currentSale);
				cashPanel.setBounds(171, 20, 273, 244);
				if(checkPanel != null)
				{
					remove(checkPanel);
					checkPanel = null;
				}
				if(creditPanel != null)
				{
					remove(creditPanel);
					creditPanel = null;
				}
				add(cashPanel);
				revalidate();
				repaint();
			}
		});
		btnNewButton.setBounds(6, 155, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Check");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				checkPanel = new CheckEditPanel(currentFrame, store, session, currentSale);
				checkPanel.setBounds(171, 20, 273, 244);
				if(cashPanel != null)
				{
					remove(cashPanel);
					cashPanel = null;
				}
				if(creditPanel != null)
				{
					remove(creditPanel);
					creditPanel = null;
				}
				add(checkPanel);
				revalidate();
				repaint();
			}
		});
		btnNewButton_1.setBounds(6, 189, 89, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Credit");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				creditPanel = new CreditEditPanel(currentFrame, store, session, currentSale);
				creditPanel.setBounds(171, 20, 273, 244);
				if(checkPanel != null)
				{
					remove(checkPanel);
					checkPanel = null;
				}
				if(cashPanel != null)
				{
					remove(cashPanel);
					cashPanel = null;
				}
				add(creditPanel);
				revalidate();
				repaint();
			}
		});
		btnNewButton_2.setBounds(6, 223, 89, 23);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Payment Complete");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSSale(currentFrame, store, session, currentSale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(136, 253, 141, 23);
		if(!currentSale.isPaymentEnough())	
			btnNewButton_3.setEnabled(false);
		add(btnNewButton_3);
		
		JLabel lblNewLabel_2 = new JLabel("Payment");
		lblNewLabel_2.setBounds(140, 11, 60, 14);
		add(lblNewLabel_2);

	}
}
