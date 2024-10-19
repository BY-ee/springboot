-- DB 데이터 확인
USE space;
SHOW DATABASES;

-- 테이블 데이터 확인
SELECT * FROM user;
SELECT * FROM qna_post;
SELECT * FROM community_post;

SELECT @@sql_mode;

-- 
CREATE TABLE user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(30) NOT NULL,
    email VARCHAR(50) UNIQUE NOT NULL,
    nickname VARCHAR(20) UNIQUE NOT NULL,
    email_opt_in BOOLEAN NOT NULL,
    terms_agreement BOOLEAN NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE qna_post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    user_nickname VARCHAR(20),
    title VARCHAR(50) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    topic VARCHAR(30) NOT NULL,
    tag VARCHAR(100),
    view_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL,
    FOREIGN KEY (user_nickname) REFERENCES user(nickname) ON DELETE SET NULL ON UPDATE CASCADE
);

CREATE TABLE community_post (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT,
    user_nickname VARCHAR(20),
    title VARCHAR(50) NOT NULL,
    content VARCHAR(2000) NOT NULL,
    topic VARCHAR(30) NOT NULL,
    tag VARCHAR(100),
    view_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES user(id) ON DELETE SET NULL,
    FOREIGN KEY (user_nickname) REFERENCES user(nickname) ON DELETE SET NULL ON UPDATE CASCADE
);