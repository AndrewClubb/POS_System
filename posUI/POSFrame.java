package posUI;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import posPD.Store;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class POSFrame extends JFrame {
	JFrame currentFrame;

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void open(Store store) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					POSFrame frame = new POSFrame(store);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public POSFrame(Store store) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		
		currentFrame = this;
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("Maintenance");
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("Store");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new StoreEditPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Cashier");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new CashierSelectionPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Register");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new RegisterSelectionPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Tax Category");
		mntmNewMenuItem_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new TaxCategorySelectionPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Item");
		mntmNewMenuItem_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ItemSelectionPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnNewMenu.add(mntmNewMenuItem_4);
		
		JMenu mnNewMenu_1 = new JMenu("POS");
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("Login");
		mntmNewMenuItem_6.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new POSLogin(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_2 = new JMenu("Reports");
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Item Sales Report");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new ItemReportPanel(currentFrame, store));
				getContentPane().revalidate();
				
			}
		});
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("Daily Sales Report");
		mntmNewMenuItem_8.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getContentPane().removeAll();
				getContentPane().add(new DailyReportPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_8);
		mnNewMenu_2.add(mntmNewMenuItem_5);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("Cashier Sales Report");
		mntmNewMenuItem_7.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				getContentPane().removeAll();
				getContentPane().add(new CashierReportPanel(currentFrame, store));
				getContentPane().revalidate();
			}
		});
		mnNewMenu_2.add(mntmNewMenuItem_7);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		getContentPane().removeAll();
		POSHomePanel homePanel = new POSHomePanel(store);
		getContentPane().add(homePanel);
		homePanel.setLayout(null);
		getContentPane().revalidate();
	}
}
