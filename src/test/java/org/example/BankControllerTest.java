package org.example;

import PracticeUsingMokito.BankController;
import PracticeUsingMokito.BankService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BankControllerTest {

    @Mock
    private BankService service;  // mock dependency

    @InjectMocks
    private BankController controller; // controller gets service mock injected

    @Test
    void testDepositController() {
        when(service.deposit(1, 200)).thenReturn(1200.0);

        String response = controller.depositMoney(1, 200);

        assertEquals("Deposit successful! New Balance: 1200.0", response);
        verify(service).deposit(1, 200);
    }

    @Test
    void testWithdrawController() {
        when(service.withdraw(1, 100)).thenReturn(900.0);

        String response = controller.withdrawMoney(1, 100);

        assertEquals("Withdrawal successful! New Balance: 900.0", response);
        verify(service).withdraw(1, 100);
    }
}
