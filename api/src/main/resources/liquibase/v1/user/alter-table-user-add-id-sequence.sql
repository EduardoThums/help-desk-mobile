CREATE SEQUENCE user_id_seq
INCREMENT 1
START 1;

ALTER TABLE "user" ALTER COLUMN id SET DEFAULT nextval('user_id_seq');