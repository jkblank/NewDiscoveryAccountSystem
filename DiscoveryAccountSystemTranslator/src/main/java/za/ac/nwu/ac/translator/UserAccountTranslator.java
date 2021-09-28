package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.UserAccountDto;

public interface UserAccountTranslator {



    UserAccountDto create(UserAccountDto userAccount);

    UserAccountDto updateUserAccount(long memberID, long accountTypeID, long accountBalance);

    default UserAccountDto getUserByMemberIDandMnemonic(Long memberID, Long accountTypeID) {
        return null;
    }
}
