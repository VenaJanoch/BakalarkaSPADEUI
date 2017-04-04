package services;

public class ClassSwitcher {

	private Control control;

	public ClassSwitcher(Control control) {
		this.control = control;

	}

	public int roleClassToSupperClass(int classIndex) {

		if (classIndex == Constans.roleTypeManagementClass) {

			return Constans.roleTypeManagementSuperClass;

		} else if (classIndex > Constans.roleTypeManagementClass && classIndex <= Constans.roleTypeTeamMemberClass) {

			return Constans.roleTypeTeamMemberSuperClass;

		} else if (classIndex > Constans.roleTypeTeamMemberClass && classIndex <= Constans.roleTypeStakeholederClass) {

			return Constans.roleTypeStakeholederSuperClass;

		} else {

			return Constans.roleTypeOtherSuperClass;
		}

	}

	public int priorityClassToSupperClass(int classIndex) {

		if (classIndex == Constans.priorityTypeNormalClass) {

			return Constans.priorityTypeNormaSuperClass;

		} else if (classIndex <= Constans.priorityTypeLowClass) {

			return Constans.priorityTypeLowSuperClass;

		} else if (classIndex > Constans.priorityTypeNormalClass && classIndex <= Constans.priorityTypeHighClass) {

			return Constans.priorityTypeHighClassSuperClass;

		} else {

			return Constans.priorityTypeOtherSuperClass;
		}
	}

	public int relationClassToSupperClass(int classIndex) {

		if (classIndex == Constans.relationTypeGeneralClass) {

			return Constans.relationTypeGeneralSuperClass;

		} else if (classIndex <= Constans.relationTypeSimilaryClass) {

			return Constans.relationTypeSimilarySuperClass;

		} else if (classIndex > Constans.relationTypeSimilaryClass
				&& classIndex <= Constans.relationTypeTemporalClass) {

			return Constans.relationTypeTemporalSuperClass;

		} else if (classIndex > Constans.relationTypeTemporalClass
				&& classIndex <= Constans.relationTypeHierachyClass) {

			return Constans.relationTypeHierachySuperClass;

		} else {

			return Constans.relationTypeCasualSuperClass;
		}
	}

	public int resolutionClassToSupperClass(int classIndex) {
		if (classIndex <= Constans.resolutionTypeFinishedClass) {

			return Constans.resolutionFinishedSuperClass;

		} else {

			return Constans.resolutionUnFinishedSuperClass;
		}
	}

	public int statusClassToSupperClass(int classIndex) {
		if (classIndex <= Constans.statusTypeOpenClass) {

			return Constans.statusTypeOpenSuperClass;

		} else {

			return Constans.statusTypeCloseSuperClass;
		}
	}

	// public int roleSuperClassToClass(int classIndex) {
	//
	// System.out.println(classIndex + "classIndex");
	//
	// if (classIndex == Constans.roleTypeManagementSuperClass) {
	// System.out.println(Constans.roleTypeManagementClass + "1");
	//
	// return Constans.roleTypeManagementClass;
	//
	// } else if (classIndex > Constans.roleTypeManagementSuperClass
	// && classIndex < Constans.roleTypeTeamMemberSuperClass) {
	// System.out.println(Constans.roleTypeTeamMemberClass);
	// return Constans.roleTypeTeamMemberClass;
	//
	// } else if (classIndex > Constans.roleTypeTeamMemberSuperClass
	// && classIndex < Constans.roleTypeStakeholederSuperClass) {
	// return Constans.roleTypeStakeholederClass;
	// } else {
	// return Constans.roleTypeOtherClass;
	// }
	//
	// }

}
