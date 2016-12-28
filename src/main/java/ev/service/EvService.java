package ev.service;

import java.util.Map;

public interface EvService {
	
	public String getXidBySwid(String replaceAll);

	public void storeEntitlement(String xid, String entIdType, String entIdValue);
	public Map getEvDetails(String xidvalue);
}
