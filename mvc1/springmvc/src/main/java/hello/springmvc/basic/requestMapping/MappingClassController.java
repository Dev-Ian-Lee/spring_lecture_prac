package hello.springmvc.basic.requestMapping;

import org.springframework.web.bind.annotation.*;

@RequestMapping("/mapping/users")
@RestController
public class MappingClassController {

    @GetMapping()
    public String users() {
        return "get users";
    }

    @PostMapping()
    public String addUser() {
        return "post user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable("userId") String userId) {
        return "get userId=" + userId;
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String userId) {

        return "update userId=" + userId;
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        return "delete userId=" + userId;
    }
}
