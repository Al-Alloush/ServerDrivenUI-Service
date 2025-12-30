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
VALUES ('bdfc7632-35c7-4a9c-852c-111111111111', 'theme-light-111111111111', 'default_button_style', 'Primary Button', '#FFFFFF', '#729af7', 1.0, true, true, 'OpenSansRegular', 16.0, 'Bold', 0.0, 'NoWrap', 'None', '16,10', '0', -1.0, -1.0, 44.0, 44.0, 'Fill', 'Center', 'Left,10', '#26c998', 1, 8, 'icon_save.png', 'Primary action button', 'Double tap to activate', CURRENT_TIMESTAMP);

-- Dark Theme - Primary Button
INSERT INTO dotnetmaui_crossplatform_button_style (id, theme_id, style_key, text, text_color, background_color, opacity, is_visible, is_enabled, font_family, font_size, font_attributes, character_spacing, line_break_mode, text_transform, padding, margin, height_request, width_request, min_height_request, min_width_request, horizontal_options, vertical_options, content_layout, border_color, border_width, corner_radius, image_source, semantic_description, semantic_hint, created_at)
VALUES ('bdfc7632-35c7-4a9c-852c-222222222222', 'theme-dark-222222222222', 'default_button_style', 'Primary Button', '#FFFFFF', '#729af7', 1.0, true, true, 'OpenSansRegular', 16.0, 'Bold', 0.0, 'NoWrap', 'None', '16,10', '0', -1.0, -1.0, 44.0, 44.0, 'Fill', 'Center', 'Left,10', '#26c998', 1, 8, 'icon_save.png', 'Primary action button', 'Double tap to activate', CURRENT_TIMESTAMP);

-- Light Theme - Secondary Button
INSERT INTO dotnetmaui_crossplatform_button_style (id, theme_id, style_key, text, text_color, background_color, opacity, is_visible, is_enabled, font_family, font_size, font_attributes, character_spacing, line_break_mode, text_transform, padding, margin, height_request, width_request, min_height_request, min_width_request, horizontal_options, vertical_options, content_layout, border_color, border_width, corner_radius, image_source, semantic_description, semantic_hint, created_at)
VALUES ('c3dbc5af-6658-4d86-9e11-333333333333', 'theme-light-111111111111', 'Secondary', 'Secondary Button', '#FFFFFF', '#3f733c', 1.0, true, true, 'OpenSansRegular', 14.0, 'None', 0.0, 'NoWrap', 'None', '12,8', '0', -1.0, -1.0, 44.0, 44.0, 'Fill', 'Center', 'Left,8', '#444dcf', 1, 4, NULL, 'Secondary action button', 'Double tap to activate', CURRENT_TIMESTAMP);

-- Dark Theme - Secondary Button
INSERT INTO dotnetmaui_crossplatform_button_style (id, theme_id, style_key, text, text_color, background_color, opacity, is_visible, is_enabled, font_family, font_size, font_attributes, character_spacing, line_break_mode, text_transform, padding, margin, height_request, width_request, min_height_request, min_width_request, horizontal_options, vertical_options, content_layout, border_color, border_width, corner_radius, image_source, semantic_description, semantic_hint, created_at)
VALUES ('c3dbc5af-6658-4d86-9e11-444444444444', 'theme-dark-222222222222', 'Secondary', 'Secondary Button', '#8c8c8c', '#0e300c', 1.0, true, true, 'OpenSansRegular', 14.0, 'None', 0.0, 'NoWrap', 'None', '12,8', '0', -1.0, -1.0, 44.0, 44.0, 'Fill', 'Center', 'Left,8', '#444dcf', 1, 4, NULL, 'Secondary action button', 'Double tap to activate', CURRENT_TIMESTAMP);

-- ============================================================
-- BUTTON SHADOWS
-- ============================================================

-- Light Primary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (id, button_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('d1e2f3a4-b5c6-47d8-89e0-111111111111', 'bdfc7632-35c7-4a9c-852c-111111111111', '#000000', 0.3, 8.0, '0,4', CURRENT_TIMESTAMP);

-- Dark Primary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (id, button_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('d1e2f3a4-b5c6-47d8-89e0-222222222222', 'bdfc7632-35c7-4a9c-852c-222222222222', '#8c8c8c', 0.3, 8.0, '0,4', CURRENT_TIMESTAMP);

-- Light Secondary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (id, button_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('e2f3a4b5-c6d7-48e9-90f1-333333333333', 'c3dbc5af-6658-4d86-9e11-333333333333', '#000000', 0.2, 6.0, '0,3', CURRENT_TIMESTAMP);

-- Dark Secondary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (id, button_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('e2f3a4b5-c6d7-48e9-90f1-444444444444', 'c3dbc5af-6658-4d86-9e11-444444444444', '#8c8c8c', 0.2, 6.0, '0,3', CURRENT_TIMESTAMP);

-- ============================================================
-- BUTTON VISUAL STATES
-- ============================================================

-- Light Primary - Disabled
INSERT INTO dotnetmaui_crossplatform_button_visualstate (id, button_style_id, name, opacity, text_color, background_color, border_color)
VALUES ('f2b3c4d5-e6f7-48a9-91b2-111111111111', 'bdfc7632-35c7-4a9c-852c-111111111111', 'Disabled', 0.5, '#999999', '#CCCCCC', '#CCCCCC');

-- Dark Primary - Disabled
INSERT INTO dotnetmaui_crossplatform_button_visualstate (id, button_style_id, name, opacity, text_color, background_color, border_color)
VALUES ('f2b3c4d5-e6f7-48a9-91b2-222222222222', 'bdfc7632-35c7-4a9c-852c-222222222222', 'Disabled', 0.5, '#916e6e', '#0e4009', '#CCCCCC');
