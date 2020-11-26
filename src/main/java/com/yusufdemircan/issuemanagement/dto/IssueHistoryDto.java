package com.yusufdemircan.issuemanagement.dto;

import com.yusufdemircan.issuemanagement.entity.IssueStatus;
import com.yusufdemircan.issuemanagement.dto.IssueDto;
import com.yusufdemircan.issuemanagement.dto.UserDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;

/**
 * Created by yusufdemircan on 4.10.2020.
 */
@Data
@ApiModel(value = "Issue History Data Transfer Object")
public class IssueHistoryDto {
    @ApiModelProperty(required = true,value = "ID")
    private Long id;
    @ApiModelProperty(required = true,value = "Issue")
    private IssueDto issue;
    @ApiModelProperty(required = true,value = "Descriptnon")
    private String description;
    @ApiModelProperty(required = true,value = "Date")
    private Date date;
    @ApiModelProperty(required = true,value = "Issue Status")
    private IssueStatus issueStatus;
    @ApiModelProperty(required = true,value = "Details")
    private String details;
    @ApiModelProperty(required = true,value = "Assignee")
    private UserDto assignee;
}