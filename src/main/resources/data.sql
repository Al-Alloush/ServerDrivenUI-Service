-- ============================================================
-- SUBSCRIPTION PLAN SETUP
-- ============================================================
INSERT INTO subscription_plan (
    id,
    code,
    name,
    description,
    max_projects,
    max_platforms_per_project,
    monthly_price,
    is_default,
    default_duration_days,
    created_at
) VALUES (
             'b9d2b44c-2f58-4ff3-a71b-111111111111',
             'FREE_3M',
             'Free (3 months)',
             'Default free tier: 1 project and 1 platform for 3 months.',
             1,
             1,
             0.00,
             TRUE,
             90,
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- DEMO USER SETUP
-- ============================================================
INSERT INTO users (
    id,
    keycloak_user_id,
    email,
    display_name,
    created_at
) VALUES (
             '4d0a5d5d-9304-4bd4-80b1-222222222222',
             'demo-keycloak-imeterrecorder',
             'demo.imeterrecorder@codexo.dev',
             'iMeterRecorder Demo Owner',
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- USER SUBSCRIPTION ASSIGNMENT
-- ============================================================
INSERT INTO user_subscription (
    id,
    user_id,
    subscription_plan_id,
    status,
    started_at,
    expires_at,
    created_at
) VALUES (
             'e71b0c59-eb22-4bc6-9ab3-333333333333',
             '4d0a5d5d-9304-4bd4-80b1-222222222222',
             'b9d2b44c-2f58-4ff3-a71b-111111111111',
             'ACTIVE',
             CURRENT_TIMESTAMP,
             NULL,
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- PLATFORM DEFINITION
-- ============================================================
INSERT INTO platform (
    id,
    code,
    name,
    description
) VALUES (
             'f5b31971-b864-4e62-a4c0-444444444444',
             'DOTNET_MAUI_CROSS_PLATFORM',
             '.NET MAUI Cross Platform',
             'Cross-platform mobile / desktop client built with .NET MAUI.'
         );

-- ============================================================
-- PROJECT SETUP
-- ============================================================
INSERT INTO project (
    id,
    user_id,
    name,
    slug,
    description,
    created_at,
    updated_at,
    is_deleted
) VALUES (
             '1f9a554d-7520-4416-8eb2-555555555555',
             '4d0a5d5d-9304-4bd4-80b1-222222222222',
             'iMeterRecorder Mobile',
             'imeterrecorder',
             'Server-driven UI demo project for the iMeterRecorder .NET MAUI app.',
             CURRENT_TIMESTAMP,
             CURRENT_TIMESTAMP,
             FALSE
         );

-- ============================================================
-- BRAND IDENTITY
-- ============================================================
INSERT INTO brand_identity (
    id,
    project_id,
    brand_name,
    primary_color,
    secondary_color,
    tertiary_color,
    font_family,
    logo_url,
    description,
    created_at
) VALUES (
             '8a52f3e2-0c17-4f43-9b39-666666666666',
             '1f9a554d-7520-4416-8eb2-555555555555',
             'iMeterRecorder',
             '#26c998',
             '#444dcf',
             '#2B0B98',
             'OpenSansRegular',
             'https://codexo.dev/assets/imeterrecorder-logo.svg',
             'Default brand identity for the iMeterRecorder demo application.',
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- PROJECT-PLATFORM ASSOCIATION
-- ============================================================
INSERT INTO project_platform (
    id,
    project_id,
    platform_id,
    is_active,
    created_at
) VALUES (
             'aa1be66a-d1f4-4a0e-a3ad-777777777777',
             '1f9a554d-7520-4416-8eb2-555555555555',
             'f5b31971-b864-4e62-a4c0-444444444444',
             TRUE,
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- PROJECT API KEY
-- ============================================================
INSERT INTO project_api_key (
    id,
    project_platform_id,
    api_key,
    is_active,
    created_at,
    expires_at,
    last_used_at
) VALUES (
             'pk-demo-111111111111',
             'aa1be66a-d1f4-4a0e-a3ad-777777777777',
             'pk_live_imeterrecorder_demo_2024',
             TRUE,
             CURRENT_TIMESTAMP,
             DATEADD('DAY', 90, CURRENT_TIMESTAMP),
             NULL
         );

-- ============================================================
-- THEME DEFINITIONS
-- ============================================================
INSERT INTO platform_theme (
    id,
    project_platform_id,
    theme_name,
    display_name,
    is_default,
    description,
    created_at
) VALUES (
             'theme-light-111111111111',
             'aa1be66a-d1f4-4a0e-a3ad-777777777777',
             'light',
             'Light Theme',
             TRUE,
             'Default light theme for iMeterRecorder',
             CURRENT_TIMESTAMP
         );

INSERT INTO platform_theme (
    id,
    project_platform_id,
    theme_name,
    display_name,
    is_default,
    description,
    created_at
) VALUES (
             'theme-dark-222222222222',
             'aa1be66a-d1f4-4a0e-a3ad-777777777777',
             'dark',
             'Dark Theme',
             FALSE,
             'Dark theme for iMeterRecorder',
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- BUTTON STYLE DEFINITIONS
-- ============================================================

-- Light Theme - Primary Button
INSERT INTO dotnetmaui_crossplatform_button_style (
    id,
    theme_id,
    style_key,
    text,
    text_color,
    background_color,
    opacity,
    is_visible,
    is_enabled,
    font_family,
    font_size,
    font_attributes,
    character_spacing,
    line_break_mode,
    text_transform,
    padding,
    margin,
    height_request,
    width_request,
    min_height_request,
    min_width_request,
    horizontal_options,
    vertical_options,
    content_layout,
    border_color,
    border_width,
    corner_radius,
    image_source,
    semantic_description,
    semantic_hint,
    created_at
) VALUES (
             'bdfc7632-35c7-4a9c-852c-111111111111',
             'theme-light-111111111111',
             'default_button_style',
             'Primary Button',
             '#FFFFFF',
             '#729af7',
             1.0,
             TRUE,
             TRUE,
             'OpenSansRegular',
             16.0,
             'Bold',
             0.0,
             'NoWrap',
             'None',
             '16,10',
             '0',
             -1.0,
             -1.0,
             44.0,
             44.0,
             'Fill',
             'Center',
             'Left,10',
             '#26c998',
             1,
             8,
             'icon_save.png',
             'Primary action button',
             'Double tap to activate',
             CURRENT_TIMESTAMP
         );

-- Dark Theme - Primary Button
INSERT INTO dotnetmaui_crossplatform_button_style (
    id,
    theme_id,
    style_key,
    text,
    text_color,
    background_color,
    opacity,
    is_visible,
    is_enabled,
    font_family,
    font_size,
    font_attributes,
    character_spacing,
    line_break_mode,
    text_transform,
    padding,
    margin,
    height_request,
    width_request,
    min_height_request,
    min_width_request,
    horizontal_options,
    vertical_options,
    content_layout,
    border_color,
    border_width,
    corner_radius,
    image_source,
    semantic_description,
    semantic_hint,
    created_at
) VALUES (
             'bdfc7632-35c7-4a9c-852c-222222222222',
             'theme-dark-222222222222',
             'default_button_style',
             'Primary Button',
             '#FFFFFF',
             '#729af7',
             1.0,
             TRUE,
             TRUE,
             'OpenSansRegular',
             16.0,
             'Bold',
             0.0,
             'NoWrap',
             'None',
             '16,10',
             '0',
             -1.0,
             -1.0,
             44.0,
             44.0,
             'Fill',
             'Center',
             'Left,10',
             '#26c998',
             1,
             8,
             'icon_save.png',
             'Primary action button',
             'Double tap to activate',
             CURRENT_TIMESTAMP
         );

-- Light Theme - Secondary Button
INSERT INTO dotnetmaui_crossplatform_button_style (
    id,
    theme_id,
    style_key,
    text,
    text_color,
    background_color,
    opacity,
    is_visible,
    is_enabled,
    font_family,
    font_size,
    font_attributes,
    character_spacing,
    line_break_mode,
    text_transform,
    padding,
    margin,
    height_request,
    width_request,
    min_height_request,
    min_width_request,
    horizontal_options,
    vertical_options,
    content_layout,
    border_color,
    border_width,
    corner_radius,
    image_source,
    semantic_description,
    semantic_hint,
    created_at
) VALUES (
             'c3dbc5af-6658-4d86-9e11-333333333333',
             'theme-light-111111111111',
             'Secondary',
             'Secondary Button',
             '#FFFFFF',
             '#3f733c',
             1.0,
             TRUE,
             TRUE,
             'OpenSansRegular',
             14.0,
             'None',
             0.0,
             'NoWrap',
             'None',
             '12,8',
             '0',
             -1.0,
             -1.0,
             44.0,
             44.0,
             'Fill',
             'Center',
             'Left,8',
             '#444dcf',
             1,
             4,
             NULL,
             'Secondary action button',
             'Double tap to activate',
             CURRENT_TIMESTAMP
         );

-- Dark Theme - Secondary Button
INSERT INTO dotnetmaui_crossplatform_button_style (
    id,
    theme_id,
    style_key,
    text,
    text_color,
    background_color,
    opacity,
    is_visible,
    is_enabled,
    font_family,
    font_size,
    font_attributes,
    character_spacing,
    line_break_mode,
    text_transform,
    padding,
    margin,
    height_request,
    width_request,
    min_height_request,
    min_width_request,
    horizontal_options,
    vertical_options,
    content_layout,
    border_color,
    border_width,
    corner_radius,
    image_source,
    semantic_description,
    semantic_hint,
    created_at
) VALUES (
             'c3dbc5af-6658-4d86-9e11-444444444444',
             'theme-dark-222222222222',
             'Secondary',
             'Secondary Button',
             '#8c8c8c',
             '#0e300c',
             1.0,
             TRUE,
             TRUE,
             'OpenSansRegular',
             14.0,
             'None',
             0.0,
             'NoWrap',
             'None',
             '12,8',
             '0',
             -1.0,
             -1.0,
             44.0,
             44.0,
             'Fill',
             'Center',
             'Left,8',
             '#444dcf',
             1,
             4,
             NULL,
             'Secondary action button',
             'Double tap to activate',
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- BUTTON SHADOWS
-- ============================================================

-- Light Primary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (
    id,
    button_style_id,
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'd1e2f3a4-b5c6-47d8-89e0-111111111111',
             'bdfc7632-35c7-4a9c-852c-111111111111',
             '#000000',
             0.3,
             8.0,
             '0,4',
             CURRENT_TIMESTAMP
         );

-- Dark Primary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (
    id,
    button_style_id,
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'd1e2f3a4-b5c6-47d8-89e0-222222222222',
             'bdfc7632-35c7-4a9c-852c-222222222222',
             '#8c8c8c',
             0.3,
             8.0,
             '0,4',
             CURRENT_TIMESTAMP
         );

-- Light Secondary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (
    id,
    button_style_id,
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'e2f3a4b5-c6d7-48e9-90f1-333333333333',
             'c3dbc5af-6658-4d86-9e11-333333333333',
             '#000000',
             0.2,
             6.0,
             '0,3',
             CURRENT_TIMESTAMP
         );

-- Dark Secondary Button Shadow
INSERT INTO dotnetmaui_crossplatform_button_shadow (
    id,
    button_style_id,
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'e2f3a4b5-c6d7-48e9-90f1-444444444444',
             'c3dbc5af-6658-4d86-9e11-444444444444',
             '#8c8c8c',
             0.2,
             6.0,
             '0,3',
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- BUTTON VISUAL STATES
-- ============================================================

-- -- Light Primary - Normal
-- INSERT INTO dotnetmaui_crossplatform_button_visualstate (
--     id,
--     button_style_id,
--     name,
--     opacity,
--     text_color,
--     background_color,
--     border_color
-- ) VALUES (
--              'f1a2b3c4-d5e6-47f8-89a0-111111111111',
--              'bdfc7632-35c7-4a9c-852c-111111111111',
--              'Normal',
--              1.0,
--              '#FFFFFF',
--              '#26c998',
--              '#26c998'
--          );

-- Light Primary - Disabled
INSERT INTO dotnetmaui_crossplatform_button_visualstate (
    id,
    button_style_id,
    name,
    opacity,
    text_color,
    background_color,
    border_color
) VALUES (
             'f2b3c4d5-e6f7-48a9-91b2-111111111111',
             'bdfc7632-35c7-4a9c-852c-111111111111',
             'Disabled',
             0.5,
             '#999999',
             '#CCCCCC',
             '#CCCCCC'
         );

-- -- Light Primary - PointerOver
-- INSERT INTO dotnetmaui_crossplatform_button_visualstate (
--     id,
--     button_style_id,
--     name,
--     opacity,
--     text_color,
--     background_color,
--     border_color
-- ) VALUES (
--              'f3c4d5e6-f7a8-49b0-92c3-111111111111',
--              'bdfc7632-35c7-4a9c-852c-111111111111',
--              'PointerOver',
--              1.0,
--              '#FFFFFF',
--              '#1fb582',
--              '#1fb582'
--          );
--
-- -- Light Primary - Pressed
-- INSERT INTO dotnetmaui_crossplatform_button_visualstate (
--     id,
--     button_style_id,
--     name,
--     opacity,
--     text_color,
--     background_color,
--     border_color
-- ) VALUES (
--              'f4d5e6f7-a8b9-40c1-93d4-111111111111',
--              'bdfc7632-35c7-4a9c-852c-111111111111',
--              'Pressed',
--              0.9,
--              '#FFFFFF',
--              '#18a06d',
--              '#18a06d'
--          );

-- -- Dark Primary - Normal
-- INSERT INTO dotnetmaui_crossplatform_button_visualstate (
--     id,
--     button_style_id,
--     name,
--     opacity,
--     text_color,
--     background_color,
--     border_color
-- ) VALUES (
--              'f1a2b3c4-d5e6-47f8-89a0-222222222222',
--              'bdfc7632-35c7-4a9c-852c-222222222222',
--              'Normal',
--              1.0,
--              '#8c8c8c',
--              '#401309',
--              '#26c998'
--          );

-- Dark Primary - Disabled
INSERT INTO dotnetmaui_crossplatform_button_visualstate (
    id,
    button_style_id,
    name,
    opacity,
    text_color,
    background_color,
    border_color
) VALUES (
             'f2b3c4d5-e6f7-48a9-91b2-222222222222',
             'bdfc7632-35c7-4a9c-852c-222222222222',
             'Disabled',
             0.5,
             '#916e6e',
             '#0e4009',
             '#CCCCCC'
         );

-- -- Dark Primary - PointerOver
-- INSERT INTO dotnetmaui_crossplatform_button_visualstate (
--     id,
--     button_style_id,
--     name,
--     opacity,
--     text_color,
--     background_color,
--     border_color
-- ) VALUES (
--              'f3c4d5e6-f7a8-49b0-92c3-222222222222',
--              'bdfc7632-35c7-4a9c-852c-222222222222',
--              'PointerOver',
--              1.0,
--              '#66848a',
--              '#092540',
--              '#1fb582'
--          );
--
-- -- Dark Primary - Pressed
-- INSERT INTO dotnetmaui_crossplatform_button_visualstate (
--     id,
--     button_style_id,
--     name,
--     opacity,
--     text_color,
--     background_color,
--     border_color
-- ) VALUES (
--              'f4d5e6f7-a8b9-40c1-93d4-222222222222',
--              'bdfc7632-35c7-4a9c-852c-222222222222',
--              'Pressed',
--              0.9,
--              '#517c85',
--              '#09402b',
--              '#18a06d'
--          );

-- ============================================================
-- VISUAL STATE SHADOWS
-- ============================================================

-- -- Light Primary - Normal State Shadow
-- INSERT INTO dotnetmaui_crossplatform_button_visualstate_shadow (
--     id,
--     button_visual_status_style_id,
--     shadow_brush,
--     shadow_opacity,
--     shadow_radius,
--     shadow_offset,
--     created_at
-- ) VALUES (
--              'b1c2d3e4-f5a6-47b8-90c1-111111111111',
--              'f1a2b3c4-d5e6-47f8-89a0-111111111111',
--              '#000000',
--              0.3,
--              8.0,
--              '0,4',
--              CURRENT_TIMESTAMP
--          );
--
-- -- Dark Primary - Normal State Shadow
-- INSERT INTO dotnetmaui_crossplatform_button_visualstate_shadow (
--     id,
--     button_visual_status_style_id,
--     shadow_brush,
--     shadow_opacity,
--     shadow_radius,
--     shadow_offset,
--     created_at
-- ) VALUES (
--              'b1c2d3e4-f5a6-47b8-90c1-222222222222',
--              'f1a2b3c4-d5e6-47f8-89a0-222222222222',
--              '#696969',
--              0.3,
--              8.0,
--              '0,4',
--              CURRENT_TIMESTAMP
--          );

-- ============================================================
-- BORDER STYLE DEFINITIONS
-- ============================================================

-- Light Theme - Default Border
INSERT INTO dotnetmaui_crossplatform_border_style (
    id,
    theme_id,
    style_key,
    background,
    stroke,
    stroke_thickness,
    stroke_dash_array,
    stroke_dash_offset,
    stroke_line_cap,
    stroke_line_join,
    stroke_miter_limit,
    stroke_shape,
    padding,
    height_request,
    width_request,
    minimum_height_request,
    minimum_width_request,
    maximum_height_request,
    maximum_width_request,
    horizontal_options,
    vertical_options,
    margin,
    is_visible,
    is_enabled,
    opacity,
    input_transparent,
    anchor_x,
    anchor_y,
    rotation,
    rotation_x,
    rotation_y,
    scale,
    scale_x,
    scale_y,
    translation_x,
    translation_y,
    z_index,
    flow_direction,
    automation_id,
    created_at
) VALUES (
             'border-light-111111111111',
             'theme-light-111111111111',
             'default_border_style',
             '#f5f5f5',
             '#C8C8C8',
             1.0,
             '0',
             0.0,
             'FLAT',
             'MITER',
             10.0,
             'RoundRectangle 12',
             '5,5',
             -1.0,
             -1.0,
             -1.0,
             -1.0,
             1.7976931348623157E308,
             1.7976931348623157E308,
             'FILL',
             'FILL',
             '5,5',
             TRUE,
             TRUE,
             1.0,
             FALSE,
             0.5,
             0.5,
             0.0,
             0.0,
             0.0,
             1.0,
             1.0,
             1.0,
             0.0,
             0.0,
             0,
             'MATCH_PARENT',
             '',
             CURRENT_TIMESTAMP
         );

-- Dark Theme - Default Border
INSERT INTO dotnetmaui_crossplatform_border_style (
    id,
    theme_id,
    style_key,
    background,
    stroke,
    stroke_thickness,
    stroke_dash_array,
    stroke_dash_offset,
    stroke_line_cap,
    stroke_line_join,
    stroke_miter_limit,
    stroke_shape,
    padding,
    height_request,
    width_request,
    minimum_height_request,
    minimum_width_request,
    maximum_height_request,
    maximum_width_request,
    horizontal_options,
    vertical_options,
    margin,
    is_visible,
    is_enabled,
    opacity,
    input_transparent,
    anchor_x,
    anchor_y,
    rotation,
    rotation_x,
    rotation_y,
    scale,
    scale_x,
    scale_y,
    translation_x,
    translation_y,
    z_index,
    flow_direction,
    automation_id,
    created_at
) VALUES (
             'border-dark-222222222222',
             'theme-dark-222222222222',
             'default_border_style',
             '#3d516b',
             '#6E6E6E',
             1.0,
             '0',
             0.0,
             'FLAT',
             'MITER',
             10.0,
             'RoundRectangle 12',
             '5,5',
             -1.0,
             -1.0,
             -1.0,
             -1.0,
             1.7976931348623157E308,
             1.7976931348623157E308,
             'FILL',
             'FILL',
             '5,5',
             TRUE,
             TRUE,
             1.0,
             FALSE,
             0.5,
             0.5,
             0.0,
             0.0,
             0.0,
             1.0,
             1.0,
             1.0,
             0.0,
             0.0,
             0,
             'MATCH_PARENT',
             '',
             CURRENT_TIMESTAMP
         );



-- ============================================================
-- BORDER SHADOWS
-- ============================================================

-- Light Border Shadow
INSERT INTO dotnetmaui_crossplatform_border_shadow (
    id,
    border_style_id,
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'bshadow-light-111111111111',
             'border-light-111111111111',
             '#3d516b',
             0.3,
             3.0,
             '-0.3,0.3',
             CURRENT_TIMESTAMP
         );

-- Dark Border Shadow
INSERT INTO dotnetmaui_crossplatform_border_shadow (
    id,
    border_style_id,
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'bshadow-dark-222222222222',
             'border-dark-222222222222',
             '#f5f5f5',
             0.3,
             3.0,
             '-0.3,0.3',
             CURRENT_TIMESTAMP
         );


-- ============================================================
-- BORDER VISUAL STATES
-- ============================================================

-- -- Light Border - Normal
-- INSERT INTO dotnetmaui_crossplatform_border_visualstate (
--     id,
--     border_style_id,
--     name,
--     opacity
-- ) VALUES (
--              'b1c2d3e4-f5a6-47b8-90c1-111111111111',
--              'border-light-111111111111',
--              'Normal',
--              1.0
--          );

-- Light Border - Disabled
INSERT INTO dotnetmaui_crossplatform_border_visualstate (
    id,
    border_style_id,
    name,
    opacity
) VALUES (
             'b2c3d4e5-f6a7-48b9-91c2-222222222222',
             'border-light-111111111111',
             'Disabled',
             0.5
         );

-- -- Dark Border - Normal
-- INSERT INTO dotnetmaui_crossplatform_border_visualstate (
--     id,
--     border_style_id,
--     name,
--     opacity
-- ) VALUES (
--              'b3c4d5e6-f7a8-49b0-92c3-333333333333',
--              'border-dark-222222222222',
--              'Normal',
--              1.0
--          );

-- Dark Border - Disabled
INSERT INTO dotnetmaui_crossplatform_border_visualstate (
    id,
    border_style_id,
    name,
    opacity
) VALUES (
             'b4c5d6e7-f8a9-40b1-93c4-444444444444',
             'border-dark-222222222222',
             'Disabled',
             0.5
         );

