package com.yusufdemircan.issuemanagement.service;

import com.yusufdemircan.issuemanagement.dto.IssueDto;
import com.yusufdemircan.issuemanagement.entity.Issue;

import com.yusufdemircan.issuemanagement.util.TPage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IssueService {

    IssueDto save(IssueDto issue);

    IssueDto getById(Long id);

    TPage<IssueDto> getAllPageble(Pageable pageable);


    IssueDto update(Long id, IssueDto project);

    Boolean delete(Long id);
}
