package com.owais.dao;

import com.owais.model.Member;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MemberRepository extends CrudRepository<Member, Long> {

    List<Member> findByStudentId(String studentId);
    List<Member> findByPhoneNo(String phoneNo);
}