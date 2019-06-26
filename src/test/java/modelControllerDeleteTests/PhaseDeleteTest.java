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

public class PhaseDeleteTest {

    LocalDate date;
    DataModel dataModel;

    @Before
    public void setUp() throws Exception {

        date = LocalDate.of(2018, 10, 10);

        WarmUp warmUp = new WarmUp();
        dataModel = warmUp.getDataModel();
        FormController formController = warmUp.getFormController();
        DeleteFormController deleteFormController = warmUp.getDeleteFormController();
        formController.createTableItem(SegmentType.Phase);
        formController.createTableItem(SegmentType.Phase);
        ObservableList list = FXCollections.observableArrayList();
        list.add(0);
        deleteFormController.deletePhaseForm(list, new ArrayList<>());
    }

    @Test
    public void testListSize() {
        assertSame(1, dataModel.getPhases().size());
    }

    @Test
    public void testDeleteCorectItem() {
        assertSame(1, dataModel.getPhases().get(0).getId());
    }


}
