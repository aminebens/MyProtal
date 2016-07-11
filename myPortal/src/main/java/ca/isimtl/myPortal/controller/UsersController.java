package ca.isimtl.myPortal.controller;

import ca.isimtl.myPortal.model.User;
import ca.isimtl.myPortal.model.UserRole;
import ca.isimtl.myPortal.service.UserRoleService;
import ca.isimtl.myPortal.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/")
public class UsersController {

    @Autowired
    UserService service;
    
    @Autowired
    UserRoleService userRoleService;

    @Autowired
    MessageSource messageSource;

    /*
     * This method will list all existing users.
     */
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listUsers(ModelMap model) {
        List<User> users = service.getAllUsers();
        model.addAttribute("users", users);
        return "allusers";
    }

    /*
     * This method will provide the medium to add a new user.
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.GET)
    public String newUser(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("edit", false);
        return "registration";
    }

    /*
     * This method will be called on form submission, handling POST request for
     * saving user in database. It also validates the user input
     */
    @RequestMapping(value = {"/new"}, method = RequestMethod.POST)
    public String saveUser(@Valid User user, BindingResult result,
            ModelMap model) {

        if (result.hasErrors()) {
            return "registration";
        }

        service.saveUser(user);

        model.addAttribute("success", "User " + user.getPrenom() + " " + user.getNom() + " added successfully");
        return "success";
    }

    /*
     * This method will provide the medium to update an existing user.
     */
    @RequestMapping(value = {"/edit-{id}-user"}, method = RequestMethod.GET)
    public String editUser(@PathVariable int id, ModelMap model) {
        User user = service.findById(id);
        model.addAttribute("user", user);
        model.addAttribute("edit", true);
        return "registration";
    }
    
    /*
     * This method will be called on form submission, handling POST request for
     * updating user in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-{id}-user" }, method = RequestMethod.POST)
    public String updateEmployee(@Valid User user, BindingResult result,
            ModelMap model, @PathVariable int id) {
 
        if (result.hasErrors()) {
            return "registration";
        }
 
        service.updateUser(user);
 
        model.addAttribute("success", "User " + user.getPrenom() + " " + user.getNom() + " updated successfully");
        return "success";
    }
    
    /**
     * This method will provide UserRole list to views
     * @return 
     */
    @ModelAttribute("roles")
    public List<UserRole> initializeProfiles() {
        return userRoleService.findAll();
    }
}