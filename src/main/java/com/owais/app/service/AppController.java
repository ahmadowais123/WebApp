package com.owais.app.service;

import com.owais.app.service.dao.MemberRepository;
import com.owais.app.service.exception.MemberNotFoundException;
import com.owais.app.service.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
public class AppController {

    @Autowired
    MemberRepository memberRepository;

    //Return Hello World
    @RequestMapping(method = RequestMethod.GET, value = "/hello")
    @ResponseBody
    public String helloWorld() {
        return "Hello World!!";
    }

    //Create a member
    @RequestMapping(method = RequestMethod.POST, value = "/create")
    public ResponseEntity createMember(
            @RequestParam("name") String name,
            @RequestParam("studentId") String studentId,
            @RequestParam("phoneNo") String phoneNo,
            @RequestParam("dateOfBirth") String dateOfBirth
    ) {
        Member member = new Member(name, studentId, phoneNo, dateOfBirth);

        if(checkDuplicate(member)) {
            return new ResponseEntity("Student with that ID/Phone No. already exists", HttpStatus.BAD_REQUEST);
        }

        memberRepository.save(member);
        Long id = member.getId();

        return new ResponseEntity<>(id, HttpStatus.CREATED);
    }

    private boolean checkDuplicate(Member member) {
        if(memberRepository.findByStudentId(member.getStudentId()) != null
                || memberRepository.findByPhoneNo(member.getPhoneNo()) != null) {
            return true;
        }

        return false;
    }

    //Delete a member
    @RequestMapping(method = RequestMethod.DELETE, value="/delete")
    public ResponseEntity deleteMember(String studentId) {

        Member member = memberRepository.findByStudentId(studentId);

        if(member == null) {
            return new ResponseEntity("No student with that Id exists", HttpStatus.BAD_REQUEST);
        }

        memberRepository.delete(member.getId());

        return new ResponseEntity("Successfully Deleted", HttpStatus.NO_CONTENT);
    }

    //Return all members
    @RequestMapping(method = RequestMethod.GET, value = "/members")
    public Iterable<Member> findMembers() throws MemberNotFoundException {
        Iterable<Member> listMembers = memberRepository.findAll();

        if(listMembers == null) {
            throw new MemberNotFoundException("No members found.");
        }

        return listMembers;

    }

    //Find a member
    @RequestMapping(method = RequestMethod.GET, value = "/member")
    public Member findMember(
            @RequestParam("studentId") String studentId
    ) throws MemberNotFoundException {
        Member member = memberRepository.findByStudentId(studentId);

        if(member == null) {
            throw new MemberNotFoundException(String.format("There is no member with id %s", studentId));
        }

        return member;
    }

}
