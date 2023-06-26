package com.example.search.controller;

import com.example.search.service.UniversityStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class SearchController {
    private final UniversityStudentService universityStudentService;
    @Autowired
    public SearchController(UniversityStudentService universityStudentService) {
        this.universityStudentService = universityStudentService;
    }

    @GetMapping(value = "/weather/search/{id}", params = "name")
    public ResponseEntity<?> getDetailsWithUniversityName(@RequestParam String name, @PathVariable Integer id) {
        return new ResponseEntity<>(universityStudentService.getUniversityStudentInfoByName(id, name), HttpStatus.OK);
    }

    @GetMapping(value = "/weather/search/{id}", params = "country")
    public ResponseEntity<?> getDetailsWithUniversityCountry(@RequestParam String country, @PathVariable Integer id) {
        return new ResponseEntity<>(universityStudentService.getUniversityStudentInfoByName(id, country), HttpStatus.OK);
    }
}
