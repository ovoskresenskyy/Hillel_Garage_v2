package com.example.hillel_garage_v2.controller;

import com.example.hillel_garage_v2.model.Owner;
import com.example.hillel_garage_v2.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners")
public class OwnerController {
    private final OwnerService ownerService;

    @Autowired
    public OwnerController(OwnerService ownerService) {
        this.ownerService = ownerService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("owners", ownerService.getAll());
        return "owners/list";
    }

    @GetMapping("/add_new_form")
    public String addNewForm(Model model) {
        model.addAttribute("owner", Owner.builder().build());
        return "/owners/new";
    }

    @PostMapping
    public String saveOwner(@ModelAttribute("owner") Owner owner) {
        ownerService.addNew(owner);
        return "redirect:/owners";
    }

    @GetMapping("/update_form/{id}")
    public String updateForm(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("owner", ownerService.findById(id));
        return "owners/update";
    }

    @PostMapping("/update")
    public String updateOwner(@ModelAttribute("owner") Owner owner) {
        ownerService.update(owner);
        return "redirect:/owners";
    }

    @GetMapping("/delete/{id}")
    public String deleteOwner(@PathVariable(value = "id") int id) {
        ownerService.delete(id);
        return "redirect:/owners";
    }
}
