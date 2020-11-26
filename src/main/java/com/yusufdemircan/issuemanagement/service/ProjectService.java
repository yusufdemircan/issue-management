package com.yusufdemircan.issuemanagement.service;




import com.yusufdemircan.issuemanagement.dto.ProjectDto;
import com.yusufdemircan.issuemanagement.entity.Project;
import com.yusufdemircan.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface ProjectService {

    ProjectDto save(ProjectDto project);

    ProjectDto getById(Long id);

    ProjectDto update(Long id,ProjectDto projectDto);

    ProjectDto getByProjectCode(String projectCode);

    List<Project> getByProjectCodeContains(String projectCode);

    TPage<ProjectDto> getAllPageble(Pageable pageable);

    Boolean delete(ProjectDto project);

    Boolean delete(Long id);
}
