package dev.codexo.app.srv.serverdrivenui.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/imeterrecorder")
public class IMeterRecorderController {



    public IMeterRecorderController() {

    }


    // ping
    @RequestMapping("/ping")
    public ResponseEntity<@NonNull String> ping() {
        return ResponseEntity.ok("IMeterRecorderController is alive");
    }
}
