package services;

import java.time.LocalDate;
import java.util.ArrayList;

import SPADEPAC.Activity;
import SPADEPAC.Artifact;
import SPADEPAC.Branch;
import SPADEPAC.Change;
import SPADEPAC.ConfigPersonRelation;
import SPADEPAC.Configuration;
import SPADEPAC.Coordinates;
import SPADEPAC.Criterion;
import SPADEPAC.Iteration;
import SPADEPAC.Milestone;
import SPADEPAC.ObjectFactory;
import SPADEPAC.Phase;
import SPADEPAC.Priority;
import SPADEPAC.Project;
import SPADEPAC.Relation;
import SPADEPAC.Resolution;
import SPADEPAC.Role;
import SPADEPAC.RoleType;
import SPADEPAC.Severity;
import SPADEPAC.Status;
import SPADEPAC.Type;
import SPADEPAC.WorkUnit;
import abstractform.BasicForm;
import forms.ActivityForm;
import forms.ArtifactForm;
import forms.ChangeForm;
import forms.ConfigurationForm;
import forms.IterationForm;
import forms.WorkUnitForm;
import graphics.CanvasItem;
import javafx.collections.ObservableList;
import model.IdentificatorCreater;
import tables.ConfigTable;
import tables.TagTable;

import javax.xml.datatype.XMLGregorianCalendar;

public class FillForms {

	/** Globální proměnné třídy */
	private static Control control;
	private Project project;
	private ArrayList<BasicForm> forms;
	private static ObjectFactory objF;
	private static SegmentLists lists;

	private IdentificatorCreater idCreater;
	private DeleteControl deleteControl;
	private static FormControl formControl;


}
