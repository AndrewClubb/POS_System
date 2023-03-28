package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Store;
import posPD.TaxCategory;
import posPD.TaxRate;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class TaxCategoryEditPanel extends JPanel {
	private JTextField textField;
	JButton btnNewButton_3;
	JButton btnNewButton_4;
	JPanel currentPanel = this;
	JList<TaxRate> list;
	DefaultListModel<TaxRate> taxRateList;

	/**
	 * Create the panel.
	 */
	public TaxCategoryEditPanel(JFrame currentFrame, Store store, TaxCategory taxCategory, Boolean isAdd) {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				taxRateList = new DefaultListModel<TaxRate>();
				for(TaxRate taxRate: taxCategory.getTaxRates())
				{
					taxRateList.addElement(taxRate);
				}
				list.setModel(taxRateList);
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tax Category Edit");
		lblNewLabel.setBounds(164, 22, 105, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Category");
		lblNewLabel_1.setBounds(28, 93, 62, 14);
		add(lblNewLabel_1);
		
		textField = new JTextField(taxCategory.getCategory());
		textField.setBounds(87, 90, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Tax Rates");
		lblNewLabel_2.setBounds(302, 47, 62, 14);
		add(lblNewLabel_2);
		
		taxRateList = new DefaultListModel<TaxRate>();
		for(TaxRate taxRate: taxCategory.getTaxRates())
		{
			taxRateList.addElement(taxRate);
		}
		list = new JList<TaxRate>(taxRateList);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!list.isSelectionEmpty())
				{
					btnNewButton_3.setEnabled(true);
					btnNewButton_4.setEnabled(true);
				}
				else
				{
					btnNewButton_3.setEnabled(false);
					btnNewButton_4.setEnabled(false);
				}
			}
		});
		list.setBounds(260, 79, 139, 98);
		add(list);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxCategory.setCategory(textField.getText());
				if(isAdd)
					store.addTaxCategory(taxCategory);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategorySelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(55, 239, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategorySelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(260, 239, 89, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Add");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, currentPanel, taxCategory, new TaxRate(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setBounds(228, 188, 56, 23);
		add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Edit");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxRateEditPanel(currentFrame, currentPanel, taxCategory, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(294, 188, 55, 23);
		add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.setEnabled(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxCategory.removeTaxRate(list.getSelectedValue());
				taxRateList.removeElement(list.getSelectedValue());
			}
		});
		btnNewButton_4.setBounds(359, 188, 71, 23);
		add(btnNewButton_4);
		
	}

}
