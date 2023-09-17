package com.example.class_studen.service;

import com.example.class_studen.dto.*;
import com.example.class_studen.model.Role;
import com.example.class_studen.model.User;
import com.example.class_studen.model.UserAccess;
import com.example.class_studen.model.UserRefresh;
import com.example.class_studen.repository.USerRepository;
import com.example.class_studen.repository.UserAccessRepository;
import com.example.class_studen.repository.UserRefreshRepository;
import com.example.class_studen.security.JwtUtils;
import com.example.class_studen.service.mapper.UserMapper;
import com.example.class_studen.service.validation.USerValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRefreshRepository userRefreshRepository;
    private final UserAccessRepository userAccessRepository;
    private final USerRepository uSerRepository;
    private final USerValidation uSerValidation;
    private final UserMapper userMapper;
    private final JwtUtils jwtUtils;

    public ResponseDto<UserDto> register(UserDto userDto) {
        List<ErrorsDto> errors = this.uSerValidation.validate(userDto);
        if (errors.isEmpty()) {
            return ResponseDto.<UserDto>builder()
                    .code(-3)
                    .message("Validation error")
                    .errors(errors)
                    .build();
        }
        User user = this.userMapper.toEntity(userDto);
        user.setCreatedAt(LocalDateTime.now());
        //user.setRole(Role.ADMIN);
        this.uSerRepository.save(user);
        return ResponseDto.<UserDto>builder()
                .success(true)
                .message("Ok")
                .data(this.userMapper.toDto(user))
                .build();
    }

    public ResponseDto<TokenResponseDto> registerConfirm(RegisterConfirmDto dto) {
        Optional<User> optional = this.uSerRepository.findByUsernameAndDeletedAtIsNull(dto.getUsername());
        if (optional.isEmpty()) {
            return ResponseDto.<TokenResponseDto>builder()
                    .code(-1)
                    .message("username is not found")
                    .build();
        }
        User user = optional.get();
        user.setEnabled(true);
        User auth = this.uSerRepository.save(user);
        String token = toJsonByUser(auth);

        this.userAccessRepository.findById(token)
                .ifPresent(this.userAccessRepository::delete);

        this.userRefreshRepository.findById(token)
                .ifPresent(this.userRefreshRepository::delete);

        String jwtToken = this.jwtUtils.generateToken(token);
        UserDto userDto = this.userMapper.toDto(auth);

        this.userAccessRepository.save(new UserAccess(
                token, userDto
        ));

        this.userRefreshRepository.save(new UserRefresh(
                token, userDto
        ));
        return ResponseDto.<TokenResponseDto>builder()
                .success(true)
                .message("Ok")
                .data(TokenResponseDto.builder()
                        .accessToken(jwtToken)
                        .refreshToken(jwtToken)
                        .build())
                .build();
    }

    public ResponseDto<TokenResponseDto> login(LoginDto loginDto) {
        Optional<User> optional = this.uSerRepository.findByUsernameAndEnabledIsTrueAndDeletedAtIsNull(loginDto.getUsername());
        if (optional.isEmpty()) {
            return ResponseDto.<TokenResponseDto>builder()
                    .code(-1)
                    .message("username is not found")
                    .build();
        }
        var user = optional.get();
        String token = toJsonByUser(user);

        this.userAccessRepository.findById(token)
                .ifPresent(this.userAccessRepository::delete);

        this.userRefreshRepository.findById(token)
                .ifPresent(this.userRefreshRepository::delete);

        String jwtToken = this.jwtUtils.generateToken(token);
        UserDto dto = this.userMapper.toDto(user);

        this.userRefreshRepository.save(new UserRefresh(
                token, dto
        ));

        this.userAccessRepository.save(new UserAccess(
                token, dto
        ));

        return ResponseDto.<TokenResponseDto>builder()
                .success(true)
                .message("Ok")
                .data(TokenResponseDto.builder()
                        .accessToken(jwtToken)
                        .refreshToken(jwtToken)
                        .build())
                .build();

    }

    public ResponseDto<TokenResponseDto> logOut(LoginDto loginDto) {
        Optional<User> optional = this.uSerRepository.findByUsernameAndEnabledIsTrueAndDeletedAtIsNull(loginDto.getUsername());
        if (optional.isEmpty()) {
            return ResponseDto.<TokenResponseDto>builder()
                    .code(-1)
                    .message("username is not found")
                    .build();
        }
        User user = optional.get();
        user.setEnabled(false);
        this.uSerRepository.save(user);

        return ResponseDto.<TokenResponseDto>builder()
                .success(true)
                .message("Ok")
                .build();
    }

    public ResponseDto<TokenResponseDto> refreshToken(String token) {
        if (!jwtUtils.isValid(token)) return ResponseDto.<TokenResponseDto>builder()
                .code(-3)
                .message("Token is not valid")
                .build();
        return this.userRefreshRepository.findById(token)
                .map(userRefresh -> {

                    this.userAccessRepository.findById(token)
                            .ifPresent(this.userAccessRepository::delete);
                    this.userRefreshRepository.findById(token)
                            .ifPresent(this.userRefreshRepository::delete);

                    UserDto userDto = userRefresh.getDto();
                    User user = this.userMapper.toEntity(userDto);
                    user.setEnabled(true);
                    String newToken = toJsonByUser(user);

                    this.userAccessRepository.save(new UserAccess(
                            newToken, this.userMapper.toDto(user)
                    ));
                    this.userRefreshRepository.save(new UserRefresh(
                            token, this.userMapper.toDto(user)
                    ));
                    String jwtToken = this.jwtUtils.generateToken(newToken);

                    return ResponseDto.<TokenResponseDto>builder()
                            .success(true)
                            .message("Ok")
                            .data(TokenResponseDto.builder()
                                    .accessToken(jwtToken)
                                    .refreshToken(jwtToken)
                                    .build())
                            .build();


                })
                .orElse(ResponseDto.<TokenResponseDto>builder()
                        .code(-1)
                        .message("username is not found")
                        .build());
    }

    private String toJsonByUser(User user) {
        return
                "userId-" + user.getUserId() +
                        ", firstname-'" + user.getFirstname() + '\'' +
                        ", lastname-'" + user.getLastname() + '\'' +
                        ", username-'" + user.getUsername() + '\'' +
                        "enabled-" + user.getEnabled();
    }

    @Override
    public UserDto loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.uSerRepository.findByUsernameAndEnabledIsTrueAndDeletedAtIsNull(username)
                .map(this.userMapper::toDto)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("this %s :; username is not found", username)));
    }
}
