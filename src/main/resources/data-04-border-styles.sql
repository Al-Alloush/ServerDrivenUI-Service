-- ============================================================
-- DATA-04-BORDER-STYLES.SQL
-- Border styles, shadows, and visual states
-- ============================================================
-- Depends on: data-02-projects.sql (platform_theme)
-- ============================================================

-- ============================================================
-- BORDER STYLE DEFINITIONS
-- ============================================================

-- Light Theme - Default Border
INSERT INTO dotnetmaui_crossplatform_border_style (id, theme_id, style_key, background, stroke, stroke_thickness, stroke_dash_array, stroke_dash_offset, stroke_line_cap, stroke_line_join, stroke_miter_limit, stroke_shape, padding, height_request, width_request, minimum_height_request, minimum_width_request, maximum_height_request, maximum_width_request, horizontal_options, vertical_options, margin, is_visible, is_enabled, opacity, input_transparent, anchor_x, anchor_y, rotation, rotation_x, rotation_y, scale, scale_x, scale_y, translation_x, translation_y, z_index, flow_direction, automation_id, created_at)
VALUES ('border-light-111111111111', 'theme-light-111111111111', 'default_border_style', '#f5f5f5', '#C8C8C8', 1.0, '0', 0.0, 'FLAT', 'MITER', 10.0, 'RoundRectangle 12', '5,5', -1.0, -1.0, -1.0, -1.0, 1.7976931348623157E308, 1.7976931348623157E308, 'FILL', 'FILL', '5,5', true, true, 1.0, false, 0.5, 0.5, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0, 'MATCH_PARENT', '', CURRENT_TIMESTAMP);

-- Dark Theme - Default Border
INSERT INTO dotnetmaui_crossplatform_border_style (id, theme_id, style_key, background, stroke, stroke_thickness, stroke_dash_array, stroke_dash_offset, stroke_line_cap, stroke_line_join, stroke_miter_limit, stroke_shape, padding, height_request, width_request, minimum_height_request, minimum_width_request, maximum_height_request, maximum_width_request, horizontal_options, vertical_options, margin, is_visible, is_enabled, opacity, input_transparent, anchor_x, anchor_y, rotation, rotation_x, rotation_y, scale, scale_x, scale_y, translation_x, translation_y, z_index, flow_direction, automation_id, created_at)
VALUES ('border-dark-222222222222', 'theme-dark-222222222222', 'default_border_style', '#3d516b', '#6E6E6E', 1.0, '0', 0.0, 'FLAT', 'MITER', 10.0, 'RoundRectangle 12', '5,5', -1.0, -1.0, -1.0, -1.0, 1.7976931348623157E308, 1.7976931348623157E308, 'FILL', 'FILL', '5,5', true, true, 1.0, false, 0.5, 0.5, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0, 'MATCH_PARENT', '', CURRENT_TIMESTAMP);

-- ============================================================
-- BORDER SHADOWS
-- ============================================================

-- Light Border Shadow
INSERT INTO dotnetmaui_crossplatform_border_shadow (id, border_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('bshadow-light-111111111111', 'border-light-111111111111', '#3d516b', 0.3, 3.0, '-0.3,0.3', CURRENT_TIMESTAMP);

-- Dark Border Shadow
INSERT INTO dotnetmaui_crossplatform_border_shadow (id, border_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('bshadow-dark-222222222222', 'border-dark-222222222222', '#f5f5f5', 0.3, 3.0, '-0.3,0.3', CURRENT_TIMESTAMP);

-- ============================================================
-- BORDER VISUAL STATES
-- ============================================================

-- Light Border - Disabled
INSERT INTO dotnetmaui_crossplatform_border_visualstate (id, border_style_id, name, opacity)
VALUES ('b2c3d4e5-f6a7-48b9-91c2-222222222222', 'border-light-111111111111', 'Disabled', 0.5);

-- Dark Border - Disabled
INSERT INTO dotnetmaui_crossplatform_border_visualstate (id, border_style_id, name, opacity)
VALUES ('b4c5d6e7-f8a9-40b1-93c4-444444444444', 'border-dark-222222222222', 'Disabled', 0.5);
