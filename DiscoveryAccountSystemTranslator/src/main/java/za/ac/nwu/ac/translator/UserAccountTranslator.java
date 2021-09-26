package za.ac.nwu.ac.translator;

import za.ac.nwu.ac.domain.dto.UserAccountDto;

public interface UserAccountTranslator {


//    UserAccountDto getUserByMemberIDandMnemonic(String memberid, String mnemonic);

    UserAccountDto create(UserAccountDto userAccount);
}
