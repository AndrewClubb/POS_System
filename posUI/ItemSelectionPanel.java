package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Store;
import posPD.TaxCategory;
import posPD.UPC;
import posPD.Item;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemSelectionPanel extends JPanel {

	JButton btnNewButton_1;
	JButton btnNewButton_2;
	/**
	 * Create the panel.
	 */
	public ItemSelectionPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Item");
		lblNewLabel.setBounds(186, 26, 80, 14);
		add(lblNewLabel);
		
		DefaultListModel<Item> itemList = new DefaultListModel<Item>();
		for (Item item: store.getItems().values())
		{
			itemList.addElement(item);
		}
		JList<Item> list = new JList<Item>(itemList);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!list.isSelectionEmpty())
				{
					btnNewButton_1.setEnabled(true);
					btnNewButton_2.setEnabled(true);
				}
				else
				{
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
				}
			}
		});
		list.setBounds(106, 53, 235, 161);
		add(list);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, store, new Item(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(38, 241, 89, 23);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new ItemEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(177, 241, 89, 23);
		add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeItem(list.getSelectedValue());
				itemList.removeElement(list.getSelectedValue());
			}
		});
		btnNewButton_2.setBounds(322, 241, 89, 23);
		add(btnNewButton_2);

	}

}
