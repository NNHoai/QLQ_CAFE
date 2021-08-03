package DTO;

public class Table {
	private Integer id_ban;
	private String ten_ban;
	private String status;
	public Table() {
	}
        
    public void copy(Table table){
        this.setId_ban(table.getId_ban());
        this.setTen_ban(table.getTen_ban());
        this.setStatus(table.getStatus());
    }
	
	public Table(Integer id, String name, String status) {
		this.id_ban = id;
		this.ten_ban = name;
		this.status = status;
	}
	public int getId_ban() {
		return id_ban;
	}
	public void setId_ban(Integer id_ban) {
		this.id_ban = id_ban;
	}
	public String getTen_ban() {
		return ten_ban;
	}
	public void setTen_ban(String ten_ban) {
		this.ten_ban = ten_ban;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
