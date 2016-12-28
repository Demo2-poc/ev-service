package ev.dao;

import java.util.Map;

import ev.model.EvModel;

public interface GuestServiceDAO {
	
//	public String getXidBySwid(String swid);

	public void storeEntitlement(EvModel evModel);
	public Map getEvDetails(String xidvalue);
}
