package BLL;

import java.util.ArrayList;
import java.util.List;

import DAL.Table_DAL;
import DTO.Food;
import DTO.Table;

public class Table_BLL {
	private final Table_DAL table_DAL;
	public Table_BLL() {
		table_DAL = new Table_DAL();
	}
	public List<Table> getAll() {
		List<Table> s = new ArrayList<>();
		for(Table i : table_DAL.getAll())
		{
			s.add(i);
		}
		return s;
	}
	public boolean CheckID(Integer id)
	{
		for(Table i : table_DAL.getAll())
		{
			if(i.getId_ban() == id)
				return true;
		}
		return false;
	}
	public void add(Table table) {
		table_DAL.add(table);
	}
	public boolean remove(Integer id) {
		return table_DAL.remove(id);
	}
	public boolean update(Table table) {
		return table_DAL.update(table);
	}
	public boolean updateStatusBusy(int id_b) {
		return table_DAL.updateStatusBusy(id_b);
	}
	public boolean updateStatusEmpty(int id_b) {
		return table_DAL.updateStatusEmpty(id_b);
	}
}
