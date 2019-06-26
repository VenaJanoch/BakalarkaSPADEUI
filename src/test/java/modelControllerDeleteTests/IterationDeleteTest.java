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

public class IterationDeleteTest {

    LocalDate date;
    DataModel dataModel;

    @Before
    public void setUp() throws Exception {

        date = LocalDate.of(2018, 10, 10);

        WarmUp warmUp = new WarmUp();
        dataModel = warmUp.getDataModel();
        FormController formController = warmUp.getFormController();
        DeleteFormController deleteFormController = warmUp.getDeleteFormController();
        formController.createTableItem(SegmentType.Iteration);
        formController.createTableItem(SegmentType.Iteration);
        ObservableList list = FXCollections.observableArrayList();
        list.add(0);
        deleteFormController.deleteIterationForm(list, new ArrayList<>());
    }

    @Test
    public void testListSize() {
        assertSame(1, dataModel.getIterations().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getIterations().get(0).getId());
    }

}
