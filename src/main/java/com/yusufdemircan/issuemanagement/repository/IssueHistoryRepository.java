package com.yusufdemircan.issuemanagement.repository;

import com.yusufdemircan.issuemanagement.entity.IssueHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IssueHistoryRepository extends JpaRepository<IssueHistory,Long> {

}
