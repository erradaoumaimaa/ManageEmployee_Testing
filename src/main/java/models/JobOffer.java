package models;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import models.JobApplication;
@Entity
@Table(name = "jobs")
public class JobOffer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "requirement", nullable = false)
    private String requirement;

    @Column(name = "publication_date", nullable = false)
    private LocalDate publicationDate;

    @Column(name = "expiry_date", nullable = false)
    private LocalDate expiryDate;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<JobApplication> jobApplications;

    public JobOffer() {}

    public JobOffer(String title, String description, String requirement, LocalDate publicationDate, LocalDate expiryDate, String status) {
        this.title = title;
        this.description = description;
        this.requirement = requirement;
        this.publicationDate = publicationDate;
        this.expiryDate = expiryDate;
        this.status = status;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getRequirement() { return requirement; }

    public void setRequirement(String requirement) { this.requirement = requirement; }

    public LocalDate getPublicationDate() { return publicationDate; }

    public void setPublicationDate(LocalDate publicationDate) { this.publicationDate = publicationDate; }

    public LocalDate getExpiryDate() { return expiryDate; }

    public void setExpiryDate(LocalDate expiryDate) { this.expiryDate = expiryDate; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public List<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public void setJobApplications(List<JobApplication> jobApplications) {
        this.jobApplications = jobApplications;
    }
    public void addJobApplication(JobApplication application) {
        this.jobApplications.add(application);
        application.setJobOffer(this);
    }
}
