package ev.util;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.lang.StringUtils;

import ev.entity.ATSPayloadEvent;

public class XmlParser {
	
	public static Object xmlStringToObject(final String canonicalXml) throws JAXBException {
		final JAXBContext jContext = JAXBContext.newInstance(ATSPayloadEvent.class);
		final Unmarshaller jaxbUnmarshaller = jContext.createUnmarshaller();
		final StringReader reader = new StringReader(canonicalXml);
		return jaxbUnmarshaller.unmarshal(reader);
	}
	
	public static ATSPayloadEvent xmlStringToPojoObject(final String canonicalXml){
		ATSPayloadEvent obj = new ATSPayloadEvent();
		try{
			if (!canonicalXml.equals("")) {
				obj.setTCOD(StringUtils.substringBetween(canonicalXml, "<TCOD>","</TCOD>").trim());
                obj.setAccountId(StringUtils.substringBetween(canonicalXml, "<AccountId>","</AccountId>").trim());
                String aer = StringUtils.substringBetween(canonicalXml, "<AccountExternalReference>","</AccountExternalReference>").trim();
                obj.setAccountExternalReference(aer);
//                System.out.println(StringUtils.substringBetween(canonicalXml, "<ns5: externalReferenceIdsid=\"{","}\"type=\"swid\"/>").trim());
          }
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return obj;
	}

}
