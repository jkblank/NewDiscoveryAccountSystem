package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.UserAccountDto;

import javax.transaction.Transactional;

public interface ModifyUserAccountFlow {

    @Transactional

    UserAccountDto subtractCurrencyFromUserAccount(Integer transactionAmount, Long memberID, Long accountTypeID);

    @Transactional
    UserAccountDto addCurrencytoUserAccount(Integer transactionAmount, Long memberID, Long accountTypeID);

}
