package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.UserAccountDto;

public interface ModifyUserAccountFlow {
    UserAccountDto updateUserAccount(long memberID, long accountTypeID, long accountBalance);
    //ToDO: make this work

}
