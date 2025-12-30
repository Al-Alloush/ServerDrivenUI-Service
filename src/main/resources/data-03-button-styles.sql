-- ============================================================
-- DATA-03-BUTTON-STYLES.SQL
-- Button styles, shadows, and visual states
-- ============================================================
-- This file contains button style definitions.
-- Depends on: data-02-projects.sql (platform_theme)
-- ============================================================

-- ============================================================
-- BUTTON STYLE DEFINITIONS
-- ============================================================

-- Light Theme - Primary Button
INSERT INTO dotnetmaui_crossplatform_button_style (id, theme_id, style_key, text, text_color, background_color, opacity, is_visible, is_enabled, font_family, font_size, font_attributes, character_spacing, line_break_mode, text_transform, padding, margin, height_request, width_request, min_height_request, min_width_request, horizontal_options, vertical_options, content_layout, border_color, border_width, corner_radius, image_source, semantic_description, semantic_hint, created_at)
VALUES ('bdfc7632-35c7-4a9c-852c-1a2b3c4d5e6f', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'default_button_style', 'Primary Button', '#FFFFFF', '#729af7', 1.0, 1, 1, 'OpenSansRegular', 16.0, 'Bold', 0.0, 'NoWrap', 'None', '16,10', '0', -1.0, -1.0, 44.0, 44.0, 'Fill', 'Center', 'Left,10', '#26c998', 1, 8, 'icon_save.png', 'Primary action button', 'Double tap to activate', CURRENT_TIMESTAMP);

-- Dark Theme - Primary Button
INSERT INTO dotnetmaui_crossplatform_button_style (id, theme_id, style_key, text, text_color, background_color, opacity, is_visible, is_enabled, font_family, font_size, font_attributes, character_spacing, line_break_mode, text_transform, padding, margin, height_request, width_request, min_height_request, min_width_request, horizontal_options, vertical_options, content_layout, border_color, border_width, corner_radius, image_source, semantic_description, semantic_hint, created_at)
VALUES ('cdfc8743-46d8-5b0d-963d-2b3c4d5e6f7a', 'b2c3d4e5-f6a7-8901-bcde-f12345678901', 'default_button_style', 'Primary Button', '#FFFFFF', '#729af7', 1.0, 1, 1, 'OpenSansRegular', 16.0, 'Bold', 0.0, 'NoWrap', 'None', '16,10', '0', -1.0, -1.0, 44.0, 44.0, 'Fill', 'Center', 'Left,10', '#26c998', 1, 8, 'icon_save.png', 'Primary action button', 'Double tap to activate', CURRENT_TIMESTAMP);

-- Light Theme - Secondary Button
INSERT INTO dotnetmaui_crossplatform_button_style (id, theme_id, style_key, text, text_color, background_color, opacity, is_visible, is_enabled, font_family, font_size, font_attributes, character_spacing, line_break_mode, text_transform, padding, margin, height_request, width_request, min_height_request, min_width_request, horizontal_options, vertical_options, content_layout, border_color, border_width, corner_radius, image_source, semantic_description, semantic_hint, created_at)
VALUES ('c3dbc5af-6658-4d86-9e11-3c4d5e6f7a8b', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'Secondary', 'Secondary Button', '#FFFFFF', '#3f733c', 1.0, 1, 1, 'OpenSansRegular', 14.0, 'None', 0.0, 'NoWrap', 'None', '12,8', '0', -1.0, -1.0, 44.0, 44.0, 'Fill', 'Center', 'Left,8', '#444dcf', 1, 4, NULL, 'Secondary action button', 'Double tap to activate', CURRENT_TIMESTAMP);

-- Dark Theme - Secondary Button
INSERT INTO dotnetmaui_crossplatform_button_style (id, theme_id, style_key, text, text_color, background_color, opacity, is_visible, is_enabled, font_family, font_size, font_attributes, character_spacing, line_break_mode, text_transform, padding, margin, height_request, width_request, min_height_request, min_width_request, horizontal_options, vertical_options, content_layout, border_color, border_width, corner_radius, image_source, semantic_description, semantic_hint, created_at)
VALUES ('d4ecd6b0-7769-5e97-0f22-4d5e6f7a8b9c', 'b2c3d4e5-f6a7-8901-bcde-f12345678901', 'Secondary', 'Secondary Button', '#8c8c8c', '#0e300c', 1.0, 1, 1, 'OpenSansRegular', 14.0, 'None', 0.0, 'NoWrap', 'None', '12,8', '0', -1.0, -1.0, 44.0, 44.0, 'Fill', 'Center', 'Left,8', '#444dcf', 1, 4, NULL, 'Secondary action button', 'Double tap to activate', CURRENT_TIMESTAMP);

-- ============================================================
-- BUTTON SHADOWS
-- ============================================================

-- Light Primary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (id, button_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('d1e2f3a4-b5c6-47d8-89e0-5e6f7a8b9c0d', 'bdfc7632-35c7-4a9c-852c-1a2b3c4d5e6f', '#000000', 0.3, 8.0, '0,4', CURRENT_TIMESTAMP);

-- Dark Primary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (id, button_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('e2f3a4b5-c6d7-58e9-90f1-6f7a8b9c0d1e', 'cdfc8743-46d8-5b0d-963d-2b3c4d5e6f7a', '#8c8c8c', 0.3, 8.0, '0,4', CURRENT_TIMESTAMP);

-- Light Secondary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (id, button_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('f3a4b5c6-d7e8-69f0-01a2-7a8b9c0d1e2f', 'c3dbc5af-6658-4d86-9e11-3c4d5e6f7a8b', '#000000', 0.2, 6.0, '0,3', CURRENT_TIMESTAMP);

-- Dark Secondary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (id, button_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('a4b5c6d7-e8f9-70a1-12b3-8b9c0d1e2f3a', 'd4ecd6b0-7769-5e97-0f22-4d5e6f7a8b9c', '#8c8c8c', 0.2, 6.0, '0,3', CURRENT_TIMESTAMP);

-- ============================================================
-- BUTTON VISUAL STATES
-- ============================================================

-- Light Primary - Disabled
INSERT INTO dotnetmaui_crossplatform_button_visualstate (id, button_style_id, name, opacity, text_color, background_color, border_color)
VALUES ('f2b3c4d5-e6f7-48a9-91b2-9c0d1e2f3a4b', 'bdfc7632-35c7-4a9c-852c-1a2b3c4d5e6f', 'Disabled', 0.5, '#999999', '#CCCCCC', '#CCCCCC');

-- Dark Primary - Disabled
INSERT INTO dotnetmaui_crossplatform_button_visualstate (id, button_style_id, name, opacity, text_color, background_color, border_color)
VALUES ('a3c4d5e6-f7a8-59b0-02c3-0d1e2f3a4b5c', 'cdfc8743-46d8-5b0d-963d-2b3c4d5e6f7a', 'Disabled', 0.5, '#916e6e', '#0e4009', '#CCCCCC');
