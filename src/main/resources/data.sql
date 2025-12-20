-- ===== Subscription plan: Free tier 3 months =====
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

-- ===== Demo user (owner of iMeterRecorder) =====
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

-- ===== User subscription -> FREE_3M =====
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

-- ===== Platform: .NET MAUI cross-platform =====
INSERT INTO platform (
    id,
    code,
    name,
    description
) VALUES (
             'f5b31971-b864-4e62-a4c0-444444444444',
             'DOTNET_MAUI',
             '.NET MAUI Cross Platform',
             'Cross-platform mobile / desktop client built with .NET MAUI.'
         );

-- ===== Project: iMeterRecorder =====
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

-- ===== Brand identity for iMeterRecorder =====
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

-- ===== ProjectPlatform: iMeterRecorder on .NET MAUI =====
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

-- ===== Button styles for iMeterRecorder / .NET MAUI =====
-- Primary button
INSERT INTO dotnet_maui_button_style (
    id,
    project_platform_id,
    style_key,
    -- Appearance
    text,
    text_color,
    background_color,
    opacity,
    is_visible,
    is_enabled,
    -- Typography
    font_family,
    font_size,
    font_attributes,
    character_spacing,
    line_break_mode,
    text_transform,
    -- Layout
    padding,
    margin,
    height_request,
    width_request,
    min_height_request,
    min_width_request,
    horizontal_options,
    vertical_options,
    content_layout,
    -- Border
    border_color,
    border_width,
    corner_radius,
    -- Image
    image_source,
    -- Accessibility
    semantic_description,
    semantic_hint,
    created_at
) VALUES (
             'bdfc7632-35c7-4a9c-852c-888888888888',
             'aa1be66a-d1f4-4a0e-a3ad-777777777777',
             'PrimaryButton',
             'Primary Button',
             '#FFFFFF',
             '#26c998',
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

-- Secondary button
INSERT INTO dotnet_maui_button_style (
    id,
    project_platform_id,
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
             'c3dbc5af-6658-4d86-94d9-999999999999',
             'aa1be66a-d1f4-4a0e-a3ad-777777777777',
             'SecondaryButton',
             'Secondary Button',
             '#FFFFFF',
             '#444dcf',
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

-- Shadow for Primary Button
INSERT INTO dotnet_maui_button_shadow (
    id,
    button_style_id,
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'd1e2f3a4-b5c6-47d8-89e0-aaaaaaaaaaaa',
             'bdfc7632-35c7-4a9c-852c-888888888888',
             '#000000',
             0.3,
             8.0,
             '0,4',
             CURRENT_TIMESTAMP
         );

-- Shadow for Secondary Button
INSERT INTO dotnet_maui_button_shadow (
    id,
    button_style_id,
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'e2f3a4b5-c6d7-48e9-90f1-bbbbbbbbbbbb',
             'c3dbc5af-6658-4d86-94d9-999999999999',
             '#000000',
             0.2,
             6.0,
             '0,3',
             CURRENT_TIMESTAMP
         );

-- Visual states for Primary Button
-- Normal state
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,
    background_color,
    border_color
) VALUES (
             'f1a2b3c4-d5e6-47f8-90a1-cccccccccccc',
             'bdfc7632-35c7-4a9c-852c-888888888888',
             'Normal',
             1.0,
             '#FFFFFF',
             '#26c998',
             '#26c998'
         );

-- Disabled state
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,
    background_color,
    border_color
) VALUES (
             'f2b3c4d5-e6f7-48a9-91b2-dddddddddddd',
             'bdfc7632-35c7-4a9c-852c-888888888888',
             'Disabled',
             0.5,
             '#999999',
             '#CCCCCC',
             '#CCCCCC'
         );

-- PointerOver state
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,
    background_color,
    border_color
) VALUES (
             'f3c4d5e6-f7a8-49b0-92c3-eeeeeeeeeeee',
             'bdfc7632-35c7-4a9c-852c-888888888888',
             'PointerOver',
             1.0,
             '#FFFFFF',
             '#1fb582',
             '#1fb582'
         );

-- Pressed state
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,
    background_color,
    border_color
) VALUES (
             'f4d5e6f7-a8b9-40c1-93d4-ffffffffffff',
             'bdfc7632-35c7-4a9c-852c-888888888888',
             'Pressed',
             0.9,
             '#FFFFFF',
             '#18a06d',
             '#18a06d'
         );

-- Visual states for Secondary Button
-- Normal state
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,
    background_color,
    border_color
) VALUES (
             'a1b2c3d4-e5f6-47a8-90b9-111111111112',
             'c3dbc5af-6658-4d86-94d9-999999999999',
             'Normal',
             1.0,
             '#FFFFFF',
             '#444dcf',
             '#444dcf'
         );

-- Disabled state
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,
    background_color,
    border_color
) VALUES (
             'a2c3d4e5-f6a7-48b9-91c0-222222222223',
             'c3dbc5af-6658-4d86-94d9-999999999999',
             'Disabled',
             0.5,
             '#999999',
             '#CCCCCC',
             '#CCCCCC'
         );

-- PointerOver state
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,
    background_color,
    border_color
) VALUES (
             'a3d4e5f6-a7b8-49c0-92d1-333333333334',
             'c3dbc5af-6658-4d86-94d9-999999999999',
             'PointerOver',
             1.0,
             '#FFFFFF',
             '#3640b8',
             '#3640b8'
         );

-- Pressed state
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,
    background_color,
    border_color
) VALUES (
             'a4e5f6a7-b8c9-40d1-93e2-444444444445',
             'c3dbc5af-6658-4d86-94d9-999999999999',
             'Pressed',
             0.9,
             '#FFFFFF',
             '#2833a1',
             '#2833a1'
         );

-- Shadow for Primary Button Normal state
INSERT INTO dotnet_maui_button_visual_state_shadow (
    id,
    button_visual_status_style_id,
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'b1c2d3e4-f5a6-47b8-90c1-aaaaaaaaaa11',
             'f1a2b3c4-d5e6-47f8-90a1-cccccccccccc',
             '#000000',
             0.3,
             8.0,
             '0,4',
             CURRENT_TIMESTAMP
         );

-- Shadow for Secondary Button Normal state
INSERT INTO dotnet_maui_button_visual_state_shadow (
    id,
    button_visual_status_style_id,
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'c2d3e4f5-a6b7-48c9-91d2-bbbbbbbbbb22',
             'a1b2c3d4-e5f6-47a8-90b9-111111111112',
             '#000000',
             0.2,
             6.0,
             '0,3',
             CURRENT_TIMESTAMP
         );
