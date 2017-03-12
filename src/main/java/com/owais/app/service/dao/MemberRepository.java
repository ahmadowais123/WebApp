package com.owais.app.service.dao;

import com.owais.app.service.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {

    @Transactional
    Member findByStudentId(String studentId);

    @Transactional
    Member findByPhoneNo(String phoneNo);
}