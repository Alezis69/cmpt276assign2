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

    @DeleteMapping("/Rectangle/DeleteAllRectangles")
    public ResponseEntity<Void> deleteAllRectangles() {
        rectangleRepository.deleteAll();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/Rectangle/viewRectangle/{id}")
    public String viewRectangle(@PathVariable int id, Model model) {
        Rectangle rectangle = rectangleRepository.findById(id).orElse(null);
        if (rectangle != null) {
            model.addAttribute("rectangle", rectangle);
            return "viewRectangle.html";
        } else {
            // Handle not found error
            return "redirect:/Rectangle/viewAllRectangles";
        }
    }

    @PostMapping("/Rectangle/EditRectangle/{id}")
    public String editRectangle(@PathVariable int id, @RequestParam Map<String, String> updatedRectangle, HttpServletResponse response) {
        Rectangle existingRectangle = rectangleRepository.findById(id).orElse(null);
        if (existingRectangle == null) {
            // Handle not found error or redirect as needed
            return "redirect:/Rectangle/viewAllRectangles";
        }

        String name = updatedRectangle.get("name");
        int width = Integer.parseInt(updatedRectangle.get("width"));
        int height = Integer.parseInt(updatedRectangle.get("height"));
        String colour = updatedRectangle.get("colour");
        String borderColour = updatedRectangle.get("borderColour");
        String borderStyle = updatedRectangle.get("borderStyle");

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

        // Update the existing rectangle's properties
        existingRectangle.setName(name);
        existingRectangle.setWidth(width);
        existingRectangle.setHeight(height);
        existingRectangle.setColour(colour);
        existingRectangle.setBorderColour(borderColour);
        existingRectangle.setBorderStyle(borderStyle);

        rectangleRepository.save(existingRectangle);
        response.setStatus(200); // OK status

        return "redirect:/Rectangle/viewAllRectangles";
    }

}
