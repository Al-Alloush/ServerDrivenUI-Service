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
    background_color_token,
    background_color_custom,
    text_color_token,
    text_color_custom,
    border_color_token,
    border_color_custom,
    corner_radius,
    border_width,
    created_at
) VALUES (
             'bdfc7632-35c7-4a9c-852c-888888888888',
             'aa1be66a-d1f4-4a0e-a3ad-777777777777',
             'PrimaryButton',
             'PRIMARY',          -- use brand.primaryColor (#26c998)
             NULL,
             'CUSTOM',           -- custom text color
             '#FFFFFF',
             'PRIMARY',          -- border == primary
             NULL,
             8,
             1,
             CURRENT_TIMESTAMP
         );

-- Secondary button
INSERT INTO dotnet_maui_button_style (
    id,
    project_platform_id,
    style_key,
    background_color_token,
    background_color_custom,
    text_color_token,
    text_color_custom,
    border_color_token,
    border_color_custom,
    corner_radius,
    border_width,
    created_at
) VALUES (
             'c3dbc5af-6658-4d86-94d9-999999999999',
             'aa1be66a-d1f4-4a0e-a3ad-777777777777',
             'SecondaryButton',
             'SECONDARY',        -- use brand.secondaryColor (#444dcf)
             NULL,
             'CUSTOM',
             '#FFFFFF',
             'SECONDARY',
             NULL,
             4,
             1,
             CURRENT_TIMESTAMP
         );
