package za.ac.nwu.ac.web.sb.controller;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final Logger LOGGER = LoggerFactory.getLogger(UserAccountController.class);

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
        LOGGER.info("Attempting to create new User Account with Input Value {}", userAccount);
        UserAccountDto userAccountResponse = createUserAccountFlow.create(userAccount);
        LOGGER.info("Succesfully created new User Account with Input Value {}", userAccount);
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
                    name = "memberID",
                    example = "100000000000001",
                    required = true)
            @PathVariable("memberID")final String memberID,

            @ApiParam(value = "The AccountTypeID that uniquely identifies the AccountType.",
                    name = "accountTypeID",
                    example = "100000000000003",
                    required = true)
            @PathVariable("accountTypeID") final String accountTypeID){
//ToDO: add if checks to ensure values are added
        Long memberToPass = Long.valueOf(0);
        Long accountToPass = Long.valueOf(0);

        try{
             memberToPass = Long.parseLong(memberID);
             accountToPass = Long.parseLong(accountTypeID);

        }catch(NumberFormatException e){
            LOGGER.error("Parses Failed", e);
            throw new RuntimeException("Parsing of input values failed", e);
        }

        LOGGER.info("Attempting to find User Account with properties: " +
                "\nAccountTypeID = {}" +
                "\nMemberID = {}",accountTypeID,memberID);
        UserAccountDto userAccount =fetchUserAccountFlow.getUserByMemberIDandAccountID(memberToPass , accountToPass);
        LOGGER.info("User Account with specified properties found.");
        GeneralResponse<UserAccountDto> response = new GeneralResponse<>(true, userAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
        }

    @GetMapping("Miles/{memberID}")
    @ApiOperation(value="Gets a UserAccount for specified MemberID and AccountTypeID",
            notes = "Gets a UserAccount for specified MemberID and AccountTypeID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Account Types Returned", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 404, message = "Not Found", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<UserAccountDto>> getUserMilesAccount(
            @ApiParam(value = "The MemberID that uniquely identifies the UserAccountOwner.",
                    name = "memberID",
                    example = "100000000000001",
                    required = true)
            @PathVariable("memberID")final String memberID)
            {
//ToDO: add if checks to ensure values are added
        Long memberToPass = Long.valueOf(0);
        try{
            memberToPass = Long.parseLong(memberID);
        }catch(NumberFormatException e){
            LOGGER.error("Parses Failed", e);
            throw new RuntimeException("Parsing of input values failed", e);
        }
        LOGGER.info("Attempting to find User Account with properties: " +
                "\nMemberID = {}",memberID);
        UserAccountDto userAccount =fetchUserAccountFlow.getUserMilesAccount(memberToPass);
        LOGGER.info("User Account with specified properties found.");
        GeneralResponse<UserAccountDto> response = new GeneralResponse<>(true, userAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }



        @PutMapping("Miles/subtract/{subtractTransactionValue}")
        @ApiOperation(value = "Decreases a User's Miles Account with the value of a transaction",
                notes = "")
        @ApiResponses(value = {
                @ApiResponse(code = 201, message = "Account Type Successfully Created", response = GeneralResponse.class),
                @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
                @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
        })
        public ResponseEntity<GeneralResponse<UserAccountDto>> subtractMilesFromUserAccount(
                @ApiParam(value="Transaction Value",
                        name="subtractTransactionValue",
                        example = "600",
                        required = true)
                @PathVariable("subtractTransactionValue") final String transactionValue,

                @ApiParam(value = "The MemberID that uniquely identifies the UserAccount Owner.",
                        name = "memberID",
                        example = "1000000001",
                        required = true)
                @RequestParam("memberID") final Long memberID
        ){

            Integer intToPass =0;
            try{
                intToPass =Integer.parseInt(transactionValue);
            }catch (NumberFormatException e){
                LOGGER.error("TransactionValue Parse Failed", e);
                throw new RuntimeException("Parsing of input values failed", e);
            }
            LOGGER.info("Value of TransactionValue {}",transactionValue);
            LOGGER.info("Value of MemberID {}",memberID);
            UserAccountDto userAccount = modifyUserAccountFlow.subtractMilesFromUserAccount(intToPass, memberID);
            LOGGER.info("Update Operation Completed Successfully");
            GeneralResponse<UserAccountDto> response = new GeneralResponse<>(true, userAccount);
            return new ResponseEntity<>(response, HttpStatus.OK);
        }


    @PutMapping("Miles/add/{additionTransactionValue}")
    @ApiOperation(value = "Increases a User's Miles Account with the value of a transaction",
            notes = "")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Account Type Successfully Created", response = GeneralResponse.class),
            @ApiResponse(code = 400, message = "Bad Request", response = GeneralResponse.class),
            @ApiResponse(code = 500, message = "Internal Server Error", response = GeneralResponse.class)
    })
    public ResponseEntity<GeneralResponse<UserAccountDto>> addMilesToUserAccount(
            @ApiParam(value="Transaction Value",
                    name="additionTransactionValue",
                    example = "600",
                    required = true)
            @PathVariable("additionTransactionValue") final String transactionValue,

            @ApiParam(value = "The MemberID that uniquely identifies the UserAccount Owner.",
                    name = "memberID",
                    example = "1000000001",
                    required = true)
            @RequestParam("memberID") final Long memberID
    ){

        Integer intToPass =0;
        try{
            intToPass =Integer.parseInt(transactionValue);
        }catch (NumberFormatException e){
            LOGGER.error("TransactionValue Parse Failed", e);
            throw new RuntimeException("Parsing of input values failed", e);
        }
        LOGGER.info("Value of TransactionValue {}",transactionValue);
        LOGGER.info("Value of MemberID {}",memberID);
        UserAccountDto userAccount = modifyUserAccountFlow.addMilestoUserAccount(intToPass, memberID);
        LOGGER.info("Update Operation Completed Successfully");
        GeneralResponse<UserAccountDto> response = new GeneralResponse<>(true, userAccount);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
