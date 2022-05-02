package toyproject.annonymouschat.User.service;

import toyproject.annonymouschat.User.dto.UserRegistrationDto;
import toyproject.annonymouschat.User.repository.UserRepository;

public class UserService {
    private UserRepository userRepository = new UserRepository();

    public String registration(UserRegistrationDto dto) {
        return userRepository.registration(dto);
    }
}
