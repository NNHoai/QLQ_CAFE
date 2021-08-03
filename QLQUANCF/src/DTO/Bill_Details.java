package DTO;

public class Bill_Details {
	private Integer id_cthd;
	private Integer id_hd;
	private Integer id_ma;
	private Integer soluong;
	
	public Bill_Details() {
	}
        
    public void copy(Bill_Details bill_details){
    	this.setId_cthd(bill_details.getId_cthd());
        this.setId_hd(bill_details.getId_hd());
        this.setId_ma(bill_details.getId_ma());
        this.setSoluong(bill_details.getSoluong());
    }
	
	public Bill_Details(Integer id_cthd, Integer id_hd, Integer id_ma, Integer soluong) {
		this.id_cthd = id_cthd;
		this.id_hd = id_hd;
		this.id_ma = id_ma;
		this.soluong = soluong;
	}

	public Integer getId_hd() {
		return id_hd;
	}

	public void setId_hd(Integer id_hd) {
		this.id_hd = id_hd;
	}

	public Integer getId_cthd() {
		return id_cthd;
	}

	public void setId_cthd(Integer id_cthd) {
		this.id_cthd = id_cthd;
	}

	public Integer getId_ma() {
		return id_ma;
	}

	public void setId_ma(Integer id_ma) {
		this.id_ma = id_ma;
	}

	public Integer getSoluong() {
		return soluong;
	}

	public void setSoluong(Integer soluong) {
		this.soluong = soluong;
	}
}
