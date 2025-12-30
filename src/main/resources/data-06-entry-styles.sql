-- ============================================================
-- DATA-06-ENTRY-STYLES.SQL
-- Entry styles, shadows, and visual states
-- ============================================================
-- Depends on: data-02-projects.sql (platform_theme)
-- ============================================================

-- ============================================================
-- ENTRY STYLES
-- ============================================================

-- Light Theme - Default Entry Style
INSERT INTO dotnetmaui_crossplatform_entry_style (id, theme_id, style_key, background_color, text_color, placeholder_color, cursor_color, selection_highlight_color, font_size, font_family, height_request, margin, clear_button_visibility, return_type, horizontal_text_alignment, vertical_text_alignment, keyboard, is_password, max_length, created_at)
VALUES ('entry-light-default-111', 'theme-light-111111111111', 'default_entry_style', '#FFFFFF', '#1A1A1A', '#9E9E9E', '#007AFF', '#B3D7FF', 16.0, 'OpenSansRegular', 48.0, '0', 'WHILE_EDITING', 'NEXT', 'START', 'CENTER', NULL, false, NULL, CURRENT_TIMESTAMP);

-- Dark Theme - Default Entry Style
INSERT INTO dotnetmaui_crossplatform_entry_style (id, theme_id, style_key, background_color, text_color, placeholder_color, cursor_color, selection_highlight_color, font_size, font_family, height_request, margin, clear_button_visibility, return_type, horizontal_text_alignment, vertical_text_alignment, keyboard, is_password, max_length, created_at)
VALUES ('entry-dark-default-222', 'theme-dark-222222222222', 'default_entry_style', '#1E1E1E', '#F5F5F5', '#757575', '#4DA3FF', '#3A5F8A', 16.0, 'OpenSansRegular', 48.0, '0', 'WHILE_EDITING', 'NEXT', 'START', 'CENTER', NULL, false, NULL, CURRENT_TIMESTAMP);

-- Light Theme - Digit Entry Style
INSERT INTO dotnetmaui_crossplatform_entry_style (id, theme_id, style_key, background_color, text_color, placeholder_color, cursor_color, selection_highlight_color, font_size, font_family, height_request, margin, clear_button_visibility, return_type, horizontal_text_alignment, vertical_text_alignment, keyboard, is_password, max_length, created_at)
VALUES ('entry-light-digit-111', 'theme-light-111111111111', 'DigitEntryDefault', '#FFFFFF', '#1A1A1A', '#9E9E9E', '#007AFF', '#B3D7FF', 16.0, 'OpenSansRegular', 48.0, '0', 'WHILE_EDITING', 'DONE', 'CENTER', 'CENTER', 'NUMERIC', false, 10, CURRENT_TIMESTAMP);

-- Dark Theme - Digit Entry Style
INSERT INTO dotnetmaui_crossplatform_entry_style (id, theme_id, style_key, background_color, text_color, placeholder_color, cursor_color, selection_highlight_color, font_size, font_family, height_request, margin, clear_button_visibility, return_type, horizontal_text_alignment, vertical_text_alignment, keyboard, is_password, max_length, created_at)
VALUES ('entry-dark-digit-222', 'theme-dark-222222222222', 'DigitEntryDefault', '#1E1E1E', '#F5F5F5', '#757575', '#4DA3FF', '#3A5F8A', 16.0, 'OpenSansRegular', 48.0, '0', 'WHILE_EDITING', 'DONE', 'CENTER', 'CENTER', 'NUMERIC', false, 10, CURRENT_TIMESTAMP);

