package BLL;

import java.util.ArrayList;
import java.util.List;

import DAL.Work_DAL;
import DTO.Table;
import DTO.Work;

public class Work_BLL {
	private final Work_DAL work_DAL;
	public Work_BLL() {
		work_DAL = new Work_DAL();
	}
	public List<Work> getAll() {
		List<Work> s = new ArrayList<>();
		for(Work i : work_DAL.getAll())
		{
			s.add(i);
		}
		return s;
	}
	public String getNameWork(int id) {
		Work s = new Work();
		for(Work i : work_DAL.getAll())
		{
			if(i.getId_cv() == id)
				s = i;
		}
		return s.getTen_cv().toString();
	}
	public boolean CheckID(Integer id)
	{
		for(Work i : work_DAL.getAll())
		{
			if(i.getId_cv() == id)
				return true;
		}
		return false;
	}
	public void add(Work work) {
		work_DAL.add(work);
	}
	public boolean remove(Integer id) {
		return work_DAL.remove(id);
	}
	public boolean update(Work work) {
		return work_DAL.update(work);
	}
}
