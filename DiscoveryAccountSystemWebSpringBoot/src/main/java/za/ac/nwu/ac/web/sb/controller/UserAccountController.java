package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateUserAccountFlow;

@RestController
@RequestMapping("user-account")
public class UserAccountController {
    private final CreateUserAccountFlow createUserAccountFlow;

    @Autowired
    public UserAccountController(CreateUserAccountFlow createUserAccountFlow) {
        this.createUserAccountFlow = createUserAccountFlow;
    }

    @PostMapping("")
    @ApiOperation(value = "Create a new UserAccount of a specific AccountType")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account Type Successfully Created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<UserAccountDto>> create(
            @ApiParam(value = "Request body to create a new User Account", required = true)
            @RequestBody UserAccountDto userAccount ){
        UserAccountDto userAccountResponse = createUserAccountFlow.create(userAccount);
        GeneralResponse<UserAccountDto> response = new GeneralResponse<>(true, userAccountResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
