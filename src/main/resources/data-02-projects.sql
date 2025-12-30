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
VALUES ('1f9a554d-7520-4416-8eb2-a3c5d7e9f1b2', '4d0a5d5d-9304-4bd4-80b1-6f8e2c9a3d7b', 'iMeterRecorder Mobile', 'imeterrecorder', 'Server-driven UI demo project for the iMeterRecorder .NET MAUI app.', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 0);

-- ============================================================
-- BRAND IDENTITY
-- ============================================================
INSERT INTO brand_identity (id, project_id, brand_name, primary_color, secondary_color, tertiary_color, font_family, logo_url, description, created_at)
VALUES ('8a52f3e2-0c17-4f43-9b39-b4d6e8f0a2c3', '1f9a554d-7520-4416-8eb2-a3c5d7e9f1b2', 'iMeterRecorder', '#26c998', '#444dcf', '#2B0B98', 'OpenSansRegular', 'https://codexo.dev/assets/imeterrecorder-logo.svg', 'Default brand identity for the iMeterRecorder demo application.', CURRENT_TIMESTAMP);

-- ============================================================
-- PROJECT-PLATFORM ASSOCIATION
-- ============================================================
INSERT INTO project_platform (id, project_id, platform_id, is_active, created_at)
VALUES ('aa1be66a-d1f4-4a0e-a3ad-c5e7f9a1b3d4', '1f9a554d-7520-4416-8eb2-a3c5d7e9f1b2', 'f5b31971-b864-4e62-a4c0-8d3f9e2a1b4c', 1, CURRENT_TIMESTAMP);

-- ============================================================
-- PROJECT API KEY
-- ============================================================
INSERT INTO project_api_key (id, project_platform_id, api_key, is_active, created_at, expires_at, last_used_at)
VALUES ('2b3c4d5e-6f7a-8b9c-0d1e-f2a3b4c5d6e7', 'aa1be66a-d1f4-4a0e-a3ad-c5e7f9a1b3d4', 'pk_live_imeterrecorder_demo_2024', 1, CURRENT_TIMESTAMP, DATEADD(DAY, 90, CURRENT_TIMESTAMP), NULL);

-- ============================================================
-- THEME DEFINITIONS
-- ============================================================
INSERT INTO platform_theme (id, project_platform_id, theme_name, display_name, is_default, description, created_at)
VALUES ('a1b2c3d4-e5f6-7890-abcd-ef1234567890', 'aa1be66a-d1f4-4a0e-a3ad-c5e7f9a1b3d4', 'light', 'Light Theme', 1, 'Default light theme for iMeterRecorder', CURRENT_TIMESTAMP);

INSERT INTO platform_theme (id, project_platform_id, theme_name, display_name, is_default, description, created_at)
VALUES ('b2c3d4e5-f6a7-8901-bcde-f12345678901', 'aa1be66a-d1f4-4a0e-a3ad-c5e7f9a1b3d4', 'dark', 'Dark Theme', 0, 'Dark theme for iMeterRecorder', CURRENT_TIMESTAMP);
