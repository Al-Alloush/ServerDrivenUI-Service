package dev.codexo.app.srv.serverdrivenui.controller;

import dev.codexo.app.srv.serverdrivenui.model.dto.brandidentity.BrandResponseDto;
import dev.codexo.app.srv.serverdrivenui.model.dto.brandidentity.BrandUpdateDto;
import dev.codexo.app.srv.serverdrivenui.service.brandidentity.BrandIdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/projects/{projectId}/brand")
@RequiredArgsConstructor
public class BrandIdentityController {

    private final BrandIdentityService brandIdentityService;

    @GetMapping
    public ResponseEntity<BrandResponseDto> getBrandIdentity(@PathVariable String projectId) {
        return ResponseEntity.ok(brandIdentityService.getBrandByProjectId(projectId));
    }

    @PutMapping
    public ResponseEntity<BrandResponseDto> updateBrandIdentity(
            @PathVariable String projectId,
            @RequestBody BrandUpdateDto updateDto) {
        return ResponseEntity.ok(brandIdentityService.updateBrand(projectId, updateDto));
    }
}
