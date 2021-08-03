package BLL;

import java.util.ArrayList;
import java.util.List;

import DAL.Bill_DAL;
import DTO.Bill;

public class Bill_BLL {
	private final Bill_DAL bill_DAL;
	public Bill_BLL() {
		bill_DAL = new Bill_DAL();
	}
	public List<Bill> getAll() {
		List<Bill> s = new ArrayList<>();
		for(Bill i : bill_DAL.getAll())
		{
			s.add(i);
		}
		return s;
	}
	public int CheckBillByIDBan(int id_b)
	{
		for(Bill i : bill_DAL.getAll())
		{
			if(i.getId_b() == id_b )
			{
				if(i.getStatus().equals("Chưa thanh toán"))
					return i.getId_hd();
			}
		}
		return 0;
	}
	public void add(int id_nv, int id_b)
	{
		bill_DAL.add(id_nv, id_b);
	}
	public void update(int id, float tt)
	{
		bill_DAL.update(id,tt);
	}
}
