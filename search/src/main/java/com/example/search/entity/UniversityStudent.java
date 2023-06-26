package com.example.search.entity;

import com.example.student.dto.StudentDTO;
import com.example.university.entity.University;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UniversityStudent {
    private StudentDTO student;
    private List<University> universities;
}
