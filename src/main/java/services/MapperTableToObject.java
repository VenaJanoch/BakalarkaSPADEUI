package services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperTableToObject {
    private Map<Integer, ArrayList<String>> milestoneToCriterionMapperName;
    private Map<Integer, ArrayList<Integer>> milestoneToCriterionMapperId;
    private Map<Integer, ArrayList<String>> roleTypeToRoleMapperName;
    private Map<Integer, ArrayList<Integer>> roleTypeToRoleMapperId;


    public MapperTableToObject(){
        this.milestoneToCriterionMapperName = new HashMap<>();
        roleTypeToRoleMapperName = new HashMap<>();
        this.milestoneToCriterionMapperId = new HashMap<>();
        roleTypeToRoleMapperId = new HashMap<>();

    }

    public void mapMilestoneToCriterion(List<Integer> criterionIndex, String milestonename, int id){
        for(int index : criterionIndex){
            if(getMilestoneToCriterionMapperName().containsKey(index)){
                getMilestoneToCriterionMapperName().get(index).add(" Milestone: " + milestonename);
                getMilestoneToCriterionMapperId().get(index).add(id);
            }else{
                ArrayList<String> listName = new ArrayList<>();
                ArrayList<Integer> listId = new ArrayList<>();
                listName.add(" Milestone: " + milestonename);
                listId.add(id);
                getMilestoneToCriterionMapperName().put(index, listName);
                getMilestoneToCriterionMapperId().put(index, listId);
            }
        }
    }

    public void mapRoleToRoleType(int roleType, String roleName, int roleIndex) {

            if(getRoleTypeToRoleMapperId().containsKey(roleType)){
                getRoleTypeToRoleMapperName().get(roleType).add(" Role: " + roleName);
                getRoleTypeToRoleMapperId().get(roleType).add(roleIndex);
            }else{
                ArrayList<String> listName = new ArrayList<>();
                ArrayList<Integer> listId = new ArrayList<>();
                listName.add(" Role: " + roleName);
                listId.add(roleIndex);
                getRoleTypeToRoleMapperName().put(roleType, listName);
                getRoleTypeToRoleMapperId().put(roleType, listId);
            }

    }


    /** Getters and Setters **/

    public Map<Integer, ArrayList<String>> getMilestoneToCriterionMapperName() {
        return milestoneToCriterionMapperName;
    }

    public Map<Integer, ArrayList<Integer>> getMilestoneToCriterionMapperId() {
        return milestoneToCriterionMapperId;
    }

    public Map<Integer, ArrayList<String>> getRoleTypeToRoleMapperName() {
        return roleTypeToRoleMapperName;
    }

    public Map<Integer, ArrayList<Integer>> getRoleTypeToRoleMapperId() {
        return roleTypeToRoleMapperId;
    }


}
