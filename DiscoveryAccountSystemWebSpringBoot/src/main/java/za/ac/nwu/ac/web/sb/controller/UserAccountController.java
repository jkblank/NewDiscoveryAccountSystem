package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateUserAccountFlow;
import za.ac.nwu.ac.logic.flow.FetchUserAccountFlow;
import za.ac.nwu.ac.logic.flow.ModifyUserAccountFlow;

@RestController
@RequestMapping("user-account")
public class UserAccountController {
    private final CreateUserAccountFlow createUserAccountFlow;
    private final FetchUserAccountFlow fetchUserAccountFlow;
    private final ModifyUserAccountFlow modifyUserAccountFlow;

    @Autowired
    public UserAccountController(CreateUserAccountFlow createUserAccountFlow, FetchUserAccountFlow fetchUserAccountFlow, ModifyUserAccountFlow modifyUserAccountFlow) {
        this.createUserAccountFlow = createUserAccountFlow;
        this.fetchUserAccountFlow = fetchUserAccountFlow;
        this.modifyUserAccountFlow = modifyUserAccountFlow;
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
        //ToDO: add if checks to ensure values are added
        UserAccountDto userAccountResponse = createUserAccountFlow.create(userAccount);
        GeneralResponse<UserAccountDto> response = new GeneralResponse<>(true, userAccountResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{memberID}/{accountTypeID}")
    @ApiOperation(value="Gets a UserAccount for specified MemberID and AccountTypeID",
            notes = "Gets a UserAccount for specified MemberID and AccountTypeID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account Types Returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<UserAccountDto>> getUserByMemberIDandAccountID(
            @ApiParam(value = "The MemberID that uniquely identifies the UserAccountOwner.",
            name = "Member 1",
//            type = "Long",
            example = "100000000000001",
            required = true)
            @PathVariable("memberID")final Long memberID,

            @ApiParam(value = "The AccountTypeID that uniquely identifies the AccountType.",
                    name = "Currency AccountID",
//                    type = "Long",
                    example = "100000000000003",
                    required = true)
            @PathVariable("accountTypeID") final Long accountTypeID){
//        UserAccountDto userAccount =fetchUserAccountFlow.getUserByMemberIDandAccountID(Long.toString(memberID), Long.toString(accountTypeID));
        //ToDO: add if checks to ensure values are added
        UserAccountDto userAccount =fetchUserAccountFlow.getUserByMemberIDandAccountID(memberID , accountTypeID);
        GeneralResponse<UserAccountDto> response = new GeneralResponse<>(true, userAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);

        }

    @PutMapping("{transactionValue}")
    @ApiOperation(value = "Updates a UserAccount with the value of a transaction",
            notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account Type Successfully Created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<UserAccountDto>> updateUserAccount(
            @ApiParam(value="Transaction Value",
                    name="Value of transaction",
                    example = "600",
                    required = true)
            @PathVariable("transactionValue") final  String transactionValue,

            @ApiParam(value = "The MemberID that uniquely identifies the UserAccountOwner.",
            name = "Member 1",
            example = "1000000001")
            @RequestParam("memberID") final Long memberID,

            @ApiParam(value = "The AccountTypeID that uniquely identifies the AccountType.",
            name="Currency AccountTypeID",
            example = "1000000001",
            required = true)
            @RequestParam("accountTypeID") final Long accountTypeID
            ){
        //ToDO: add if checks to ensure values are added
        UserAccountDto userAccount = modifyUserAccountFlow.updateUserAccount(Integer.parseInt(transactionValue),memberID , accountTypeID);
        GeneralResponse<UserAccountDto> response = new GeneralResponse<>(true, userAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


}
