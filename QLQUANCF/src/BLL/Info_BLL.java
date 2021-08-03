package BLL;

import DAL.Info_DAL;
import DTO.Food;
import DTO.info;

public class Info_BLL {
	private final Info_DAL info_DAL;
	public Info_BLL() {
		info_DAL = new Info_DAL();
	}
	public info getInfo() {
		info in = info_DAL.getInfo();
		return in;
	}
	public boolean update(info in)
	{
		return info_DAL.update(in);
	}
}
