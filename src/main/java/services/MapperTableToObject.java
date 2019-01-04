package services;

import javafx.scene.control.Tab;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapperTableToObject {
    private Map<Integer, ArrayList<TableToObjectInstanc>> milestoneToCriterionMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> roleTypeToRoleMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> priorityToWUMapper;
    private Map<Integer, ArrayList<TableToObjectInstanc>> severityToWUMapper;


    public MapperTableToObject(){
        this.milestoneToCriterionMapper = new HashMap<>();
        this.roleTypeToRoleMapper = new HashMap<>();
        this.priorityToWUMapper = new HashMap<>();
        this.severityToWUMapper = new HashMap<>();
    }

    public void mapMilestoneToCriterion(List<Integer> criterionIndex, TableToObjectInstanc instanc){
        for(int index : criterionIndex){
            if(getMilestoneToCriterionMapper().containsKey(index)){
                getMilestoneToCriterionMapper().get(index).add(instanc);
            }else{
                ArrayList<TableToObjectInstanc> listName = new ArrayList<>();
                listName.add(instanc);
                getMilestoneToCriterionMapper().put(index, listName);
            }
        }
    }

    public void mapPriorityToWorkUnit(int priorityIndex, TableToObjectInstanc instanc) {

            if(priorityToWUMapper.containsKey(priorityIndex)){
                priorityToWUMapper.get(priorityIndex).add(instanc);
               }else{
                ArrayList<TableToObjectInstanc> listName = new ArrayList<>();
                listName.add(instanc);
                priorityToWUMapper.put(priorityIndex, listName);
               }

    }

    public void mapRoleTypeToRole(int roleTypeIndex, TableToObjectInstanc instanc){
        if(getRoleTypeToRoleMapper().containsKey(roleTypeIndex)){
            getRoleTypeToRoleMapper().get(roleTypeIndex).add(instanc);
        }else{
            ArrayList<TableToObjectInstanc> listName = new ArrayList<>();
            listName.add(instanc);
            getRoleTypeToRoleMapper().put(roleTypeIndex, listName);
        }

    }

    public void mapSeverityToWorkUnit(int roleType, TableToObjectInstanc instanc) {

        if(severityToWUMapper.containsKey(roleType)){
            severityToWUMapper.get(roleType).add(instanc);
            }else{
            ArrayList<TableToObjectInstanc> listName = new ArrayList<>();
            listName.add(instanc);
            severityToWUMapper.put(roleType, listName);
        }

    }

    /** Getters and Setters **/

    public Map<Integer, ArrayList<TableToObjectInstanc>> getMilestoneToCriterionMapper() {
        return milestoneToCriterionMapper;
    }

    public Map<Integer, ArrayList<TableToObjectInstanc>> getRoleTypeToRoleMapper() {
        return roleTypeToRoleMapper;
    }


}
