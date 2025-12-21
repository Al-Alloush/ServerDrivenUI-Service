-- ============================================================
-- SUBSCRIPTION PLAN SETUP
-- ============================================================
-- Defines the free tier subscription plan that limits users to
-- 1 project and 1 platform for 90 days
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
-- Creates a demo user account that will own the iMeterRecorder
-- project. This user is linked to Keycloak for authentication.
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
-- Assigns the FREE_3M subscription plan to the demo user
-- Status: ACTIVE means the subscription is currently valid
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
             NULL,  -- No expiration for demo purposes
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- PLATFORM DEFINITION
-- ============================================================
-- Registers .NET MAUI as a supported platform in the system
-- Code: DOTNET_MAUI is used throughout the application to
-- identify this platform type
-- ============================================================

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

-- ============================================================
-- PROJECT SETUP
-- ============================================================
-- Creates the iMeterRecorder project owned by the demo user
-- Slug: 'imeterrecorder' is used in API URLs like:
--   /dotnetmaui/style/imeterrecorder/buttons
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
-- Defines the visual brand identity for the iMeterRecorder app
-- Colors, fonts, and logo that define the app's look and feel
-- These values can be referenced by style definitions
-- ============================================================

INSERT INTO brand_identity (
    id,
    project_id,
    brand_name,
    primary_color,      -- Main brand color: teal/green
    secondary_color,    -- Accent color: purple/blue
    tertiary_color,     -- Third accent: dark purple
    font_family,        -- Default font across the app
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
-- Links the iMeterRecorder project to the .NET MAUI platform
-- is_active: TRUE means this platform is enabled for the project
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
-- THEME DEFINITIONS
-- ============================================================
-- Creates Light and Dark themes for the iMeterRecorder app
-- Each theme will have its own set of button styles
-- ============================================================

-- Light Theme (Default)
INSERT INTO platform_theme (
    id,
    project_platform_id,
    theme_name,
    display_name,
    is_default,          -- This is the default theme
    description,
    created_at
) VALUES (
             'theme-light-111111111111',
             'aa1be66a-d1f4-4a0e-a3ad-777777777777',
             'Light',
             'Light Theme',
             TRUE,
             'Default light theme for iMeterRecorder',
             CURRENT_TIMESTAMP
         );

-- Dark Theme
INSERT INTO platform_theme (
    id,
    project_platform_id,
    theme_name,
    display_name,
    is_default,          -- Not the default theme
    description,
    created_at
) VALUES (
             'theme-dark-222222222222',
             'aa1be66a-d1f4-4a0e-a3ad-777777777777',
             'Dark',
             'Dark Theme',
             FALSE,
             'Dark theme for iMeterRecorder',
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- BUTTON STYLE DEFINITIONS
-- ============================================================
-- Each button style defines the complete visual appearance
-- and behavior of a button in the MAUI application
-- Grouped by: Theme (Light/Dark) and Type (Primary/Secondary)
-- ============================================================

-- ------------------------------------------------------------
-- LIGHT THEME - Primary Button
-- ------------------------------------------------------------
-- Main call-to-action button for light theme
-- Uses brand colors with white text on teal background
-- ------------------------------------------------------------

INSERT INTO dotnet_maui_button_style (
    id,
    theme_id,                    -- Links to Light theme
    style_key,                   -- Identifier used in XAML/code
    -- Appearance properties
    text,                        -- Default button text
    text_color,                  -- White text
    background_color,            -- Blue background
    opacity,                     -- Fully opaque
    is_visible,                  -- Initially visible
    is_enabled,                  -- Initially enabled
    -- Typography properties
    font_family,                 -- Uses brand font
    font_size,                   -- 16pt text
    font_attributes,             -- Bold text
    character_spacing,           -- No extra spacing
    line_break_mode,             -- Don't wrap text
    text_transform,              -- No case transformation
    -- Layout properties
    padding,                     -- Internal spacing: 16px horizontal, 10px vertical
    margin,                      -- No external margin
    height_request,              -- -1 = auto height
    width_request,               -- -1 = auto width
    min_height_request,          -- Minimum 44px for touch targets
    min_width_request,           -- Minimum 44px for touch targets
    horizontal_options,          -- Fill available horizontal space
    vertical_options,            -- Center vertically
    content_layout,              -- Icon/content position: Left with 10px spacing
    -- Border properties
    border_color,                -- Teal border matches brand
    border_width,                -- 1px border
    corner_radius,               -- 8px rounded corners
    -- Image properties
    image_source,                -- Icon file name
    -- Accessibility properties
    semantic_description,        -- Screen reader description
    semantic_hint,               -- Screen reader usage hint
    created_at
) VALUES (
             'bdfc7632-LightPrimaryButton',
             'theme-light-111111111111',
             'PrimaryButton',
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

-- ------------------------------------------------------------
-- DARK THEME - Primary Button
-- ------------------------------------------------------------
-- Main call-to-action button for dark theme
-- Uses muted colors suitable for dark backgrounds
-- ------------------------------------------------------------

INSERT INTO dotnet_maui_button_style (
    id,
    theme_id,                    -- Links to Light theme
    style_key,                   -- Identifier used in XAML/code
    -- Appearance properties
    text,                        -- Default button text
    text_color,                  -- White text
    background_color,            -- Blue background
    opacity,                     -- Fully opaque
    is_visible,                  -- Initially visible
    is_enabled,                  -- Initially enabled
    -- Typography properties
    font_family,                 -- Uses brand font
    font_size,                   -- 16pt text
    font_attributes,             -- Bold text
    character_spacing,           -- No extra spacing
    line_break_mode,             -- Don't wrap text
    text_transform,              -- No case transformation
    -- Layout properties
    padding,                     -- Internal spacing: 16px horizontal, 10px vertical
    margin,                      -- No external margin
    height_request,              -- -1 = auto height
    width_request,               -- -1 = auto width
    min_height_request,          -- Minimum 44px for touch targets
    min_width_request,           -- Minimum 44px for touch targets
    horizontal_options,          -- Fill available horizontal space
    vertical_options,            -- Center vertically
    content_layout,              -- Icon/content position: Left with 10px spacing
    -- Border properties
    border_color,                -- Teal border matches brand
    border_width,                -- 1px border
    corner_radius,               -- 8px rounded corners
    -- Image properties
    image_source,                -- Icon file name
    -- Accessibility properties
    semantic_description,        -- Screen reader description
    semantic_hint,               -- Screen reader usage hint
    created_at
) VALUES (
             'bdfc-DarPrButtonLightPrimaryButton',
             'theme-dark-222222222222',
             'PrimaryButton',
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

-- ------------------------------------------------------------
-- LIGHT THEME - Secondary Button
-- ------------------------------------------------------------
-- Less prominent action button for light theme
-- Uses darker green background with white text
-- ------------------------------------------------------------

INSERT INTO dotnet_maui_button_style (
    id,
    theme_id,
    style_key,
    text,
    text_color,
    background_color,            -- Darker green
    opacity,
    is_visible,
    is_enabled,
    font_family,
    font_size,                   -- Slightly smaller: 14pt
    font_attributes,             -- Normal weight (not bold)
    character_spacing,
    line_break_mode,
    text_transform,
    padding,                     -- Less padding: 12x8
    margin,
    height_request,
    width_request,
    min_height_request,
    min_width_request,
    horizontal_options,
    vertical_options,
    content_layout,              -- Icon with 8px spacing
    border_color,                -- Purple border
    border_width,
    corner_radius,               -- Less rounded: 4px
    image_source,                -- No icon
    semantic_description,
    semantic_hint,
    created_at
) VALUES (
             'c3dbc5af-lightSecondaryButton',
             'theme-light-111111111111',
             'SecondaryButton',
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

-- ------------------------------------------------------------
-- DARK THEME - Secondary Button
-- ------------------------------------------------------------
-- Less prominent action button for dark theme
-- Uses very dark green background with gray text
-- ------------------------------------------------------------

INSERT INTO dotnet_maui_button_style (
    id,
    theme_id,
    style_key,
    text,
    text_color,                  -- Medium gray text
    background_color,            -- Very dark green
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
             'c3dbc5af-6658-4d86-d99999999999',
             'theme-dark-222222222222',
             'SecondaryButton',
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
-- Defines drop shadow effects for button styles
-- Each button style has one base shadow configuration
-- Format for offset: "x,y" where x=horizontal, y=vertical
-- ============================================================

-- Light Primary Button Shadow
INSERT INTO dotnet_maui_button_shadow (
    id,
    button_style_id,             -- Links to Light Primary button
    shadow_brush,                -- Black shadow
    shadow_opacity,              -- 30% opacity
    shadow_radius,               -- 8px blur radius
    shadow_offset,               -- 0px right, 4px down
    created_at
) VALUES (
             'd1e2f3a4-b5c6-47d8-89e0-LightPrimaryButtonShadow',
             'bdfc7632-LightPrimaryButton',
             '#000000',
             0.3,
             8.0,
             '0,4',
             CURRENT_TIMESTAMP
         );

-- Dark Primary Button Shadow
INSERT INTO dotnet_maui_button_shadow (
    id,
    button_style_id,             -- Links to Dark Primary button
    shadow_brush,                -- Gray shadow (lighter for dark theme)
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'd1e2f3a4-b5c6-47d8-89e0-DarkPrimaryButtonShadow',
             'bdfc-DarPrButtonLightPrimaryButton',
             '#8c8c8c',
             0.3,
             8.0,
             '0,4',
             CURRENT_TIMESTAMP
         );

-- Light Secondary Button Shadow (subtler than primary)
INSERT INTO dotnet_maui_button_shadow (
    id,
    button_style_id,
    shadow_brush,
    shadow_opacity,              -- 20% opacity (less prominent)
    shadow_radius,               -- 6px blur (smaller)
    shadow_offset,               -- 3px down (less distance)
    created_at
) VALUES (
             'e2f3a4b5-c6d7-48e9-90f1-laaaaaaaaaaa',
             'c3dbc5af-lightSecondaryButton',
             '#000000',
             0.2,
             6.0,
             '0,3',
             CURRENT_TIMESTAMP
         );

-- Dark Secondary Button Shadow
INSERT INTO dotnet_maui_button_shadow (
    id,
    button_style_id,
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'e2f3a4b5-c6d7-48e9-90f1-lbbbbbbbbbbb',
             'c3dbc5af-6658-4d86-d99999999999',
             '#8c8c8c',
             0.2,
             6.0,
             '0,3',
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- BUTTON VISUAL STATES
-- ============================================================
-- Defines how buttons look in different interaction states:
--   - Normal: Default appearance
--   - Disabled: When button is not interactive
--   - PointerOver: Mouse hover or touch feedback
--   - Pressed: Active press/tap state
-- Each state can override colors, opacity, and other properties
-- ============================================================

-- ------------------------------------------------------------
-- LIGHT PRIMARY BUTTON - Visual States
-- ------------------------------------------------------------

-- Normal State (baseline appearance)
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,                        -- State name used in MAUI
    opacity,
    text_color,
    background_color,            -- Brand teal
    border_color
) VALUES (
             'f1a2b3c4-LightPrimaryButtonVisualStateNormal',
             'bdfc7632-LightPrimaryButton',
             'Normal',
             1.0,
             '#FFFFFF',
             '#26c998',
             '#26c998'
         );

-- Disabled State (button cannot be interacted with)
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,                     -- 50% opacity to indicate disabled
    text_color,                  -- Gray text
    background_color,            -- Light gray background
    border_color
) VALUES (
             'f2b3c4d5-e6f7-48a9-91b2-lddddddddddd',
             'bdfc7632-LightPrimaryButton',
             'Disabled',
             0.5,
             '#999999',
             '#CCCCCC',
             '#CCCCCC'
         );

-- PointerOver State (hover/touch feedback)
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,
    background_color,            -- Slightly darker teal
    border_color
) VALUES (
             'f3c4d5e6-f7a8-49b0-92c3-leeeeeeeeeee',
             'bdfc7632-LightPrimaryButton',
             'PointerOver',
             1.0,
             '#FFFFFF',
             '#1fb582',
             '#1fb582'
         );

-- Pressed State (active press/tap)
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,                     -- Slightly transparent for feedback
    text_color,
    background_color,            -- Even darker teal
    border_color
) VALUES (
             'f4d5e6f7-a8b9-40c1-93d4-lfffffffffff',
             'bdfc7632-LightPrimaryButton',
             'Pressed',
             0.9,
             '#FFFFFF',
             '#18a06d',
             '#18a06d'
         );

-- ------------------------------------------------------------
-- DARK PRIMARY BUTTON - Visual States
-- ------------------------------------------------------------

-- Normal State
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,                  -- Gray text for dark theme
    background_color,            -- Dark red-brown
    border_color
) VALUES (
             'f1a2b3c4-DarkPrimaryButtonVisualStateNormal',
             'bdfc-DarPrButtonLightPrimaryButton',
             'Normal',
             1.0,
             '#8c8c8c',
             '#401309',
             '#26c998'
         );

-- Disabled State
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,                  -- Muted red-gray
    background_color,            -- Very dark green
    border_color
) VALUES (
             'f2b3c4d5-e6f7-48a9-91b2-dddddddddddd',
             'bdfc-DarPrButtonLightPrimaryButton',
             'Disabled',
             0.5,
             '#916e6e',
             '#0e4009',
             '#CCCCCC'
         );

-- PointerOver State
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,                  -- Blue-gray
    background_color,            -- Dark blue
    border_color
) VALUES (
             'f3c4d5e6-f7a8-49b0-92c3-deeeeeeeeeee',
             'bdfc-DarPrButtonLightPrimaryButton',
             'PointerOver',
             1.0,
             '#66848a',
             '#092540',
             '#1fb582'
         );

