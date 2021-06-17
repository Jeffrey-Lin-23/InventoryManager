package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.Window.Type;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JDesktopPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private JDesktopPane table = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
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
	public MainFrame() {
		setType(Type.UTILITY);
		setTitle("I.M.S Homepage");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 563, 406);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setFont(new Font("Lantinghei SC", Font.PLAIN, 14));
		setJMenuBar(menuBar);
		
		JMenu mnNewMenu = new JMenu("General");
		mnNewMenu.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icons8-home-page-30.png")));
		menuBar.add(mnNewMenu);
		
		JMenu mnNewMenu_2 = new JMenu("Inventory Management");
		mnNewMenu_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icons8-database-30.png")));
		mnNewMenu.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("Inventory Add");
		mntmNewMenuItem_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryAddInterFrame inventoryAddinterFrame = new InventoryAddInterFrame();
				inventoryAddinterFrame.setVisible(true);
				table.add(inventoryAddinterFrame);
			}
		});
		mntmNewMenuItem_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icons8-add-database-30.png")));
		mnNewMenu_2.add(mntmNewMenuItem_1);
		
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("Inventory Maintenance ");
		mntmNewMenuItem_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InventoryManagementFrame inventoryManagementFrame = new InventoryManagementFrame();
				inventoryManagementFrame.setVisible(true);
				table.add(inventoryManagementFrame);
			}
		});
		mntmNewMenuItem_2.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icons8-database-administrator-30.png")));
		mnNewMenu_2.add(mntmNewMenuItem_2);
		
		JMenu mnNewMenu_3 = new JMenu("Customer List");
		mnNewMenu_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icons8-form-30.png")));
		mnNewMenu.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("Add new Customer");
		mntmNewMenuItem_3.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icons8-contact-us-30.png")));
		mnNewMenu_3.add(mntmNewMenuItem_3);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("Maintenance exisiting Customer ");
		mntmNewMenuItem_4.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icons8-request-service-30.png")));
		mnNewMenu_3.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("Logout");
		mntmNewMenuItem_5.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int result = JOptionPane.showConfirmDialog(null, "Do you really want to logout?");
				if (result==0) {
					dispose();
				}
			}
		});
		mntmNewMenuItem_5.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icons8-logout-rounded-up-30.png")));
		mntmNewMenuItem_5.setSelectedIcon(new ImageIcon(MainFrame.class.getResource("/images/icons8-logout-rounded-up-30.png")));
		mnNewMenu.add(mntmNewMenuItem_5);
		
		JMenu mnNewMenu_1 = new JMenu("About Us");
		mnNewMenu_1.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icons8-face-id-30.png")));
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("FSD 32555 Goup 7");
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterFrame interFrame = new InterFrame();
				interFrame.setVisible(true);
				table.add(interFrame);
			}
		});
		mntmNewMenuItem.setIcon(new ImageIcon(MainFrame.class.getResource("/images/icons8-facebook-like-30.png")));
		mnNewMenu_1.add(mntmNewMenuItem);
		contentPane = new JPanel();
		contentPane.setForeground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		table = new JDesktopPane();
		table.setBackground(Color.WHITE);
		contentPane.add(table, BorderLayout.CENTER);
		
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		
	}
}
