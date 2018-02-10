//package pl.wrryy.amelco.utils;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
//import org.springframework.stereotype.Component;
//import pl.wrryy.amelco.entity.User;
//import pl.wrryy.amelco.service.UserService;
//@Component
//public class LoggedUser {
//    private static UserService userService;
//
//    public static User getLoggedUser(){
//        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        User loggedUser = userService.findByUserName(auth.getName());
//        return loggedUser;
//    }
//}
