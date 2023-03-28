package posUI;

import javax.swing.JPanel;

import posPD.Store;
import javax.swing.JLabel;

public class POSHomePanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public POSHomePanel(Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Welcome to the POS System");
		lblNewLabel.setBounds(130, 51, 165, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel(store.getName());
		lblNewLabel_1.setBounds(130, 105, 192, 14);
		add(lblNewLabel_1);

	}
}
