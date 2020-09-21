package com.yusufdemircan.issuemanagement.service.impl;

import com.yusufdemircan.issuemanagement.dto.IssueDto;
import com.yusufdemircan.issuemanagement.entity.Issue;
import com.yusufdemircan.issuemanagement.repository.IssueRepository;
import com.yusufdemircan.issuemanagement.service.IssueService;
import com.yusufdemircan.issuemanagement.util.TPage;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class IssueServiceImpl implements IssueService {

    private final IssueRepository issueRepository;
    private final ModelMapper modelMapper;

    public IssueServiceImpl(IssueRepository issueRepository, ModelMapper modelMapper) {
        this.issueRepository = issueRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public IssueDto save(IssueDto issueDto) {
        if (issueDto.getDate() == null) {
            throw new IllegalArgumentException("Date cannot be null");
        }
        Issue issueDb = modelMapper.map(issueDto, Issue.class);
        issueDb = issueRepository.save(issueDb);
        return modelMapper.map(issueDb,IssueDto.class);
    }

    @Override
    public IssueDto getById(Long id) {
        return null;
    }

    @Override
    public TPage<IssueDto> getAllPageble(Pageable pageable) {
        Page<Issue> data =issueRepository.findAll(pageable);
        TPage page = new TPage<IssueDto[]>();
        IssueDto[] dtos = modelMapper.map(data.getContent(),IssueDto[].class);
        page.setStat(data, Arrays.asList(dtos));
        return page;
    }

    @Override
    public Boolean delete(IssueDto issue) {
        return null;
    }
}
