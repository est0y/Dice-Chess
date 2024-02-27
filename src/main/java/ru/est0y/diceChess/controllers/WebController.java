package ru.est0y.diceChess.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@AllArgsConstructor
@Controller
@Slf4j
public class WebController {
    @GetMapping("/room/{roomId}")
    public String roomPage(@PathVariable String roomId,
                           Model model) {

        model.addAttribute("roomId",roomId);
        return "room";
    }


}
