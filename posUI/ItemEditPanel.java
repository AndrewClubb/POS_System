package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Item;
import posPD.UPC;
import posPD.Price;
import posPD.Store;
import posPD.TaxCategory;
import posPD.TaxRate;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.AncestorListener;
import javax.swing.event.AncestorEvent;

public class ItemEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JButton btnNewButton_3;
	private JButton btnNewButton_4;
	private JButton btnNewButton_6;
	private JButton btnNewButton_7;
	private JPanel currentPanel = this;
	private DefaultListModel<UPC> upcList;
	DefaultListModel<Price> priceList;
	private JList<UPC> list;
	JList<Price> list_1;
	private JComboBox<TaxCategory> comboBox;

	/**
	 * Create the panel.
	 */
	public ItemEditPanel(JFrame currentFrame, Store store, Item item, Boolean isAdd) {
		addAncestorListener(new AncestorListener() {
			public void ancestorAdded(AncestorEvent arg0) {
				upcList = new DefaultListModel<UPC>();
				for(UPC upc: item.getUPCs().values())
				{
					upcList.addElement(upc);
				}
				list.setModel(upcList);
				
				priceList = new DefaultListModel<Price>();
				for(Price price: item.getPrices())
				{
					priceList.addElement(price);
				}
				list_1.setModel(priceList);
			}
			public void ancestorMoved(AncestorEvent arg0) {
			}
			public void ancestorRemoved(AncestorEvent arg0) {
			}
		});
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Item");
		lblNewLabel.setBounds(172, 26, 59, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Item Number:");
		lblNewLabel_1.setBounds(27, 65, 77, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Description:");
		lblNewLabel_2.setBounds(27, 90, 77, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Tax Category: ");
		lblNewLabel_3.setBounds(27, 115, 89, 14);
		add(lblNewLabel_3);
		
		textField = new JTextField(item.getNumber());
		textField.setBounds(114, 62, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField(item.getDescription());
		textField_1.setBounds(114, 87, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		
		upcList = new DefaultListModel<UPC>();
		for (UPC upc:item.getUPCs().values())
		{
			upcList.addElement(upc);
		}
		list = new JList<UPC>(upcList);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!list.isSelectionEmpty())
				{
					btnNewButton_3.setEnabled(true);
					if(list.getSelectedValue().isOkayToDelete())
						btnNewButton_4.setEnabled(true);
				}
				else
				{
					btnNewButton_3.setEnabled(false);
					btnNewButton_4.setEnabled(false);
				}
			}
		});
		list.setBounds(293, 40, 102, 67);
		add(list);
		
		JLabel lblNewLabel_4 = new JLabel("UPCs");
		lblNewLabel_4.setBounds(323, 26, 38, 14);
		add(lblNewLabel_4);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.setNumber(textField.getText());
				item.setDescription(textField_1.getText());
				item.setTaxCategory(comboBox.getItemAt(comboBox.getSelectedIndex()));
				if(isAdd)
					store.addItem(item);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewButton.setBounds(27, 237, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemSelectionPanel(currentFrame, store));
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewButton_1.setBounds(126, 237, 89, 23);
		add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Add");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UPCEditPanel(currentFrame, currentPanel, item, new UPC(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_2.setBounds(252, 111, 59, 23);
		add(btnNewButton_2);
		
		btnNewButton_3 = new JButton("Edit");
		btnNewButton_3.setEnabled(false);
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new UPCEditPanel(currentFrame, currentPanel, item, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_3.setBounds(313, 111, 59, 23);
		add(btnNewButton_3);
		
		btnNewButton_4 = new JButton("Delete");
		btnNewButton_4.setEnabled(false);
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				item.removeUPC(list.getSelectedValue());
				upcList.removeElement(list.getSelectedValue());
			}
		});
		btnNewButton_4.setBounds(370, 111, 70, 23);
		add(btnNewButton_4);
		
		priceList = new DefaultListModel<Price>();
		for(Price price:item.getPrices())
		{
			priceList.addElement(price);
		}
		list_1 = new JList<Price>(priceList);
		list_1.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent e) {
				if(!list_1.isSelectionEmpty())
				{
					btnNewButton_6.setEnabled(true);
					if(list_1.getSelectedValue().isOkayToDelete())
						btnNewButton_7.setEnabled(true);
				}
				else
				{
					btnNewButton_6.setEnabled(false);
					btnNewButton_7.setEnabled(false);
				}
			}
		});
		list_1.setBounds(291, 165, 104, 67);
		add(list_1);
		
		JLabel lblNewLabel_5 = new JLabel("Prices");
		lblNewLabel_5.setBounds(323, 152, 46, 14);
		add(lblNewLabel_5);
		
		JButton btnNewButton_5 = new JButton("Add");
		btnNewButton_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, currentPanel, item, new Price(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_5.setBounds(252, 237, 59, 23);
		add(btnNewButton_5);
		
		btnNewButton_6 = new JButton("Edit");
		btnNewButton_6.setEnabled(false);
		btnNewButton_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new PriceEditPanel(currentFrame, currentPanel, item, list_1.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_6.setBounds(313, 237, 59, 23);
		add(btnNewButton_6);
		
		btnNewButton_7 = new JButton("Delete");
		btnNewButton_7.setEnabled(false);
		btnNewButton_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				item.removePrice(list_1.getSelectedValue());
				priceList.removeElement(list_1.getSelectedValue());
			}
		});
		btnNewButton_7.setBounds(370, 237, 70, 23);
		add(btnNewButton_7);
		
		DefaultComboBoxModel<TaxCategory> taxCategoriesList = new DefaultComboBoxModel<TaxCategory>();
		for(TaxCategory taxCategory : store.getTaxCategories().values())
		{
			taxCategoriesList.addElement(taxCategory);
		}
		comboBox = new JComboBox<TaxCategory>(taxCategoriesList);
		comboBox.setSelectedItem(item.getTaxCategory());
		comboBox.setBounds(114, 112, 86, 17);
		add(comboBox);
		
				
	}
}
