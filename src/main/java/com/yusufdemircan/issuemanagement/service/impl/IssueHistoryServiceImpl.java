package com.yusufdemircan.issuemanagement.service.impl;

import com.yusufdemircan.issuemanagement.dto.IssueDetailDto;
import com.yusufdemircan.issuemanagement.dto.IssueHistoryDto;
import com.yusufdemircan.issuemanagement.dto.IssueUpdateDto;
import com.yusufdemircan.issuemanagement.entity.Issue;
import com.yusufdemircan.issuemanagement.entity.IssueHistory;
import com.yusufdemircan.issuemanagement.repository.IssueHistoryRepository;
import com.yusufdemircan.issuemanagement.repository.IssueRepository;
import com.yusufdemircan.issuemanagement.service.IssueHistoryService;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class IssueHistoryServiceImpl implements IssueHistoryService {
    private final IssueHistoryRepository issueHistoryRepository;
    private final ModelMapper modelMapper;


    public IssueHistoryServiceImpl(IssueHistoryRepository issueHistoryRepository,ModelMapper modelMapper) {
        this.issueHistoryRepository = issueHistoryRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public IssueHistory save(IssueHistory issueHistory) {
        if (issueHistory.getDate() == null){
            throw new IllegalArgumentException("Date cannot be null");
        }

        return issueHistoryRepository.save(issueHistory);
    }
    @Override
    public List<IssueHistoryDto> getByIssueId(Long id) {
        return Arrays.asList(modelMapper.map(issueHistoryRepository.getByIssueIdOrderById(id), IssueHistoryDto[].class));
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
    public void addHistory(Long id, Issue issue) {
        IssueHistory history = new IssueHistory();
        history.setIssue(issue);
        history.setAssignee(issue.getAssignee());
        history.setDate(issue.getDate());
        history.setDescription(issue.getDescription());
        history.setDetails(issue.getDetails());
        history.setIssueStatus(issue.getIssueStatus());
        issueHistoryRepository.save(history);
    }

    @Override
    public Boolean delete(IssueHistory issueHistory) {
        return null;
    }
}
