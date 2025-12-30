package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.label;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.label.LabelStyleEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.LabelStyleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class LabelStyleDeleteService {

    private final LabelStyleRepository labelStyleRepository;

    /**
     * Delete a label style by its ID.
     *
     * @param labelId The ID of the label style to delete
     * @throws EntityNotFoundException if label style not found
     */
    @Transactional
    public void deleteLabelStyle(String labelId) {
        log.debug("Deleting label style with ID: {}", labelId);

        LabelStyleEntity labelStyle = labelStyleRepository.findById(labelId)
                .orElseThrow(() -> new EntityNotFoundException("Label style not found with ID: " + labelId));

        labelStyleRepository.delete(labelStyle);
        log.info("Successfully deleted label style with ID: {} and key: {}", labelId, labelStyle.getKey());
    }
}
