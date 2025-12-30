package dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.service.buttons;

import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.model.entity.button.ButtonStyleEntity;
import dev.codexo.app.srv.serverdrivenui.dotnet.maui.crossplatform.repository.ButtonStyleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ButtonStyleDeleteService {

    private final ButtonStyleRepository buttonStyleRepository;

    /**
     * Delete a button style by its ID.
     *
     * @param buttonId The ID of the button style to delete
     * @throws EntityNotFoundException if button style not found
     */
    @Transactional
    public void deleteButtonStyle(String buttonId) {
        log.debug("Deleting button style with ID: {}", buttonId);

        ButtonStyleEntity buttonStyle = buttonStyleRepository.findById(buttonId)
                .orElseThrow(() -> new EntityNotFoundException("Button style not found with ID: " + buttonId));

        buttonStyleRepository.delete(buttonStyle);
        log.info("Successfully deleted button style with ID: {} and key: {}", buttonId, buttonStyle.getStyleKey());
    }
}

