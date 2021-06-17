package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import java.awt.Font;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.LayoutStyle.ComponentPlacement;

import dao.InventoryDao;
import model.Inventory;
import util.DBUtil;
import util.StringUtil;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;

public class InventoryAddInterFrame extends JInternalFrame {
	private JTextField inventoryNameTxt;
	private JTextField inventoryTypeTxt;
	private JTextField inventoryAmountTxt;
	private JTextField inventoryUnitTxt;
	private DBUtil dbUtil = new DBUtil();
	private InventoryDao inventoryDao = new InventoryDao();


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					InventoryAddInterFrame frame = new InventoryAddInterFrame();
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
	public InventoryAddInterFrame() {
		getContentPane().setFont(new Font("Lantinghei SC", Font.PLAIN, 13));
		
		JLabel lblNewLabel = new JLabel("Inventory Name:");
		lblNewLabel.setFont(new Font("Lantinghei SC", Font.PLAIN, 13));
		
		JLabel lblNewLabel_1 = new JLabel("Inventory Type:");
		lblNewLabel_1.setFont(new Font("Lantinghei SC", Font.PLAIN, 13));
		
		JLabel lblNewLabel_2 = new JLabel("Amount:");
		lblNewLabel_2.setFont(new Font("Lantinghei SC", Font.PLAIN, 13));
		
		JLabel lblNewLabel_3 = new JLabel("Unit:");
		lblNewLabel_3.setFont(new Font("Lantinghei SC", Font.PLAIN, 13));
		
		inventoryNameTxt = new JTextField();
		inventoryNameTxt.setColumns(10);
		
		inventoryTypeTxt = new JTextField();
		inventoryTypeTxt.setColumns(10);
		
		inventoryAmountTxt = new JTextField();
		inventoryAmountTxt.setColumns(10);
		
		inventoryUnitTxt = new JTextField();
		inventoryUnitTxt.setColumns(10);
		
		JButton btnNewButton = new JButton("Add");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				inventoryAddActionPerformed(e);
			}
		});
		
		JButton btnNewButton_1 = new JButton("Reset");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetValueActionPerformed(e);
			}
		});
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(81)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(lblNewLabel)
						.addComponent(lblNewLabel_2)
						.addComponent(lblNewLabel_3)
						.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
							.addComponent(btnNewButton)
							.addComponent(lblNewLabel_1)))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
							.addComponent(inventoryUnitTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(inventoryAmountTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(inventoryTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
							.addComponent(inventoryNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addComponent(btnNewButton_1))
					.addContainerGap(94, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(52)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel)
						.addComponent(inventoryNameTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_1)
						.addComponent(inventoryTypeTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(12)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_2)
						.addComponent(inventoryAmountTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(lblNewLabel_3)
						.addComponent(inventoryUnitTxt, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
					.addGap(18)
					.addGroup(groupLayout.createParallelGroup(Alignment.BASELINE)
						.addComponent(btnNewButton)
						.addComponent(btnNewButton_1))
					.addContainerGap(15, Short.MAX_VALUE))
		);
		getContentPane().setLayout(groupLayout);
		setClosable(true);
		setIconifiable(true);
		setTitle("Add Inventory");
		setBounds(100, 100, 450, 300);

	}
	
	protected void inventoryAddActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		String inventoryName=this.inventoryNameTxt.getText();
		String inventoryType=this.inventoryTypeTxt.getText();
		String inventoryAmount=this.inventoryAmountTxt.getText();
		String inventoryUnit=this.inventoryUnitTxt.getText();
		if(StringUtil.isEmpty(inventoryName)) {
			JOptionPane.showMessageDialog(null, "Inventory name cannot be null.");
			return;
		}
		if(StringUtil.isEmpty(inventoryType)) {
			JOptionPane.showMessageDialog(null, "Inventory type cannot be null.");
			return;
		}
		if(StringUtil.isEmpty(inventoryAmount)) {
			JOptionPane.showMessageDialog(null, "Inventory amount cannot be null.");
			return;
		}
		Inventory inventory = new Inventory(inventoryName,inventoryType,inventoryAmount,inventoryUnit);
		Connection con = null;
		try {
			con = dbUtil.getConnection();
			int n=inventoryDao.add(con, inventory);
			if(n==1) {
				JOptionPane.showMessageDialog(null, "Successful Add Inventory!");
				resetValue();
			}else {
				JOptionPane.showMessageDialog(null,"Unable to Add Inventory!");
			}
			
		}catch (Exception e){
			e.printStackTrace();
			JOptionPane.showMessageDialog(null,"Unable to Add Inventory!");
		}finally {
			try {
				dbUtil.closeCon(con);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

	protected void resetValueActionPerformed(ActionEvent evt) {
		// TODO Auto-generated method stub
		this.resetValue();
		
	}

	private void resetValue() {
		this.inventoryNameTxt.setText("");
		this.inventoryTypeTxt.setText("");
		this.inventoryAmountTxt.setText("");
		this.inventoryUnitTxt.setText("");
	}
	
}
