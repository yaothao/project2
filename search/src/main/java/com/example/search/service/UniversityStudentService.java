package com.example.search.service;

import com.example.search.entity.UniversityStudent;
import org.springframework.stereotype.Service;

@Service
public interface UniversityStudentService {

    UniversityStudent getUniversityStudentInfoByName(Integer id, String name);

    UniversityStudent getUniversityStudentInfoByCountry(Integer id, String country);
}
