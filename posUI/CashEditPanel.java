package posUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;

import posPD.Cash;
import posPD.Sale;
import posPD.Session;
import posPD.Store;

import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.awt.event.ActionEvent;

public class CashEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public CashEditPanel(JFrame currentFrame, Store store, Session session, Sale sale) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Amount Tendered :");
		lblNewLabel.setBounds(10, 45, 123, 14);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.setBounds(127, 42, 59, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Cash cash = new Cash();
				cash.setAmtTendered(textField.getText());
				
				if(sale.calcTotal().compareTo(new BigDecimal(textField.getText())) > 0)
					cash.setAmount(textField.getText());
				else
					cash.setAmount(sale.calcTotal());
				
				sale.addPayment(cash);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(10, 89, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame, store, session, sale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(127, 89, 89, 23);
		add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Cash Payment");
		lblNewLabel_1.setBounds(63, 11, 123, 14);
		add(lblNewLabel_1);

	}

}
