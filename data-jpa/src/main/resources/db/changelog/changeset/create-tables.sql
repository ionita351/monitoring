--liquibase formatted sql
--changeset Oleg Ivanov:create-tables
--comment Initial table creation

CREATE TABLE measurement (
    id UUID PRIMARY KEY,
    device_number VARCHAR(80),
    time_stamp TIMESTAMP,
    latitude decimal,
    longitude decimal,
    alert BOOLEAN
);

CREATE TABLE device (
    id UUID PRIMARY KEY,
    device_number VARCHAR(80),
    measurement_id UUID REFERENCES measurement(id)
);

CREATE UNIQUE INDEX ui_device_measurement_id ON device(measurement_id);

CREATE TABLE car (
    id UUID PRIMARY KEY,
    state_sign VARCHAR(80),
    brand VARCHAR(80),
    device_id UUID REFERENCES device(id)
);

CREATE UNIQUE INDEX ui_car_device_id ON car(device_id);

