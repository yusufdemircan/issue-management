package com.yusufdemircan.issuemanagement.service.impl;

import com.yusufdemircan.issuemanagement.entity.IssueHistory;
import com.yusufdemircan.issuemanagement.repository.IssueHistoryRepository;
import com.yusufdemircan.issuemanagement.service.IssueHistoryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IssueHistoryServiceImpl implements IssueHistoryService {
    private final IssueHistoryRepository issueHistoryRepository;

    public IssueHistoryServiceImpl(IssueHistoryRepository issueHistoryRepository) {
        this.issueHistoryRepository = issueHistoryRepository;
    }

    @Override
    public IssueHistory save(IssueHistory issueHistory) {
        if (issueHistory.getDate() == null){
            throw new IllegalArgumentException("Date cannot be null");
        }

        return issueHistoryRepository.save(issueHistory);
    }

    @Override
    public IssueHistory getById(Long id) {
        return null;
    }

    @Override
    public Page<IssueHistory> getAllPageble(Pageable pageable) {
        return null;
    }

    @Override
    public Boolean delete(IssueHistory issueHistory) {
        return null;
    }
}