-- Pressed State
INSERT INTO dotnet_maui_button_visual_state (
    id,
    button_style_id,
    name,
    opacity,
    text_color,                  -- Darker blue-gray
    background_color,            -- Dark teal
    border_color
) VALUES (
             'f4d5e6f7-a8b9-40c1-93d4-dfffffffffff',
             'bdfc-DarPrButtonLightPrimaryButton',
             'Pressed',
             0.9,
             '#517c85',
             '#09402b',
             '#18a06d'
         );

-- ============================================================
-- VISUAL STATE SHADOWS
-- ============================================================
-- Separate shadow configurations for specific visual states
-- Allows shadows to change during interactions (e.g., pressed
-- state might have a smaller shadow to simulate depth change)
-- ============================================================

-- Light Primary Button - Normal State Shadow
INSERT INTO dotnet_maui_button_visual_state_shadow (
    id,
    button_visual_status_style_id,  -- Links to Normal visual state
    shadow_brush,
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'b1c2d3e4-f5a6-47b8-90c1-aaaaaaaaaa11',
             'f1a2b3c4-LightPrimaryButtonVisualStateNormal',
             '#000000',
             0.3,
             8.0,
             '0,4',
             CURRENT_TIMESTAMP
         );

-- Dark Primary Button - Normal State Shadow
INSERT INTO dotnet_maui_button_visual_state_shadow (
    id,
    button_visual_status_style_id,
    shadow_brush,                   -- Lighter shadow for dark theme
    shadow_opacity,
    shadow_radius,
    shadow_offset,
    created_at
) VALUES (
             'b1c2d3e4-f5a6-47b8-90c1-aaaaaaaaaa12',
             'f1a2b3c4-DarkPrimaryButtonVisualStateNormal',
             '#696969',
             0.3,
             8.0,
             '0,4',
             CURRENT_TIMESTAMP
         );

-- ============================================================
-- END OF SEED DATA
-- ============================================================
-- This script creates a complete demo environment with:
--   - 1 subscription plan (FREE_3M)
--   - 1 demo user
--   - 1 platform (.NET MAUI)
--   - 1 project (iMeterRecorder)
--   - 1 brand identity
--   - 2 themes (Light and Dark)
--   - 4 button styles (2 per theme: Primary and Secondary)
--   - 4 base shadows (1 per button style)
--   - 8 visual states (4 per Primary button: Normal, Disabled,
--     PointerOver, Pressed)
--   - 2 visual state shadows (for Normal states)
-- ============================================================
