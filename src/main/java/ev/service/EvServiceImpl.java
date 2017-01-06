package ev.service;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ev.controller.EvServiceController;
import ev.dao.GuestServiceDAO;
import ev.model.EvModel;

@Service
public class EvServiceImpl implements EvService {

	@Autowired
	GuestServiceDAO guestServiceDAO;
	final static Logger logger = Logger.getLogger(EvServiceImpl.class);

	@Override
	@Transactional("transactionManager")
	public String getXidBySwid(String swid) {

		try {
			// return guestServiceDAO.getXidBySwid(swid);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}

	@Override
	@Transactional("transactionManager")
	public void storeEntitlement(String xid, String entIdType, String entIdValue) {
		long startTime = System.currentTimeMillis();
		logger.debug("started storeEntitlement service is invoked start time in ms =" + startTime);
		try {
			EvModel evModel = new EvModel();
			evModel.setXid(xid);
			evModel.setEntIdType(entIdType);
			evModel.setEntIdValue(entIdValue);

			guestServiceDAO.storeEntitlement(evModel);
			long durationTaken = System.currentTimeMillis() - startTime;
			logger.debug("total time taken to process request ms =" + durationTaken);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	@Transactional("transactionManager")
	public Map getEvDetails(String xidvalue) {
		Map evdetailsMap = new HashMap();
		long startTime = System.currentTimeMillis();
		logger.debug("started getEvDetails service is invoked start time in ms =" + startTime);
		try {
			evdetailsMap = guestServiceDAO.getEvDetails(xidvalue);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		long durationTaken = System.currentTimeMillis() - startTime;
		logger.debug("total time taken to process request ms =" + durationTaken);
		return evdetailsMap;

	}

}
