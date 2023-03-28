package posUI;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import posPD.Cashier;
import posPD.Item;
import posPD.Sale;
import posPD.SaleLineItem;
import posPD.Session;
import posPD.Store;
import javax.swing.JLabel;
import com.github.lgooddatepicker.components.DatePicker;

import java.awt.TextArea;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ItemReportPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public ItemReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Item Report");
		lblNewLabel.setBounds(186, 29, 73, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setBounds(80, 60, 46, 14);
		add(lblNewLabel_1);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(136, 60, 160, 25);
		add(datePicker);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 89, 380, 151);
		add(scrollPane);
		
		TextArea textArea = new TextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDate date = datePicker.getDate();
				String report = generateItemReport(store, date);
				textArea.setText(report);
			}
		});
		btnNewButton.setBounds(87, 250, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(256, 251, 89, 23);
		add(btnNewButton_1);

	}
	
	public String generateItemReport(Store store, LocalDate date) {
		String tab = "\t";
		String report = "Item Report for :" + date.toString() + "\n\n";
		Integer count = 0;
		
		for(Item item : store.getItems().values())
		{
			for (Session session : store.getSessions())
			{
				for(Sale sale : session.getSales())
				{
					for (SaleLineItem sli : sale.getSaleLineItems())
					{
						if(sli.getItem().equals(item))
							count += sli.getQuantity();
					}
				}
			}
			if(count > 0)
			{
				report += item.getNumber() + " " + item.getDescription();
				if(item.getDescription().length() < 11)
					report += tab;
				report += tab + tab + tab + count.toString() + "\n";
			}
		}
		
		return report;
	}
}
