package BLL;

import java.util.ArrayList;
import java.util.List;

import DAL.Food_DAL;
import DTO.Food;

public class Food_BLL {
	private final Food_DAL food_DAL;
	public Food_BLL() {
		food_DAL = new Food_DAL();
	}
	public List<Food> getAll() {
		List<Food> s = new ArrayList<>();
		for(Food i : food_DAL.getAll())
		{
			s.add(i);
		}
		return s;
	}
	public List<Food> getFoodbyIDMenu(int id) {
		List<Food> s = new ArrayList<>();
		for(Food i : food_DAL.getAll())
		{
			if(i.getId_td() == id)
			{
				s.add(i);
			}
		}
		return s;
	}
	
	public int getIDFoodByName(String name) {
		int id = 0;
		for(Food i : food_DAL.getAll())
		{
			if(i.getTen_ma().equals(name))
			{
				id = i.getId_ma();
			}
		}
		return id;
	}
	public int getIDMenuByName(String name) {
		int id = 0;
		for(Food i : food_DAL.getAll())
		{
			if(i.getTen_ma().equals(name))
			{
				id = i.getId_td();
			}
		}
		return id;
	}
	public boolean CheckID(Integer id)
	{
		for(Food i : food_DAL.getAll())
		{
			if(i.getId_ma() == id)
				return true;
		}
		return false;
	}
	public void add(Food food) {
		food_DAL.add(food);
	}
	public boolean remove(Integer id) {
		return food_DAL.remove(id);
	}
	public boolean update(Food food) {
		return food_DAL.update(food);
	}
}
