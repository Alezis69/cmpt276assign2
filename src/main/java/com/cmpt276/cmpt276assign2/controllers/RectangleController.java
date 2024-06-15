package com.cmpt276.cmpt276assign2.controllers;


import com.cmpt276.cmpt276assign2.models.Rectangle;
import com.cmpt276.cmpt276assign2.models.RectangleRepository;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class RectangleController {

    @Autowired
    private RectangleRepository rectangleRepository;

    @GetMapping("/Rectangle/viewAllRectangles")
    public String getRectangle(Model model) {

        List<Rectangle> rectangles = rectangleRepository.findAll();

        model.addAttribute("rectangles", rectangles);
        return "allRectangles.html";
    }

    @PostMapping("/Rectangle/AddRectangle")
    public String addRectangle(@RequestParam Map<String, String> newRectangle, HttpServletResponse response) {
        System.out.println(newRectangle + "added");
        String name = newRectangle.get("name");
        int width = Integer.parseInt(newRectangle.get("width"));
        int height = Integer.parseInt(newRectangle.get("height"));
        String colour = newRectangle.get("colour");
        String borderColour = newRectangle.get("borderColour");
        String borderStyle = newRectangle.get("borderStyle");

        // Validate width and height
        width = Math.abs(width);
        height = Math.abs(height);
        if (width < 1) {
            width = 1;
        }
        if (height < 1) {
            height = 1;
        }

        // Validate hex colors
        if (!colour.matches("^#([A-Fa-f0-9]{6})$")) {
            colour = "#808080"; // Set to grey if invalid
        }
        if (!borderColour.matches("^#([A-Fa-f0-9]{6})$")) {
            borderColour = "#808080"; // Set to grey if invalid
        }

        Rectangle rectangle = new Rectangle(name, width, height, colour, borderColour, borderStyle);
        rectangleRepository.save(rectangle);
        response.setStatus(201);
        return "redirect:/Rectangle/viewAllRectangles";
    }

    @DeleteMapping("/Rectangle/DeleteRectangle/{id}")
    public ResponseEntity<Void> deleteRectangle(@PathVariable int id) {
        rectangleRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
