package com.yusufdemircan.issuemanagement.service.impl;

import com.yusufdemircan.issuemanagement.dto.ProjectDto;
import com.yusufdemircan.issuemanagement.entity.Project;
import com.yusufdemircan.issuemanagement.entity.User;
import com.yusufdemircan.issuemanagement.repository.ProjectRepository;
import com.yusufdemircan.issuemanagement.service.ProjectService;
import com.yusufdemircan.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectRepository projectRepository;
    private final ModelMapper modelMapper;
    private final UserServiceImpl userServiceImpl;

    public ProjectServiceImpl(ProjectRepository projectRepository, ModelMapper modelMapper, UserServiceImpl userServiceImpl) {
        this.projectRepository = projectRepository;
        this.modelMapper = modelMapper;
        this.userServiceImpl = userServiceImpl;
    }

    @Override
    public ProjectDto save(ProjectDto project) {
        //BU KONTROLÜ PROJECTDTO SINIFINDA @NONNULL ANOTASYONU İLE YAPILDI.
        /*if (project.getProjectCode() == null) {
            throw new IllegalArgumentException("ProjectCode cannot be null");
        }*/

        Project projectCheck = projectRepository.getByProjectCode(project.getProjectCode());
        if (projectCheck != null) {
            throw new IllegalArgumentException("ProjectCode already Exist");
        }

        Project p = modelMapper.map(project, Project.class);
        User user = userServiceImpl.getById2(project.getManagerId());
        p.setManager(user);
        p = projectRepository.save(p);
        project.setId(p.getId());
        return project;
    }


    @Override
    public ProjectDto getById(Long id) {
        Project p = projectRepository.getOne(id);
        return modelMapper.map(p, ProjectDto.class);
    }

    @Override
    public ProjectDto update(Long id, ProjectDto projectDto) {
        Project projectDb = projectRepository.getOne(id);
        if (projectDb == null) {
            throw new IllegalArgumentException("Project Does Not Exist ID:" + id);
        }

        Project projectCheck = projectRepository.getByProjectCodeAndIdNot(projectDto.getProjectCode(), id);
        if (projectCheck != null) {
            throw new IllegalArgumentException("ProjectCode already Exist");
        }
        projectDb.setProjectCode(projectDto.getProjectCode());
        projectDb.setProjectName(projectDto.getProjectName());
        projectRepository.save(projectDb);
        return modelMapper.map(projectDb, ProjectDto.class);
    }

    @Override
    public ProjectDto getByProjectCode(String projectCode) {
        return null;
    }

    @Override
    public List<Project> getByProjectCodeContains(String projectCode) {
        return projectRepository.getByProjectCodeContains(projectCode);
    }

    @Override
    public TPage<ProjectDto> getAllPageble(Pageable pageable) {
        Page<Project> data = projectRepository.findAll(pageable);
        TPage<ProjectDto> response = new TPage<>();
        ProjectDto[] dtos = modelMapper.map(data.getContent(), ProjectDto[].class);
        response.setStat(data, Arrays.asList(dtos));
        return response;
    }

    @Override
    public Boolean delete(ProjectDto project) {

        return null;
    }

    @Override
    public Boolean delete(Long id) {
        projectRepository.deleteById(id);
        return true;
    }

    public List<ProjectDto> getAll() {
        List<Project> data = projectRepository.findAll();
        return Arrays.asList(modelMapper.map(data, ProjectDto[].class));

    }
}
