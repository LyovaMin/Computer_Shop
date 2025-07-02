package by.lyofchik.AppSpring.Service.UserService;

import by.lyofchik.AppSpring.Model.Entities.User;

public interface ChangePassword {
    User changePassword(User user, String newPassword);
}
