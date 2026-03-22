package com.telusko.spring_boot_rest.service;

import com.telusko.spring_boot_rest.model.JobPost;
import com.telusko.spring_boot_rest.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 비즈니스 로직 처리 계층
public class JobService {

    @Autowired // Repository 객체 자동 주입
    private JobRepo repo;

    public void addJob(JobPost jobPost) {
        repo.addJob(jobPost); // 데이터 저장
    }

    public List<JobPost> getAllJobs() {
        return repo.getAllJobs(); // 전체 데이터 조회
    }

    public JobPost getJob(int postId) {
        return repo.getJob(postId); // 특정 데이터 조회
    }

    public void updateJob(JobPost jobPost) {
        repo.updateJob(jobPost); // Repository에 수정 요청 전달
    }

    public void deleteJob(int postId) {
        repo.deleteJob(postId); // Repository에 삭제 요청 전달
    }
}