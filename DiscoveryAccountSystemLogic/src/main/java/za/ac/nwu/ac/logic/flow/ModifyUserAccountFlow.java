package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.UserAccountDto;

public interface ModifyUserAccountFlow {
    UserAccountDto updateUserAccount(Integer transactionAmount, Long memberID, Long accountTypeID);
    //ToDO: make this work

}
