package com.ys.auction.service.users;

import com.ys.auction.domain.users.UsersRepository;
import com.ys.auction.dto.users.UsersSaveRequestDto;
import com.ys.auction.exception.user.DuplicateEmailException;
import com.ys.auction.exception.user.DuplicateNicknameException;
import com.ys.auction.exception.user.WrongConfirmPasswordException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UsersService {

    private final UsersRepository usersRepository;
    // 이메일 유효검증
    public boolean checkEmailUnique(String email) {
        return !usersRepository.existsByEmail(email);
    }
    // 닉네임 유효검증
    public boolean checkNicknameUnique(String nickname) {
        return !usersRepository.existsByNickname(nickname);
    }
    // 패스워드 유효검증
    public boolean checkPasswordSame(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

    @Transactional
    public void save(UsersSaveRequestDto requestDto) {
        if (!checkEmailUnique(requestDto.getEmail()))
            throw new DuplicateEmailException();
        if (!checkNicknameUnique(requestDto.getNickname()))
            throw new DuplicateNicknameException();
        if (!checkPasswordSame(requestDto.getPassword(), requestDto.getConfirmPassword()))
            throw new WrongConfirmPasswordException();
        usersRepository.save(requestDto.toEntity());
    }
}