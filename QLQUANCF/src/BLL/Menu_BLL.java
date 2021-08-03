package BLL;

import java.util.ArrayList;
import java.util.List;

import DAL.Menu_DAL;
import DTO.Menu;

public class Menu_BLL {
	private final Menu_DAL menu_DAL;
	public Menu_BLL() {
		menu_DAL = new Menu_DAL();
	}
	public List<Menu> getAll() {
		List<Menu> s = new ArrayList<>();
		for(Menu i : menu_DAL.getAll())
		{
			s.add(i);
		}
		return s;
	}
	public Integer getIDMenu(String name) {
		Menu s = new Menu();
		for(Menu i : menu_DAL.getAll())
		{
			if(i.getTen_td().equals(name))
				s = i;
		}
		return s.getId_td();
	}
	public String getNameMenu(int id) {
		Menu s = new Menu();
		for(Menu i : menu_DAL.getAll())
		{
			if(i.getId_td() == id)
				s = i;
		}
		return s.getTen_td().toString();
	}
	public boolean CheckID(Integer id)
	{
		for(Menu i : menu_DAL.getAll())
		{
			if(i.getId_td() == id)
				return true;
		}
		return false;
	}
	public void add(Menu menu) {
		menu_DAL.add(menu);
	}
	public boolean remove(Integer id) {
		return menu_DAL.remove(id);
	}
	public boolean update(Menu menu) {
		return menu_DAL.update(menu);
	}
}
