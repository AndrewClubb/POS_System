package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Store;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class StoreEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public StoreEditPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Store");
		lblNewLabel.setBounds(195, 25, 58, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Name:");
		lblNewLabel_1.setBounds(32, 83, 46, 14);
		add(lblNewLabel_1);
		
		textField = new JTextField(store.getName());
		textField.setBounds(76, 80, 137, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				store.setName(textField.getText());
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton.setBounds(98, 214, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(263, 214, 89, 23);
		add(btnNewButton_1);

	}
}
