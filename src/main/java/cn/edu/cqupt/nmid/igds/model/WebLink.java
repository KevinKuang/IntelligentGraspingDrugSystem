package cn.edu.cqupt.nmid.igds.model;

/**
 * Created by Administrator on 2017/7/24.
 */
public class WebLink {
    private long id;
    private WebUser doctor;
    private WebUser patient;
    private String status;

    public WebLink (Link link){
        this.id = link.getId();
        this.status = link .getStatus();
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public WebUser getDoctor() {
        return doctor;
    }

    public void setDoctor(WebUser doctor) {
        this.doctor = doctor;
    }

    public WebUser getPatient() {
        return patient;
    }

    public void setPatient(WebUser patient) {
        this.patient = patient;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "WebLink{" +
                "id=" + id +
                ", doctor=" + doctor +
                ", patient=" + patient +
                ", status='" + status + '\'' +
                '}';
    }
}
