package model;

import XML.ProcessGenerator;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import services.Constans;

import java.io.File;

/**
 * Trida slouzici pro praci s XML souborem(nacitani a ukladani modelu)
 *
 * @author Václav Janoch
 */
public class FileManipulator {

    /**
     * Globalni promenne tridy
     **/
    private File XMLProcessFile;

    private FileChooser fileChooser;
    private ProcessGenerator procesGener;
    private DataModel dataModel;
    private boolean firstSave;
    private boolean save = false;


    /**
     * Konstruktor tridy, zinicializuje globalni promenne tridy
     *
     * @param dataModel instace datoveho modelu pro ulozeni ci nahrani dat
     */
    public FileManipulator(DataModel dataModel) {
        this.dataModel = dataModel;

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
    public boolean saveFile() {

        if (XMLProcessFile == null || firstSave) {

            boolean isSave = saveAsFile();

            if (isSave) {
                firstSave = false;
                save = true;
                return true;
            } else {
                return false;
            }

        } else {
            dataModel.saveProcess(XMLProcessFile);
            return true;
        }
    }

    /**
     * Umožní zobrazení systémového okna pro výběr souboru k uložení. Načte
     * soubor do globální proměnné XMLProcessFile pro další polužití
     */

    public boolean saveAsFile() {
        if (!save) {
            fileChooser.setTitle("Save Process");

            XMLProcessFile = fileChooser.showSaveDialog(new Stage());

            if (XMLProcessFile != null) {
                dataModel.saveProcess(XMLProcessFile);
                save = true;
            } else {
                return false;
            }

        } else {
            save = false;
        }
        return true;
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


}

