package com.tourism.pfe.User;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
@CrossOrigin(origins = "*")
public class UserController {
    private final UserService userService;


    @PostMapping("/add")
    public User addUser(@RequestParam("name") String name,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("role") String role,
                        @RequestParam("profilePic") MultipartFile profilePic,
                        HttpServletRequest request) throws IOException{

        User newUser = new User();
        newUser.setName(name);
        newUser.setEmail(email);
        newUser.setPassword(password);
        newUser.setRole(Role.valueOf(role));

        if (profilePic != null) {
            byte[] profilePicBytes = profilePic.getBytes();
            System.out.println("Profile Pic Size: " + profilePicBytes.length);
            newUser.setProfilePic(profilePicBytes);
        }

        System.out.println("Profile Pic received: " + newUser.getProfilePic());

        return userService.addUser(newUser,request);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable int id){
        return userService.getUser(id);
    }

    @GetMapping("/")
    public List<User> getUsers(){
        return userService.getUsers();
    }

    @PatchMapping("/update")
    public User upadateUser(@RequestBody User user){
        userService.updateUser(user);
        return user;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id,HttpServletRequest request){
        userService.deleteUser(id,request);
    }
    @PatchMapping("/lock/{id}")
    public void lockUser(@PathVariable int id){
        userService.lockUser(id);
    }

    @PatchMapping("/unlock/{id}")
    public void unlockUser(@PathVariable int id){
        userService.unlockUser(id);
    }

    @PatchMapping("/updatePassword/{id}/{password}")
    public void updatePassword(@PathVariable int id, @PathVariable String password){
        userService.updatePassword(id, password);
    }

    @GetMapping("/profilePic/{id}")
    public byte[] getProfilePic(@PathVariable int id){
        return userService.getUser(id).getProfilePic();
    }
}