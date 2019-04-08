package services;

import controllers.DataPreparer;
import controllers.VerifyController;
import database.SQLVerifyObject;
import tables.VerifyTable;

import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SQLAtributeCreator {

    public static String createDateAttribute(String atribute, List<XMLGregorianCalendar> date, List<Integer> dateIndicator){
        int i = 0;
        String atributeSection = "";
        for (XMLGregorianCalendar xmlDate : date){
            atributeSection += " AND ";
            atributeSection += atribute;
            atributeSection += String.valueOf(Constans.NUMBER_INDICATORS[dateIndicator.get(i)] + " ");
            atributeSection += "'" + DataPreparer.convertDateFromXML(xmlDate).toString() + "'";
            i++;
        }

        return atributeSection;
    }

    /**
     * Spustí zadaný skript a naplní seznam artefaktů
     * @param sql skript pro spuštění
     * @param id identifikátor výběru
     * @return seznam artefaktů
     */
    public static ArrayList<SQLVerifyObject> getAtributesFromDB(Connection pripojeni, VerifyController verifyController, String sql, int id,
                                                                int[] paramsInt) {
        PreparedStatement stmt = null;
        ResultSet rs = null;
        ArrayList<SQLVerifyObject> list = new ArrayList<SQLVerifyObject>();
        try {
            stmt = pripojeni.prepareStatement(sql);
            int i = 1;
            if (id != -1){
                stmt.setInt(1, id);
            }


            rs = stmt.executeQuery();
            while(rs.next()){
                list.add(verifyController.createSQLVerifyObject(rs.getInt("id"), sql, true));
            }

            if (list.size() == 0){
                SQLVerifyObject verifyObject = verifyController.createSQLVerifyObject( -1, sql, false);
                list.add(verifyObject);
            }

        } catch (SQLException e) {
            //	JOptionPane.showMessageDialog(null , Konstanty.POPISY.getProperty("chybaScriptPhase"));
            e.printStackTrace();
        } catch (Exception e){
            //	JOptionPane.showMessageDialog(null , Konstanty.POPISY.getProperty("chybaDataPhase"));
            e.printStackTrace();
        } finally {
            try {
                rs.close();
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return list;
    }


    public static String createStringAttribute(String atributName, List<String> name1, List<Integer> nameIndicator) {
        int i = 0;
        String atributeSection = "";
        for (String namel : name1){
            atributeSection += " AND ";
            atributeSection += atributName + " ";
            atributeSection += String.valueOf(Constans.INDICATORS[nameIndicator.get(i)] + " ");
            atributeSection += "'" + namel + "'";
            i++;
        }

        return atributeSection;
    }

    public static String createBooleanAttribute(String atributName, boolean isMain) {

        String atributeSection = "";
            atributeSection += " AND ";
            atributeSection += atributName;
            int main = 1;
            if (!isMain) {
            main = 0;
            }

            atributeSection += "=" + String.valueOf(main) + " ";

        return atributeSection;

    }

    public static String createClassAttribute(String element_classification, int classId) {

        String atributeSection = "";
        atributeSection += " left join " + element_classification + " c on p.classId = " + classId ;
        atributeSection += " left join project_instance i on p.projectInstanceId = i.id ";

        return atributeSection;
    }
}
