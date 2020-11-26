package com.yusufdemircan.issuemanagement.api;





import com.yusufdemircan.issuemanagement.dto.UserDto;
import com.yusufdemircan.issuemanagement.service.impl.UserServiceImpl;
import com.yusufdemircan.issuemanagement.util.ApiPaths;
import com.yusufdemircan.issuemanagement.util.TPage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(ApiPaths.UserCtrl.CTRL)
@Api(value = ApiPaths.UserCtrl.CTRL,description = "User APIs")
public class UserController {

    private final UserServiceImpl userServiceImpl;

    public UserController(UserServiceImpl userServiceImpl){
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("/pagination")
    @ApiOperation(value = "Get all pageable operation",response = UserDto.class)
    public ResponseEntity<TPage<UserDto>> getAllByPagination(Pageable pageable) {
        TPage<UserDto> data = userServiceImpl.getAllPageble(pageable);
        return ResponseEntity.ok(data);
    }

    @GetMapping()
    @ApiOperation(value = "Get All By Operation", response = UserDto.class)
    public ResponseEntity<List<UserDto>> getAll() {
        List<UserDto> data = userServiceImpl.getAll();
        return ResponseEntity.ok(data);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "Get By Id Operation",response = UserDto.class)
    public ResponseEntity<UserDto> getById(@PathVariable(value = "id",required = true) Long id) {
        UserDto userDto = userServiceImpl.getById(id);
        return ResponseEntity.ok(userDto);
    }

    @PostMapping
    @ApiOperation(value = "POST Operation",response = UserDto.class)
    public ResponseEntity<UserDto> createIssue(@Valid @RequestBody UserDto userDto){

        return ResponseEntity.ok(userServiceImpl.save(userDto));

    }

    /*@PutMapping("/{id}")
    @ApiOperation(value = "PUT Operation",response = UserDto.class)
    public ResponseEntity<UserDto> updateIssue(@PathVariable(value = "id",required = true) long id,@Valid @RequestBody UserDto userDto){
        //SOLID
        return ResponseEntity.ok(userServiceImpl.update(id,userDto));
    }
*/
    @DeleteMapping("/{id}")
    @ApiOperation(value = "DELETE Operation",response = Boolean.class)
    public ResponseEntity<Boolean> deleteIssue(@PathVariable(value = "id",required = true)  long id){
        return ResponseEntity.ok(userServiceImpl.delete(id));
    }

}
