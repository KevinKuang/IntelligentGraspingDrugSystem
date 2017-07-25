package cn.edu.cqupt.nmid.igds.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/7/25.
 */
public class Prescription implements Serializable{
    private static final long serialVersionUID = 1L;
    private long linkId;
    /**
     * Key : 药品名
     * Value : 药品用量
     * */
    private ArrayList<DrugDose> drugs ;

    public long getLinkId() {
        return linkId;
    }

    public void setLinkId(long linkId) {
        this.linkId = linkId;
    }

    public ArrayList<DrugDose> getDrugs() {
        return drugs;
    }

    public void setDrugs(ArrayList<DrugDose> drugs) {
        this.drugs = drugs;
    }

    @Override
    public String toString() {
        return "Prescription{" +
                "linkId=" + linkId +
                ", drugs=" + drugs +
                '}';
    }

    public String encodeDrugsToString (){
        StringBuilder sb = new StringBuilder();
        for (DrugDose drugDose:drugs ) {
            sb.append(drugDose.encodeString());
            sb.append(",");
        }
        return new String(sb);
    }

    public static List<DrugDose> decodeDrugsString (String str){
        String drugsString[] = str.split(",");
        List<DrugDose> drugDoses = new ArrayList<>();
        for ( String drugString: drugsString) {
            drugDoses.add(DrugDose.decodeString(drugString));
        }
        return drugDoses;
    }
}
