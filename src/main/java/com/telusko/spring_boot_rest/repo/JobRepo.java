package com.telusko.spring_boot_rest.repo;

import com.telusko.spring_boot_rest.model.JobPost;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository // 데이터 저장소 역할 (Spring이 관리)
public class JobRepo {

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

    // 모든 채용 공고 조회
    public List<JobPost> getAllJobs() {
        return jobs;
    }

    // 새로운 채용 공고 추가
    public void addJob(JobPost job) {
        jobs.add(job); // 리스트에 추가
        System.out.println(jobs); // 콘솔 출력 (디버깅용)
    }

    public JobPost getJob(int postId) {

        for (JobPost job : jobs) { // jobs 리스트를 하나씩 순회
            if (job.getPostId() == postId) // ID가 일치하면
                return job; // 해당 데이터 반환
        }

        return null; // 못 찾으면 null 반환
    }

    public void updateJob(JobPost jobPost) {
        for (JobPost jobPost1 : jobs) { // 리스트 전체 탐색
            if (jobPost1.getPostId() == jobPost.getPostId()) { // 같은 ID 찾기

                jobPost1.setPostProfile(jobPost.getPostProfile()); // 프로필 수정
                jobPost1.setPostDesc(jobPost.getPostDesc()); // 설명 수정
                jobPost1.setReqExperience(jobPost.getReqExperience()); // 경력 수정
                jobPost1.setPostTechStack(jobPost.getPostTechStack()); // 기술스택 수정
            }
        }
    }

    public void deleteJob(int postId) {
        for (JobPost jobPost : jobs) { // 리스트 순회
            if (jobPost.getPostId() == postId) // ID 일치 확인
                jobs.remove(jobPost); // 해당 데이터 삭제
        }
    }
}