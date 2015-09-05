package br.inf.ufes.lprm.example;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;

import org.iso.mpeg.mpegv._2010.iidl.InteractionInfoType;
import org.iso.mpeg.mpegv._2010.siv.AccelerationSensorType;

public class UnMarshal {

	public static void main(String[] args) {
		String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>"+
			"<InteractionInfo xmlns:ns2=\"urn:mpeg:mpeg-v:2010:01-CT-NS\" xmlns:ns3=\"urn:mpeg:mpeg-v:2010:01-DCV-NS\" xmlns:ns4=\"urn:mpeg:mpeg7:schema:2004\" xmlns:ns5=\"urn:mpeg:mpeg-v:2010:01-SIV-NS\" xmlns:ns6=\"urn:mpeg:mpeg-v:2010:01-IIDL-NS\">"+
			"    <ns6:SensedInfoList>"+
			"        <ns6:SensedInfo xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"ns5:AccelerationSensorType\" sensorIdRef=\"DEV_ID_001\" activate=\"true\">"+
			"            <ns5:Acceleration>"+
			"                <ns2:X>0.09</ns2:X>"+
			"                <ns2:Y>-0.45</ns2:Y>"+
			"                <ns2:Z>9.85</ns2:Z>"+
			"            </ns5:Acceleration>"+
			"        </ns6:SensedInfo>"+
			"    </ns6:SensedInfoList>"+
			"</InteractionInfo>";
		
		StringReader reader = new StringReader(xml);
		
		JAXBContext jaxbContext;
		try {
			jaxbContext = JAXBContext.newInstance(InteractionInfoType.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<InteractionInfoType> jii = jaxbUnmarshaller.unmarshal(new StreamSource(reader), InteractionInfoType.class);
			InteractionInfoType ii = jii.getValue();
			if (ii.getSensedInfoList() != null && ii.getSensedInfoList().getSensedInfo() != null){
				AccelerationSensorType si = (AccelerationSensorType)ii.getSensedInfoList().getSensedInfo().get(0);
				System.out.println("ID: " + si.getSensorIdRef());
				System.out.println("ACTIVATE: " + si.isActivate());
				System.out.println("X: " + si.getAcceleration().getX());
				System.out.println("Y: " + si.getAcceleration().getY());
				System.out.println("Z: " + si.getAcceleration().getZ());
			}
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

}
