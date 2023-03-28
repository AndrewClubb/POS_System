package posUI;

import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.TextField;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.Button;
import javax.swing.JScrollPane;
import com.github.lgooddatepicker.components.DatePicker;

import posPD.Cashier;
import posPD.Session;
import posPD.Store;

import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.GregorianCalendar;
import java.awt.event.ActionEvent;
import java.awt.TextArea;
import javax.swing.JTabbedPane;

public class CashierReportPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public CashierReportPanel(JFrame currentFrame, Store store) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Cashier Report");
		lblNewLabel.setBounds(177, 11, 87, 14);
		add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Date: ");
		lblNewLabel_1.setBounds(68, 41, 46, 14);
		add(lblNewLabel_1);
		
		DatePicker datePicker = new DatePicker();
		datePicker.setBounds(140, 36, 170, 25);
		add(datePicker);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 61, 430, 160);
		add(scrollPane);
		
		TextArea textArea = new TextArea();
		scrollPane.setViewportView(textArea);
		
		JButton btnNewButton = new JButton("Generate");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LocalDate date = datePicker.getDate();
				String report = generateCashierReport(store, date);
				textArea.setText(report);
			}
		});
		btnNewButton.setBounds(68, 248, 89, 23);
		add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Cancel");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				currentFrame.getContentPane().removeAll();
				currentFrame.getContentPane().add(new POSHomePanel(store));
				currentFrame.getContentPane().revalidate();
			}
		});
		btnNewButton_1.setBounds(282, 248, 89, 23);
		add(btnNewButton_1);

	}
	
	public String generateCashierReport(Store store, LocalDate date) {
		String tab = "\t";
		String report = "Cashier Report for :" + date.toString() + "\n\n";
		BigDecimal totalCount = new BigDecimal("0");
		BigDecimal totalAmt = new BigDecimal("0");
		BigDecimal totalDiff = new BigDecimal("0");
		
		report += "Number" + tab + tab + "Name" + tab + tab + "Count" + tab + tab + "Amount" + tab + tab + "Diff";
		for(Cashier cashier : store.getCashiers().values())
		{
			report += "\n" + cashier.getNumber();
			report += tab + tab + cashier.getPerson().getName();
			report += tab + tab + cashier.calcSaleCountForDate(date);
			totalCount = totalCount.add(new BigDecimal(cashier.calcSaleCountForDate(date)));
			report += tab + tab + cashier.calcTotalForDate(date).toString();
			totalAmt = totalAmt.add(cashier.calcTotalForDate(date));
			report += tab + tab + cashier.calcDiffForDate(date).toString();
			totalDiff = totalDiff.add(cashier.calcDiffForDate(date));
		}
		report += "\n\nTotal :" + tab + tab + tab + tab + totalCount;
		report += tab + tab + totalAmt.toString();
		report += tab + tab + totalDiff.toString();
		
		
		return report;
	}
}
