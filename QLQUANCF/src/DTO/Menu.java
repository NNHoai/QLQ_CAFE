package DTO;

import java.util.HashSet;

public class Menu {
	private Integer id_td;
	private String ten_td;
	private HashSet<Food> Foods;
	public Menu() {
	}
        
    public void copy(Menu menu){
        this.setId_td(menu.getId_td());
        this.setTen_td(menu.getTen_td());
    }
	
	public Menu(Integer id, String name) {
		this.id_td = id;
		this.ten_td = name;
	}
	
	public int getId_td() {
		return id_td;
	}
	public void setId_td(Integer id_td) {
		this.id_td = id_td;
	}
	public String getTen_td() {
		return ten_td;
	}
	public void setTen_td(String ten_td) {
		this.ten_td = ten_td;
	}

	public HashSet<Food> getFoods() {
		if(Foods == null)
			Foods = new HashSet<Food>();
		return Foods;
	}

//	public void setFoods(HashSet<Food> foods) {
//		Foods = foods;
//	}
	@Override
	public String toString() {
		return getTen_td();
	}
}
