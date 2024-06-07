package org.nimo.aquarium.config.auth;

import lombok.RequiredArgsConstructor;
import org.nimo.aquarium.domain.user.User;
import org.nimo.aquarium.domain.user.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{

        User userEntity = userRepository.findByUsername(username);

        if(userEntity == null){
            return null;
        }
        else{
            return new PrincipalDetails(userEntity);
        }
    }
}
