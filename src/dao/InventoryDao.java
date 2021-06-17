package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import model.Inventory;
import util.StringUtil;

public class InventoryDao {
	
	public int add(Connection con, Inventory inventory)throws Exception{
		String sql="insert into inventory_List values(null,?,?,?,?)";
		PreparedStatement pstmt = con.prepareStatement(sql);
		pstmt.setString(1, inventory.getInventoryName());
		pstmt.setString(2, inventory.getInventoryType());
		pstmt.setString(3, inventory.getInventoryAmount());
		pstmt.setString(4, inventory.getInventoryUnit());
		return pstmt.executeUpdate();
	}
	
	public ResultSet list(Connection con, Inventory inventory)throws Exception{
		StringBuffer sb=new StringBuffer("select * from Inventory_List");
		if(StringUtil.isEmpty(inventory.getInventoryName())) {
			sb.append(" and inventoryName like'%"+inventory.getInventoryName()+"%'");
		}
		PreparedStatement pstmt = con.prepareStatement(sb.toString().replaceFirst("and", "where"));
		return pstmt.executeQuery();
	}
	
	public int delete(Connection con, String id)throws Exception{
		String sql = "detele from Inventory_List where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,id);
		return pstmt.executeUpdate();
	}
	
	public int update(Connection con, Inventory inventory)throws Exception{
		String sql = "update inventory_List set inventoryName=?,inventoryType=?,inventoryAmount=?,inventoryUnit?,inventoryName=? where id=?";
		PreparedStatement pstmt=con.prepareStatement(sql);
		pstmt.setString(1,inventory.getInventoryName());
		pstmt.setString(2,inventory.getInventoryType());
		pstmt.setString(3,inventory.getInventoryAmount());
		pstmt.setString(4,inventory.getInventoryUnit());
		pstmt.setInt(5,inventory.getId());
		return pstmt.executeUpdate();
	}

}
