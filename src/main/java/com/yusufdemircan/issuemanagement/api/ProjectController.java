package com.yusufdemircan.issuemanagement.api;

import com.yusufdemircan.issuemanagement.dto.ProjectDto;

import com.yusufdemircan.issuemanagement.service.impl.ProjectServiceImpl;


import com.yusufdemircan.issuemanagement.util.ApiPaths;
import com.yusufdemircan.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.NonNullFields;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(ApiPaths.ProjectCtrl.CTRL)
@Api(value=ApiPaths.ProjectCtrl.CTRL,description = "Project APIs")
@Slf4j
public class ProjectController {

    private final ProjectServiceImpl projectServiceImpl;

    public ProjectController(ProjectServiceImpl projectServiceImpl){
        this.projectServiceImpl = projectServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get all pageable operation",response = ProjectDto.class)
    public ResponseEntity<TPage<ProjectDto>> getAllByPagination(Pageable pageable) {
        TPage<ProjectDto> data = projectServiceImpl.getAllPageble(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping("")
    @ApiOperation(value = "Get all  operation",response = ProjectDto.class,responseContainer = "List")
    public ResponseEntity<List<ProjectDto>> getAll() {
        List<ProjectDto> data = projectServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Get by id operation",response = ProjectDto.class)
    public ResponseEntity<ProjectDto> getById(@PathVariable(value = "id",required = true) Long id) {
        log.info("ProjectController -> GetById");
        log.debug("ProjectController -> GetById -> PARAM : "+id);
        ProjectDto projectDto = projectServiceImpl.getById(id);
        return ResponseEntity.ok(projectDto);
    }

    @PostMapping
    @ApiOperation(value = "POST operation",response = ProjectDto.class)
    public ResponseEntity<ProjectDto> createProject(@Valid @RequestBody ProjectDto projectDto){

        return ResponseEntity.ok(projectServiceImpl.save(projectDto));

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "PUT operation",response = ProjectDto.class)
    public ResponseEntity<ProjectDto> updateProject(@PathVariable(value = "id",required = true) long id,@Valid @RequestBody ProjectDto projectDto){
        //SOLID
        return ResponseEntity.ok(projectServiceImpl.update(id,projectDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete operation",response = Boolean.class)
    public ResponseEntity<Boolean> deleteProject(@PathVariable(value = "id",required = true)  long id){
        return ResponseEntity.ok(projectServiceImpl.delete(id));
    }

}
