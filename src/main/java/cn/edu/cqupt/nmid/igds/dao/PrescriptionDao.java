package cn.edu.cqupt.nmid.igds.dao;

import cn.edu.cqupt.nmid.igds.model.DrugDose;
import cn.edu.cqupt.nmid.igds.model.Prescription;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/7/25.
 */
@Repository
public interface PrescriptionDao  {

    int savePrescription (Prescription prescription);

    int checkPrescription (int linkId);

    int checkDrug (int linkId, String drugName);

    List<DrugDose> getPrescription (@Param("linkId") long linkId);
}
