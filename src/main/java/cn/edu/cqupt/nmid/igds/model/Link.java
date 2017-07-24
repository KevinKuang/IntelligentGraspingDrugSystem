package cn.edu.cqupt.nmid.igds.model;

/**
 * Created by Administrator on 2017/7/8.
 */
public class Link {
    private long id;
    private String doctorId;
    private String patientId;
    private String status;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Link{" +
                "doctorId='" + doctorId + '\'' +
                ", patientId='" + patientId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
