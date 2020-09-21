package com.yusufdemircan.issuemanagement.repository;

import com.yusufdemircan.issuemanagement.entity.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueRepository extends JpaRepository<Issue,Long> {
}
