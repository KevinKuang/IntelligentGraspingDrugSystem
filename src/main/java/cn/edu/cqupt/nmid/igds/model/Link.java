package cn.edu.cqupt.nmid.igds.model;

/**
 * Created by Administrator on 2017/7/8.
 */
public class Link {
    private String doctorId;
    private String patientId;

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    @Override
    public String toString() {
        return "Link{" +
                "doctorId='" + doctorId + '\'' +
                ", patientId='" + patientId + '\'' +
                '}';
    }
}
