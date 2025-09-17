package org.example;

import PracticeUsingMokito.BankAccount;
import PracticeUsingMokito.BankAccountRepo;
import PracticeUsingMokito.BankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BankServiceTest {

        @Mock
        private BankAccountRepo dao;  // dependency is mocked

        @InjectMocks
        private BankService service; // service gets mock injected automatically

        @Test
        void testDepositNegativeAmount() {
              Exception ex = assertThrows(IllegalArgumentException.class, () -> service.deposit(1, -100));
              assertEquals("Deposit amount must be greater than zero", ex.getMessage());
        }

        @Test
        void testWithdrawNegativeAmount() {
               Exception ex = assertThrows(IllegalArgumentException.class, () -> service.withdraw(1, -200));
               assertEquals("Withdrawal amount must be greater than zero", ex.getMessage());
        }

        @Test
        void testDepositNormalScenario() {
            BankAccount account = new BankAccount(1, "Alice", 1000);
            when(dao.findById(1)).thenReturn(account);

            double newBalance = service.deposit(1, 500);

            assertEquals(1500, newBalance);
            verify(dao).updateAccount(account); // interaction check
        }

        @Test
        void testWithdrawExceedsLimit() {
            BankAccount account = new BankAccount(1, "Alice", 10000);
            when(dao.findById(1)).thenReturn(account);

            Exception ex = assertThrows(IllegalArgumentException.class, () -> service.withdraw(1, 6000));
            assertEquals("Withdrawal limit exceeded (max 5000 per transaction)", ex.getMessage());
        }

        @Test
        void testWithdrawNormalScenario() {
            BankAccount account = new BankAccount(2, "Bob", 1000);
            when(dao.findById(2)).thenReturn(account);

            double newBalance = service.withdraw(2, 400);

            assertEquals(600, newBalance);
            verify(dao).updateAccount(account);
        }

        @Test
        void testWithdrawInsufficientFunds() {
            BankAccount account = new BankAccount(3, "Charlie", 200);
            when(dao.findById(3)).thenReturn(account);

            RuntimeException ex = assertThrows(RuntimeException.class,
                    () -> service.withdraw(3, 500));

            assertEquals("Insufficient funds", ex.getMessage());
        }

        @Test
        void testAccountNotFound() {
            when(dao.findById(99)).thenReturn(null);

            RuntimeException ex = assertThrows(RuntimeException.class,
                    () -> service.deposit(99, 100));

            assertEquals("Account not found", ex.getMessage());
        }
}
