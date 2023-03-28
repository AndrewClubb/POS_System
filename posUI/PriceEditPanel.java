package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;

import posPD.Item;
import posPD.Price;
import posPD.PromoPrice;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PriceEditPanel extends JPanel {
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JCheckBox chckbxNewCheckBox;
	private Price price;
	//private PromoPrice promoPrice = new PromoPrice();
	boolean isPromoPrice = false;

	/**
	 * Create the panel.
	 */
	public PriceEditPanel(JFrame currentFrame, JPanel currentPanel, Item item, Price inPrice, Boolean isAdd) {
		setLayout(null);
		
		price = inPrice;
		/*boolean isPrice = inPrice.getClass().equals(Price.class);
		if(!isPrice)
			promoPrice = (PromoPrice)inPrice;
		*/
		if(inPrice instanceof PromoPrice)
			isPromoPrice = true; 
		JLabel lblNewLabel = new JLabel("Edit Price");
		lblNewLabel.setBounds(185, 26, 56, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Price :");
		lblNewLabel_1.setBounds(51, 85, 46, 14);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Effective Date: ");
		lblNewLabel_2.setBounds(51, 116, 85, 14);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("End Date:");
		lblNewLabel_3.setBounds(0, 0, 0, 0);
		add(lblNewLabel_3);
		
		String priceString = "";
		if(price.getPrice() != null)
			priceString = price.getPrice().toString();
		textField = new JTextField(priceString);
		textField.setBounds(146, 82, 86, 20);
		add(textField);
		textField.setColumns(10);
		
		String effectiveDateString = "";
		if(price.getEffectiveDate() != null)
			effectiveDateString = price.getEffectiveDate().toString();
		textField_1 = new JTextField(effectiveDateString);
		textField_1.setBounds(146, 113, 86, 20);
		add(textField_1);
		textField_1.setColumns(10);
		
		String endDateString = "";
		if(isPromoPrice) {
			endDateString = ((PromoPrice) price).getEndDate().toString();
			lblNewLabel_3.setBounds(51, 147, 56, 14);
			textField_2.setBounds(146, 144, 86, 20);
			chckbxNewCheckBox.setSelected(true);
			textField_2 = new JTextField(endDateString);
		}
		else {
			textField_2 = new JTextField(endDateString);
			textField_2.setBounds(0, 0, 0, 0);
		}
		add(textField_2);
		textField_2.setColumns(10);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				price.setPrice(textField.getText());
				price.setEffectiveDate(textField_1.getText());
				if(isPromoPrice)
					((PromoPrice) price).setEndDate(textField_2.getText());
				
				if(isAdd)
					item.addPrice(price);
				
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(currentPanel);
				currentFrame.getContentPane().revalidate();
				currentFrame.getContentPane().repaint();
			}
		});
		btnNewButton.setBounds(91, 220, 89, 23);
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
		btnNewButton_1.setBounds(259, 220, 89, 23);
		add(btnNewButton_1);
		
		chckbxNewCheckBox = new JCheckBox("Promo Price");
		chckbxNewCheckBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxNewCheckBox.isSelected())
				{
					lblNewLabel_3.setBounds(51, 147, 56, 14);
					textField_2.setBounds(146, 144, 86, 20);
					price = new PromoPrice();
					isPromoPrice = true;
				}
				else
				{
					lblNewLabel_3.setBounds(0, 0, 0, 0);
					textField_2.setBounds(0, 0, 0, 0);
					price = new Price();
					isPromoPrice = false;
				}
			}
		});
		chckbxNewCheckBox.setBounds(144, 47, 97, 23);
		add(chckbxNewCheckBox);
		
		if(isPromoPrice)
		{
			lblNewLabel_3.setBounds(51, 147, 56, 14);
			textField_2.setBounds(146, 144, 86, 20);
			chckbxNewCheckBox.setSelected(true);
		}
		
		if(!isAdd)
			chckbxNewCheckBox.setEnabled(false);
	}
}
