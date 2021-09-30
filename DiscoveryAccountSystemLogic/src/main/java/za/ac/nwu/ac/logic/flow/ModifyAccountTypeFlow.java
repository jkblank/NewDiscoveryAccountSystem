package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTypeDto;

public interface ModifyAccountTypeFlow {
     AccountTypeDto deleteAccountTypeByMnemonic(String mnemonic);

     AccountTypeDto updateAccountType(AccountTypeDto accountType);
}
