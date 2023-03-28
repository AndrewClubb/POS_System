package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Cashier;
import posPD.Store;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CashierSelectionPanel extends JPanel {

	JButton btnNewButton_1;
	JButton btnNewButton_2;
	/**
	 * Create the panel.
	 */
	public CashierSelectionPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		DefaultListModel<Cashier> cashierList = new DefaultListModel<Cashier>();
		for(Cashier cashier:store.getCashiers().values())
		{
			cashierList.addElement(cashier);
		}
		
		JList<Cashier> list = new JList<Cashier>(cashierList);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				if(!list.isSelectionEmpty())
				{
					btnNewButton_1.setEnabled(true);
					if(list.getSelectedValue().isOkayToDelete())
						btnNewButton_2.setEnabled(true);
				}
				else
				{
					btnNewButton_1.setEnabled(false);
					btnNewButton_2.setEnabled(false);
				}
			}
		});
		list.setBounds(133, 52, 168, 139);
		add(list);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierEditPanel(currentFrame, store, new Cashier(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(40, 232, 89, 23);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new CashierEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(177, 232, 89, 23);
		add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeCashier(list.getSelectedValue());
				cashierList.removeElement(list.getSelectedValue());
			}
		});
		btnNewButton_2.setBounds(321, 232, 89, 23);
		add(btnNewButton_2);
		
		JLabel lblNewLabel = new JLabel("Cashier Selection");
		lblNewLabel.setBounds(169, 27, 110, 14);
		add(lblNewLabel);

	}
}
