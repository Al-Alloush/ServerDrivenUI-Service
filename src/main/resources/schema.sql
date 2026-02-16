-- ============================================================
-- SCHEMA.SQL - Database Schema Creation
-- ============================================================
-- This file creates all necessary tables for the Server Driven UI Service
-- Executed before data-*.sql files
-- ============================================================

-- ============================================================
-- DROP TABLES (in reverse dependency order)
-- ============================================================
IF OBJECT_ID('project_api_key', 'U') IS NOT NULL DROP TABLE project_api_key;
IF OBJECT_ID('platform_theme', 'U') IS NOT NULL DROP TABLE platform_theme;
IF OBJECT_ID('project_platform', 'U') IS NOT NULL DROP TABLE project_platform;
IF OBJECT_ID('brand_identity', 'U') IS NOT NULL DROP TABLE brand_identity;
IF OBJECT_ID('project', 'U') IS NOT NULL DROP TABLE project;
IF OBJECT_ID('user_subscription', 'U') IS NOT NULL DROP TABLE user_subscription;
IF OBJECT_ID('users', 'U') IS NOT NULL DROP TABLE users;
IF OBJECT_ID('subscription_plan', 'U') IS NOT NULL DROP TABLE subscription_plan;
IF OBJECT_ID('platform', 'U') IS NOT NULL DROP TABLE platform;

