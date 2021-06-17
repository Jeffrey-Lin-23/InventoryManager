package model;

public class Inventory {
	private int id;
	private String inventoryName;
	private String inventoryType;
	private String inventoryAmount;
	private String inventoryUnit;
	
	
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	


	public Inventory(String inventoryName, String inventoryType, String inventoryAmount, String inventoryUnit) {
		super();
		this.inventoryName = inventoryName;
		this.inventoryType = inventoryType;
		this.inventoryAmount = inventoryAmount;
		this.inventoryUnit = inventoryUnit;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getInventoryName() {
		return inventoryName;
	}
	public void setInventoryName(String inventoryName) {
		this.inventoryName = inventoryName;
	}
	public String getInventoryType() {
		return inventoryType;
	}
	public void setInventoryType(String inventoryType) {
		this.inventoryType = inventoryType;
	}
	public String getInventoryAmount() {
		return inventoryAmount;
	}
	public void setInventoryAmount(String inventoryAmount) {
		this.inventoryAmount = inventoryAmount;
	}
	public String getInventoryUnit() {
		return inventoryUnit;
	}
	public void setInventoryUnit(String inventoryUnit) {
		this.inventoryUnit = inventoryUnit;
	}


	
	
	
}
