package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.UserAccountDto;

public interface UserAccountTranslator {



    UserAccountDto create(UserAccountDto userAccount);

    UserAccountDto updateUserAccount(long memberID, long accountTypeID, int transactionAmount);

    UserAccountDto getUserByMemberIDandAccountTypeID(Long memberID, Long accountTypeID);
}
