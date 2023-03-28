package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Cashier;
import posPD.Register;
import posPD.Store;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class RegisterSelectionPanel extends JPanel {

	JButton btnNewButton_1;
	JButton btnNewButton_2;
	/**
	 * Create the panel.
	 */
	public RegisterSelectionPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Select Register");
		lblNewLabel.setBounds(169, 26, 88, 14);
		add(lblNewLabel);
		
		DefaultListModel<Register> registerList = new DefaultListModel<Register>();
		for(Register register:store.getRegisters().values())
		{
			registerList.addElement(register);
		}
		
		JList<Register> list = new JList<Register>(registerList);
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
		list.setBounds(157, 57, 112, 138);
		add(list);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterEditPanel(currentFrame, store, new Register(), true));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(33, 232, 89, 23);
		add(btnNewButton);
		
		btnNewButton_1 = new JButton("Edit");
		btnNewButton_1.setEnabled(false);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new RegisterEditPanel(currentFrame, store, list.getSelectedValue(), false));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(169, 232, 89, 23);
		add(btnNewButton_1);
		
		btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.setEnabled(false);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.removeRegister(list.getSelectedValue());
				registerList.removeElement(list.getSelectedValue());
			}
		});
		btnNewButton_2.setBounds(311, 232, 89, 23);
		add(btnNewButton_2);

	}
}
