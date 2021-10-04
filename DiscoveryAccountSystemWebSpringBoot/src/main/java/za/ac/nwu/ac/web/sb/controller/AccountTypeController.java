package za.ac.nwu.ac.web.sb.controller;


import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import za.ac.nwu.ac.domain.dto.AccountTypeDto;
import za.ac.nwu.ac.domain.service.GeneralResponse;
import za.ac.nwu.ac.logic.flow.CreateAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.FetchAccountTypeFlow;
import za.ac.nwu.ac.logic.flow.ModifyAccountTypeFlow;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("account-type")
public class AccountTypeController {

    private final FetchAccountTypeFlow fetchAccountTypeFlow;
    private final CreateAccountTypeFlow createAccountTypeFlow;
    private final ModifyAccountTypeFlow modifyAccountTypeFlow;

    @Autowired
    public AccountTypeController(FetchAccountTypeFlow fetchAccountTypeFlow,/*Qualifier calls component name*/
                                 @Qualifier("createAccountTypeFlowName") CreateAccountTypeFlow createAccountTypeFlow, ModifyAccountTypeFlow modifyAccountTypeFlow){
        this.fetchAccountTypeFlow=fetchAccountTypeFlow;
        this.createAccountTypeFlow=createAccountTypeFlow;
        this.modifyAccountTypeFlow = modifyAccountTypeFlow;
    }

    @GetMapping("/all")
    @ApiOperation(value="Gets all the configured Account types.",notes = "Returns a list of Account Types")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account Types Returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<List<AccountTypeDto>>> getAll(){
        List<AccountTypeDto> accountTypes = fetchAccountTypeFlow.getAllAccountTypes();
        GeneralResponse<List<AccountTypeDto>> response = new GeneralResponse<>(true, accountTypes);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("")
    @ApiOperation(value="Creates a new Account type.",notes = "Creates a new Account type in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account Type Successfully Created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})
    public ResponseEntity<GeneralResponse<AccountTypeDto>> create(
            @ApiParam(value = "Request body to create a new AccountType",
                    required = true)
            @RequestBody AccountTypeDto accountType){
        AccountTypeDto accountTypeResponse = createAccountTypeFlow.create(accountType);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountTypeResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("{mnemonic}")
    @ApiOperation(value = "Fetches the specified AccountType.",notes = "Fetches the AccountType correstponding to the given mnemonic.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found"),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTypeDto>> getAccountTypeByMnemonic(
            @ApiParam(value = "The mnemonic that uniquely identifies the AccountType.",
            example = "MILES",
            name = "mnemonic",
            required = true)
            @PathVariable("mnemonic")final String mnemonic){
        AccountTypeDto accountType = fetchAccountTypeFlow.getAccountTypeByMnemonic(mnemonic);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    //ToDo: deleteAccountType
    @DeleteMapping("{mnemonic}")
    @ApiOperation(value = "Fetches the specified AccountType.",notes = "Deletes the AccountType correstponding to the given mnemonic.")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Goal Found"),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Resource Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<AccountTypeDto>> deleteAccountTypeByMnemonic(
            @ApiParam(value = "The mnemonic that uniquely identifies the AccountType.",
                    example = "MILES",
                    name = "mnemonic",
                    required = true)
            @PathVariable("mnemonic")final String mnemonic){
        AccountTypeDto accountType = modifyAccountTypeFlow.deleteAccountTypeByMnemonic(mnemonic);
        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountType);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    //ToDo: updateAccountType
    @PutMapping("{mnemonic}")
    @ApiOperation(value="Updates an existing Account type.",notes = "Updates an existing Account type in the DB.")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account Type Successfully Created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)})

    public ResponseEntity<GeneralResponse<AccountTypeDto>> updateAccountType(
            @ApiParam(value = "Mnemonic that uniquely identifies the AccountType.",
                    example = "MILES",
                    name = "mnemonic",
                    required = true)
            @PathVariable("mnemonic") final String mnemonic,

            @ApiParam(value = "The new AccountTypeName that the specified AccountType should be updated with. ",
                    example = "MILES",
                    name = "newAccountTypeName",
                    required = true)
            @RequestParam(value ="newAccountTypeName") final String newAccountTypeName,

            @ApiParam(value = "The optional new creation date in ISO format. (yyy-MM-dd) \n \r If empty or null, value will not be updated ",
                    name = "newCreationDate")
            @RequestParam(value ="newCreationDate", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                LocalDate newCreationDate){

//ToDo: if date ="today
        AccountTypeDto tempDto = fetchAccountTypeFlow.getAccountTypeByMnemonic(mnemonic);
        if(null==newCreationDate){
            newCreationDate = tempDto.getCreationDate();
        }
//        else{
//            newCreationDate=LocalDate.now();
//        }
        AccountTypeDto accountType = new AccountTypeDto(mnemonic, newAccountTypeName, newCreationDate);

        AccountTypeDto accountTypeResponse = modifyAccountTypeFlow.updateAccountType(accountType);

        GeneralResponse<AccountTypeDto> response = new GeneralResponse<>(true, accountTypeResponse);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    //ToDo: updateAccountTypeWithNoOptionalDate

    //ToDo: updateAccountTypeObitMandatory()
}
