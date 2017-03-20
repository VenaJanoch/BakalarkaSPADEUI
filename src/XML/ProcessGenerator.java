package XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import Obsluha.Constans;
import SPADEPAC.ObjectFactory;
import SPADEPAC.Project;

public class ProcessGenerator {

	private JAXBContext jc;
	private ObjectFactory of;
	private JAXBElement rootElement;
	private Marshaller marshaller;
	private Unmarshaller unMarshaller;

	public ProcessGenerator() {

		try {
			
			jc = JAXBContext.newInstance("SPADEPAC");
			of = new ObjectFactory();
			
			marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, Constans.XSDNAME);

			unMarshaller = jc.createUnmarshaller();
			
			
			
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void saveProcess(File file, Project project) {

		try {

			rootElement = of.createPoject(project);
			marshaller.marshal(rootElement, new FileOutputStream(file));

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public Project readProcess(File file){
		
		JAXBElement element = null;
		
		try {
			element = (JAXBElement) unMarshaller.unmarshal(file);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		return (Project) element.getValue();
		
	}
	

	/*** Getrs and Setrs ****/
	public ObjectFactory getOf() {
		return of;
	}

	public void setOf(ObjectFactory of) {
		this.of = of;
	}
	
	
	

}
