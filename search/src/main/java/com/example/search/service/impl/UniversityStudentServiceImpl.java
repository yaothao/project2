package com.example.search.service.impl;

import com.example.search.entity.UniversityStudent;
import com.example.search.service.UniversityStudentService;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

import com.example.student.dto.StudentDTO;
@Service
@Slf4j
public class UniversityStudentServiceImpl implements UniversityStudentService {
    private final RestTemplate restTemplate;
    private static Logger logger = LoggerFactory.getLogger(UniversityStudentServiceImpl.class);

    @Autowired
    public UniversityStudentServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    @Transactional
    public UniversityStudent getUniversityStudentInfoByName(Integer id, String name) {
        UniversityStudent result = new UniversityStudent();
        logger.info(String.format("Student id {%d} is being processed", id));
        CompletableFuture.allOf(
                CompletableFuture
                        .supplyAsync(() -> restTemplate.getForObject(
                                String.format("http://STUDENT/student/%d", id), StudentDTO.class)
                        )
                        .thenAccept(studentResult -> {
                            logger.info(String.format("Student out info received [%s]", studentResult.toString()));
                            result.setStudent(studentResult);
                        }),
                CompletableFuture
                        .supplyAsync(() -> restTemplate.getForObject(
                                String.format("http://UNIVERSITY/university/search?name=%s", name), ArrayList.class)
                        )
                        .thenAccept(universityResult -> result.setUniversities(universityResult))

        ).join();

        return result;
    }

    @Override
    @Transactional
    public UniversityStudent getUniversityStudentInfoByCountry(Integer id, String country) {
        UniversityStudent result = new UniversityStudent();

        CompletableFuture.allOf(
                CompletableFuture
                        .supplyAsync(() -> restTemplate.getForObject(
                                String.format("http://STUDENT/student/%d", id), StudentDTO.class)
                        )
                        .thenAccept(studentResult -> {
                            logger.info(String.format("Student out info received [%s]", studentResult.toString()));
                            result.setStudent(studentResult);
                        }),
                CompletableFuture
                        .supplyAsync(() -> restTemplate.getForObject(
                                String.format("http://UNIVERSITY/university/search?name=%s", country), ArrayList.class)
                        )
                        .thenAccept(universityResult -> result.setUniversities(universityResult))

        ).join();
        return result;
    }
}