-- Light Theme - Password Entry Style
INSERT INTO dotnetmaui_crossplatform_entry_style (id, theme_id, style_key, background_color, text_color, placeholder_color, cursor_color, selection_highlight_color, font_size, font_family, height_request, margin, clear_button_visibility, return_type, horizontal_text_alignment, vertical_text_alignment, keyboard, is_password, max_length, created_at)
VALUES ('entry-light-password-111', 'theme-light-111111111111', 'PasswordEntryDefault', '#FFFFFF', '#1A1A1A', '#9E9E9E', '#007AFF', '#B3D7FF', 16.0, 'OpenSansRegular', 48.0, '0', 'NEVER', 'DONE', 'START', 'CENTER', NULL, true, 128, CURRENT_TIMESTAMP);

-- Dark Theme - Password Entry Style
INSERT INTO dotnetmaui_crossplatform_entry_style (id, theme_id, style_key, background_color, text_color, placeholder_color, cursor_color, selection_highlight_color, font_size, font_family, height_request, margin, clear_button_visibility, return_type, horizontal_text_alignment, vertical_text_alignment, keyboard, is_password, max_length, created_at)
VALUES ('entry-dark-password-222', 'theme-dark-222222222222', 'PasswordEntryDefault', '#1E1E1E', '#F5F5F5', '#757575', '#4DA3FF', '#3A5F8A', 16.0, 'OpenSansRegular', 48.0, '0', 'NEVER', 'DONE', 'START', 'CENTER', NULL, true, 128, CURRENT_TIMESTAMP);

-- ============================================================
-- ENTRY SHADOWS
-- ============================================================

-- Light Theme - Default Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-shadow-light-default', 'entry-light-default-111', '#40000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- Dark Theme - Default Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-shadow-dark-default', 'entry-dark-default-222', '#60000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- Light Theme - Digit Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-shadow-light-digit', 'entry-light-digit-111', '#40000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- Dark Theme - Digit Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-shadow-dark-digit', 'entry-dark-digit-222', '#60000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- Light Theme - Password Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-shadow-light-password', 'entry-light-password-111', '#40000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- Dark Theme - Password Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-shadow-dark-password', 'entry-dark-password-222', '#60000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- ============================================================
-- ENTRY VISUAL STATES
-- ============================================================

-- Light Default Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('entry-vs-light-default-disabled', 'entry-light-default-111', 'Disabled', 0.5, '#F0F0F0', '#BDBDBD', CURRENT_TIMESTAMP);

-- Dark Default Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('entry-vs-dark-default-disabled', 'entry-dark-default-222', 'Disabled', 0.5, '#2A2A2A', '#616161', CURRENT_TIMESTAMP);

-- Light Digit Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('entry-vs-light-digit-disabled', 'entry-light-digit-111', 'Disabled', 0.5, '#F0F0F0', '#BDBDBD', CURRENT_TIMESTAMP);

-- Dark Digit Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('entry-vs-dark-digit-disabled', 'entry-dark-digit-222', 'Disabled', 0.5, '#2A2A2A', '#616161', CURRENT_TIMESTAMP);

-- Light Password Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('entry-vs-light-password-disabled', 'entry-light-password-111', 'Disabled', 0.5, '#F0F0F0', '#BDBDBD', CURRENT_TIMESTAMP);

-- Dark Password Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('entry-vs-dark-password-disabled', 'entry-dark-password-222', 'Disabled', 0.5, '#2A2A2A', '#616161', CURRENT_TIMESTAMP);

-- ============================================================
-- ENTRY VISUAL STATE SHADOWS (Disabled = Transparent)
-- ============================================================

-- Light Default Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-vs-shadow-light-def-disabled', 'entry-vs-light-default-disabled', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);

-- Dark Default Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-vs-shadow-dark-def-disabled', 'entry-vs-dark-default-disabled', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);

-- Light Digit Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-vs-shadow-light-dig-disabled', 'entry-vs-light-digit-disabled', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);

-- Dark Digit Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-vs-shadow-dark-dig-disabled', 'entry-vs-dark-digit-disabled', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);

-- Light Password Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-vs-shadow-light-pwd-disabled', 'entry-vs-light-password-disabled', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);

-- Dark Password Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('entry-vs-shadow-dark-pwd-disabled', 'entry-vs-dark-password-disabled', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);
