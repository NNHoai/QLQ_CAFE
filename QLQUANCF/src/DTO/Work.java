package DTO;

import java.util.HashSet;

public class Work {
	private Integer id_cv;
	private String ten_cv;
	private Float luongcb; 
	private HashSet<Staff> Staffs;
	public Work() {
	}
        
    public void copy(Work work){
        this.setId_cv(work.getId_cv());
        this.setTen_cv(work.getTen_cv());
        this.setLuong(work.getLuong());
    }
	
	public Work(Integer id, String name, Float luong) {
		this.id_cv = id;
		this.ten_cv = name;
		this.luongcb= luong;
	}

	public int getId_cv() {
		return id_cv;
	}

	public void setId_cv(Integer id_cv) {
		this.id_cv = id_cv;
	}

	public String getTen_cv() {
		return ten_cv;
	}

	public void setTen_cv(String ten_cv) {
		this.ten_cv = ten_cv;
	}

	public float getLuong() {
		return luongcb;
	}

	public void setLuong(Float luong) {
		this.luongcb = luong;
	}

	public HashSet<Staff> getStaffs() {
		if(Staffs == null)
			Staffs = new HashSet<Staff>();
		return Staffs;
	}

//	public void setStaffs(HashSet<Staff> staffs) {
//		Staffs = staffs;
//	}
	@Override
	public String toString() {
		return getTen_cv();
	}
}
