package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Sale;
import posPD.SaleLineItem;
import posPD.Session;
import posPD.Store;
import posPD.Item;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POSSale extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private Sale currentSale;
	private DefaultListModel<SaleLineItem> saleList;
	private JPanel currentPanel;

	/**
	 * Create the panel.
	 */
	public POSSale(JFrame currentFrame, Store store, Session session, Sale sale) {
		setLayout(null);
		currentSale = sale;
		currentPanel = this;
		
		JLabel lblNewLabel_12 = new JLabel("UPC not found");
		lblNewLabel_12.setBounds(0, 0, 0, 0);
		add(lblNewLabel_12);
		
		JLabel lblNewLabel = new JLabel("Cashier:");
		lblNewLabel.setBounds(10, 11, 53, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Register:");
		lblNewLabel_1.setBounds(10, 29, 53, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel(session.getCashier().toString());
		lblNewLabel_2.setBounds(73, 11, 66, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel(session.getRegister().toString());
		lblNewLabel_3.setBounds(73, 29, 66, 14);
		add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Sale");
		lblNewLabel_4.setBounds(211, 29, 35, 14);
		add(lblNewLabel_4);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Tax Free");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected())
				{
					currentSale.setTaxFree("Y");
				}
				else
				{
					currentSale.setTaxFree("N");
				}
				updateMoneyTextFields();
			}
		});
		chckbxNewCheckBox.setBounds(325, 52, 76, 23);
		add(chckbxNewCheckBox);
		
		JLabel lblNewLabel_5 = new JLabel("Item : ");
		lblNewLabel_5.setBounds(17, 82, 35, 14);
		add(lblNewLabel_5);
		
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Item item = store.findItemForUPC(textField.getText());
				if(item == null)
				{
					lblNewLabel_12.setBounds(131, 56, 83, 14);
				}
				else
				{
					SaleLineItem sli = new SaleLineItem(currentSale, item, textField_1.getText());
					currentSale.addSaleLineItem(sli);
					saleList.addElement(sli);
					
					updateMoneyTextFields();
					
					lblNewLabel_12.setBounds(0, 0, 0, 0);
				}
				textField.setText("");
			}
		});
		textField.setBounds(62, 79, 107, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_6 = new JLabel("Quantity : ");
		lblNewLabel_6.setBounds(179, 82, 58, 14);
		add(lblNewLabel_6);
		
		textField_1 = new JTextField("1");
		textField_1.setBounds(239, 79, 46, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		saleList = new DefaultListModel<SaleLineItem>();
		for(SaleLineItem sli : currentSale.getSaleLineItems())
		{
			saleList.addElement(sli);
		}
		JList<SaleLineItem> list = new JList<SaleLineItem>(saleList);
		list.setBounds(27, 107, 243, 106);
		add(list);
		
		JLabel lblNewLabel_7 = new JLabel("SubTotal :");
		lblNewLabel_7.setBounds(292, 119, 72, 14);
		add(lblNewLabel_7);
		
		textField_2 = new JTextField(currentSale.calcSubTotal().toString());
		textField_2.setBounds(374, 116, 66, 20);
		textField_2.setEditable(false);
		add(textField_2);
		textField_2.setColumns(10);
		
		JLabel lblNewLabel_8 = new JLabel("Tax :");
		lblNewLabel_8.setBounds(315, 144, 49, 14);
		add(lblNewLabel_8);
		
		textField_3 = new JTextField(currentSale.calcTax().toString());
		textField_3.setBounds(374, 141, 66, 20);
		textField_3.setEditable(false);
		add(textField_3);
		textField_3.setColumns(10);
		
		JLabel lblNewLabel_9 = new JLabel("Total :");
		lblNewLabel_9.setBounds(313, 169, 49, 14);
		add(lblNewLabel_9);
		
		textField_4 = new JTextField(currentSale.calcTotal().toString());
		textField_4.setBounds(374, 166, 66, 20);
		textField_4.setEditable(false);
		add(textField_4);
		textField_4.setColumns(10);
		
		JLabel lblNewLabel_10 = new JLabel("Amt Tendered :");
		lblNewLabel_10.setBounds(268, 228, 96, 14);
		add(lblNewLabel_10);
		
		textField_5 = new JTextField(currentSale.calcAmtTendered().toString());
		textField_5.setBounds(374, 225, 66, 20);
		textField_5.setEditable(false);
		add(textField_5);
		textField_5.setColumns(10);
		
		JLabel lblNewLabel_11 = new JLabel("Change :");
		lblNewLabel_11.setBounds(304, 262, 60, 14);
		add(lblNewLabel_11);
		
		textField_6 = new JTextField(currentSale.calcChange().toString());
		textField_6.setBounds(374, 259, 66, 20);
		textField_6.setEditable(false);
		add(textField_6);
		textField_6.setColumns(10);
		
		JButton btnNewButton = new JButton("Payment");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSPayment(currentFrame, store, session, currentSale));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(10, 224, 111, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Complete Sale");
		btnNewButton_1.setEnabled(false);
		if(!textField_4.getText().contentEquals("0.00"))
			btnNewButton_1.setEnabled(true);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				session.addSale(currentSale);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSSale(currentFrame, store, session, new Sale()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(131, 224, 127, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Cancel Sale");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSSale(currentFrame, store, session, new Sale()));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setBounds(10, 258, 111, 23);
		add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("End Session");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new SessionSummaryPanel(currentFrame, store, session));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(131, 258, 127, 23);
		if(session.getSales().isEmpty())
			btnNewButton_3.setEnabled(false);
		add(btnNewButton_3);		
	}
	
	public void updateMoneyTextFields() {
		textField_2.setText(currentSale.calcSubTotal().toString());
		textField_3.setText(currentSale.calcTax().toString());
		textField_4.setText(currentSale.calcTotal().toString());
	}
}
