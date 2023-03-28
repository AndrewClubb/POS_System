package posUI;

import java.awt.TextArea;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.github.lgooddatepicker.components.DatePicker;

import posPD.Cashier;
import posPD.Payment;
import posPD.Sale;
import posPD.Session;
import posPD.Store;
import posPD.Cash;
import posPD.Check;
import posPD.Credit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DailyReportPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public DailyReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Sales Report");
		lblNewLabel.setBounds(186, 29, 73, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date");
		lblNewLabel_1.setBounds(80, 60, 46, 14);
		add(lblNewLabel_1);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(136, 60, 160, 25);
		add(datePicker);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(36, 89, 380, 166);
		add(scrollPane);
		
		TextArea textArea = new TextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LocalDate date = datePicker.getDate();
				String report = generateDailyReport(store, date);
				textArea.setText(report);
			}
		});
		btnNewButton.setBounds(87, 266, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Close");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(254, 266, 89, 23);
		add(btnNewButton_1);
	}
	
	public String generateDailyReport(Store store, LocalDate date) {
		BigDecimal totalCash = new BigDecimal("0");
		BigDecimal totalCheck = new BigDecimal("0");
		BigDecimal totalCredit = new BigDecimal("0");
		BigDecimal totalAmount = new BigDecimal("0");
		String tab = "\t";
		String report = "Sales Report for :" + date.toString() + "\n\n";
		report += "Time" + tab + tab + "Cash" + tab + "Check" + tab + "Credit" + tab + "Total\n";
		
		for (Session session : store.getSessions())
		{
			if(session.getStartDateTime().toLocalDate().equals(date))
			{
				for(Sale sale : session.getSales())
				{
					for(Payment payment : sale.getPayments())
					{
						report += session.getStartDateTime().toLocalTime().toString() + tab;
						if(payment instanceof Cash)
						{
							report += payment.getAmount().toString() + tab + "0.00" + tab + "0.00";
							totalCash = totalCash.add(payment.getAmount());
						}
						else if(payment instanceof Check)
						{
							report += "0.00" + tab + payment.getAmount().toString() + tab + "0.00";
							totalCheck = totalCheck.add(payment.getAmount());
						}
						else
						{
							report += "0.00" + tab + "0.00" + tab + payment.getAmount().toString();
							totalCredit = totalCredit.add(payment.getAmount());
						}
						report += tab + payment.getAmount().toString() + "\n";
						totalAmount = totalAmount.add(payment.getAmount());
					}
				}
			}
		}
		
		report += "\nTotal:" + tab + tab + totalCash.toString() + tab + totalCheck.toString();
		report += tab + totalCredit.toString() + tab + totalAmount.toString();
		
		return report;
	}

}
