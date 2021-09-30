package za.ac.nwu.ac.repo.persistence;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import za.ac.nwu.ac.domain.persistence.AccountType;
import za.ac.nwu.ac.repo.config.RepositoryTestConfig;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
@ContextConfiguration(classes = {RepositoryTestConfig.class})
public class AccountTypeRepositoryTest {

    @Autowired
    AccountTypeRepository accountTypeRepository;
    @Ignore
    @Test
    public void getAccountTypeByMnemonicNativeQuery() {
    }
    @Ignore
    @Test
    public void getAccountTypeByMnemonic() {
    }

    @Ignore
    @Test
    public void getAccountTypeDTOByMnemonic() {
        AccountType miles = accountTypeRepository.getAccountTypeByMnemonic("MILES");
        assertNotNull(miles);
        assertEquals("MILES",miles.getMnemonic());
    }

    @Ignore
    @Test
    public void createSavePoint() {
    }

    @Ignore
    @Test
    public void commitDB() {
    }

    @Ignore
    @Test
    public void rollbackDB() {
    }
}