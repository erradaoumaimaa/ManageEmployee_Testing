package models;
import javax.persistence.*;

@Entity
@Table(name = "job_applications")
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "applicant_name", nullable = false)
    private String applicantName;

    @Column(name = "applicant_email", nullable = false)
    private String applicantEmail;

    @Column(name = "applicant_phone", nullable = false)
    private String applicantPhone;

    @Column(name = "cv", nullable = false)
    private String cv;

    @ManyToOne
    @JoinColumn(name = "job_offer_id", nullable = false)
    private JobOffer jobOffer;

    public JobApplication() {}

    public JobApplication(String applicantName, String applicantEmail, String applicantPhone, String cv, JobOffer jobOffer) {
        this.applicantName = applicantName;
        this.applicantEmail = applicantEmail;
        this.applicantPhone = applicantPhone;
        this.cv = cv;
        this.jobOffer = jobOffer;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getApplicantName() {
        return applicantName;
    }

    public void setApplicantName(String applicantName) {
        this.applicantName = applicantName;
    }

    public String getApplicantEmail() {
        return applicantEmail;
    }

    public void setApplicantEmail(String applicantEmail) {
        this.applicantEmail = applicantEmail;
    }

    public String getApplicantPhone() {
        return applicantPhone;
    }

    public void setApplicantPhone(String applicantPhone) {
        this.applicantPhone = applicantPhone;
    }

    public String getCv() {
        return cv;
    }

    public void setCv(String cv) {
        this.cv = cv;
    }

    public JobOffer getJobOffer() {
        return jobOffer;
    }

    public void setJobOffer(JobOffer jobOffer) {
        this.jobOffer = jobOffer;
    }
}