package com.owais.app.service.test;

import com.owais.app.service.dao.MemberRepository;
import com.owais.app.service.model.Member;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class RepositoryTests {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void getMember() {
        testEntityManager.persist(new Member("Owais Khan",
                "260617913",
                "514-663-2088",
                "13-12-1995"));

        Member member = memberRepository.findByStudentId("260617913");
        Assert.assertTrue(member.getDateOfBirth().equals("13-12-1995"));
        Assert.assertTrue(member.getName().equals("Owais Khan"));
        Assert.assertTrue(member.getStudentId().equals("260617913"));
        Assert.assertTrue(member.getPhoneNo().equals("514-663-2088"));
    }
}
