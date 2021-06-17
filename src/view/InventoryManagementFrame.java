package view;

import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JInternalFrame;
import javax.swing.JScrollPane;
import java.awt.Color;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import dao.InventoryDao;
import model.Inventory;
import util.DBUtil;
import util.StringUtil;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.EtchedBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InventoryManagementFrame extends JInternalFrame {
	private JTable inventoryTable;
	private DBUtil dbUtil = new DBUtil();
	private InventoryDao inventoryDao = new InventoryDao();
	private JTextField s_inventoryName;
	private JTextField idText;
	private JTextField inventoryNameTxt;
	private JTextField inventoryAmountTxt;
	private JTextField inventoryUnitTxt;
	private JTextField inventoryTypeTxt;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryManagementFrame frame = new InventoryManagementFrame();
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
	public InventoryManagementFrame() {
		setIconifiable(true);
		setClosable(true);
		getContentPane().setForeground(Color.WHITE);
		setForeground(Color.WHITE);
		setTitle("Inventory Management Page");
		setBounds(100, 100, 485, 416);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				inventoryTableMousePressed(e);
			}
		});
		
		JLabel lblNewLabel = new JLabel("Inventory Name:");
		lblNewLabel.setFont(new Font("Lantinghei SC", Font.PLAIN, 13));
		
		s_inventoryName = new JTextField();
		s_inventoryName.setColumns(10);
		
		JButton btnNewButton = new JButton("Search");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventorySearchActionPerformed(e);
			}
		});
		btnNewButton.setIcon(new ImageIcon(InventoryManagementFrame.class.getResource("/images/icons8-manager-30.png")));
		btnNewButton.setFont(new Font("Lantinghei SC", Font.BOLD, 13));
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null), "Inventory Update", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(35)
					.addComponent(lblNewLabel)
					.addGap(18)
					.addComponent(s_inventoryName, GroupLayout.PREFERRED_SIZE, 124, GroupLayout.PREFERRED_SIZE)
					.addGap(51)
					.addComponent(btnNewButton)
					.addGap(34))
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 365, Short.MAX_VALUE)
						.addComponent(scrollPane, Alignment.TRAILING, 0, 0, Short.MAX_VALUE))
					.addGap(17))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap(52, Short.MAX_VALUE)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(s_inventoryName, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(btnNewButton))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 89, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 173, GroupLayout.PREFERRED_SIZE)
					.addGap(19))
		);
		
		JLabel lblNewLabel_1 = new JLabel("Id:");
		
		idText = new JTextField();
		idText.setEditable(false);
		idText.setColumns(10);
		
		inventoryNameTxt = new JTextField();
		inventoryNameTxt.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Inventory Name:");
		
		JLabel lblNewLabel_3 = new JLabel("Amount:");
		
		inventoryAmountTxt = new JTextField();
		inventoryAmountTxt.setColumns(10);
		
		JButton btnNewButton_1 = new JButton("Update");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryUpdateActionEvent(e);
			}
		});
		
		JButton btnNewButton_2 = new JButton("Delete");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryDeleteActionEvent(e);
			}
		});
		
		JLabel lblNewLabel_4 = new JLabel("Unit:");
		
		inventoryUnitTxt = new JTextField();
		inventoryUnitTxt.setColumns(10);
		
		JLabel lblNewLabel_5 = new JLabel("Type:");
		
		inventoryTypeTxt = new JTextField();
		inventoryTypeTxt.setColumns(10);
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING, false)
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_1)
							.addGap(57)
							.addComponent(idText, GroupLayout.PREFERRED_SIZE, 85, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_3)
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(btnNewButton_1)
								.addComponent(inventoryAmountTxt, 0, 0, Short.MAX_VALUE))))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_2)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(inventoryNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(btnNewButton_2)))
						.addGroup(gl_panel.createSequentialGroup()
							.addComponent(lblNewLabel_4)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(inventoryUnitTxt, GroupLayout.PREFERRED_SIZE, 65, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(lblNewLabel_5)
							.addPreferredGap(ComponentPlacement.RELATED)
							.addComponent(inventoryTypeTxt, GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)))
					.addContainerGap())
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(16)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(idText, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(inventoryNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_2))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(inventoryAmountTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_4)
						.addComponent(inventoryUnitTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblNewLabel_5)
						.addComponent(inventoryTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton_1)
						.addComponent(btnNewButton_2))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		
		inventoryTable = new JTable();
		inventoryTable.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"id", "Name", "Type", "Amount", "Unit"
			}
		));
		inventoryTable.setFont(new Font("Segoe UI", Font.PLAIN, 12));
		inventoryTable.setForeground(new Color(0, 0, 0));
		scrollPane.setViewportView(inventoryTable);
		getContentPane().setLayout(groupLayout);
		
		this.fillTable(new Inventory());

	}
	
	private void inventoryDeleteActionEvent(ActionEvent evt) {
		// TODO Auto-generated method stub
		String id = idText.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "Please choose the target to delete.");
			return;
		}
		int n = JOptionPane.showConfirmDialog(null, "Are you sure to delete that?");
		if(n==0) {
			Connection con = null;
			try {
				con = dbUtil.getConnection();
				int deleteNum = inventoryDao.delete(con,id);
				if (deleteNum == 1) {
					JOptionPane.showMessageDialog(null, "Detelet Success.");
					this.resetValue();
					this.fillTable(new Inventory());
				}else{
					JOptionPane.showMessageDialog(null, "Detelet Success.");
				}
				}catch (Exception e) {
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "Detelet Success.");
				}finally {
					try {
						dbUtil.closeCon(con);
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
		}
	}

	private void inventoryUpdateActionEvent(ActionEvent evt) {
		// TODO Auto-generated method stub
		String id = idText.getText();
		String inventoryName = inventoryNameTxt.getText();
		String inventoryType = inventoryTypeTxt.getText();
		String inventoryAmount = inventoryAmountTxt.getText();
		String inventoryUnit = inventoryUnitTxt.getText();
		if(StringUtil.isEmpty(id)) {
			JOptionPane.showMessageDialog(null, "Please do not forget to fill the id");
			return;
		}
		Inventory inventory = new Inventory(inventoryName,inventoryType,inventoryAmount,inventoryUnit);
		Connection con = null;
		try {
			con = dbUtil.getConnection();
			int modifyNum = inventoryDao.update(con,inventory);
			if (modifyNum == 1) {
				JOptionPane.showMessageDialog(null, "Update Successful!");
				this.resetValue();
				this.fillTable(new Inventory());
			}else{
				JOptionPane.showMessageDialog(null, "Update Unsuccessful!");
			}
			}catch(Exception e){
				e.printStackTrace();			
			}finally {
				try {
					dbUtil.closeCon(con);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		
	}

	private void inventoryTableMousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = inventoryTable.getSelectedRow();
		idText.setText((String) inventoryTable.getValueAt(row, 0));
		inventoryNameTxt.setText((String) inventoryTable.getValueAt(row, 1));
		inventoryAmountTxt.setText((String) inventoryTable.getValueAt(row, 3));
	}


	private void inventorySearchActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String s_inventoryName =this.s_inventoryName.getText();
		Inventory inventory = new Inventory();
		inventory.setInventoryName(s_inventoryName);
		this.fillTable(inventory);
	}

	public void fillTable(Inventory inventory) {
		DefaultTableModel dtm = (DefaultTableModel) inventoryTable.getModel();	
		dtm.setRowCount(0);
		Connection con = null;
		try {
			con = dbUtil.getConnection();
			ResultSet rs=inventoryDao.list(con, inventory);
			while(rs.next()) {
				Vector<String> v= new Vector<String>();
				v.add(rs.getString("id"));
				v.add(rs.getString("inventoryName"));
				v.add(rs.getString("inventoryType"));
				v.add(rs.getString("inventoryAmount"));
				v.add(rs.getString("inventoryUnit"));
				dtm.addRow(v);
			}
			
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	private void resetValue() {
		this.idText.setText("");
		this.inventoryNameTxt.setText("");
		this.inventoryTypeTxt.setText("");
		this.inventoryAmountTxt.setText("");
		this.inventoryUnitTxt.setText("");
		
	}
}
