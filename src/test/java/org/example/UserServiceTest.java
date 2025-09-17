package org.example;

import BDDMokitoPractice.UserRepository;
import BDDMokitoPractice.UserService;
import BDDMokitoPractice.UserUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserService service;


    @Test
    void testStaticMethodValidId() {

        // Mock static method
        try (MockedStatic<UserUtils> mockedStatic = mockStatic(UserUtils.class)) {
            // Given
            mockedStatic.when(() -> UserUtils.isValidId(1)).thenReturn(true);
            given(repository.findUserById(1)).willReturn("Siddhi");

            // When
            String result = service.getUserName(1);

            // Then
            assertEquals("Siddhi", result);
            then(repository).should().findUserById(1);
            mockedStatic.verify(() -> UserUtils.isValidId(1));
        }
    }

    @Test
    void testStaticMethodInvalidId() {

        try (MockedStatic<UserUtils> mockedStatic = mockStatic(UserUtils.class)) {
            // Given
            mockedStatic.when(() -> UserUtils.isValidId(-5)).thenReturn(false);

            // Then
            IllegalArgumentException ex =
                    assertThrows(IllegalArgumentException.class, () -> service.getUserName(-5));

            assertEquals("Invalid ID", ex.getMessage());

            // Verify static method was called
            mockedStatic.verify(() -> UserUtils.isValidId(-5));
            // Repository should never be called since ID is invalid
            then(repository).shouldHaveNoInteractions();
        }
    }

    @Test
    void testStaticMethodThrowsException() {
        try (MockedStatic<UserUtils> mockedStatic = mockStatic(UserUtils.class)) {
            // Given
            mockedStatic.when(() -> UserUtils.isValidId(anyInt()))
                    .thenThrow(new RuntimeException("Static failure"));

            // Then
            RuntimeException ex =
                    assertThrows(RuntimeException.class, () -> service.getUserName(1));

            assertEquals("Static failure", ex.getMessage());
            mockedStatic.verify(() -> UserUtils.isValidId(1));
            then(repository).shouldHaveNoInteractions();
        }
    }

    @Test
    void testGivenWillReturn() {

        // BDDMockito stubbing
        given(repository.findUserById(1)).willReturn("Siddhi");

        // Act
        String result = service.getUserName(1);

        // Assert
        assertEquals("Siddhi", result);
        // Verify (BDD style)
        then(repository).should().findUserById(1);
    }

    @Test
    void testGivenWillThrow() {

        // BDDMockito exception stubbing
        given(repository.findUserById(99)).willThrow(new RuntimeException("User not found"));

        RuntimeException ex =
                assertThrows(RuntimeException.class, () -> service.getUserName(99));

        assertEquals("User not found", ex.getMessage());

        // Verify
        then(repository).should().findUserById(99);
    }

    @Test
    void testGivenWillAnswer() {

        // Dynamic mocking using willAnswer
        given(repository.findUserById(anyInt()))
                .willAnswer(invocation -> "User-" + invocation.getArgument(0));

        // Act
        String result1 = service.getUserName(10);
        String result2 = service.getUserName(20);

        // Assert
        assertEquals("User-10", result1);
        assertEquals("User-20", result2);

        // Verify call count
        then(repository).should(times(2)).findUserById(anyInt());
    }

    @Test
    void testDeleteUserWithVerify() {

        // Act
        service.removeUser(5);

        // Verify with BDD style
        then(repository).should().deleteUser(5);
        then(repository).shouldHaveNoMoreInteractions();
    }
}

