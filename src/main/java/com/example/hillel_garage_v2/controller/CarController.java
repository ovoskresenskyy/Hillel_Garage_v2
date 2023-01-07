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

    @GetMapping("/{owner_id}")
    public String getAllByOwnerID(@PathVariable(value = "owner_id") int owner_id, Model model) {
        model.addAttribute("owner", ownerService.findById(owner_id));
        model.addAttribute("cars", carService.getAllByOwnerID(owner_id));
        return "cars/list";
    }

    @GetMapping("/{owner_id}/registration")
    public String addNewCar(@PathVariable(value = "owner_id") int owner_id, Model model) {
        model.addAttribute("owner", ownerService.findById(owner_id));
        model.addAttribute("car", Car.builder()
                .ownerID(owner_id)
                .build());
        return "/cars/new";
    }

    @PostMapping
    public String saveCar(@ModelAttribute("car") Car car) {
        carService.addNew(car);
        return "redirect:/cars/" + car.getOwnerID();
    }

    @GetMapping("/{id}/updating")
    public String updateForm(@PathVariable(value = "id") int id, Model model) {
        Car car = carService.findById(id);
        model.addAttribute("car", car);
        model.addAttribute("owner", ownerService.findById(car.getOwnerID()));
        return "cars/update";
    }

    @PutMapping
    public String updateCar(@ModelAttribute("car") Car car) {
        carService.update(car);
        return "redirect:/cars/" + car.getOwnerID();
    }

    @DeleteMapping("/{id}")
    public String deleteCar(@PathVariable(value = "id") int id) {
        int ownerID = carService.findById(id).getOwnerID();
        carService.delete(id);
        return "redirect:/cars/" + ownerID;
    }
}
