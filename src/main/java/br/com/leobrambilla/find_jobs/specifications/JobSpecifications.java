package br.com.leobrambilla.find_jobs.specifications;

import br.com.leobrambilla.find_jobs.entities.Job;
import org.springframework.data.jpa.domain.Specification;

public class JobSpecifications {

    public static Specification<Job> hasJobType(Job.JobType jobType) {
        return (root, query, cb) -> cb.equal(root.get("jobType"), jobType);
    }

    public static Specification<Job> hasJobStatus(Job.JobStatus jobStatus) {
        return (root, query, cb) -> cb.equal(root.get("jobStatus"), jobStatus);
    }

    public static Specification<Job> hasJobLocation(String jobLocation) {
        return (root, query, cb) -> cb.like(root.get("jobLocation"), "%" + jobLocation + "%");
    }

    public static Specification<Job> hasJobRequisitions(String jobRequisitions) {
        return (root, query, cb) -> cb.like(root.get("jobRequisitions"), "%" + jobRequisitions + "%");
    }
}
