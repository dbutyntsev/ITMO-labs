-- Создание таблиц

CREATE TABLE city
(
  id BIGINT PRIMARY KEY,        
  name VARCHAR NOT NULL         
);
 

CREATE TABLE role (
  id BIGINT PRIMARY KEY,        
  name VARCHAR NOT NULL,        
  influence_level NUMERIC(3, 2) 
);
 

CREATE TABLE inhabitant (
  id BIGINT PRIMARY KEY,        
  name VARCHAR NOT NULL,        
  adventure_drive INTEGER DEFAULT 0, 
  city_id BIGINT,               
  role_id BIGINT,               
  action_id BIGINT              
);
 

CREATE TABLE action (
  id BIGINT PRIMARY KEY,        
  name VARCHAR NOT NULL,        
  influence_level NUMERIC(3, 2) 
);
 

CREATE TABLE flow_history (
  id BIGINT PRIMARY KEY,        
  inhabitant_id BIGINT,         
  action_id BIGINT              
);
 

CREATE TABLE saga (
  id BIGINT PRIMARY KEY,        
  title VARCHAR NOT NULL,       
  genre VARCHAR NOT NULL,       
  danger_level NUMERIC(3, 2)    
);
 

CREATE TABLE saga_study (
  id BIGINT PRIMARY KEY,        
  inhabitant_id BIGINT,         
  saga_id BIGINT,               
  is_studied BOOLEAN DEFAULT FALSE 
);

-- Ограничения внешних ключей
ALTER TABLE inhabitant ADD CONSTRAINT fk_inhabitant_city FOREIGN KEY (city_id) REFERENCES city(id);
ALTER TABLE inhabitant ADD CONSTRAINT fk_inhabitant_role FOREIGN KEY (role_id) REFERENCES role(id);
 
ALTER TABLE flow_history ADD CONSTRAINT fk_flow_inhabitant FOREIGN KEY (inhabitant_id) REFERENCES inhabitant(id);
ALTER TABLE flow_history ADD CONSTRAINT fk_flow_action FOREIGN KEY (action_id) REFERENCES action(id);
 
ALTER TABLE saga_study ADD CONSTRAINT fk_study_inhabitant FOREIGN KEY (inhabitant_id) REFERENCES inhabitant(id);
ALTER TABLE saga_study ADD CONSTRAINT fk_study_saga FOREIGN KEY (saga_id) REFERENCES saga(id);