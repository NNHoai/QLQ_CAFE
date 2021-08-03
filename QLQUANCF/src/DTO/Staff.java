package DTO;

public class Staff {
	private Integer id_nv;
	private Integer id_cv;
	private String ten_nv;
	private String lienhe;
	private String diachi;
	private String username;
	private String password;
	private Float hesol;
	private Integer casang;
	private Integer cachieu;
	private Integer catoi;

	public Staff() {
	}
        
    public void copy(Staff staff){
        this.setId_nv(staff.getId_nv());
        this.setId_cv(staff.getId_cv());
        this.setTen_nv(staff.getTen_nv());
        this.setLienhe(staff.getLienhe());
        this.setDiachi(staff.getDiachi());
        this.setUsername(staff.getUsername());
        this.setPassword(staff.getPassword());
        this.setHesol(staff.getHesol());
        this.setCasang(staff.getCasang());
        this.setCachieu(staff.getCachieu());
        this.setCatoi(staff.getCatoi());
    }
	
	public Staff(Integer id_nv, Integer id_cv, String name, String lh, String dc, String tk, String mk, Float hsl, Integer cs, Integer cc, Integer ct) {
		this.id_nv = id_nv;
		this.id_cv = id_cv;
		this.ten_nv = name;
		this.lienhe = lh;
		this.diachi = dc;
		this.username = tk;
		this.password = mk;
		this.hesol = hsl;
		this.casang = cs;
		this.cachieu = cc;
		this.catoi = ct;
	}

	public Integer getId_cv() {
		return id_cv;
	}

	public void setId_cv(Integer id_cv) {
		this.id_cv = id_cv;
	}

	public int getId_nv() {
		return id_nv;
	}

	public void setId_nv(Integer id_nv) {
		this.id_nv = id_nv;
	}

	public String getTen_nv() {
		return ten_nv;
	}

	public void setTen_nv(String ten_nv) {
		this.ten_nv = ten_nv;
	}

	public String getLienhe() {
		return lienhe;
	}

	public void setLienhe(String lienhe) {
		this.lienhe = lienhe;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getDiachi() {
		return diachi;
	}

	public void setDiachi(String diachi) {
		this.diachi = diachi;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Float getHesol() {
		return hesol;
	}

	public void setHesol(Float hesol) {
		this.hesol = hesol;
	}

	public Integer getCasang() {
		return casang;
	}

	public void setCasang(Integer casang) {
		this.casang = casang;
	}

	public Integer getCachieu() {
		return cachieu;
	}

	public void setCachieu(Integer cachieu) {
		this.cachieu = cachieu;
	}

	public Integer getCatoi() {
		return catoi;
	}

	public void setCatoi(Integer catoi) {
		this.catoi = catoi;
	}
}
