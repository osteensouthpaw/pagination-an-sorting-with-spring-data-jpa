package com.omega.paginationAndSorting.user;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api/v1/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public UserPageResponse findAllUsers(
                @RequestParam(required = false, defaultValue = "0") int page,
                @RequestParam(required = false, defaultValue = "10") int size,
                @RequestParam(required = false, defaultValue = "userId") String sortField,
                @RequestParam(required = false, defaultValue = "asc") String sortOrder
    ) {
        return userService.findAll(page, size, sortField, sortOrder);
    }

}
