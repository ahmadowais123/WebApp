package com.owais.app.service;

import com.owais.app.service.dao.MemberRepository;
import com.owais.app.service.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api")
public class AppController {

    @Autowired
    MemberRepository memberRepository;

    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    @ResponseBody
    public String helloWorld() {
        return "Hello World!!";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity createMember(
            @RequestParam("name") String name,
            @RequestParam("studentId") String studentId,
            @RequestParam("phoneNo") String phoneNo,
            @RequestParam("dateOfBirth") String dateOfBirth
    ) {
        Member member = new Member(name, studentId, phoneNo, dateOfBirth);
        memberRepository.save(member);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    //Return all members
    @RequestMapping(method = RequestMethod.GET, value = "/members")
    public ResponseEntity findMembers() {
        Iterable<Member> listMembers = memberRepository.findAll();

        return new ResponseEntity<>(listMembers, HttpStatus.FOUND);

    }

    @RequestMapping(method = RequestMethod.GET, value = "/member")
    public ResponseEntity findMember(
            @RequestParam("studentId") String studentId
    ) {
        List<Member> members = memberRepository.findByStudentId(studentId);

        if(members.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(members, HttpStatus.FOUND);
    }
}
