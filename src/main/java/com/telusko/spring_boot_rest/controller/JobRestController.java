package com.telusko.spring_boot_rest.controller;

import com.telusko.spring_boot_rest.model.JobPost;
import com.telusko.spring_boot_rest.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // JSON 형태로 응답하는 REST 컨트롤러
@CrossOrigin(origins = "http://localhost:3000") // React(3000)에서 요청 허용
public class JobRestController {

    @Autowired // Service 객체 자동 주입
    private JobService service;

    @GetMapping(path = "jobPosts", produces = {"application/json"}) // GET /jobPosts → 전체 목록 조회, 응답을 JSON 형태로
    public List<JobPost> getAllJobs() {
        return service.getAllJobs(); // 서비스에서 데이터 가져오기
    }

    @GetMapping("jobPost/{postId}") // GET /jobPost/1 → 특정 데이터 조회
    public JobPost getJob(@PathVariable("postId") int postId) {
        return service.getJob(postId); // ID로 데이터 조회
    }

    @PostMapping(path = "jobPost", consumes = {"application/xml"}) // POST /jobPost → 데이터 추가, XML 형태로 들어와야 처리 가능
    public JobPost addJob(@RequestBody JobPost jobPost) { // JSON → 객체 변환

        service.addJob(jobPost); // 전달받은 데이터를 저장

        return service.getJob(jobPost.getPostId()); // 저장된 데이터 다시 조회해서 반환
    }

    @PutMapping("jobPost") // PUT 요청 → 데이터 수정
    public JobPost updateJob(@RequestBody JobPost jobPost) {
        service.updateJob(jobPost); // 서비스에 수정 요청
        return service.getJob(jobPost.getPostId()); // 수정된 결과 반환
    }

    @DeleteMapping("jobPost/{postId}") // DELETE 요청
    public String deleteJob(@PathVariable int postId) {
        service.deleteJob(postId); // 삭제 요청
        return "Deleted"; // 결과 반환
    }

    @GetMapping("jobPosts/keyword/{keyword}") // URL로 keyword를 받아서 서비스 계층에 검색 요청
    public List<JobPost> searchByKeyword(@PathVariable("keyword") String keyword) {
        return service.search(keyword);
    }

    @GetMapping("load") // 초기 데이터(DB에 샘플 데이터) 넣는 API
    public String loadData() {
        service.load();
        return "success";
    }
}
