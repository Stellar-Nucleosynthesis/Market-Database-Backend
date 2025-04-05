package tech.zlagoda.market_database_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tech.zlagoda.market_database_backend.pojos.UserInfo;
import tech.zlagoda.market_database_backend.repositories.UserInfoRepository;
import tech.zlagoda.market_database_backend.pojos.UserInfoDetails;

import static tech.zlagoda.market_database_backend.validators.UserInfoValidator.validate;


@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    UserInfoService(UserInfoRepository repository, PasswordEncoder encoder) {
        this.repository = repository;
        this.encoder = encoder;
    }

    private final UserInfoRepository repository;
    private final PasswordEncoder encoder;

    public String addUserInfo(UserInfo userInfo) {
        validate(userInfo);
        userInfo.setPassword(encoder.encode(userInfo.getPassword()));
        repository.addUserInfo(userInfo);
        return userInfo.getUsername();
    }

    public String deleteUserInfo(String username){
        repository.deleteUserInfo(username);
        return username;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo info = repository.getUserInfo(username);
        if(info == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserInfoDetails(info);
    }
}
