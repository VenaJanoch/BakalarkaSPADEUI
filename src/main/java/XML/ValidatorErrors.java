package XML;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import services.Alerts;

public class ValidatorErrors implements ErrorHandler {

    /**
     * generatedError
     * Vypíše informace o chybě spolu s vážností problému
     *
     * @param categori
     * @param e
     * @throws SAXException
     */
    public void generatedError(String categori, SAXException e) throws SAXException {
        throw new SAXException(categori + ": " + e.toString());
    }

    /**
     * Vypíše chybu typu warning
     */
    @Override
    public void warning(SAXParseException e) throws SAXException {
        generatedError("Warning", e);
    }

    /**
     * Vypíše chybu typu error
     */
    @Override
    public void error(SAXParseException e) throws SAXException {
        generatedError("Error", e);
    }

    /**
     * Vypíše chybu typu fatal error
     */
    @Override
    public void fatalError(SAXParseException e) throws SAXException {
        generatedError("Fatal error", e);
    }
}
