package com.java_html.mvc.controllers;

import com.java_html.mvc.models.Car;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Controller
public class MainController {
    private List<Car> cars = new ArrayList<>();

    public MainController() {
        cars.add(new Car(1, "Tesla"));
        cars.add(new Car(2, "Range Rover"));
        cars.add(new Car(3, "Bentley"));
        cars.add(new Car(4, "Bugatti"));
        cars.add(new Car(5, "Mercedes-Benz"));
        cars.add(new Car(6, "Lamborghini"));
    }

    @GetMapping("/")
    public String home(Model model) {
        HashMap<String, String> map = new HashMap<>();
        map.put("hello", "Hello, Model-View-Controller");
        map.put("description", "Cars page");

        model.addAllAttributes(map);
        return "index.html";
    }

    @GetMapping("/cars")
    public String carsPage(Model model) {
        model.addAttribute("cars", cars);
        return "cars_page.html";
    }

    @GetMapping("/car")
    public String carPage(@RequestParam("id") int id, Model model) {
        model.addAttribute("id", id);
        return "car.html";
    }

    @GetMapping("/car/{id}")
    public String carPageById(@PathVariable int id, Model model) {
        model.addAttribute("id", id);
        return "car.html";
    }

    @PostMapping("/car")
    public String saveCar(@RequestParam int id, @RequestParam String name, RedirectAttributes redirectAttributes) {
        cars.add(new Car(id, name));
//        model.addAttribute("cars", cars);

        redirectAttributes.addFlashAttribute("cars", cars);

        return "redirect:/cars";
    }
}
