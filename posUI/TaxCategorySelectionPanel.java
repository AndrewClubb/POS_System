package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Cashier;
import posPD.Store;
import posPD.TaxCategory;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaxCategorySelectionPanel extends JPanel {

	JButton btnNewButton_1;
	JButton btnNewButton_2;
	/**
	 * Create the panel.
	 */
	public TaxCategorySelectionPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tax Categories");
		lblNewLabel.setBounds(169, 28, 87, 14);
		add(lblNewLabel);
		
		DefaultListModel<TaxCategory> taxCategoryList = new DefaultListModel<TaxCategory>();
		for(TaxCategory taxCategory : store.getTaxCategories().values())
		{
			taxCategoryList.addElement(taxCategory);
		}
		
		JList<TaxCategory> list = new JList<TaxCategory>(taxCategoryList);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!list.isSelectionEmpty())
				{
					btnNewButton_1.setEnabled(true);
					if(store.isOkayToDeleteTC(list.getSelectedValue()))
						btnNewButton_2.setEnabled(true);
				}
				else
				{
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
				}
			}
		});
		list.setBounds(142, 54, 139, 146);
		add(list);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, new TaxCategory(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(31, 230, 89, 23);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new TaxCategoryEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(167, 230, 89, 23);
		add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeTaxCategory(list.getSelectedValue());
				taxCategoryList.removeElement(list.getSelectedValue());
			}
		});
		btnNewButton_2.setBounds(317, 230, 89, 23);
		add(btnNewButton_2);

	}

}
