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
@RequestMapping("/cars")
public class CarController {

    private final OwnerService ownerService;
    private final CarService carService;

    @Autowired
    public CarController(OwnerService ownerService, CarService carService) {
        this.ownerService = ownerService;
        this.carService = carService;
    }

    @GetMapping
    public String getAllCars(Model model) {
        model.addAttribute("cars", carService.getAll());
        return "cars/list";
    }

    @GetMapping("/add_new_form")
    public String addNewCar(Model model) {
        model.addAttribute("car", Car.builder().build());
        return "/cars/new";
    }

    @PostMapping("/save")
    public String saveCar(@ModelAttribute("car") Car car) {
        carService.addCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/update_form/{id}")
    public String updateForm(@PathVariable(value = "id") int id,
                             Model model) {
        model.addAttribute("car", carService.getCar(id));
        return "cars/update";
    }

    @PostMapping("/update")
    public String updateCar(@ModelAttribute("car") Car car) {
        carService.updateCar(car);
        return "redirect:/cars";
    }

    @GetMapping("/delete/{id}")
    public String deleteCar(@PathVariable(value = "id") int id) {
        carService.deleteCar(id);
        return "redirect:/cars";
    }
}