-- Drop .NET MAUI style tables
IF OBJECT_ID('dotnetmaui_crossplatform_button_visualstate_shadow', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_button_visualstate_shadow;
IF OBJECT_ID('dotnetmaui_crossplatform_button_visualstate', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_button_visualstate;
IF OBJECT_ID('dotnetmaui_crossplatform_button_shadow', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_button_shadow;
IF OBJECT_ID('dotnetmaui_crossplatform_button_style', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_button_style;

IF OBJECT_ID('dotnetmaui_crossplatform_border_visualstate', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_border_visualstate;
IF OBJECT_ID('dotnetmaui_crossplatform_border_shadow', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_border_shadow;
IF OBJECT_ID('dotnetmaui_crossplatform_border_style', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_border_style;

IF OBJECT_ID('dotnetmaui_crossplatform_label_visualstate_shadow', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_label_visualstate_shadow;
IF OBJECT_ID('dotnetmaui_crossplatform_label_visualstate', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_label_visualstate;
IF OBJECT_ID('dotnetmaui_crossplatform_label_shadow', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_label_shadow;
IF OBJECT_ID('dotnetmaui_crossplatform_label_style', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_label_style;

IF OBJECT_ID('dotnetmaui_crossplatform_entry_visualstate_shadow', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_entry_visualstate_shadow;
IF OBJECT_ID('dotnetmaui_crossplatform_entry_visualstate', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_entry_visualstate;
IF OBJECT_ID('dotnetmaui_crossplatform_entry_shadow', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_entry_shadow;
IF OBJECT_ID('dotnetmaui_crossplatform_entry_style', 'U') IS NOT NULL DROP TABLE dotnetmaui_crossplatform_entry_style;

-- ============================================================
-- CREATE CORE TABLES
-- ============================================================

-- Platform table
CREATE TABLE platform (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(100) NOT NULL UNIQUE,
    name VARCHAR(200) NOT NULL,
    description VARCHAR(500)
);

-- Subscription plan table
CREATE TABLE subscription_plan (
    id VARCHAR(36) PRIMARY KEY,
    code VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    max_projects INT NOT NULL,
    max_platforms_per_project INT NOT NULL,
    monthly_price DECIMAL(10, 2) NOT NULL,
    is_default BIT NOT NULL DEFAULT 0,
    default_duration_days INT NOT NULL,
    created_at DATETIMEOFFSET NOT NULL
);

-- Users table
CREATE TABLE users (
    id VARCHAR(36) PRIMARY KEY,
    keycloak_user_id VARCHAR(255) NOT NULL UNIQUE,
    email VARCHAR(255) NOT NULL UNIQUE,
    display_name VARCHAR(255),
    created_at DATETIMEOFFSET NOT NULL
);

-- User subscription table
CREATE TABLE user_subscription (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    subscription_plan_id VARCHAR(36) NOT NULL,
    status VARCHAR(50) NOT NULL,
    started_at DATETIMEOFFSET NOT NULL,
    expires_at DATETIMEOFFSET,
    created_at DATETIMEOFFSET NOT NULL,
    CONSTRAINT fk_user_subscription_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_user_subscription_plan FOREIGN KEY (subscription_plan_id) REFERENCES subscription_plan(id)
);

-- Project table
CREATE TABLE project (
    id VARCHAR(36) PRIMARY KEY,
    user_id VARCHAR(36) NOT NULL,
    name VARCHAR(200) NOT NULL,
    slug VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(1000),
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET NOT NULL,
    is_deleted BIT NOT NULL DEFAULT 0,
    CONSTRAINT fk_project_user FOREIGN KEY (user_id) REFERENCES users(id)
);

-- Brand identity table
CREATE TABLE brand_identity (
    id VARCHAR(36) PRIMARY KEY,
    project_id VARCHAR(36) NOT NULL,
    brand_name VARCHAR(200) NOT NULL,
    primary_color VARCHAR(20),
    secondary_color VARCHAR(20),
    tertiary_color VARCHAR(20),
    font_family VARCHAR(100),
    logo_url VARCHAR(500),
    description VARCHAR(1000),
    created_at DATETIMEOFFSET NOT NULL,
    CONSTRAINT fk_brand_identity_project FOREIGN KEY (project_id) REFERENCES project(id)
);

-- Project platform association table
CREATE TABLE project_platform (
    id VARCHAR(36) PRIMARY KEY,
    project_id VARCHAR(36) NOT NULL,
    platform_id VARCHAR(36) NOT NULL,
    is_active BIT NOT NULL DEFAULT 1,
    created_at DATETIMEOFFSET NOT NULL,
    CONSTRAINT fk_project_platform_project FOREIGN KEY (project_id) REFERENCES project(id),
    CONSTRAINT fk_project_platform_platform FOREIGN KEY (platform_id) REFERENCES platform(id),
    CONSTRAINT uk_project_platform_projectid_platformid UNIQUE (project_id, platform_id)
);

-- ============================================================
-- PROJECT API KEY TABLE (THE MISSING TABLE!)
-- ============================================================
CREATE TABLE project_api_key (
    id VARCHAR(36) PRIMARY KEY,
    project_platform_id VARCHAR(36) NOT NULL,
    api_key VARCHAR(64) NOT NULL UNIQUE,
    is_active BIT NOT NULL DEFAULT 1,
    created_at DATETIMEOFFSET NOT NULL,
    expires_at DATETIMEOFFSET,
    last_used_at DATETIMEOFFSET,
    CONSTRAINT fk_project_api_key_project_platform FOREIGN KEY (project_platform_id) REFERENCES project_platform(id)
);

-- Platform theme table
CREATE TABLE platform_theme (
    id VARCHAR(36) PRIMARY KEY,
    project_platform_id VARCHAR(36) NOT NULL,
    theme_name VARCHAR(50) NOT NULL,
    display_name VARCHAR(100) NOT NULL,
    is_default BIT NOT NULL DEFAULT 0,
    description VARCHAR(500),
    created_at DATETIMEOFFSET NOT NULL,
    CONSTRAINT fk_platform_theme_project_platform FOREIGN KEY (project_platform_id) REFERENCES project_platform(id),
    CONSTRAINT uk_platform_theme_projectplatform_name UNIQUE (project_platform_id, theme_name)
);

-- ============================================================
-- .NET MAUI CROSS-PLATFORM STYLE TABLES
-- ============================================================

-- Button Style
CREATE TABLE dotnetmaui_crossplatform_button_style (
    id VARCHAR(36) PRIMARY KEY,
    platform_theme_id VARCHAR(36) NOT NULL,
    style_key VARCHAR(100) NOT NULL,
    display_name VARCHAR(200),
    background_color VARCHAR(20),
    text_color VARCHAR(20),
    border_color VARCHAR(20),
    border_width FLOAT,
    corner_radius FLOAT,
    font_size FLOAT,
    font_family VARCHAR(100),
    font_attributes VARCHAR(50),
    padding_left FLOAT,
    padding_top FLOAT,
    padding_right FLOAT,
    padding_bottom FLOAT,
    horizontal_options VARCHAR(50),
    vertical_options VARCHAR(50),
    min_width FLOAT,
    min_height FLOAT,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    CONSTRAINT fk_button_style_theme FOREIGN KEY (platform_theme_id) REFERENCES platform_theme(id),
    CONSTRAINT uk_button_style_theme_key UNIQUE (platform_theme_id, style_key)
);

CREATE TABLE dotnetmaui_crossplatform_button_shadow (
    id VARCHAR(36) PRIMARY KEY,
    button_style_id VARCHAR(36) NOT NULL,
    brush VARCHAR(20),
    offset_x FLOAT,
    offset_y FLOAT,
    radius FLOAT,
    opacity FLOAT,
    CONSTRAINT fk_button_shadow_style FOREIGN KEY (button_style_id) REFERENCES dotnetmaui_crossplatform_button_style(id)
);

CREATE TABLE dotnetmaui_crossplatform_button_visualstate (
    id VARCHAR(36) PRIMARY KEY,
    button_style_id VARCHAR(36) NOT NULL,
    state_name VARCHAR(50) NOT NULL,
    background_color VARCHAR(20),
    text_color VARCHAR(20),
    border_color VARCHAR(20),
    opacity FLOAT,
    scale FLOAT,
    CONSTRAINT fk_button_visualstate_style FOREIGN KEY (button_style_id) REFERENCES dotnetmaui_crossplatform_button_style(id)
);

CREATE TABLE dotnetmaui_crossplatform_button_visualstate_shadow (
    id VARCHAR(36) PRIMARY KEY,
    visual_state_id VARCHAR(36) NOT NULL,
    brush VARCHAR(20),
    offset_x FLOAT,
    offset_y FLOAT,
    radius FLOAT,
    opacity FLOAT,
    CONSTRAINT fk_button_vs_shadow_vs FOREIGN KEY (visual_state_id) REFERENCES dotnetmaui_crossplatform_button_visualstate(id)
);

-- Border Style
CREATE TABLE dotnetmaui_crossplatform_border_style (
    id VARCHAR(36) PRIMARY KEY,
    platform_theme_id VARCHAR(36) NOT NULL,
    style_key VARCHAR(100) NOT NULL,
    display_name VARCHAR(200),
    background_color VARCHAR(20),
    stroke VARCHAR(20),
    stroke_thickness FLOAT,
    stroke_shape VARCHAR(100),
    padding_left FLOAT,
    padding_top FLOAT,
    padding_right FLOAT,
    padding_bottom FLOAT,
    horizontal_options VARCHAR(50),
    vertical_options VARCHAR(50),
    min_width FLOAT,
    min_height FLOAT,
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    CONSTRAINT fk_border_style_theme FOREIGN KEY (platform_theme_id) REFERENCES platform_theme(id),
    CONSTRAINT uk_border_style_theme_key UNIQUE (platform_theme_id, style_key)
);

CREATE TABLE dotnetmaui_crossplatform_border_shadow (
    id VARCHAR(36) PRIMARY KEY,
    border_style_id VARCHAR(36) NOT NULL,
    brush VARCHAR(20),
    offset_x FLOAT,
    offset_y FLOAT,
    radius FLOAT,
    opacity FLOAT,
    CONSTRAINT fk_border_shadow_style FOREIGN KEY (border_style_id) REFERENCES dotnetmaui_crossplatform_border_style(id)
);

CREATE TABLE dotnetmaui_crossplatform_border_visualstate (
    id VARCHAR(36) PRIMARY KEY,
    border_style_id VARCHAR(36) NOT NULL,
    state_name VARCHAR(50) NOT NULL,
    background_color VARCHAR(20),
    stroke VARCHAR(20),
    stroke_thickness FLOAT,
    opacity FLOAT,
    scale FLOAT,
    CONSTRAINT fk_border_visualstate_style FOREIGN KEY (border_style_id) REFERENCES dotnetmaui_crossplatform_border_style(id)
);

-- Label Style
CREATE TABLE dotnetmaui_crossplatform_label_style (
    id VARCHAR(36) PRIMARY KEY,
    platform_theme_id VARCHAR(36) NOT NULL,
    style_key VARCHAR(100) NOT NULL,
    display_name VARCHAR(200),
    text_color VARCHAR(20),
    background_color VARCHAR(20),
    font_size FLOAT,
    font_family VARCHAR(100),
    font_attributes VARCHAR(50),
    horizontal_text_alignment VARCHAR(50),
    vertical_text_alignment VARCHAR(50),
    line_height FLOAT,
    character_spacing FLOAT,
    padding_left FLOAT,
    padding_top FLOAT,
    padding_right FLOAT,
    padding_bottom FLOAT,
    horizontal_options VARCHAR(50),
    vertical_options VARCHAR(50),
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    CONSTRAINT fk_label_style_theme FOREIGN KEY (platform_theme_id) REFERENCES platform_theme(id),
    CONSTRAINT uk_label_style_theme_key UNIQUE (platform_theme_id, style_key)
);

CREATE TABLE dotnetmaui_crossplatform_label_shadow (
    id VARCHAR(36) PRIMARY KEY,
    label_style_id VARCHAR(36) NOT NULL,
    brush VARCHAR(20),
    offset_x FLOAT,
    offset_y FLOAT,
    radius FLOAT,
    opacity FLOAT,
    CONSTRAINT fk_label_shadow_style FOREIGN KEY (label_style_id) REFERENCES dotnetmaui_crossplatform_label_style(id)
);

CREATE TABLE dotnetmaui_crossplatform_label_visualstate (
    id VARCHAR(36) PRIMARY KEY,
    label_style_id VARCHAR(36) NOT NULL,
    state_name VARCHAR(50) NOT NULL,
    text_color VARCHAR(20),
    background_color VARCHAR(20),
    opacity FLOAT,
    scale FLOAT,
    CONSTRAINT fk_label_visualstate_style FOREIGN KEY (label_style_id) REFERENCES dotnetmaui_crossplatform_label_style(id)
);

CREATE TABLE dotnetmaui_crossplatform_label_visualstate_shadow (
    id VARCHAR(36) PRIMARY KEY,
    visual_state_id VARCHAR(36) NOT NULL,
    brush VARCHAR(20),
    offset_x FLOAT,
    offset_y FLOAT,
    radius FLOAT,
    opacity FLOAT,
    CONSTRAINT fk_label_vs_shadow_vs FOREIGN KEY (visual_state_id) REFERENCES dotnetmaui_crossplatform_label_visualstate(id)
);

-- Entry Style
CREATE TABLE dotnetmaui_crossplatform_entry_style (
    id VARCHAR(36) PRIMARY KEY,
    platform_theme_id VARCHAR(36) NOT NULL,
    style_key VARCHAR(100) NOT NULL,
    display_name VARCHAR(200),
    text_color VARCHAR(20),
    background_color VARCHAR(20),
    placeholder_color VARCHAR(20),
    font_size FLOAT,
    font_family VARCHAR(100),
    font_attributes VARCHAR(50),
    horizontal_text_alignment VARCHAR(50),
    vertical_text_alignment VARCHAR(50),
    padding_left FLOAT,
    padding_top FLOAT,
    padding_right FLOAT,
    padding_bottom FLOAT,
    horizontal_options VARCHAR(50),
    vertical_options VARCHAR(50),
    created_at DATETIMEOFFSET NOT NULL,
    updated_at DATETIMEOFFSET,
    CONSTRAINT fk_entry_style_theme FOREIGN KEY (platform_theme_id) REFERENCES platform_theme(id),
    CONSTRAINT uk_entry_style_theme_key UNIQUE (platform_theme_id, style_key)
);

CREATE TABLE dotnetmaui_crossplatform_entry_shadow (
    id VARCHAR(36) PRIMARY KEY,
    entry_style_id VARCHAR(36) NOT NULL,
    brush VARCHAR(20),
    offset_x FLOAT,
    offset_y FLOAT,
    radius FLOAT,
    opacity FLOAT,
    CONSTRAINT fk_entry_shadow_style FOREIGN KEY (entry_style_id) REFERENCES dotnetmaui_crossplatform_entry_style(id)
);

CREATE TABLE dotnetmaui_crossplatform_entry_visualstate (
    id VARCHAR(36) PRIMARY KEY,
    entry_style_id VARCHAR(36) NOT NULL,
    state_name VARCHAR(50) NOT NULL,
    text_color VARCHAR(20),
    background_color VARCHAR(20),
    placeholder_color VARCHAR(20),
    opacity FLOAT,
    scale FLOAT,
    CONSTRAINT fk_entry_visualstate_style FOREIGN KEY (entry_style_id) REFERENCES dotnetmaui_crossplatform_entry_style(id)
);

CREATE TABLE dotnetmaui_crossplatform_entry_visualstate_shadow (
    id VARCHAR(36) PRIMARY KEY,
    visual_state_id VARCHAR(36) NOT NULL,
    brush VARCHAR(20),
    offset_x FLOAT,
    offset_y FLOAT,
    radius FLOAT,
    opacity FLOAT,
    CONSTRAINT fk_entry_vs_shadow_vs FOREIGN KEY (visual_state_id) REFERENCES dotnetmaui_crossplatform_entry_visualstate(id)
);
