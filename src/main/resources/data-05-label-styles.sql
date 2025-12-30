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
VALUES ('label-light-111111111111', 'theme-light-111111111111', 'default_label_style', '', '#000000', NULL, 'OpenSansRegular', 14.0, 'NONE', true, 'NONE', 0.0, 'TAIL_TRUNCATION', -1, -1.0, 'START', 'CENTER', 'NONE', '0', -1.0, -1.0, -1.0, -1.0, 1.7976931348623157E308, 1.7976931348623157E308, 'START', 'CENTER', '0', 'Transparent', true, true, 1.0, false, 0.5, 0.5, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0, 'MATCH_PARENT', '', CURRENT_TIMESTAMP);

-- Dark Theme Label Style
INSERT INTO dotnetmaui_crossplatform_label_style (id, theme_id, style_key, text, text_color, formatted_text, font_family, font_size, font_attributes, font_auto_scaling_enabled, text_transform, character_spacing, line_break_mode, max_lines, line_height, horizontal_text_alignment, vertical_text_alignment, text_decorations, padding, height_request, width_request, minimum_height_request, minimum_width_request, maximum_height_request, maximum_width_request, horizontal_options, vertical_options, margin, background_color, is_visible, is_enabled, opacity, input_transparent, anchor_x, anchor_y, rotation, rotation_x, rotation_y, scale, scale_x, scale_y, translation_x, translation_y, z_index, flow_direction, automation_id, created_at)
VALUES ('label-dark-222222222222', 'theme-dark-222222222222', 'default_label_style', '', '#FFFFFF', NULL, 'OpenSansRegular', 14.0, 'NONE', true, 'NONE', 0.0, 'TAIL_TRUNCATION', -1, -1.0, 'START', 'CENTER', 'NONE', '0', -1.0, -1.0, -1.0, -1.0, 1.7976931348623157E308, 1.7976931348623157E308, 'START', 'CENTER', '0', 'Transparent', true, true, 1.0, false, 0.5, 0.5, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0, 'MATCH_PARENT', '', CURRENT_TIMESTAMP);

-- ============================================================
-- LABEL SHADOWS
-- ============================================================

-- Light Label Shadow
INSERT INTO dotnetmaui_crossplatform_label_shadow (id, label_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('label-shadow-light-111111111111', 'label-light-111111111111', '#3d516b', 0.3, 3.0, '-0.3,0.3', CURRENT_TIMESTAMP);

-- Dark Label Shadow
INSERT INTO dotnetmaui_crossplatform_label_shadow (id, label_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('label-shadow-dark-222222222222', 'label-dark-222222222222', '#f5f5f5', 0.3, 3.0, '-0.3,0.3', CURRENT_TIMESTAMP);

-- ============================================================
-- LABEL VISUAL STATES
-- ============================================================

-- Light Label - Disabled
INSERT INTO dotnetmaui_crossplatform_label_visualstate (id, label_style_id, state_name, opacity, text_color, font_family, font_attributes, created_at)
VALUES ('label-vs-light-disabled', 'label-light-111111111111', 'Disabled', 0.5, '#6E6E6E', 'OpenSansRegular', 'NONE', CURRENT_TIMESTAMP);

-- Dark Label - Disabled
INSERT INTO dotnetmaui_crossplatform_label_visualstate (id, label_style_id, state_name, opacity, text_color, font_family, font_attributes, created_at)
VALUES ('label-vs-dark-disabled', 'label-dark-222222222222', 'Disabled', 0.5, '#919191', 'OpenSansRegular', 'NONE', CURRENT_TIMESTAMP);

-- ============================================================
-- LABEL VISUAL STATE SHADOWS
-- ============================================================

-- Light Label - Disabled State Shadow
INSERT INTO dotnetmaui_crossplatform_label_visualstate_shadow (id, label_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('label-vs-shadow-light-disabled', 'label-vs-light-disabled', '#3d516b', 0.3, 3.0, '-0.0,0.0', CURRENT_TIMESTAMP);

-- Dark Label - Disabled State Shadow
INSERT INTO dotnetmaui_crossplatform_label_visualstate_shadow (id, label_visual_status_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('label-vs-shadow-dark-disabled', 'label-vs-dark-disabled', '#f5f5f5', 0.3, 3.0, '-0.0,0.0', CURRENT_TIMESTAMP);
