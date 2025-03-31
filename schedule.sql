USE schedule

CREATE TABLE schedule
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '할일 식별자',
    title       VARCHAR(255) NOT NULL COMMENT '할일 제목',
    content     VARCHAR(255) NOT NULL COMMENT '할일 내용',
    author      VARCHAR(255) NOT NULL COMMENT '작성자',
    create_date DATETIME     NOT NULL COMMENT '작성일',
    edit_date   DATETIME     NOT NULL COMMENT '수정일',
);