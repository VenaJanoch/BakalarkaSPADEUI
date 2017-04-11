package XML;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import services.Alerts;

public class ValidatorErrors implements ErrorHandler {

	public void generatedError(String categori, SAXException e) throws SAXException {

		
		throw new SAXException(categori + ": " + e.toString());
	}

	@Override
	public void warning(SAXParseException e) throws SAXException {
		generatedError("Warning", e);
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		generatedError("Error", e);
	}

	@Override
	public void fatalError(SAXParseException e) throws SAXException {
		generatedError("Fatal error", e);
	}
}
