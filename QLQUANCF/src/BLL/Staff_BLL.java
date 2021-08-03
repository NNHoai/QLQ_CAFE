package BLL;

import java.util.ArrayList;
import java.util.List;

import DAL.Staff_DAL;
import DTO.Food;
import DTO.Staff;

public class Staff_BLL {
	private final Staff_DAL staff_DAL;
	public Staff_BLL() {
		staff_DAL = new Staff_DAL();
	}
	public Staff checklogin(String username, String password) {
		Staff s = null;
		for(Staff i : staff_DAL.getAll())
		{
			if(i.getUsername().equals(username) && i.getPassword().equals(password))
			{
				s = i;
			}
		}
		return s;
	}
	public List<Staff> getAll() {
		List<Staff> s = new ArrayList<>();
		for(Staff i : staff_DAL.getAll())
		{
			s.add(i);
		}
		return s;
	}
	public boolean CheckID(Integer id)
	{
		for(Staff i : staff_DAL.getAll())
		{
			if(i.getId_nv() == id)
				return true;
		}
		return false;
	}
	public void add(Staff staff) {
		staff_DAL.add(staff);
	}
	public boolean remove(Integer id) {
		return staff_DAL.remove(id);
	}
	public boolean update(Staff staff) {
		return staff_DAL.update(staff);
	}
}
