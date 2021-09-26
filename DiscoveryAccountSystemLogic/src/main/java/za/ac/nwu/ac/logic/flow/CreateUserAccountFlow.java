package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.UserAccountDto;

public interface CreateUserAccountFlow {
    UserAccountDto create(UserAccountDto userAccount);
}
