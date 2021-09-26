package za.ac.nwu.ac.translator.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import za.ac.nwu.ac.domain.dto.UserAccountDto;
import za.ac.nwu.ac.domain.persistence.UserAccount;
import za.ac.nwu.ac.repo.persistence.UserAccountRepository;
import za.ac.nwu.ac.translator.UserAccountTranslator;

@Component
public class UserAccountTranslatorImpl implements UserAccountTranslator {

    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountTranslatorImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public UserAccountDto create(UserAccountDto userAccountDto) {
        try{
            UserAccount userAccount= userAccountRepository.save(userAccountDto.getUserAccount());
            return new UserAccountDto(userAccount);
        }catch (Exception e){
            throw  new RuntimeException("Unable to save to the DB", e);
        }
    }

//    @Override
//    public UserAccountDto getUserByMemberIDandMnemonic(String memberID, String mnemonic){
//        try{
//            UserAccount userAccount = userAccountRepository.getUserByMemberIDandMnemonic(memberID, mnemonic);
//            return new UserAccountDto(userAccount);
//        }catch ( Exception e){
//            throw new RuntimeException("Unable to read from the DB", e);
//            //ToDo: New exception to be implemented here
//        }
//    }
}