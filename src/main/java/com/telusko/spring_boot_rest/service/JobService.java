package com.telusko.spring_boot_rest.service;

import com.telusko.spring_boot_rest.model.JobPost;
import com.telusko.spring_boot_rest.repo.JobRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service // 비즈니스 로직 처리 계층
public class JobService {

    @Autowired // Repository 객체 자동 주입
    private JobRepo repo;

    public void addJob(JobPost jobPost) {
        repo.save(jobPost); // 데이터 저장
    }

    public List<JobPost> getAllJobs() {
        return repo.findAll(); // 전체 데이터 조회
    }

    public JobPost getJob(int postId) {
        return repo.findById(postId).orElse(new JobPost()); // 특정 데이터 조회
    }

    public void updateJob(JobPost jobPost) {
        repo.save(jobPost); // Repository에 수정 요청 전달
    }

    public void deleteJob(int postId) {
        repo.deleteById(postId); // Repository에 삭제 요청 전달
    }

    public void load() {
        // 채용 공고 리스트 (초기 데이터 포함)
        List<JobPost> jobs = new ArrayList<>(Arrays.asList(

                new JobPost(1, "Java Developer", "Must have good experience in core Java and advanced Java", 2,
                        List.of("Core Java", "J2EE", "Spring Boot", "Hibernate")),

                new JobPost(2, "Frontend Developer", "Experience in building responsive web applications using React", 3,
                        List.of("HTML", "CSS", "JavaScript", "React")),

                new JobPost(3, "Data Scientist", "Strong background in machine learning and data analysis", 4,
                        List.of("Python", "Machine Learning", "Data Analysis")),

                new JobPost(4, "Network Engineer", "Design and implement computer networks for efficient data communication", 5,
                        List.of("Networking", "Cisco", "Routing", "Switching")),

                new JobPost(5, "Mobile App Developer", "Experience in mobile app development for iOS and Android", 3,
                        List.of("iOS Development", "Android Development", "Mobile App"))
        ));

        repo.saveAll(jobs); // 리스트에 있는 모든 데이터를 한 번에 DB에 저장
    }

    public List<JobPost> search(String keyword) { // keyword를 두 필드(postProfile, postDesc)에 각각 전달해서 검색
        return repo.findByPostProfileContainingOrPostDescContaining(keyword, keyword);
    }
}