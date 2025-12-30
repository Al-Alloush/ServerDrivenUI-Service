-- ============================================================
-- DATA-01-BASE.SQL
-- Platform, subscription_plan, users, user_subscription
-- ============================================================
-- This file contains base/foundational data that all other
-- tables depend on. Must be executed first.
-- ============================================================

-- ============================================================
-- PLATFORM DEFINITION
-- ============================================================
INSERT INTO platform (id, code, name, description)
VALUES ('f5b31971-b864-4e62-a4c0-444444444444', 'DOTNET_MAUI_CROSS_PLATFORM', '.NET MAUI Cross Platform', 'Cross-platform mobile / desktop client built with .NET MAUI.');

-- ============================================================
-- SUBSCRIPTION PLAN SETUP
-- ============================================================
INSERT INTO subscription_plan (id, code, name, description, max_projects, max_platforms_per_project, monthly_price, is_default, default_duration_days, created_at)
VALUES ('b9d2b44c-2f58-4ff3-a71b-111111111111', 'FREE_3M', 'Free (3 months)', 'Default free tier: 1 project and 1 platform for 3 months.', 1, 1, 0.00, true, 90, CURRENT_TIMESTAMP);

-- ============================================================
-- DEMO USER SETUP
-- ============================================================
INSERT INTO users (id, keycloak_user_id, email, display_name, created_at)
VALUES ('4d0a5d5d-9304-4bd4-80b1-222222222222', 'demo-keycloak-imeterrecorder', 'demo.imeterrecorder@codexo.dev', 'iMeterRecorder Demo Owner', CURRENT_TIMESTAMP);

-- ============================================================
-- USER SUBSCRIPTION ASSIGNMENT
-- ============================================================
INSERT INTO user_subscription (id, user_id, subscription_plan_id, status, started_at, expires_at, created_at)
VALUES ('e71b0c59-eb22-4bc6-9ab3-333333333333', '4d0a5d5d-9304-4bd4-80b1-222222222222', 'b9d2b44c-2f58-4ff3-a71b-111111111111', 'ACTIVE', CURRENT_TIMESTAMP, NULL, CURRENT_TIMESTAMP);
