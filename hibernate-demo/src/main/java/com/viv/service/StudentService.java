package com.viv.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;

import com.viv.dao.StudentRepository;
import com.viv.entity.Student;

import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional.TxType;
import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public List<Student> getAllStudents() {

        throw new UnsupportedOperationException("Unimplemented method 'getAllStudents'");
    }

    @Transactional
    public Student getStudentById(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'getStudentById'");
    }

    /**
     * @Transactional annotation automatically manages the lifecycle of Hibernate
     *                sessions and transactions. When applied to a method or class:
     *                A Hibernate session is opened when the method begins.
     *                Transactions are started and committed or rolled back
     *                automatically.
     *                The session is closed when the method completes.
     * @param student
     * @return
     */
    @Transactional
    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    @Transactional(TxType.REQUIRED)
    public Student updateStudent(Long id, Student student) {
        throw new UnsupportedOperationException("Unimplemented method 'updateStudent'");
    }

    public void deleteStudent(Long id) {
        throw new UnsupportedOperationException("Unimplemented method 'deleteStudent'");
    }

}
