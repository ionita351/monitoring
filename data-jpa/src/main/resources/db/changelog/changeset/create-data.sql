--liquibase formatted sql
--changeset Oleg Ivanov:create-data
--comment Initial DATA creation

insert into device(id, device_number) values ('bd6b82d4-6f5f-11f0-be55-ae727d68b02b', 'APU_FEIGN');
insert into device(id, device_number) values ('d121a56a-6f5f-11f0-be55-ae727d68b02b', 'APU_GRPC');
insert into device(id, device_number) values ('e18c4450-6f5f-11f0-be55-ae727d68b02b', 'APU_REST');
insert into device(id, device_number) values ('ea197246-6f5f-11f0-be55-ae727d68b02b', 'APU_WS');

insert into car(id, state_sign, brand, device_id) values ('f66efd68-6f5f-11f0-be55-ae727d68b02b', '001', 'FEIGN', 'bd6b82d4-6f5f-11f0-be55-ae727d68b02b');
insert into car(id, state_sign, brand, device_id) values ('00d6e9f0-6f60-11f0-be55-ae727d68b02b', '002', 'GRPC', 'd121a56a-6f5f-11f0-be55-ae727d68b02b');
insert into car(id, state_sign, brand, device_id) values ('0cbbdb54-6f60-11f0-be55-ae727d68b02b', '003', 'REST', 'e18c4450-6f5f-11f0-be55-ae727d68b02b');
insert into car(id, state_sign, brand, device_id) values ('1d55e4b4-6f60-11f0-be55-ae727d68b02b', '004', 'WS', 'ea197246-6f5f-11f0-be55-ae727d68b02b');
