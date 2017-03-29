package XML;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.util.JAXBSource;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

import Obsluha.Constans;

public class ProcessValidator {

	private SchemaFactory schFac;
	private Source souborSchema;
	private Schema schemaXSD;
	private javax.xml.validation.Validator validator;

	private JAXBContext jc;
	private JAXBElement root;

	public ProcessValidator(JAXBContext jc) {

		this.jc = jc;

		try {
			schFac = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			souborSchema = new StreamSource(new File(Constans.XSDNAME));
			schemaXSD = schFac.newSchema(souborSchema);

			validator = schemaXSD.newValidator();

			validator.setErrorHandler(new ValidatorErrors());

		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void validatePrecess(JAXBElement root) {

		try {
			JAXBSource source = new JAXBSource(jc, root);
			validator.validate(source);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}

	}

	public Schema getSchemaXSD() {
		return schemaXSD;
	}

	public void setSchemaXSD(Schema schemaXSD) {
		this.schemaXSD = schemaXSD;
	}

	
	
}
