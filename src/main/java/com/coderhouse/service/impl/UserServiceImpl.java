package com.coderhouse.service.impl;
import com.coderhouse.builder.UserBuilder;
import com.coderhouse.cache.CacheClient;
import com.coderhouse.model.UserFactory;
import com.coderhouse.model.database.document.UserDocument;
import com.coderhouse.model.request.UserRequest;
import com.coderhouse.model.response.UserResponse;
import com.coderhouse.repository.UserRepository;
import com.coderhouse.security.JwtProvider;
import com.coderhouse.service.UserService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserFactory userFactory = new UserFactory();
    private final JwtProvider jwtProvider;
    private final CacheClient<String> cache;
    @Override
    public String authenticateUser(String email, String pwd) throws Exception {
        String token = cache.recover(email,String.class);
        if(token==null){
            var user = userRepository.findByEmail(email);
            if(user==null){
                throw new Exception("no se encontro usuario con ese email en la base de datos");
            }
            if (!(user.getEmail().equals(email) && BCrypt.checkpw(pwd,user.getHashedPass()))) {
                throw new Exception("El usuario o el password es inválido");
            }
            token = saveUserInCache(email);
        }
        return token;
    }

    @Override
    public UserResponse create(UserRequest request) throws Exception{
        UserDocument existingUser = this.userRepository.findByEmail(request.getEmail());
        if(existingUser!=null){
            throw new Exception("El usuario ya existe");
        }
        if(!request.getPassword().equals(request.getPassword2())){
            throw new Exception("las contraseñas no coinciden");
        }
        UserDocument userDocument = this.userFactory.createUser(request);
        return UserBuilder.entityToResponseCreate(this.userRepository.save(userDocument));

    }

    @Override
    public UserResponse update(String id, UserRequest request) {
        UserDocument userDocumentFromReq = this.userFactory.createUser(request);
        userDocumentFromReq.setId(id);
        return UserBuilder.entityToResponseCreate(this.userRepository.save(userDocumentFromReq));
    }

    private String saveUserInCache(String email) throws JsonProcessingException {
        var token = jwtProvider.getJWTToken(email);
        return cache.save(email,token);
    }
    @Override
    public List<UserResponse> searchAll() {
        return UserBuilder.entityListToResponse(this.userRepository.findAll());
    }
}
