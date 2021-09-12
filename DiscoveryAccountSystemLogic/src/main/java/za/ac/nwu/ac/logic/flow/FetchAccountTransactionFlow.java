package za.ac.nwu.ac.logic.flow;

import za.ac.nwu.ac.domain.dto.AccountTransactionDto;

import java.util.List;

public interface FetchAccountTransactionFlow {
    List<AccountTransactionDto> getAllAccountTransactions();
}
