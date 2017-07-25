package cn.edu.cqupt.nmid.igds.service;


import cn.edu.cqupt.nmid.igds.model.json.ResponseJson;


/**
 * Created by Administrator on 2017/7/25.
 */
public interface PrescriptionService {
    public ResponseJson savePrescription (int linkId,String PrescriptionMessage);

    public ResponseJson getPrescription (int linkId);
}
