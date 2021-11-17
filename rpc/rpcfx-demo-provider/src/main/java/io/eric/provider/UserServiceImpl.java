package io.eric.provider;

import io.eric.api.User;
import io.eric.api.UserService;
import org.springframework.stereotype.Service;

@Service("io.com.eric.api.UserService")
public class UserServiceImpl implements UserService {

    @Override
    public User findById(int id) {
        return new User(id, "KK" + System.currentTimeMillis());
    }
}
