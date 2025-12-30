-- ============================================================
-- DATA-02-PROJECTS.SQL
-- Project, brand_identity, project_platform, project_api_key, platform_theme
-- ============================================================
-- Depends on: data-01-base.sql (platform, users)
-- ============================================================

-- ============================================================
-- PROJECT SETUP
-- ============================================================
INSERT INTO project (id, user_id, name, slug, description, created_at, updated_at, is_deleted)
VALUES ('1f9a554d-7520-4416-8eb2-555555555555', '4d0a5d5d-9304-4bd4-80b1-222222222222', 'iMeterRecorder Mobile', 'imeterrecorder', 'Server-driven UI demo project for the iMeterRecorder .NET MAUI app.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, false);

-- ============================================================
-- BRAND IDENTITY
-- ============================================================
INSERT INTO brand_identity (id, project_id, brand_name, primary_color, secondary_color, tertiary_color, font_family, logo_url, description, created_at)
VALUES ('8a52f3e2-0c17-4f43-9b39-666666666666', '1f9a554d-7520-4416-8eb2-555555555555', 'iMeterRecorder', '#26c998', '#444dcf', '#2B0B98', 'OpenSansRegular', 'https://codexo.dev/assets/imeterrecorder-logo.svg', 'Default brand identity for the iMeterRecorder demo application.', CURRENT_TIMESTAMP);

-- ============================================================
-- PROJECT-PLATFORM ASSOCIATION
-- ============================================================
INSERT INTO project_platform (id, project_id, platform_id, is_active, created_at)
VALUES ('aa1be66a-d1f4-4a0e-a3ad-777777777777', '1f9a554d-7520-4416-8eb2-555555555555', 'f5b31971-b864-4e62-a4c0-444444444444', true, CURRENT_TIMESTAMP);

-- ============================================================
-- PROJECT API KEY
-- ============================================================
INSERT INTO project_api_key (id, project_platform_id, api_key, is_active, created_at, expires_at, last_used_at)
VALUES ('pk-demo-111111111111', 'aa1be66a-d1f4-4a0e-a3ad-777777777777', 'pk_live_imeterrecorder_demo_2024', true, CURRENT_TIMESTAMP, DATEADD('DAY', 90, CURRENT_TIMESTAMP), NULL);

-- ============================================================
-- THEME DEFINITIONS
-- ============================================================
INSERT INTO platform_theme (id, project_platform_id, theme_name, display_name, is_default, description, created_at)
VALUES ('theme-light-111111111111', 'aa1be66a-d1f4-4a0e-a3ad-777777777777', 'light', 'Light Theme', true, 'Default light theme for iMeterRecorder', CURRENT_TIMESTAMP);

INSERT INTO platform_theme (id, project_platform_id, theme_name, display_name, is_default, description, created_at)
VALUES ('theme-dark-222222222222', 'aa1be66a-d1f4-4a0e-a3ad-777777777777', 'dark', 'Dark Theme', false, 'Dark theme for iMeterRecorder', CURRENT_TIMESTAMP);
