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
VALUES ('9c0d1e2f-3a4b-5c6d-7e8f-9a0b1c2d3e4f', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'default_entry_style', '#FFFFFF', '#1A1A1A', '#9E9E9E', '#007AFF', '#B3D7FF', 16.0, 'OpenSansRegular', 48.0, '0', 'WHILE_EDITING', 'NEXT', 'START', 'CENTER', NULL, 0, NULL, CURRENT_TIMESTAMP);

-- Dark Theme - Default Entry Style
INSERT INTO dotnetmaui_crossplatform_entry_style (id, theme_id, style_key, background_color, text_color, placeholder_color, cursor_color, selection_highlight_color, font_size, font_family, height_request, margin, clear_button_visibility, return_type, horizontal_text_alignment, vertical_text_alignment, keyboard, is_password, max_length, created_at)
VALUES ('0d1e2f3a-4b5c-6d7e-8f9a-0b1c2d3e4f5a', 'b2c3d4e5-f6a7-8901-bcde-f12345678901', 'default_entry_style', '#1E1E1E', '#F5F5F5', '#757575', '#4DA3FF', '#3A5F8A', 16.0, 'OpenSansRegular', 48.0, '0', 'WHILE_EDITING', 'NEXT', 'START', 'CENTER', NULL, 0, NULL, CURRENT_TIMESTAMP);

-- Light Theme - Digit Entry Style
INSERT INTO dotnetmaui_crossplatform_entry_style (id, theme_id, style_key, background_color, text_color, placeholder_color, cursor_color, selection_highlight_color, font_size, font_family, height_request, margin, clear_button_visibility, return_type, horizontal_text_alignment, vertical_text_alignment, keyboard, is_password, max_length, created_at)
VALUES ('1e2f3a4b-5c6d-7e8f-9a0b-1c2d3e4f5a6b', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'DigitEntryDefault', '#FFFFFF', '#1A1A1A', '#9E9E9E', '#007AFF', '#B3D7FF', 16.0, 'OpenSansRegular', 48.0, '0', 'WHILE_EDITING', 'DONE', 'CENTER', 'CENTER', 'NUMERIC', 0, 10, CURRENT_TIMESTAMP);

-- Dark Theme - Digit Entry Style
INSERT INTO dotnetmaui_crossplatform_entry_style (id, theme_id, style_key, background_color, text_color, placeholder_color, cursor_color, selection_highlight_color, font_size, font_family, height_request, margin, clear_button_visibility, return_type, horizontal_text_alignment, vertical_text_alignment, keyboard, is_password, max_length, created_at)
VALUES ('2f3a4b5c-6d7e-8f9a-0b1c-2d3e4f5a6b7c', 'b2c3d4e5-f6a7-8901-bcde-f12345678901', 'DigitEntryDefault', '#1E1E1E', '#F5F5F5', '#757575', '#4DA3FF', '#3A5F8A', 16.0, 'OpenSansRegular', 48.0, '0', 'WHILE_EDITING', 'DONE', 'CENTER', 'CENTER', 'NUMERIC', 0, 10, CURRENT_TIMESTAMP);

-- Light Theme - Password Entry Style
INSERT INTO dotnetmaui_crossplatform_entry_style (id, theme_id, style_key, background_color, text_color, placeholder_color, cursor_color, selection_highlight_color, font_size, font_family, height_request, margin, clear_button_visibility, return_type, horizontal_text_alignment, vertical_text_alignment, keyboard, is_password, max_length, created_at)
VALUES ('3a4b5c6d-7e8f-9a0b-1c2d-3e4f5a6b7c8d', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'PasswordEntryDefault', '#FFFFFF', '#1A1A1A', '#9E9E9E', '#007AFF', '#B3D7FF', 16.0, 'OpenSansRegular', 48.0, '0', 'NEVER', 'DONE', 'START', 'CENTER', NULL, 1, 128, CURRENT_TIMESTAMP);

-- Dark Theme - Password Entry Style
INSERT INTO dotnetmaui_crossplatform_entry_style (id, theme_id, style_key, background_color, text_color, placeholder_color, cursor_color, selection_highlight_color, font_size, font_family, height_request, margin, clear_button_visibility, return_type, horizontal_text_alignment, vertical_text_alignment, keyboard, is_password, max_length, created_at)
VALUES ('4b5c6d7e-8f9a-0b1c-2d3e-4f5a6b7c8d9e', 'b2c3d4e5-f6a7-8901-bcde-f12345678901', 'PasswordEntryDefault', '#1E1E1E', '#F5F5F5', '#757575', '#4DA3FF', '#3A5F8A', 16.0, 'OpenSansRegular', 48.0, '0', 'NEVER', 'DONE', 'START', 'CENTER', NULL, 1, 128, CURRENT_TIMESTAMP);

-- ============================================================
-- ENTRY SHADOWS
-- ============================================================

