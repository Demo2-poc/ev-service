package ev.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ENTITLEMENT_VIEW")
public class EvModel {


	@Column(name="XID")
	private String xid;
	
	@Column(name="ENT_ID_TYPE")
	private String entIdType;
	
	@Id
	@Column(name="ENT_ID_VALUE")
	private String entIdValue;
	
	public String getXid() {
		return xid;
	}
	public void setXid(String xid) {
		this.xid = xid;
	}
	public String getEntIdType() {
		return entIdType;
	}
	public void setEntIdType(String evIdType) {
		this.entIdType = evIdType;
	}
	public String getEntIdValue() {
		return entIdValue;
	}
	public void setEntIdValue(String evIdValue) {
		this.entIdValue = evIdValue;
	}
}
