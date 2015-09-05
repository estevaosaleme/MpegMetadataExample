package br.inf.ufes.lprm.example;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.iso.mpeg.mpegv._2010.ct.Float3DVectorType;
import org.iso.mpeg.mpegv._2010.iidl.InteractionInfoType;
import org.iso.mpeg.mpegv._2010.iidl.SensedInfoListType;
import org.iso.mpeg.mpegv._2010.siv.AccelerationSensorType;

public class Marshal {
	public static void main(String[] args) {
		AccelerationSensorType acel = new AccelerationSensorType();
        acel.setSensorIdRef("DEV_ID_001");
        acel.setActivate(true);
        Float3DVectorType data = new Float3DVectorType();
        data.setX(0.09f);
        data.setY(-0.45f);
        data.setZ(9.85f);
        acel.setAcceleration(data);
        
        SensedInfoListType sil = new SensedInfoListType();
        sil.getSensedInfo().add(acel);
        
        InteractionInfoType ii = new InteractionInfoType();
        ii.setSensedInfoList(sil);

  	  	try {
	  		JAXBContext jaxbContext = JAXBContext.newInstance(InteractionInfoType.class);
	  		Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
	
	  		jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
	  		
	  		JAXBElement<InteractionInfoType> rootElement = new JAXBElement<InteractionInfoType>(
	               new QName("InteractionInfo"), InteractionInfoType.class, ii);
	  		jaxbMarshaller.marshal(rootElement, System.out);

  	    } catch (JAXBException e) {
  	    	e.printStackTrace();
  	    }
	}

}
