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

//	public int roleSuperClassToClass(int classIndex) {
//
//		System.out.println(classIndex + "classIndex");
//		
//		if (classIndex == Constans.roleTypeManagementSuperClass) {
//			System.out.println(Constans.roleTypeManagementClass + "1");
//			
//			return Constans.roleTypeManagementClass;
//			
//		} else if (classIndex > Constans.roleTypeManagementSuperClass
//				&& classIndex < Constans.roleTypeTeamMemberSuperClass) {
//			System.out.println(Constans.roleTypeTeamMemberClass);
//			return Constans.roleTypeTeamMemberClass;
//
//		} else if (classIndex > Constans.roleTypeTeamMemberSuperClass
//				&& classIndex < Constans.roleTypeStakeholederSuperClass) {
//			return Constans.roleTypeStakeholederClass;
//		} else {
//			return Constans.roleTypeOtherClass;
//		}
//
//	}

}
