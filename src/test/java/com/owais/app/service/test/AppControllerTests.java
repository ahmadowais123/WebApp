package com.owais.app.service.test;

import com.owais.app.service.AppController;
import com.owais.app.service.dao.MemberRepository;
import com.owais.app.service.model.Member;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(AppController.class)
public class AppControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    MemberRepository memberRepository;

    @Test
    public void getHello() throws Exception{

        this.mockMvc.perform(get("/api/hello"))
                .andExpect(content().string("Hello World!!"));

    }

    @Test
    public void createMember() throws  Exception {
        this.mockMvc.perform(post("/api/member")
                .param("studentId", "260617913")
                .param("dateOfBirth", "13-12-1995")
                .param("phoneNo", "514-663-2088")
                .param("name", "Owais Khan"))
                .andExpect(status().isCreated());
    }

    @Test
    public void deleteMember() throws Exception {

        given(this.memberRepository.findByStudentId(any(String.class))).willReturn(new Member());

        this.mockMvc.perform(delete("/api/member?studentId=260617913"))
                .andExpect(status().isNoContent());

        verify(this.memberRepository).delete(any(Long.class));
        verify(this.memberRepository).findByStudentId("260617913");
        verifyNoMoreInteractions(this.memberRepository);
    }

    @Test
    public void deleteMemberNotFound() throws Exception {
        given(this.memberRepository.findByStudentId(any(String.class))).willReturn(null);

        this.mockMvc.perform(delete("/api/member?studentId=260617913"))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("No student with that Id exists"));
    }

    @Test
    public void findMemberNotFound() throws  Exception {

        String studentId = "260617913";
        given(this.memberRepository.findByStudentId(any(String.class))).willReturn(null);

        MvcResult result = this.mockMvc.perform(get("/api/member?studentId=260617913"))
                .andExpect(status().isNotFound())
                .andReturn();

        Exception resolvedException = result.getResolvedException();
        Assert.assertTrue(resolvedException.getMessage().equals(String.format("There is no member with id %s", studentId)));
    }

    @Test
    public void findMemberSuccess() throws  Exception {
        Member member = new Member("Owais Khan",
                "260617913",
                "514-663-2088",
                "13-12-1995");

        given(this.memberRepository.findByStudentId(any(String.class))).willReturn(member);

        this.mockMvc.perform(get("/api/member?studentId=260617913"))
                .andExpect(status().isOk());

        verify(this.memberRepository).findByStudentId("260617913");
        verifyNoMoreInteractions(this.memberRepository);

    }
}
