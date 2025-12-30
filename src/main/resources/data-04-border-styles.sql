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
VALUES ('b1c2d3e4-f5a6-7890-1234-a1b2c3d4e5f6', 'a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'default_border_style', '#f5f5f5', '#C8C8C8', 1.0, '0', 0.0, 'FLAT', 'MITER', 10.0, 'RoundRectangle 12', '5,5', -1.0, -1.0, -1.0, -1.0, 1.7976931348623157E308, 1.7976931348623157E308, 'FILL', 'FILL', '5,5', 1, 1, 1.0, 0, 0.5, 0.5, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0, 'MATCH_PARENT', '', CURRENT_TIMESTAMP);

-- Dark Theme - Default Border
INSERT INTO dotnetmaui_crossplatform_border_style (id, theme_id, style_key, background, stroke, stroke_thickness, stroke_dash_array, stroke_dash_offset, stroke_line_cap, stroke_line_join, stroke_miter_limit, stroke_shape, padding, height_request, width_request, minimum_height_request, minimum_width_request, maximum_height_request, maximum_width_request, horizontal_options, vertical_options, margin, is_visible, is_enabled, opacity, input_transparent, anchor_x, anchor_y, rotation, rotation_x, rotation_y, scale, scale_x, scale_y, translation_x, translation_y, z_index, flow_direction, automation_id, created_at)
VALUES ('c2d3e4f5-a6b7-8901-2345-b2c3d4e5f6a7', 'b2c3d4e5-f6a7-8901-bcde-f12345678901', 'default_border_style', '#3d516b', '#6E6E6E', 1.0, '0', 0.0, 'FLAT', 'MITER', 10.0, 'RoundRectangle 12', '5,5', -1.0, -1.0, -1.0, -1.0, 1.7976931348623157E308, 1.7976931348623157E308, 'FILL', 'FILL', '5,5', 1, 1, 1.0, 0, 0.5, 0.5, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, 0.0, 0.0, 0, 'MATCH_PARENT', '', CURRENT_TIMESTAMP);

-- ============================================================
-- BORDER SHADOWS
-- ============================================================

-- Light Border Shadow
INSERT INTO dotnetmaui_crossplatform_border_shadow (id, border_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('d3e4f5a6-b7c8-9012-3456-c3d4e5f6a7b8', 'b1c2d3e4-f5a6-7890-1234-a1b2c3d4e5f6', '#3d516b', 0.3, 3.0, '-0.3,0.3', CURRENT_TIMESTAMP);

-- Dark Border Shadow
INSERT INTO dotnetmaui_crossplatform_border_shadow (id, border_style_id, shadow_brush, shadow_opacity, shadow_radius, shadow_offset, created_at)
VALUES ('e4f5a6b7-c8d9-0123-4567-d4e5f6a7b8c9', 'c2d3e4f5-a6b7-8901-2345-b2c3d4e5f6a7', '#f5f5f5', 0.3, 3.0, '-0.3,0.3', CURRENT_TIMESTAMP);

-- ============================================================
-- BORDER VISUAL STATES
-- ============================================================

-- Light Border - Disabled
INSERT INTO dotnetmaui_crossplatform_border_visualstate (id, border_style_id, name, opacity)
VALUES ('f5a6b7c8-d9e0-1234-5678-e5f6a7b8c9d0', 'b1c2d3e4-f5a6-7890-1234-a1b2c3d4e5f6', 'Disabled', 0.5);

-- Dark Border - Disabled
INSERT INTO dotnetmaui_crossplatform_border_visualstate (id, border_style_id, name, opacity)
VALUES ('a6b7c8d9-e0f1-2345-6789-f6a7b8c9d0e1', 'c2d3e4f5-a6b7-8901-2345-b2c3d4e5f6a7', 'Disabled', 0.5);
