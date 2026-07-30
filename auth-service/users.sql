-- USERS 
CREATE TABLE users (
    id BIGINT NOT NULL AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(255) NOT NULL,      -- Stores BCrypt hashed password
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    -- Constraints
    CONSTRAINT pk_users PRIMARY KEY (id),
    CONSTRAINT uk_users_username UNIQUE (username),
    CONSTRAINT uk_users_email UNIQUE (email)
);


INSERT INTO users
(username, password, full_name, email, active)
VALUES
(
    'john',
    '$2a$10$g',
    'John Doe',
    'john@example.com',
    TRUE
);

-- ROLES
CREATE TABLE roles (
    id BIGINT NOT NULL AUTO_INCREMENT,
    role_name VARCHAR(30) NOT NULL,

    -- Constraints
    CONSTRAINT pk_roles PRIMARY KEY (id),
    CONSTRAINT uk_roles_role_name UNIQUE (role_name)
);

INSERT INTO roles (role_name)
VALUES
('ADMIN'),
('BROKER'),
('MANAGER');
