package XML;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.MarshalException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Validator;

import org.xml.sax.SAXException;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Project;
import services.Alerts;
import services.Constans;

public class ProcessGenerator {

	private JAXBContext jc;
	private ObjectFactory of;
	private JAXBElement rootElement;
	private Marshaller marshallerVal;
	private Unmarshaller unMarshallerVal;
	private Marshaller marshaller;
	private Unmarshaller unMarshaller;
	private ProcessValidator validator;

	public ProcessGenerator() {

		try {

			jc = JAXBContext.newInstance(Constans.LIBRARY);
			validator = new ProcessValidator(jc);
			of = new ObjectFactory();

			marshaller = jc.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF8");
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshaller.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, Constans.XSDNAME);

			unMarshaller = jc.createUnmarshaller();
			
			marshallerVal = jc.createMarshaller();
			marshallerVal.setProperty(Marshaller.JAXB_ENCODING, "UTF8");
			marshallerVal.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			marshallerVal.setProperty(Marshaller.JAXB_NO_NAMESPACE_SCHEMA_LOCATION, Constans.XSDNAME);
			marshallerVal.setSchema(validator.getSchemaXSD());

			unMarshallerVal = jc.createUnmarshaller();

		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	public void validate(Project project){
		rootElement = of.createPoject(project);
		try {
			marshallerVal.marshal(rootElement, new FileOutputStream(new File("Testovaci")));
		}catch (MarshalException e){
			StringWriter errors = new StringWriter();
			e.printStackTrace(new PrintWriter(errors));
			Alerts.showValidationError(errors.toString().substring(0, 300) + " ....");
			validator.validatePrecess(rootElement);
			return;
			
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Alerts.showValidationOK();
	}

	public void saveProcess(File file, Project project){

			rootElement = of.createPoject(project);
			try {

				marshallerVal.marshal(rootElement, new FileOutputStream(file));
			}catch (MarshalException e){
				StringWriter errors = new StringWriter();
				e.printStackTrace(new PrintWriter(errors));
				
				boolean tmp = Alerts.showValidationErrorSave(errors.toString().substring(0, 300) + " ....");
				if (tmp) {
					saveWithOutValidation(file, project);
				}
				
			}catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			validator.validatePrecess(rootElement);
		

	}

	private void saveWithOutValidation(File file, Project project) {
		rootElement = of.createPoject(project);
		
		try {
			marshaller.marshal(rootElement, new FileOutputStream(file));
		}catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public Project readProcess(File file) {

		JAXBElement element = null;

		try {
			element = (JAXBElement) unMarshaller.unmarshal(file);
			 validator.validatePrecess(element);

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
