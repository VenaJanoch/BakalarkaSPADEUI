package modelControllerTests;

import controllers.ApplicationController;
import controllers.DataPreparer;
import controllers.FormController;
import controllers.FormDataController;
import XML.ProcessGenerator;
import model.DataManipulator;
import model.DataModel;
import model.FileManipulator;
import model.IdentificatorCreater;
import services.*;

public class WarmUp {

    private SegmentLists lists;
    private FormDataController formDataController;
    private DataModel dataModel;
    private DataManipulator data;
    private FormController formController;

    public WarmUp(){
        this.lists =  new SegmentLists();
        MapperTableToObject mapperTableToObject = new MapperTableToObject(lists);
        IdentificatorCreater idCreator = new IdentificatorCreater();
        ProcessGenerator processGenerator = new ProcessGenerator();
        dataModel = new DataModel(processGenerator);
        data =  new DataManipulator(processGenerator, dataModel);

        FileManipulator file = new FileManipulator(dataModel);
        Alerts alerts = new Alerts(file);
        ApplicationController ap = new ApplicationController(dataModel, idCreator, lists);
        DeleteControl deleteControl = new DeleteControl(new SegmentLists(), mapperTableToObject, idCreator);
        DataPreparer dataPreparer = new DataPreparer(idCreator);
        formController = new FormController(idCreator, dataModel, ap, lists, dataPreparer);
        for(int i = 0; i < 12; i++){
            formController.getForms().add(null);
        }
        this.formDataController = new FormDataController(formController, lists, mapperTableToObject, dataModel, idCreator, dataPreparer);
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

    public DataModel getDataModel() {
        return dataModel;
    }
}

