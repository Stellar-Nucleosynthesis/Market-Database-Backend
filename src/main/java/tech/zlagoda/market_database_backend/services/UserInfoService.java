package tech.zlagoda.market_database_backend.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import tech.zlagoda.market_database_backend.pojos.UserInfo;
import tech.zlagoda.market_database_backend.repositories.UserInfoRepository;
import tech.zlagoda.market_database_backend.pojos.UserInfoDetails;


@Service
public class UserInfoService implements UserDetailsService {
    @Autowired
    UserInfoService(UserInfoRepository userInfoRepository) {
        this.userInfoRepository = userInfoRepository;

    }

    private final UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo info = userInfoRepository.getUserInfo(username);
        if(info == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return new UserInfoDetails(info);
    }
}
