package services;
/**
 * 
 * @author Václav Janoch
 *
 */
public class ClassSwitcher {

	/**
	 * Rozhodovací metoda pro mapování Role Class na Super Class
	 * 
	 * @param classIndex
	 * @return Super Class index
	 */
	public int roleClassToSupperClass(int classIndex) {

		if (classIndex == Constans.roleTypeManagementClass) {

			return Constans.roleTypeManagementSuperClass;

		} else if (classIndex == 0) {

			return -1;

		} else if (classIndex > Constans.roleTypeManagementClass && classIndex <= Constans.roleTypeTeamMemberClass) {

			return Constans.roleTypeTeamMemberSuperClass;

		} else if (classIndex > Constans.roleTypeTeamMemberClass && classIndex <= Constans.roleTypeStakeholederClass) {

			return Constans.roleTypeStakeholederSuperClass;

		} else {

			return Constans.roleTypeOtherSuperClass;
		}

	}

	/**
	 * Rozhodovací metoda pro mapování Priority Class na Super Class
	 * 
	 * @param classIndex
	 * @return Super Class index
	 */
	public int priorityClassToSupperClass(int classIndex) {

		if (classIndex == Constans.priorityTypeNormalClass) {

			return Constans.priorityTypeNormaSuperClass;

		} else if (classIndex >= 1 && classIndex <= Constans.priorityTypeLowClass) {

			return Constans.priorityTypeLowSuperClass;

		} else if (classIndex < 1) {

			return -1;

		} else if (classIndex > Constans.priorityTypeNormalClass && classIndex <= Constans.priorityTypeHighClass) {

			return Constans.priorityTypeHighClassSuperClass;

		} else {

			return Constans.priorityTypeOtherSuperClass;
		}
	}

	/**
	 * Rozhodovací metoda pro mapování Relation Class na Super Class
	 * 
	 * @param classIndex
	 * @return Super Class index
	 */
	public int relationClassToSupperClass(int classIndex) {

		if (classIndex == Constans.relationTypeGeneralClass) {

			return Constans.relationTypeGeneralSuperClass;

		} else if (classIndex == 0) {

			return -1;

		} else if (classIndex >= 1 && classIndex <= Constans.relationTypeSimilaryClass) {

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

	/**
	 * Rozhodovací metoda pro mapování Resolution Class na Super Class
	 * 
	 * @param classIndex
	 * @return Super Class index
	 */
	public int resolutionClassToSupperClass(int classIndex) {
		if (classIndex <= Constans.resolutionTypeFinishedClass && classIndex > 0) {

			return Constans.resolutionFinishedSuperClass;

		} else if (classIndex == 0) {

			return -1;
		} else {

			return Constans.resolutionUnFinishedSuperClass;
		}
	}

	/**
	 * Rozhodovací metoda pro mapování Status Class na Super Class
	 * 
	 * @param classIndex
	 * @return Super Class index
	 */
	public int statusClassToSupperClass(int classIndex) {
		if (classIndex >= 1 && classIndex <= Constans.statusTypeOpenClass) {

			return Constans.statusTypeOpenSuperClass;

		} else if (classIndex == 0) {
			return -1;
		} else {

			return Constans.statusTypeCloseSuperClass;
		}
	}

	/**
	 * Rozhodovací metoda pro mapování Type Class na Super Class
	 * 
	 * @param classIndex
	 * @return Super Class index
	 */
	public int typeClassToSupperClass(int classIndex) {
		if (classIndex >= 1 && classIndex <= Constans.typeEditionClass) {

			return Constans.typeEditionSuperClass;

		} else if (classIndex == Constans.typeCreationClass) {

			return Constans.typeCreationSuperClass;

		} else if (classIndex == 0) {

			return -1;

		} else {

			return Constans.typeGeneralSuperClass;
		}
	}

	public int ClassToSuperClass(SegmentType segmentType, int classIndex) {
		switch (segmentType){
			case RoleType:
				return roleClassToSupperClass(classIndex);
			case Severity:
				return -1 ;
			case Priority:
				return priorityClassToSupperClass(classIndex);
			case Resolution:
				return resolutionClassToSupperClass(classIndex);
			case Relation:
				return relationClassToSupperClass(classIndex);
			case Status:
				return statusClassToSupperClass(classIndex);
			case Type:
				return typeClassToSupperClass(classIndex);
		}
		return 0;
	}
}
