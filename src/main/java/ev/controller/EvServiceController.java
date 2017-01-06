package ev.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ev.service.EvService;
import ev.util.GSUtil;

@RestController
public class EvServiceController {

	@Autowired
	EvService evService;

	public static final String OBJECT_KEY = "RESERVATION.PAYLOAD";
	final static Logger logger = Logger.getLogger(EvServiceController.class);
	@Autowired
	@Qualifier("redisTemplate")
	private RedisTemplate<String, String> redisTemplate;

	@RequestMapping(value = "/providexid", method = RequestMethod.POST)
	public ResponseEntity<String> provideXid(@RequestBody String swid) {
		long startTime = System.currentTimeMillis();
		logger.debug("started provideXid service is invoked start time in ms =" + startTime);
		String xid = "";
		ResponseEntity<String> resp = null;
		try {
			xid = evService.getXidBySwid(swid);
			if (!xid.equals("")) {
				xid = GSUtil.generateXID();
			}
			resp = new ResponseEntity<String>(xid, HttpStatus.OK);
		} catch (Exception e) {
			logger.error(e.getMessage());		
			resp = new ResponseEntity<String>("", HttpStatus.INTERNAL_SERVER_ERROR);
			e.printStackTrace();
		}
		long durationTaken = System.currentTimeMillis() - startTime;
		logger.debug("total time taken to process request ms =" + durationTaken);
		return resp;
	}

	@RequestMapping(value = "/storeentitlement", method = RequestMethod.POST)
	public void storeEntitlement(@RequestParam("xid") String xid, @RequestParam("entIdType") String entIdType,
			@RequestParam("entIdValue") String entIdValue) {
		long startTime = System.currentTimeMillis();
		logger.debug("started storeEntitlement service is invoked start time in ms =" + startTime);
		try {
			evService.storeEntitlement(xid, entIdType, entIdValue);
			this.redisTemplate.opsForHash().delete(OBJECT_KEY, entIdValue);
			long durationTaken = System.currentTimeMillis() - startTime;
			logger.debug("total time taken to process request ms =" + durationTaken);
		} catch (Exception e) {
			logger.error(e.getMessage());

			e.printStackTrace();
		}
	}

	@RequestMapping(value = "/{xidvalue}", method = RequestMethod.GET)
	public Map getENTypeValue(@PathVariable String xidvalue) {
		long startTime = System.currentTimeMillis();
		logger.debug("started getENTypeValue service is invoked start time in ms =" + startTime);
		Map<String, String> map = null;
		try {
			map = evService.getEvDetails(xidvalue);
			long durationTaken = System.currentTimeMillis() - startTime;
			logger.debug("total time taken to process request ms =" + durationTaken);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return map;
	}

}
