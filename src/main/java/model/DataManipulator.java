package model;

import SPADEPAC.ObjectFactory;
import SPADEPAC.Project;
import XML.ProcessGenerator;
import forms.ProjectForm;
import services.*;

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

public class DataManipulator {


    public static ObjectFactory objF;
    private Project project;
    private ProcessGenerator procesGener;

    private SegmentLists lists;

    public DataManipulator(ProcessGenerator processGenerator) {
        this.procesGener = processGenerator;
        objF = new ObjectFactory();
        project = (Project) objF.createProject();
        lists = new SegmentLists(project);

    }


    /**
     * Vytvoří nové instance s potřebnými třídamy a restartuje třídu Control
     *
     * @param file
     */
    public void parseFile(File file) {
        Project tmpProject = procesGener.readProcess(file);
        if (tmpProject != null) {

            restartControl();
            project = tmpProject;

            ProjectForm form = new ProjectForm(this, project, canvas);
            forms.add(0, form);

            lists.restartLists(project);
            fillFormsXML = new FillFormsXML(this, lists, project, forms, fillCopy, idCreater, linkControl,
                    deleteControl, formControl);
            fillFormsXML.fillProjectFromXML(form);

            linkControl = new LinkControl(this, lists, objF, idCreater);
            linkControl.restart();
            deleteControl = new DeleteControl(this, lists, project);

            fillForms = new FillForms(this, lists, project, forms, objF, idCreater, deleteControl, formControl);
            fillCopy = new FillCopyForms(this, getLists(), project, forms, objF, idCreater, deleteControl, formControl);
            manipulation.restart(fillCopy, project, deleteControl, forms);



            parseProject();
        }
    }

    /**
     * Pomocná metoda pro validaci souboru
     */

    public void validate(Project project) {

        procesGener.validate(project);

    }

    /**
     * Pomocná metoda pro zpracování segmentů procesu
     */

    private void parseProject() {

        fillFormsXML.fillPhasesFromXML(forms.get(0));
        fillFormsXML.fillIterationFromXML(forms.get(0));
        fillFormsXML.fillActivityFromXML(forms.get(0));
        fillFormsXML.fillWorkUnitFromXML(forms.get(0), project.getWorkUnitIndexs());
        fillFormsXML.createLinks(project.getLinks());
    }

    /**
     * Umožní převedení data ve formátu LocalDate do formátu
     * XMLGregorianCalendar pro uložení do XML
     *
     * @param Ldate LocalDate
     * @return XMLGregorianCalendar
     */
    public static XMLGregorianCalendar convertDate(LocalDate Ldate) {

        if (Ldate == null) {
            return null;
        }

        Instant instant = Instant.from(Ldate.atStartOfDay(ZoneId.systemDefault()));
        Date date = Date.from(instant);
        GregorianCalendar c = new GregorianCalendar();

        XMLGregorianCalendar dateXML = null;
        try {

            c.setTime(date);
            dateXML = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);

        } catch (DatatypeConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return dateXML;
    }

    /**
     * Umožní převedení data ve formátu XMLGregorianCalendar uloženého v XML do
     * formátu LocalDate
     *
     * @param xmlDate XMLGregorianCalendar
     * @return LocalDate
     */
    public LocalDate convertDateFromXML(XMLGregorianCalendar xmlDate) {

        if (xmlDate == null) {
            return null;
        }

        Date date = xmlDate.toGregorianCalendar().getTime();
        Instant instant = date.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate localDate = zdt.toLocalDate();
        return localDate;
    }

    public Project getProject() {
        return project;
    }
}
