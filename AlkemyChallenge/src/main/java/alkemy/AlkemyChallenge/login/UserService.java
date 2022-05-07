package alkemy.AlkemyChallenge.login;

import alkemy.AlkemyChallenge.exceptions.EmailAlreadyInUseException;
import alkemy.AlkemyChallenge.security.CustomeUserDetailsService;
import alkemy.AlkemyChallenge.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {


    private final IUserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final CustomeUserDetailsService userDetailsService;

    private Authentication authentication(UserDto userDto){
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword()));
        } catch (AuthenticationException e) {
            throw new BadCredentialsException("Incorrect password and/or email. Please try again.");
        }
        return authentication;
    }

    private void checkMailIsAvailable(String email){
        if (userRepository.existsByEmail(email))
            throw new EmailAlreadyInUseException(email + " this mail is already in use by other user");
    }

    public void saveUser(UserDto userToSave) {

        checkMailIsAvailable(userToSave.getEmail());

        User user = new User();
        user.setEmail(userToSave.getEmail());
        user.setPassword(bCryptPasswordEncoder.encode(userToSave.getPassword()));

        userRepository.save(user);

    }

    public String logInUser(UserDto user) {

       Authentication authentication = authentication(user);

        SecurityContextHolder.getContext().setAuthentication(authentication);
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());

        return jwtUtil.generateToken(userDetails);

    }

}
