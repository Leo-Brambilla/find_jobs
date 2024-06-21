package br.com.leobrambilla.find_jobs.entities;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "tb_jobs")
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "job_id", updatable = false, nullable = false)
    private UUID jobId;

    @Column(name = "job_title", nullable = false)
    private String jobTitle;

    @Column(name = "job_requisitions", nullable = false)
    private String jobRequisitions;

    @Column(name = "number_of_jobs", nullable = false)
    private int numberOfJobs;

    @Column(name = "publication_date", nullable = false)
    private LocalDateTime publicationDate;

    @Column(name = "payment", nullable = false)
    private BigDecimal payment;

    @Column(name = "company", nullable = false)
    private String company;

    @Column(name = "job_description", nullable = false, columnDefinition = "TEXT")
    private String jobDescription;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_status", nullable = false)
    private JobStatus jobStatus;

    @Enumerated(EnumType.STRING)
    @Column(name = "job_type", nullable = false)
    private JobType jobType;

    @Column(name = "job_location", nullable = false)
    private String jobLocation;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public enum JobStatus {
        ABERTO, FINALIZADO, PAUSADO
    }

    public enum JobType {
        ESTAGIO, JUNIOR, PLENO, SENIOR
    }
}
