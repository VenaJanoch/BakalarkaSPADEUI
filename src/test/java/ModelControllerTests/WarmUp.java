package ModelControllerTests;

import Controllers.ApplicationController;
import Controllers.FormController;
import Controllers.FormDataController;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.FileManipulator;
import model.IdentificatorCreater;
import services.*;

public class WarmUp {

    private SegmentLists lists;
    private FormDataController formDataController;
    private DataManipulator data;
    private FormController formController;

    public WarmUp(){
        MapperTableToObject mapperTableToObject = new MapperTableToObject();
        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        data =  new DataManipulator(processGenerator,idCreator);
        this.lists =  new SegmentLists();
        FileManipulator file = new FileManipulator(processGenerator,data);
        Alerts alerts = new Alerts(file);
        ApplicationController ap = new ApplicationController(file, data, alerts, idCreator, lists);
        DeleteControl deleteControl = new DeleteControl(new SegmentLists(), mapperTableToObject);
        formController = new FormController(idCreator, data, ap, lists, deleteControl);
        for(int i = 0; i < 12; i++){
            formController.getForms().add(null);
        }
        this.formDataController = new FormDataController(formController, deleteControl, lists, mapperTableToObject, data, idCreator);
    }

    public SegmentLists getLists() {
        return lists;
    }

    public FormController getFormController() {
        return formController;
    }

    public DataManipulator getData() {
        return data;
    }

    public FormDataController getFormDataController() {
        return formDataController;
    }
}

