/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ca.isimtl.myPortal.controller;

import ca.isimtl.myPortal.model.User;
import ca.isimtl.myPortal.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Nicolas
 */
@Controller
@RequestMapping("/contacts")
public class ContactsController {
    
    @Autowired
    UserService service;
    
    @RequestMapping(value = {"/", "/list"}, method = RequestMethod.GET)
    public String listContacts(ModelMap model) {
        List<User> contacts = service.getContacts();
        model.addAttribute("contacts", contacts);
        return "contacts";
    }
}
