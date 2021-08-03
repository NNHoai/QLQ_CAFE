package DTO;

import java.sql.Date;

public class Bill {
	private Integer id_hd;
	private Integer id_nv;
	private Integer id_b;
	private Double tongtien;
	private Date ngay;
	private String status;
	
	public Bill() {
	}
        
    public void copy(Bill bill){
        this.setId_hd(bill.getId_hd());
        this.setId_nv(bill.getId_nv());
        this.setId_b(bill.getId_b());
        this.setTongtien(bill.getTongtien());
        this.setNgay(bill.getNgay());
        this.setStatus(bill.getStatus());
    }
	
	public Bill(Integer id_hd, Integer id_nv, Integer id_b, Double tt, Date ngay, String status) {
		this.id_hd = id_hd;
		this.id_nv = id_nv;
		this.id_b = id_b;
		this.tongtien = tt;
		this.ngay = ngay;
		this.status = status;
	}

	public Integer getId_hd() {
		return id_hd;
	}

	public void setId_hd(Integer id_hd) {
		this.id_hd = id_hd;
	}

	public Integer getId_b() {
		return id_b;
	}

	public void setId_b(Integer id_b) {
		this.id_b = id_b;
	}

	public Integer getId_nv() {
		return id_nv;
	}

	public void setId_nv(Integer id_nv) {
		this.id_nv = id_nv;
	}

	public Double getTongtien() {
		return tongtien;
	}

	public void setTongtien(Double tongtien) {
		this.tongtien = tongtien;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

}
