package com.yusufdemircan.issuemanagement.dto;

import com.yusufdemircan.issuemanagement.entity.IssueStatus;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Issue Update Data Transfer Object")
public class IssueUpdateDto {

    private Long id;
    @ApiModelProperty(required = true,value = "description of issue")
    private String description;
    @ApiModelProperty(required = true,value = "details of issue")
    private String details;
    @ApiModelProperty(required = true,value = "date of issue")
    private Date date;
    @ApiModelProperty(required = true,value = "issue status")
    private IssueStatus issueStatus;
    @ApiModelProperty(required = true,value = "userDto assignee")
    private Long assignee_id;
    @ApiModelProperty(required = true,value = "projectDto assignee project")
    private Long project_id;
}
