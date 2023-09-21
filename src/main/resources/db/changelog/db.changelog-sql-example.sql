--liquibase formatted sql

--changeset liquibase-demo-service:add-user-name-constraint
ALTER TABLE user_details ADD CONSTRAINT user_details_username_key UNIQUE (username);
-- ALTER TABLE user_details ADD CONSTRAINT user_details_lastname_key NOT NULL (last_name);
--  ALTER TABLE user_details CHANGE COLUMN last_name last_name VARCHAR(250) NOT NULL;
-- ALTER TABLE customer ALTER last_name SET NOT NULL;
