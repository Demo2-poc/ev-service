package ev.util;

import java.util.UUID;

public class GSUtil {
	
	public static String generateXID(){
		String uid = "";
		
		try{
			UUID idOne = UUID.randomUUID();
			uid = "xid-"+ idOne;
		}catch (Exception e) {
			e.printStackTrace();
		}
		return uid;
	}

}
