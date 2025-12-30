-- ============================================================
-- DATA-05-LABEL-STYLES.SQL
-- Label styles, shadows, and visual states
-- ============================================================
-- This file contains label style definitions.
-- Depends on: data-02-projects.sql (platform_theme)
-- ============================================================

-- ============================================================
-- LABEL STYLES
-- ============================================================

-- Light Theme Label Style
INSERT INTO dotnetmaui_crossplatform_label_style (id, theme_id, style_key, text, text_color, formatted_text, font_family, font_size, font_attributes, font_auto_scaling_enabled, text_transform, character_spacing, line_break_mode, max_lines, line_height, horizontal_text_alignment, vertical_text_alignment, text_decorations, padding, height_request, width_request, minimum_height_request, minimum_width_request, maximum_height_request, maximum_width_request, horizontal_options, vertical_options, margin, background_color, is_visible, is_enabled, opacity, input_transparent, anchor_x, anchor_y, rotation, rotation_x, rotation_y, scale, scale_x, scale_y, translation_x, translation_y, z_index, flow_direction, automation_id, created_at)
VALUES ('1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'DefaultLbl', '', '#000000', NULL, 'OpenSansRegular', 14.0, 'NONE', 1, 'NONE', 0.0, 'TAIL_TRUNCATION', -1, -1.0, 'START', 'CENTER', 'NONE', '0', -1.0, -1.0, -1.0, -1.0, 1.7976931348623157E308, 1.7976931348623157E308, 'START', 'CENTER', '0', 'Transparent', 1, 1, 1.0, 0, 0.5, 0.5, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0, 'MATCH_PARENT', '', CURRENT_TIMESTAMP);

-- Dark Theme Label Style
INSERT INTO dotnetmaui_crossplatform_label_style (id, theme_id, style_key, text, text_color, formatted_text, font_family, font_size, font_attributes, font_auto_scaling_enabled, text_transform, character_spacing, line_break_mode, max_lines, line_height, horizontal_text_alignment, vertical_text_alignment, text_decorations, padding, height_request, width_request, minimum_height_request, minimum_width_request, maximum_height_request, maximum_width_request, horizontal_options, vertical_options, margin, background_color, is_visible, is_enabled, opacity, input_transparent, anchor_x, anchor_y, rotation, rotation_x, rotation_y, scale, scale_x, scale_y, translation_x, translation_y, z_index, flow_direction, automation_id, created_at)
VALUES ('2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e', 'b2c3d4e5-f6a7-8901-bcde-f12345678901', 'DefaultLbl', '', '#FFFFFF', NULL, 'OpenSansRegular', 14.0, 'NONE', 1, 'NONE', 0.0, 'TAIL_TRUNCATION', -1, -1.0, 'START', 'CENTER', 'NONE', '0', -1.0, -1.0, -1.0, -1.0, 1.7976931348623157E308, 1.7976931348623157E308, 'START', 'CENTER', '0', 'Transparent', 1, 1, 1.0, 0, 0.5, 0.5, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0, 'MATCH_PARENT', '', CURRENT_TIMESTAMP);

-- ============================================================
-- LABEL SHADOWS
-- ============================================================

-- Light Label Shadow
INSERT INTO dotnetmaui_crossplatform_label_shadow (id, label_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('3c4d5e6f-7a8b-9c0d-1e2f-3a4b5c6d7e8f', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', '#3d516b', 0.3, 3.0, '-0.3,0.3', CURRENT_TIMESTAMP);

-- Dark Label Shadow
INSERT INTO dotnetmaui_crossplatform_label_shadow (id, label_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('4d5e6f7a-8b9c-0d1e-2f3a-4b5c6d7e8f9a', '2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e', '#f5f5f5', 0.3, 3.0, '-0.3,0.3', CURRENT_TIMESTAMP);

-- ============================================================
-- LABEL VISUAL STATES
-- ============================================================

-- Light Label - Disabled
INSERT INTO dotnetmaui_crossplatform_label_visualstate (id, label_style_id, state_name, opacity, text_color, font_family, font_attributes, created_at)
VALUES ('5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b', '1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d', 'Disabled', 0.5, '#6E6E6E', 'OpenSansRegular', 'NONE', CURRENT_TIMESTAMP);

-- Dark Label - Disabled
INSERT INTO dotnetmaui_crossplatform_label_visualstate (id, label_style_id, state_name, opacity, text_color, font_family, font_attributes, created_at)
VALUES ('6f7a8b9c-0d1e-2f3a-4b5c-6d7e8f9a0b1c', '2b3c4d5e-6f7a-8b9c-0d1e-2f3a4b5c6d7e', 'Disabled', 0.5, '#919191', 'OpenSansRegular', 'NONE', CURRENT_TIMESTAMP);

-- ============================================================
-- LABEL VISUAL STATE SHADOWS
-- ============================================================

-- Light Label - Disabled State Shadow
INSERT INTO dotnetmaui_crossplatform_label_visualstate_shadow (id, label_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('7a8b9c0d-1e2f-3a4b-5c6d-7e8f9a0b1c2d', '5e6f7a8b-9c0d-1e2f-3a4b-5c6d7e8f9a0b', '#3d516b', 0.3, 3.0, '-0.0,0.0', CURRENT_TIMESTAMP);

-- Dark Label - Disabled State Shadow
INSERT INTO dotnetmaui_crossplatform_label_visualstate_shadow (id, label_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('8b9c0d1e-2f3a-4b5c-6d7e-8f9a0b1c2d3e', '6f7a8b9c-0d1e-2f3a-4b5c-6d7e8f9a0b1c', '#f5f5f5', 0.3, 3.0, '-0.0,0.0', CURRENT_TIMESTAMP);
