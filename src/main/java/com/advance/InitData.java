package com.advance;

import com.advance.common.entity.User;
import com.advance.common.enums.UserRoleEnum;
import com.advance.user.service.UserService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class InitData {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @PostConstruct
    @Transactional
    public void init() {
        List<User> userList =
                List.of(
                        new User("김수민", passwordEncoder.encode("1234"), "user1@example.com", UserRoleEnum.ADMIN),
                        new User("장은성", passwordEncoder.encode("1234"), "user2@example.com", UserRoleEnum.NORMAL)

                );

        for (User user : userList) {
            userService.save(user);
        }
    }
}
