package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.UserAccountDto;

public interface FetchUserAccountFlow {
    UserAccountDto getUserByMemberIDandAccountID(Long memberID, Long accountTypeID);

    UserAccountDto getUserMilesAccount(Long memberID);

//    List<UserAccountDto> getUserByMemberIDandMnemonic();

//    UserAccountDto getUserByMemberIDandMnemonic(String memberID, String mnemonic);

}
