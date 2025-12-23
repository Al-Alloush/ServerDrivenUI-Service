package dev.codexo.app.srv.serverdrivenui.service.multitenant;

import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.ProjectPlatformEntity;
import dev.codexo.app.srv.serverdrivenui.model.entity.multitenant.ProjectApiKeyEntity;
import dev.codexo.app.srv.serverdrivenui.repository.PlatformRepository;
import dev.codexo.app.srv.serverdrivenui.repository.ProjectApiKeyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.OffsetDateTime;

@Service
@RequiredArgsConstructor
public class ApiKeyValidatorService {

    private final ProjectApiKeyRepository apiKeyRepository;

    @Transactional
    public ProjectPlatformEntity validateAndGetProjectPlatform(String apiKey, String platformCode) {
        // Find and validate API key
        ProjectApiKeyEntity keyEntity = apiKeyRepository
                .findByApiKeyAndActiveTrue(apiKey)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.UNAUTHORIZED,
                        "Invalid or inactive API key"
                ));

        // Check expiration
        if (keyEntity.getExpiresAt() != null &&
                keyEntity.getExpiresAt().isBefore(OffsetDateTime.now())) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "API key has expired"
            );
        }

        // Validate platform matches
        ProjectPlatformEntity projectPlatform = keyEntity.getProjectPlatform();
        if (!projectPlatform.getPlatform().getCode().equalsIgnoreCase(platformCode)) {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,
                    "API key not valid for platform: " + platformCode
            );
        }

        // Check subscription
        validateSubscription(projectPlatform.getProject());

        // Update last used timestamp
        keyEntity.setLastUsedAt(OffsetDateTime.now());
        apiKeyRepository.save(keyEntity);

        return projectPlatform;
    }

    private void validateSubscription(ProjectEntity project) {
        // Check if user has active subscription
        boolean hasActiveSubscription = project.getOwner().getSubscriptions().stream()
                .anyMatch(sub -> "ACTIVE".equals(sub.getStatus()) &&
                        (sub.getExpiresAt() == null || sub.getExpiresAt().isAfter(OffsetDateTime.now())));

        if (!hasActiveSubscription) {
            throw new ResponseStatusException(
                    HttpStatus.PAYMENT_REQUIRED,
                    "No active subscription found"
            );
        }
    }
}
