package com.example.IS_WEB_APP.controllers;

import com.example.IS_WEB_APP.models.Person;
import com.example.IS_WEB_APP.services.interfaces.PersonService;
import com.example.IS_WEB_APP.utils.ValidationUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;


@Controller
@RequestMapping
public class PersonController {
    private final PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/")
    public String showHomePage() {
        return "redirect:/people";
    }

    @GetMapping("/people")
    public String showBeers(Model model, @RequestParam(required = false) String name) {
        //Case Insensitive search;
        //personName = personName.substring(0,1).toUpperCase() + personName.substring(1).toLowerCase();

        return listPeople(model, name, false, 0);
    }


    @GetMapping("/people/new")
    public String showNewPersonForm(Model model) {
        model.addAttribute("person", new Person());
        model.addAttribute("formURL", "/people/new");
        return "createperson";
    }

    @PostMapping("/people/new")
    public String createPerson(@ModelAttribute Person person,
                               RedirectAttributes redirectAttributes) {

        if(!validateInputParams(person, redirectAttributes)){
            return "redirect:/people/new";
        }

        displayObjProperties(person);

        try {
            personService.create(person);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("createPersonExceptionMessage", e.getMessage());
            return "redirect:/people";
        }
        redirectAttributes.addFlashAttribute("createdPersonMessage", "Person created!");
        return "redirect:/people";
    }

    @GetMapping("/people/{id}/edit")
    public String showEditPersonForm(Model model, @PathVariable int id) {
        model.addAttribute("person", personService.getPersonById(id));
        model.addAttribute("personId", id);
        model.addAttribute("formURL", "/people/"+id+"/edit");
        //return "editperson";
        return "createperson";
    }

    @PostMapping("/people/{id}/edit")
    public String editPerson(@PathVariable int id, @ModelAttribute Person person,
                            RedirectAttributes redirectAttributes) {

        if(!validateInputParams(person, redirectAttributes)){
            return "redirect:/people/"+id+"/edit";
        }

        displayObjProperties(person);

        try {
            personService.update(id, person);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("editPersonExceptionMessage", e.getMessage());
            return "redirect:/people";
        }
        redirectAttributes.addFlashAttribute("updatedPersonMessage", "Person updated!");
        return "redirect:/people";
    }

    @GetMapping("/people/{id}/delete")
    public String showDeleteQuestionForm(Model model,
                                         @PathVariable("id") int id,
                                         @RequestParam(required = false) String name) {

        return listPeople(model, name, true, id);
    }

    @RequestMapping(value="/people/{id}/delete", method={RequestMethod.POST})
    public String deletePerson(@PathVariable int id, RedirectAttributes redirectAttributes) {
        try {
            personService.delete(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("deletePersonExceptionMessage", e.getMessage());
            return "redirect:/people";
        }
        redirectAttributes.addFlashAttribute("deletedPersonMessage", "Person deleted!");
        return "redirect:/people";
    }

    private boolean validateInputParams(Person person,
                                    RedirectAttributes redirectAttributes){
        if(person.getFullName().isEmpty() || person.getEmail().getEmailType().isEmpty()
                || person.getAddress().getAddressType().isEmpty()){
            String message = "Missing name, email type or address type.";
            redirectAttributes.addFlashAttribute("missingInputParamsExceptionMessage", message);
            return false;
        }

        if(!ValidationUtil.isCyrillicOrLatin(person.getFullName()) || person.getFullName().length()>90){
            String message = "Invalid name. Only Cyrillic, Latin, - and _ are allowed. The length must be less than 91 symbols.";
            redirectAttributes.addFlashAttribute("invalidNameExceptionMessage", message);
            return false;
        }

        if(!ValidationUtil.isValidEmailAddress(person.getEmail().getEmail()) || person.getEmail().getEmail().length()>40){
            String message = "Invalid email. The length must be less than 41 symbols.";
            redirectAttributes.addFlashAttribute("invalidEmailExceptionMessage", message);
            return false;
        }

        if(person.getEmail().getEmailType().length()>5){
            String message = "Invalid email type. The length must be less than 6 symbols.";
            redirectAttributes.addFlashAttribute("invalidEmailTypeExceptionMessage", message);
            return false;
        }

        if(person.getAddress().getAddressType().length()>5){
            String message = "Invalid address type. The length must be less than 6 symbols.";
            redirectAttributes.addFlashAttribute("invalidAddressTypeExceptionMessage", message);
            return false;
        }

        if(person.getAddress().getAddressInfo().length()>300){
            String message = "Invalid address type. The length must be less than 301 symbols.";
            redirectAttributes.addFlashAttribute("invalidAddressInfoExceptionMessage", message);
            return false;
        }

        if(!ValidationUtil.isDigit(person.getPin()) || person.getPin().length()!=10){
            String message = "Invalid PIN. PIN must contain exactly 10 digits.";
            redirectAttributes.addFlashAttribute("invalidPersonPINExceptionMessage", message);
            return false;
        }

        return true;
    }

    private String listPeople(Model model, String name, boolean isDeleteQuestionActivated, int id){
        List<Person> people;
        if (name != null) {
            people = personService.getPersonByName(name);
        } else {
            people = personService.getAll(); }

        model.addAttribute("people", people);
        model.addAttribute("isDeleteQuestionActivated", isDeleteQuestionActivated);
        if(id!=0){
            model.addAttribute("id", id);
        }
        return "home";
    }

    private void displayObjProperties(Person person){
        ObjectMapper mapper = new ObjectMapper();
        try{
            System.out.println(mapper.writeValueAsString(person));
        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
