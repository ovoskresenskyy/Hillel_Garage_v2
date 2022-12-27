package com.example.hillel_garage_v2.controller;

import com.example.hillel_garage_v2.model.Car;
import com.example.hillel_garage_v2.model.Owner;
import com.example.hillel_garage_v2.service.CarService;
import com.example.hillel_garage_v2.service.OwnerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerService ownerService;
    private final CarService carService;

    @Autowired
    public OwnerController(OwnerService ownerService, CarService carService) {
        this.ownerService = ownerService;
        this.carService = carService;
    }

    @GetMapping
    public String getAll(Model model) {
        model.addAttribute("ownersAndCars", ownerService.getAll());
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

    @PutMapping
    public String updateOwner(@ModelAttribute("owner") Owner owner) {
        ownerService.update(owner);
        return "redirect:/owners";
    }

    @GetMapping("/delete/{id}")
    public String deleteOwner(@PathVariable(value = "id") int id) {
        ownerService.delete(id);
        return "redirect:/owners";
    }

    @GetMapping("/{id}/add_car_form")
    public String addingCarForm(@PathVariable(value = "id") int ownerID,
                             Model model) {
        model.addAttribute("owner", ownerService.findById(ownerID));
        model.addAttribute("car", Car.builder()
                .ownerID(ownerID)
                .build());
        return "owners/cars/new";
    }

    @PostMapping("/{id}/save_car")
    public String saveCar(@PathVariable(value = "id") int ownerID,
                          @ModelAttribute("car") Car car) {
        //ownerService.addCar(ownerID, car);
        return "redirect:/owners";
    }

    @GetMapping("/update_car_form/{ownerID}/{id}")
    public String ownersCarUpdateForm(@PathVariable(value = "ownerID") int ownerID,
                                     @PathVariable(value = "id") int id,
                             Model model) {
        model.addAttribute("owner", ownerService.findById(ownerID));
        model.addAttribute("car", carService.getCar(id));
        return "owners/cars/update";
    }

    @PostMapping("/update_car")
    public String updateCar(@ModelAttribute("car") Car car) {
        carService.updateCar(car);
        return "redirect:/owners";
    }

    @GetMapping("/delete_car/{ownerID}/{id}")
    public String deleteCar(@PathVariable(value = "ownerID") int ownerID,
                                 @PathVariable(value = "id") int id) {
        //ownerService.deleteCar(ownerID, id);
        return "redirect:/owners";
    }


}
