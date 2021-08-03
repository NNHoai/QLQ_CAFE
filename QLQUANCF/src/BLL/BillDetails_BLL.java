package BLL;

import java.util.ArrayList;
import java.util.List;

import DAL.BillDetails_DAL;
import DTO.BillOrder;
import DTO.Bill_Details;

public class BillDetails_BLL {
		private final BillDetails_DAL bd_DAL;
		public BillDetails_BLL() {
			bd_DAL = new BillDetails_DAL();
		}
		public List<Bill_Details> getAll() {
			List<Bill_Details> s = new ArrayList<>();
			for(Bill_Details i : bd_DAL.getAll())
			{
				s.add(i);
			}
			return s;
		}	
		public List<BillOrder> getlsBillDbyIDBill(int idB)
		{
			List<BillOrder> ls = new ArrayList<>();
			for(BillOrder i: bd_DAL.getlsBillDbyIDBill(idB))
			{
					ls.add(i);
			}
			return ls;
		}
		public int checkID_BD(int id_hd, int id_ma)
		{
			int id_cthd = 0;
			for(Bill_Details i: bd_DAL.getAll())
			{
				if(i.getId_hd().equals(id_hd) && i.getId_ma().equals(id_ma))
				id_cthd = i.getId_cthd();
			}
			return id_cthd;
		}
		public int getSoluong(int id_cthd)
		{
			int sl = 0;
			for(Bill_Details i: bd_DAL.getAll())
			{
				if(i.getId_cthd().equals(id_cthd))
				sl = i.getSoluong();
			}
			return sl;
		}
		public void add(int id_hd, int id_ma, int sl)
		{
			bd_DAL.add(id_hd, id_ma, sl);
		}
		public void del(int id_hd, int id_ma, int sl)
		{
			bd_DAL.del(id_hd, id_ma, sl);
		}
		public void update(int sl, int id_cthd)
		{
			bd_DAL.update(sl, id_cthd);
		}
}
