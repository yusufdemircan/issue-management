package com.yusufdemircan.issuemanagement.service;


import com.yusufdemircan.issuemanagement.dto.IssueHistoryDto;
import com.yusufdemircan.issuemanagement.dto.IssueUpdateDto;
import com.yusufdemircan.issuemanagement.entity.Issue;
import com.yusufdemircan.issuemanagement.entity.IssueHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface IssueHistoryService {

    IssueHistory save(IssueHistory issueHistory);

    IssueHistory getById(Long id);

    List<IssueHistoryDto> getByIssueId(Long id);

    Page<IssueHistory> getAllPageble(Pageable pageable);

    void addHistory(Long id, Issue issue);

    Boolean delete(IssueHistory issueHistory);
}
