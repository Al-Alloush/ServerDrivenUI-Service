package dev.codexo.app.srv.serverdrivenui.service.brandidentity;

import dev.codexo.app.srv.serverdrivenui.model.dto.brandidentity.BrandResponseDto;
import dev.codexo.app.srv.serverdrivenui.model.dto.brandidentity.BrandUpdateDto;
import dev.codexo.app.srv.serverdrivenui.model.entity.BrandIdentityEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectEntity;
import dev.codexo.app.srv.serverdrivenui.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BrandIdentityService {

    private final ProjectRepository projectRepository;

    @Transactional(readOnly = true)
    public BrandResponseDto getBrandByProjectId(String projectId) {
        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found: " + projectId));

        BrandIdentityEntity brandIdentity = project.getBrandIdentity();
        if (brandIdentity == null) {
            throw new RuntimeException("Brand identity not found for project: " + projectId);
        }

        return mapToDto(brandIdentity);
    }

    @Transactional
    public BrandResponseDto updateBrand(String projectId, BrandUpdateDto updateDto) {
        ProjectEntity project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found: " + projectId));

        BrandIdentityEntity entity = project.getBrandIdentity();
        if (entity == null) {
            throw new RuntimeException("Brand identity not found for project: " + projectId);
        }

        if (updateDto.getBrandName() != null) {
            entity.setBrandName(updateDto.getBrandName());
        }
        if (updateDto.getPrimaryColor() != null) {
            entity.setPrimaryColor(updateDto.getPrimaryColor());
        }
        if (updateDto.getSecondaryColor() != null) {
            entity.setSecondaryColor(updateDto.getSecondaryColor());
        }
        if (updateDto.getTertiaryColor() != null) {
            entity.setTertiaryColor(updateDto.getTertiaryColor());
        }
        if (updateDto.getFontFamily() != null) {
            entity.setFontFamily(updateDto.getFontFamily());
        }
        if (updateDto.getLogoUrl() != null) {
            entity.setLogoUrl(updateDto.getLogoUrl());
        }
        if (updateDto.getDescription() != null) {
            entity.setDescription(updateDto.getDescription());
        }

        projectRepository.save(project);
        return mapToDto(entity);
    }

    private BrandResponseDto mapToDto(BrandIdentityEntity entity) {
        return BrandResponseDto.builder()
                .id(entity.getId())
                .brandName(entity.getBrandName())
                .primaryColor(entity.getPrimaryColor())
                .secondaryColor(entity.getSecondaryColor())
                .tertiaryColor(entity.getTertiaryColor())
                .fontFamily(entity.getFontFamily())
                .logoUrl(entity.getLogoUrl())
                .description(entity.getDescription())
                .build();
    }
}
