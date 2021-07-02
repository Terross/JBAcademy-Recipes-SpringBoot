package recipes.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import recipes.Entity.User;
import recipes.businessLayer.UserService;

import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/register")
    public void addUser(@Valid @RequestBody User user) {
        System.out.println(user.getEmail());
        System.out.println(user.getPassword());
        userService.addUser(user);
    }
}
