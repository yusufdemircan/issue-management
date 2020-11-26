package com.yusufdemircan.issuemanagement.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Project Data Transfer Object")
public class ProjectDto {
    @ApiModelProperty(required = false,value = "id of project")
    private Long id;
    @NotNull
    @ApiModelProperty(required = true,value = "name of project")
    private String projectName;
    @NotNull
    @ApiModelProperty(required = true,value = "code of project")
    private String projectCode;

    @NotNull
    @ApiModelProperty(required = true,value = "manager of project")
    private Long managerId;

    @NotNull
    @ApiModelProperty(required = true,value = "manager project")
    private UserDto manager;

}
