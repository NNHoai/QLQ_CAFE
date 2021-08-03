package DTO;

public class BillOrder {
	private String ten_ma;
	private Integer soluong;
	private Float dongia;
	private Float thanhtien;
	
	public BillOrder() {
	}
	
	public BillOrder(String ten_ma, Integer soluong, Float dongia, Float thanhtien) {
		this.ten_ma = ten_ma;
		this.soluong = soluong;
		this.dongia = dongia;
		this.thanhtien = thanhtien;
	}

	public String getTen_ma() {
		return ten_ma;
	}

	public void setTen_ma(String ten_ma) {
		this.ten_ma = ten_ma;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}

	public Float getDongia() {
		return dongia;
	}

	public void setDongia(Float dongia) {
		this.dongia = dongia;
	}

	public Float getThanhtien() {
		return thanhtien;
	}

	public void setThanhtien(Float thanhtien) {
		this.thanhtien = thanhtien;
	}
}
