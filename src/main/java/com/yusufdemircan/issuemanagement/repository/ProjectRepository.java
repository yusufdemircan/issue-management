package com.yusufdemircan.issuemanagement.repository;


import com.yusufdemircan.issuemanagement.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProjectRepository extends JpaRepository<Project,Long> {
Project getByProjectCode(String projectCode);

List<Project> getByProjectCodeContains(String projectCode);

Project getByProjectCodeAndIdNot(String prjectCode,Long id);

Page<Project> findAll(Pageable pageable);

List<Project> findAll(Sort sort);


}
