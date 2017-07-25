package cn.edu.cqupt.nmid.igds.model;

/**
 * Created by Administrator on 2017/7/25.
 */
public class DrugDose {
    private String drugName ;

    private String dose;

    public DrugDose(){}
    public DrugDose (String drugName,String  dose){
        this.dose = dose ;
        this.drugName = drugName;
    }
    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    @Override
    public String toString() {
        return "DrugDose{" +
                "drugName='" + drugName + '\'' +
                ", dose='" + dose + '\'' +
                '}';
    }

    public String encodeString (){
        StringBuilder sb = new StringBuilder(drugName);
        sb.append("_");
        sb.append(dose);
        return new String(sb);
    }

    public static DrugDose decodeString (String str){
        String strings[] = str.split("_");
        DrugDose drugDose =new DrugDose();
        if(strings.length==2) {
            drugDose.setDrugName(strings[0]);
            drugDose.setDose(strings[1]);
            return drugDose;
        }else {
            return null;
        }
    }
}