-- Light Theme - Default Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('5c6d7e8f-9a0b-1c2d-3e4f-5a6b7c8d9e0f', '9c0d1e2f-3a4b-5c6d-7e8f-9a0b1c2d3e4f', '#40000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- Dark Theme - Default Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('6d7e8f9a-0b1c-2d3e-4f5a-6b7c8d9e0f1a', '0d1e2f3a-4b5c-6d7e-8f9a-0b1c2d3e4f5a', '#60000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- Light Theme - Digit Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('7e8f9a0b-1c2d-3e4f-5a6b-7c8d9e0f1a2b', '1e2f3a4b-5c6d-7e8f-9a0b-1c2d3e4f5a6b', '#40000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- Dark Theme - Digit Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('8f9a0b1c-2d3e-4f5a-6b7c-8d9e0f1a2b3c', '2f3a4b5c-6d7e-8f9a-0b1c-2d3e4f5a6b7c', '#60000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- Light Theme - Password Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('9a0b1c2d-3e4f-5a6b-7c8d-9e0f1a2b3c4d', '3a4b5c6d-7e8f-9a0b-1c2d-3e4f5a6b7c8d', '#40000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- Dark Theme - Password Entry Shadow
INSERT INTO dotnetmaui_crossplatform_entry_shadow (id, entry_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('0b1c2d3e-4f5a-6b7c-8d9e-0f1a2b3c4d5e', '4b5c6d7e-8f9a-0b1c-2d3e-4f5a6b7c8d9e', '#60000000', 0.3, 4.0, '0,2', CURRENT_TIMESTAMP);

-- ============================================================
-- ENTRY VISUAL STATES
-- ============================================================

-- Light Default Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('1c2d3e4f-5a6b-7c8d-9e0f-1a2b3c4d5e6f', '9c0d1e2f-3a4b-5c6d-7e8f-9a0b1c2d3e4f', 'Disabled', 0.5, '#F0F0F0', '#BDBDBD', CURRENT_TIMESTAMP);

-- Dark Default Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('2d3e4f5a-6b7c-8d9e-0f1a-2b3c4d5e6f7a', '0d1e2f3a-4b5c-6d7e-8f9a-0b1c2d3e4f5a', 'Disabled', 0.5, '#2A2A2A', '#616161', CURRENT_TIMESTAMP);

-- Light Digit Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('3e4f5a6b-7c8d-9e0f-1a2b-3c4d5e6f7a8b', '1e2f3a4b-5c6d-7e8f-9a0b-1c2d3e4f5a6b', 'Disabled', 0.5, '#F0F0F0', '#BDBDBD', CURRENT_TIMESTAMP);

-- Dark Digit Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('4f5a6b7c-8d9e-0f1a-2b3c-4d5e6f7a8b9c', '2f3a4b5c-6d7e-8f9a-0b1c-2d3e4f5a6b7c', 'Disabled', 0.5, '#2A2A2A', '#616161', CURRENT_TIMESTAMP);

-- Light Password Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('5a6b7c8d-9e0f-1a2b-3c4d-5e6f7a8b9c0d', '3a4b5c6d-7e8f-9a0b-1c2d-3e4f5a6b7c8d', 'Disabled', 0.5, '#F0F0F0', '#BDBDBD', CURRENT_TIMESTAMP);

-- Dark Password Entry - Disabled
INSERT INTO dotnetmaui_crossplatform_entry_visualstate (id, entry_style_id, state_name, opacity, background_color, text_color, created_at)
VALUES ('6b7c8d9e-0f1a-2b3c-4d5e-6f7a8b9c0d1e', '4b5c6d7e-8f9a-0b1c-2d3e-4f5a6b7c8d9e', 'Disabled', 0.5, '#2A2A2A', '#616161', CURRENT_TIMESTAMP);

-- ============================================================
-- ENTRY VISUAL STATE SHADOWS (Disabled = Transparent)
-- ============================================================

-- Light Default Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('7c8d9e0f-1a2b-3c4d-5e6f-7a8b9c0d1e2f', '1c2d3e4f-5a6b-7c8d-9e0f-1a2b3c4d5e6f', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);

-- Dark Default Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('8d9e0f1a-2b3c-4d5e-6f7a-8b9c0d1e2f3a', '2d3e4f5a-6b7c-8d9e-0f1a-2b3c4d5e6f7a', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);

-- Light Digit Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('9e0f1a2b-3c4d-5e6f-7a8b-9c0d1e2f3a4b', '3e4f5a6b-7c8d-9e0f-1a2b-3c4d5e6f7a8b', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);

-- Dark Digit Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('0f1a2b3c-4d5e-6f7a-8b9c-0d1e2f3a4b5c', '4f5a6b7c-8d9e-0f1a-2b3c-4d5e6f7a8b9c', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);

-- Light Password Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6e', '5a6b7c8d-9e0f-1a2b-3c4d-5e6f7a8b9c0d', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);

-- Dark Password Entry - Disabled Shadow
INSERT INTO dotnetmaui_crossplatform_entry_visualstate_shadow (id, entry_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7f', '6b7c8d9e-0f1a-2b3c-4d5e-6f7a8b9c0d1e', 'Transparent', 0.0, 0.0, '0,0', CURRENT_TIMESTAMP);
