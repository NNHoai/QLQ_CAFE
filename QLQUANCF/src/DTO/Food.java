package DTO;

public class Food {
	private Integer id_ma;
	private Integer id_td;
	private String ten_ma;
	private Float dongia; 
	
	public Food() {
	}
        
    public void copy(Food food){
        this.setId_ma(food.getId_ma());
        this.setId_td(food.getId_td());
        this.setTen_ma(food.getTen_ma());
        this.setDongia(food.getDongia());
    }
	
	public Food(Integer id_ma, Integer id_td, String name, Float dongia) {
		this.id_ma = id_ma;
		this.id_td = id_td;
		this.ten_ma = name;
		this.dongia = dongia;
	}

	public int getId_ma() {
		return id_ma;
	}

	public void setId_ma(Integer id_ma) {
		this.id_ma = id_ma;
	}

	public int getId_td() {
		return id_td;
	}

	public void setId_td(Integer id_td) {
		this.id_td = id_td;
	}

	public String getTen_ma() {
		return ten_ma;
	}

	public void setTen_ma(String ten_ma) {
		this.ten_ma = ten_ma;
	}

	public float getDongia() {
		return dongia;
	}

	public void setDongia(Float dongia) {
		this.dongia = dongia;
	}
	@Override
	public String toString()
	{
		return getTen_ma();
	}
}
