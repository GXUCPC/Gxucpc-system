use gxucpc;

ALTER TABLE contest
    ADD COLUMN type INT NOT NULL COMMENT '比赛类型' AFTER name;

UPDATE contest
set type=1;

create table if not exists lq_information
(
    id         int auto_increment primary key,
    userName   varchar(30)    not null comment '姓名',
    userSex    varchar(4)     not null comment '性别',
    userCourse varchar(64)    not null comment '学院',
    userClass  varchar(64)    not null comment '班级',
    userQQ     varchar(16)    not null comment 'QQ',
    userMail   varchar(64)    not null comment '邮箱',
    userPhone  char(11)       not null comment '手机号',
    userId     varchar(10)    not null comment '学号',
    contestId  int            not null comment '比赛ID',
    isDiscount int            not null comment '是否九折',
    imgURI     varbinary(255) not null comment '图片URI'
) comment '蓝桥-参赛选手信息';

ALTER TABLE lq_information
ADD unique key lq_sid_cid(userId, contestId);