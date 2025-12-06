package dev.codexo.app.srv.serverdrivenui.imeterrecorder.controller;

import org.jspecify.annotations.NonNull;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;

@RestController
@RequestMapping("/imeterrecorder")
public class IMeterRecorderController {

    public IMeterRecorderController() {
    }

    // Health check endpoint
    @GetMapping("/ping")
    public ResponseEntity<@NonNull String> ping() {
        return ResponseEntity.ok("IMeterRecorderController is alive");
    }
    
    // API info endpoint  
    @GetMapping("/info")
    public ResponseEntity<@NonNull String> info() {
        return ResponseEntity.ok("ServerDrivenUI Service - IMeter Recorder API v1.0");
    }
}
