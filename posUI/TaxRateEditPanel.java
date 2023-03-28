package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.TaxCategory;
import posPD.TaxRate;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TaxRateEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public TaxRateEditPanel(JFrame currentFrame, JPanel currentPanel, TaxCategory taxCategory, TaxRate taxRate, Boolean isAdd) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Tax Rate");
		lblNewLabel.setBounds(183, 29, 74, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Rate:");
		lblNewLabel_1.setBounds(57, 87, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Date:");
		lblNewLabel_2.setBounds(57, 112, 46, 14);
		add(lblNewLabel_2);
		
		String taxRateString = "";
		if(taxRate.getTaxRate()!=null)
			taxRateString = taxRate.getTaxRate().toString();
		textField = new JTextField(taxRateString);
		textField.setBounds(113, 84, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		String effectiveDateString = "";
		if(taxRate.getEffectiveDate() != null)
			effectiveDateString = taxRate.getEffectiveDate().toString();
		textField_1 = new JTextField(effectiveDateString);
		textField_1.setBounds(113, 112, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taxRate.setTaxRate(textField.getText());
				taxRate.setEffectiveDate(textField_1.getText());
				if(isAdd)
					taxCategory.addTaxRate(taxRate);
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewButton.setBounds(81, 223, 89, 23);
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
		btnNewButton_1.setBounds(249, 223, 89, 23);
		add(btnNewButton_1);

	}

}
