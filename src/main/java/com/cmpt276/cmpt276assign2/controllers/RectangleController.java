package com.cmpt276.cmpt276assign2.controllers;


import com.cmpt276.cmpt276assign2.models.Rectangle;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
public class RectangleController {
    @GetMapping("/Rectangle/viewAllRectangles")
    public String getRectangle(Model model) {
        // database call
        List<Rectangle> rectangles = new ArrayList<>();
        rectangles.add(new Rectangle("Rectangle 1", 10, 20, "#388E3C", "#B22222", "Solid"));
        rectangles.add(new Rectangle("Rectangle 2", 20, 30, "#FFC107", "#FF1493", "Dotted"));
        rectangles.add(new Rectangle("Rectangle 3", 30, 40, "#FFC107", "#FF1493", "Dashed"));
        rectangles.add(new Rectangle("Rectangle 4", 40, 50, "#FFC107", "#FF1493", "Solid"));

        model.addAttribute("rectangles", rectangles);
        return "allRectangles.html";
    }
}
