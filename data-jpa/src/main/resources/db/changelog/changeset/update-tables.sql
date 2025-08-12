--liquibase formatted sql
--changeset Oleg Ivanov:update-tables
--comment Add additional columns

ALTER TABLE car ADD COLUMN IF NOT EXISTS time_stamp TIMESTAMP;
ALTER TABLE device ADD COLUMN IF NOT EXISTS time_stamp TIMESTAMP;