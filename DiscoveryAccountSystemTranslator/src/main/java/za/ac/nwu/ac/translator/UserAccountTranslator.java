package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.UserAccountDto;

public interface UserAccountTranslator {



    UserAccountDto create(UserAccountDto userAccount);

    UserAccountDto updateUserAccount(Integer transactionAmount, Long memberID, Long accountTypeID);

    UserAccountDto getUserByMemberIDandAccountTypeID(Long memberID, Long accountTypeID);

    UserAccountDto getUserMilesAccount(Long memberID);
}
