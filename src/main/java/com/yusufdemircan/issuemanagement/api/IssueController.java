package com.yusufdemircan.issuemanagement.api;


import com.yusufdemircan.issuemanagement.dto.IssueDetailDto;
import com.yusufdemircan.issuemanagement.dto.IssueDto;
import com.yusufdemircan.issuemanagement.dto.IssueUpdateDto;
import com.yusufdemircan.issuemanagement.entity.Issue;
import com.yusufdemircan.issuemanagement.entity.IssueStatus;
import com.yusufdemircan.issuemanagement.service.impl.IssueServiceImpl;
import com.yusufdemircan.issuemanagement.util.ApiPaths;
import com.yusufdemircan.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(ApiPaths.IssueCtrl.CTRL)
@Api(value = ApiPaths.IssueCtrl.CTRL,description = "Issue APIs")
public class IssueController {

    private final IssueServiceImpl issueServiceimpl;

    public IssueController(IssueServiceImpl issueServiceimpl){
        this.issueServiceimpl = issueServiceimpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Pagination Operation",response = IssueDto.class)
    public ResponseEntity <TPage<IssueDto>> getAll(Pageable pageable) {
        TPage<IssueDto> issueDto = issueServiceimpl.getAllPageble(pageable);
        return ResponseEntity.ok(issueDto);
    }

    @GetMapping("/statuses")
    @ApiOperation(value = "GetAll Issue status Operation",response = String.class,responseContainer = "List")
    public ResponseEntity <List<IssueStatus>> getAll() {

        return ResponseEntity.ok(Arrays.asList(IssueStatus.values()));
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation",response = IssueDto.class)
    public ResponseEntity<IssueDto> getById(@PathVariable(value = "id",required = true) Long id) {
        IssueDto issueDto = issueServiceimpl.getById(id);
        return ResponseEntity.ok(issueDto);
    }

    @GetMapping("/detail/{id}")
    @ApiOperation(value = "Get By Id Operation",response = IssueDto.class)
    public ResponseEntity<IssueDetailDto> getByIdWithDetails(@PathVariable(value = "id",required = true) Long id) {
        IssueDetailDto detailDto = issueServiceimpl.getByIdWithDetails(id);
        return ResponseEntity.ok(detailDto);
    }

    @PostMapping
    @ApiOperation(value = "POST Operation",response = IssueDto.class)
    public ResponseEntity<IssueDto> createIssue(@Valid @RequestBody IssueDto issueDto){

        return ResponseEntity.ok(issueServiceimpl.save(issueDto));

    }

    @PutMapping("/{id}")
    @ApiOperation(value = "PUT Operation",response = IssueDto.class)
    public ResponseEntity<IssueDetailDto> updateIssue(@PathVariable(value = "id",required = true) long id,@Valid @RequestBody IssueUpdateDto issueDto){
        //SOLID

        return ResponseEntity.ok(issueServiceimpl.update(id,issueDto));
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "DELETE Operation",response = Boolean.class)
    public ResponseEntity<Boolean> deleteIssue(@PathVariable(value = "id",required = true)  long id){
        return ResponseEntity.ok(issueServiceimpl.delete(id));
    }


}
