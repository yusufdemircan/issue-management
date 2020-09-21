package com.yusufdemircan.issuemanagement.service;


import com.yusufdemircan.issuemanagement.entity.IssueHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface IssueHistoryService {

    IssueHistory save(IssueHistory issueHistory);

    IssueHistory getById(Long id);

    Page<IssueHistory> getAllPageble(Pageable pageable);

    Boolean delete(IssueHistory issueHistory);
}
