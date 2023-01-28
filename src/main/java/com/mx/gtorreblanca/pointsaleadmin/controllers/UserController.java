package com.mx.gtorreblanca.pointsaleadmin.controllers;

import com.mx.gtorreblanca.pointsaleadmin.exeptions.BusinessException;
import com.mx.gtorreblanca.pointsaleadmin.models.UserVO;
import com.mx.gtorreblanca.pointsaleadmin.services.UserService;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@Log4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<Object> saveUser (@RequestBody UserVO userVO) {

        try {
            userService.saveUser(userVO);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (BusinessException e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping
    public ResponseEntity<Object> getAllUser () {

        try {

            return ResponseEntity.ok().body(userService.getAllUsers());
        } catch (BusinessException e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getById (@PathVariable final Long id) {

        try {
            return ResponseEntity.ok().body(userService.getById(id));
        } catch (BusinessException e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return ResponseEntity.internalServerError().build();
        }
    }
}
