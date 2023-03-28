package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Item;
import posPD.UPC;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UPCEditPanel extends JPanel {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public UPCEditPanel(JFrame currentFrame, JPanel currentPanel, Item item, UPC upc, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit UPC");
		lblNewLabel.setBounds(198, 27, 57, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("UPC Code:");
		lblNewLabel_1.setBounds(64, 78, 66, 14);
		add(lblNewLabel_1);
		
		textField = new JTextField(upc.getUPC());
		textField.setBounds(140, 75, 115, 20);
		add(textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upc.setUPC(textField.getText());
				if(isAdd)
				{
					item.addUPC(upc);
					upc.setItem(item);
				}
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewButton.setBounds(89, 208, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewButton_1.setBounds(245, 208, 89, 23);
		add(btnNewButton_1);

	}

}
