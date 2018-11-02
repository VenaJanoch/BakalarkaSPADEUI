package Controllers;

import model.IdentificatorCreater;

public class IdentificatorController {

    private IdentificatorCreater idCreator;

    public IdentificatorController(){

        this.idCreator = new IdentificatorCreater();
    }


    public int createLineID() {
        return  idCreator.createLineID();
    }

    public int getChangeIndex(int formIndex){

        return idCreator.getChangeIndexMaper().get(formIndex);
    }

    public  Integer getArtifactIndex(int formIndex){
        return  idCreator.getArtifactIndexMaper().get(formIndex);
    }

}
