package com.example.passwordmanager.controller;

import com.example.passwordmanager.model.Login;
import com.example.passwordmanager.model.LoginRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.random.RandomGenerator;

@RestController
public class LoginController {

    private final LoginRepository loginRepository;

    public LoginController(final LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    @GetMapping("/logins")
    public Iterable<Login> getAllPasswords() {
        return this.loginRepository.findAll();
    }

    @GetMapping("/logins/{id}")
    public Optional<Login> getLoginById(@PathVariable("id") Integer id) {
        return this.loginRepository.findById(id);
    }

    @PostMapping("/logins")
    public Login createNewLogin(@RequestBody Login login) {

        if(login.getPassword() != null) {
            login.setPasswordLastUpdated(String.valueOf(LocalDateTime.now()));
        }
        login.setCreateDate(String.valueOf(LocalDateTime.now()));
        loginRepository.save(login);
        return login;
    }

    @PutMapping("/logins/{id}")
    public Login updatePlant(@PathVariable("id") Integer id, @RequestBody Login login) {
        Optional<Login> loginToUpdateOptional = this.loginRepository.findById(id);
        if (!loginToUpdateOptional.isPresent()) {
            return null;
        }
        Login loginToUpdate = loginToUpdateOptional.get();
        if (login.getUsername() != null) {
            loginToUpdate.setUsername(login.getUsername());
        }
        if (login.getPassword() != null) {
            loginToUpdate.setPassword(login.getPassword());
            loginToUpdate.setPasswordLastUpdated(String.valueOf(LocalDateTime.now()));
        }
        if (login.getNotes() != null) {
            loginToUpdate.setNotes(login.getNotes());
        }
        if (login.getWebsite() != null) {
            loginToUpdate.setWebsite(login.getWebsite());
        }
        Login updatedLogin = this.loginRepository.save(loginToUpdate);
        return updatedLogin;
    }

    @DeleteMapping("/logins/{id}")
    public Login deleteLogin(@PathVariable("id") Integer id) {
        Login result = null;
        Optional<Login> loginToDeleteOptional = this.loginRepository.findById(id);
        if (loginToDeleteOptional.isPresent()) {
            Login loginToDelete = loginToDeleteOptional.get();
            this.loginRepository.delete(loginToDelete);
            result = loginToDelete;
        }
        return result;
    }

    @GetMapping("/generatePassword")
    public String generatePassword() {
        String password = RandomStringUtils.randomAscii(12);
        return password;
    }

}
