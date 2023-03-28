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
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.awt.event.ActionEvent;

public class SessionSummaryPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JButton btnNewButton;
	private Integer numSales;
	private BigDecimal totalSales;

	/**
	 * Create the panel.
	 */
	public SessionSummaryPanel(JFrame currentFrame, Store store, Session session) {
		setLayout(null);
		totalSales = new BigDecimal("0");
		numSales = 0;
		
		for(Sale sale : session.getSales())
		{
			numSales++;
			totalSales = totalSales.add(sale.calcTotal());
		}
		
		JLabel lblNewLabel = new JLabel("Session Summary");
		lblNewLabel.setBounds(172, 11, 105, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Cashier :");
		lblNewLabel_1.setBounds(22, 49, 60, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Register :");
		lblNewLabel_2.setBounds(22, 69, 60, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(session.getCashier().toString());
		lblNewLabel_3.setBounds(92, 49, 46, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel(session.getRegister().toString());
		lblNewLabel_4.setBounds(92, 69, 46, 14);
		add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("Number Sales :");
		lblNewLabel_5.setBounds(22, 105, 89, 14);
		add(lblNewLabel_5);
		
		textField = new JTextField(numSales.toString());
		textField.setBounds(121, 102, 86, 20);
		textField.setEditable(false);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Total Sales :");
		lblNewLabel_6.setBounds(22, 130, 89, 14);
		add(lblNewLabel_6);
		
		textField_1 = new JTextField(totalSales.toString());
		textField_1.setBounds(121, 127, 86, 20);
		textField_1.setEditable(false);
		add(textField_1);
		textField_1.setColumns(10);
		
		JLabel lblNewLabel_7 = new JLabel("Enter Cash :");
		lblNewLabel_7.setBounds(22, 155, 89, 14);
		add(lblNewLabel_7);
		
		textField_2 = new JTextField();
		textField_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField_3.setText(session.calcCashCountDiff(new BigDecimal(textField_2.getText())).toString());
				btnNewButton.setEnabled(true);
			}
		});
		textField_2.setBounds(121, 152, 86, 20);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Cash Count Diff :");
		lblNewLabel_8.setBounds(22, 180, 105, 14);
		add(lblNewLabel_8);
		
		textField_3 = new JTextField();
		textField_3.setBounds(121, 177, 86, 20);
		textField_3.setEditable(false);
		add(textField_3);
		textField_3.setColumns(10);
		
		btnNewButton = new JButton("End Session");
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.setEndDateTime(LocalDateTime.now());
				store.addSession(session);
				session.setAmountDiff(new BigDecimal(textField_3.getText()));
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(121, 226, 105, 23);
		btnNewButton.setEnabled(false);
		add(btnNewButton);

	}

}
