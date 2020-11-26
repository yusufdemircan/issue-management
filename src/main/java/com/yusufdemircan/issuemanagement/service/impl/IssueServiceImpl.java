package com.yusufdemircan.issuemanagement.service.impl;

import com.yusufdemircan.issuemanagement.dto.IssueDetailDto;
import com.yusufdemircan.issuemanagement.dto.IssueDto;
import com.yusufdemircan.issuemanagement.dto.IssueHistoryDto;
import com.yusufdemircan.issuemanagement.dto.IssueUpdateDto;
import com.yusufdemircan.issuemanagement.entity.Issue;
import com.yusufdemircan.issuemanagement.entity.IssueStatus;
import com.yusufdemircan.issuemanagement.entity.User;
import com.yusufdemircan.issuemanagement.repository.IssueRepository;
import com.yusufdemircan.issuemanagement.repository.ProjectRepository;
import com.yusufdemircan.issuemanagement.repository.UserRepository;
import com.yusufdemircan.issuemanagement.service.IssueHistoryService;
import com.yusufdemircan.issuemanagement.service.IssueService;
import com.yusufdemircan.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;
    private final IssueHistoryService issueHistoryService;
    private final IssueHistoryServiceImpl issueHistoryServiceImpl;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;

    public IssueServiceImpl(IssueRepository issueRepository,
                            ModelMapper modelMapper,
                            IssueHistoryService issueHistoryService,
                            IssueHistoryServiceImpl issueHistoryServiceImpl,
                            UserRepository userRepository,
                            ProjectRepository projectRepository) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
        this.issueHistoryService = issueHistoryService;
        this.issueHistoryServiceImpl = issueHistoryServiceImpl;
        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
    }


    @Override
    public IssueDto save(IssueDto issueDto) {
        issueDto.setDate(new Date());
        issueDto.setIssueStatus(IssueStatus.OPEN);
        Issue issueDb = modelMapper.map(issueDto, Issue.class);
        issueDb.setProject(projectRepository.getOne(issueDto.getProject_id()));
        issueDb = issueRepository.save(issueDb);
        issueDto.setId(issueDb.getId());
        return modelMapper.map(issueDb, IssueDto.class);
    }


    @Override
    public IssueDto getById(Long id) {
        Issue issue = issueRepository.getOne(id);
        IssueDto issueDto = modelMapper.map(issue, IssueDto.class);

        return issueDto;
    }

    public IssueDetailDto getByIdWithDetails(Long id) {
        Issue issue = issueRepository.getOne(id);
        IssueDetailDto detailDto = modelMapper.map(issue, IssueDetailDto.class);
        List<IssueHistoryDto> issueHistoryDtos = issueHistoryService.getByIssueId(issue.getId());
        detailDto.setIssueHistories(issueHistoryDtos);

        return detailDto;
    }

    @Override
    public TPage<IssueDto> getAllPageble(Pageable pageable) {
        Page<Issue> data = issueRepository.findAll(pageable);
        TPage page = new TPage<IssueDto[]>();
        IssueDto[] dtos = modelMapper.map(data.getContent(), IssueDto[].class);
        page.setStat(data, Arrays.asList(dtos));
        return page;
    }

    @Override
    public IssueDto update(Long id, IssueDto project) {
        return null;
    }

    @Transactional
    public IssueDetailDto update(Long id, IssueUpdateDto issue) {
        Issue issueDb = issueRepository.getOne(id);
        User user = userRepository.getOne(issue.getAssignee_id());
        issueHistoryService.addHistory(id, issueDb);

        issueDb.setAssignee(user);
        issueDb.setDate(issue.getDate());
        issueDb.setDescription(issue.getDescription());
        issueDb.setDetails(issue.getDetails());
        issueDb.setIssueStatus(issue.getIssueStatus());
        issueDb.setProject(projectRepository.getOne(issue.getProject_id()));
        issueRepository.save(issueDb);
        return getByIdWithDetails(id);
    }

    @Override
    public Boolean delete(Long id) {
        issueRepository.deleteById(id);
        return true;
    }


    public List<IssueDto> getAll() {
        List<Issue> data = issueRepository.findAll();

        return Arrays.asList(modelMapper.map(data, IssueDto[].class));


    }
}
