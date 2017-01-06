package ev.dao;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ev.model.EvModel;
import ev.service.EvServiceImpl;

@Repository
public class GuestServiceDAOImpl implements GuestServiceDAO {

	@Autowired
	@Qualifier(value = "hibernate4AnnotatedSessionFactory")
	private SessionFactory sessionFactory;

	final static Logger logger = Logger.getLogger(GuestServiceDAOImpl.class);

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}

	@Override
	public void storeEntitlement(EvModel evModel) {
		try {
			long startTime = System.currentTimeMillis();
			logger.debug("started storeEntitlement service is invoked  start time in ms =" + startTime);
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(evModel);

			long durationTaken = System.currentTimeMillis() - startTime;
			logger.debug("total time taken to process request ms =" + durationTaken);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}

	@Override
	public Map getEvDetails(String xid) {
		long startTime = System.currentTimeMillis();
		logger.debug("started getEvDetails service is invoked  start time in ms =" + startTime);
		Map<String, String> evlist = new HashMap<String, String>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(EvModel.class);
			cr.add(Restrictions.eq("xid", xid));
			List<EvModel> evtModel = cr.list();
			for (Iterator iterator = evtModel.iterator(); iterator.hasNext();) {
				EvModel ev = (EvModel) iterator.next();
				evlist.put(ev.getEntIdValue(), ev.getEntIdType());
			}

			long durationTaken = System.currentTimeMillis() - startTime;
			logger.debug("total time taken to process request ms =" + durationTaken);
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return evlist;
	}

}
