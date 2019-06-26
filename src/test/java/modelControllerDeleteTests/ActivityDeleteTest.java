package modelControllerDeleteTests;

import controllers.formControllers.DeleteFormController;
import controllers.formControllers.FormController;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.DataModel;
import org.junit.Before;
import org.junit.Test;
import services.SegmentType;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.Assert.assertSame;

public class ActivityDeleteTest {

    LocalDate date;
    DataModel dataModel;

    @Before
    public void setUp() throws Exception {

        date = LocalDate.of(2018, 10, 10);

        WarmUp warmUp = new WarmUp();
        dataModel = warmUp.getDataModel();
        FormController formController = warmUp.getFormController();
        DeleteFormController deleteFormController = warmUp.getDeleteFormController();
        formController.createTableItem(SegmentType.Activity);
        formController.createTableItem(SegmentType.Activity);
        ObservableList list = FXCollections.observableArrayList();
        list.add(0);
        deleteFormController.deleteActivityForm(list, new ArrayList<>());
    }

    @Test
    public void testListSize() {
        assertSame(1, dataModel.getActivities().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getActivities().get(0).getId());
    }
}

