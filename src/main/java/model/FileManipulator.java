package model;

import SPADEPAC.Project;
import XML.ProcessGenerator;
import forms.ProjectForm;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.*;

import javax.xml.crypto.Data;
import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.GregorianCalendar;

public class FileManipulator {

    private File XMLProcessFile;

    private FileChooser fileChooser;
    private ProcessGenerator procesGener;
    private DataManipulator dataManipulator;
    private boolean firstSave;
    private boolean save = false;


    public FileManipulator(ProcessGenerator processGener, DataManipulator dataManipulator) {
        this.procesGener = processGener;
        this.dataManipulator = dataManipulator;

        configureFileChooser();
        firstSave = true;
    }


    /**
     * Metoda pro konfiguraci FileChooseru
     */
    private void configureFileChooser() {

        fileChooser = new FileChooser();
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("XML", "*.xml"));
    }

    /**
     * Rozhodne o vyvolání okna pro uložení nebo automatickém uložení do již
     * zvoleného souboru
     */
    public void saveFile() {

        if (XMLProcessFile == null || firstSave) {

            saveAsFile();
            firstSave = false;
            save = true;
        } else {
            procesGener.saveProcess(XMLProcessFile, dataManipulator.getProject());
        }

    }

    /**
     * Umožní zobrazení systémového okna pro výběr souboru k uložení. Načte
     * soubor do globální proměnné XMLProcessFile pro další polužití
     */

    public void saveAsFile() {
        if (!save) {
            fileChooser.setTitle("Save Process");

            XMLProcessFile = fileChooser.showSaveDialog(new Stage());

            if (XMLProcessFile != null) {
                procesGener.saveProcess(XMLProcessFile, dataManipulator.getProject());
            }
        } else {
            save = false;
        }

    }

    /**
     * Umožní zobrazneí systémového okna pro výběr souboru k načtení XML souboru
     * s procesem Zavolá metody pro restartování datových struktur
     */
    public File loadFile() {

        fileChooser.setTitle(Constans.fileChooserTitle);

        XMLProcessFile = fileChooser.showOpenDialog(new Stage());
        if (XMLProcessFile != null) {

            return XMLProcessFile;
        }
        return null;
    }

    public void parseProject(File xmlProject){
        dataManipulator.setProject(procesGener.readProcess(xmlProject));
    }

}

