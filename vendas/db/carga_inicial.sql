select * from tb_usuario;

insert into tb_usuario values('7a53fcfe-f31b-4a9c-83d2-2808168478ad','$2a$10$U2thTCxKGe.iYFaJC4/uCORCUb5gGgDhv65Q4z0ofAfH.4FvWurDG','maria');
insert into tb_usuario values('3d6d8f50-5f7d-46aa-81f9-91318aa15534','$2a$10$U2thTCxKGe.iYFaJC4/uCORCUb5gGgDhv65Q4z0ofAfH.4FvWurDG','joao');
--
select * from tb_regras;

insert into tb_regras values(gen_random_uuid(),'ROLE_ADMIN');
insert into tb_regras values(gen_random_uuid(),'ROLE_USER');
--
select * from tb_regras_usuario;

insert into tb_regras_usuario values('7a53fcfe-f31b-4a9c-83d2-2808168478ad','6dedd780-319f-4581-9c53-a2fe86ef9605');
insert into tb_regras_usuario values('7a53fcfe-f31b-4a9c-83d2-2808168478ad','21765693-ab2a-4e93-8a60-69261bf6f304');
insert into tb_regras_usuario values('3d6d8f50-5f7d-46aa-81f9-91318aa15534','21765693-ab2a-4e93-8a60-69261bf6f304');
--