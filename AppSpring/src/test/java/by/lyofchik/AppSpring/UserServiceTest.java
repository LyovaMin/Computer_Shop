package by.lyofchik.AppSpring;

import by.lyofchik.AppSpring.Model.DTO.UserResponseDTO;
import by.lyofchik.AppSpring.Model.Entities.Role;
import by.lyofchik.AppSpring.Model.Entities.User;
import by.lyofchik.AppSpring.Repository.UserRepository;
import by.lyofchik.AppSpring.Service.UserService.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    void loadUserByUsername_ShouldReturnUserDetails_WhenUserExists() {
        // Arrange
        String username = "Lev";
        User user = User.builder()
                .userName(username)
                .password("hashed_password")
                .role(Role.ADMIN)
                .build();
        when(userRepository.findByUserName(username)).thenReturn(Optional.of(user));

        // Act
        UserDetails userDetails = userService.loadUserByUsername(username);

        // Assert
        assertNotNull(userDetails);
        assertEquals(username, userDetails.getUsername());
        assertTrue(userDetails.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ADMIN")));
    }

    @Test
    void loadUserByUsername_ShouldThrowException_WhenUserNotFound() {
        // Arrange
        when(userRepository.findByUserName("Unknown")).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("Unknown");
        });
    }

    @Test
    void delete_ShouldReturnTrue_WhenUserDeleted() {
        String name = "UserToDelete";
        doNothing().when(userRepository).deleteByUserName(name);

        boolean deleted = userService.delete(name);

        assertTrue(deleted);
        verify(userRepository).deleteByUserName(name);
    }

    @Test
    void delete_ShouldReturnFalse_OnException() {
        doThrow(new RuntimeException()).when(userRepository).deleteByUserName(anyString());

        boolean result = userService.delete("Any");

        assertFalse(result);
    }

    @Test
    void save_ShouldReturnNull_OnDatabaseError() {
        when(userRepository.save(any())).thenThrow(new RuntimeException("DB Error"));

        User result = userService.save(new User());

        assertNull(result);
    }
}