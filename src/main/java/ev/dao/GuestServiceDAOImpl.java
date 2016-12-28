package ev.dao;


import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import ev.model.EvModel;


@Repository
public class GuestServiceDAOImpl implements GuestServiceDAO{
	
	@Autowired
	@Qualifier(value="hibernate4AnnotatedSessionFactory")
	private SessionFactory sessionFactory;
	
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sf){
		this.sessionFactory = sf;
	}
	
	/*@Override
	public String getXidBySwid(String swid) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(GuestModel.class);
			cr.add(Restrictions.eq("guestIdValue", swid));
			return ((GuestModel)cr.list().get(0)).getXid();
			}catch (Exception e) {
				e.printStackTrace();
			}
		return "";
	}*/

	@Override
	public void storeEntitlement(EvModel evModel) {
		try{
			Session session = this.sessionFactory.getCurrentSession();
			session.persist(evModel);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public Map getEvDetails(String xid) {

		Map<String, String> evlist = new HashMap<String, String>();
		try {
			Session session = this.sessionFactory.getCurrentSession();
			Criteria cr = session.createCriteria(EvModel.class);
			cr.add(Restrictions.eq("xid", xid));
			List<EvModel> evtModel = cr.list();
			for (Iterator iterator = evtModel.iterator(); iterator.hasNext();) {
				EvModel ev = (EvModel) iterator.next();
				evlist.put(ev.getEntIdValue(),ev.getEntIdType());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return evlist;
	}

}
