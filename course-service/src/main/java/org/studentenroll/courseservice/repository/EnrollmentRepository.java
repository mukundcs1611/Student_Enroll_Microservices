package org.studentenroll.courseservice.repository;

import org.studentenroll.courseservice.entity.Enrollment;


public interface EnrollmentRepository {
    Enrollment findOne(String enrollmentId);
    void delete(Enrollment enrollment);
    Enrollment create(Enrollment enrollment);
}
