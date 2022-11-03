# 创建数据库
create database if not exists gxucpc;

use gxucpc;

# 创建比赛信息表
create table if not exists contest
(
    id               int auto_increment comment '比赛编号'
        primary key,
    name             varchar(100)         not null comment '比赛名称',
    signUpBeginTime  mediumtext           not null comment '比赛开始接受报名时间',
    signUpEndTime    mediumtext           not null comment '比赛结束报名时间',
    email            varchar(30)          not null comment '赛事邮箱',
    smtpPassword     varchar(30)          not null comment '赛事邮箱SMTP密钥',
    contestBeginTime mediumtext           not null comment '比赛开始时间',
    contestEndTime   mediumtext           not null comment '比赛结束时间',
    isDownload       tinyint(1) default 0 null comment '是否开启奖状下载',
    isQuery          tinyint(1) default 0 null comment '是否开启账密查询',
    createTime       long                 not null comment '创建时间',
    constraint contestd_id_uindex
        unique (id),
    constraint contestd_name_uindex
        unique (name)
) comment '比赛信息';

# 创建报名信息表
create table if not exists information
(
    informationId int auto_increment
        primary key,
    userName      varchar(30)          not null comment '姓名',
    userSex       varchar(4)           not null comment '性别',
    userCourse    varchar(64)          not null comment '学院',
    userClass     varchar(64)          not null comment '班级',
    userQQ        varchar(16)          not null comment 'QQ',
    userMail      varchar(64)          not null comment '邮箱',
    userPhone     char(11)             not null comment '手机号',
    `key`         varchar(20)          not null comment '密钥',
    remark        varchar(500)         null comment '备注',
    contestId     int                  not null comment '比赛ID',
    star          tinyint(1) default 0 not null comment '是否打星参赛',
    `group`       tinyint(1)           not null comment '参赛组别',
    userId        varchar(10)          not null comment '学号',
    clientNo      varchar(36)          not null comment '提交报名的客户端编号',
    constraint information_informationId_uindex
        unique (informationId),
    constraint information_contest_id_fk
        foreign key (contestId) references contest (id)
            on delete cascade,
    unique key check_user_id_contest_id (contestId, userId)
) comment '参赛选手信息';

# 触发器: 当非当年新生（如22年，学号必须为22开头）,自动转入正式组
drop trigger if exists information.checkGroup;
delimiter //
create trigger checkGroup
    before insert
    on information
    for each row
begin
    if substr(new.userId, 2, 1) != char(YEAR(curdate()) % 10 + 48) then
        set new.group = 1;
    end if;
end;
//
delimiter ;


# 创建操作日志表
create table if not exists operations
(
    id            int auto_increment
        primary key,
    operationTime mediumtext   not null comment '操作时间',
    details       varchar(500) not null comment '操作日志',
    user          varchar(30)  not null comment '真实姓名',
    constraint operations_id_uindex
        unique (id)
) comment '管理员操作日志';

# 创建杂项表(key-value存储封面URL和ICP信息)
create table if not exists redis
(
    `key` varchar(3)   not null comment 'URLorICP',
    value varchar(100) not null
) comment 'key-value服务器存贮一些常用的键值对（数据量小，不需要在开一个redis）';

# 创建文章表
create table if not exists text
(
    id      int auto_increment comment '文章id'
        primary key,
    type    varchar(10)  not null comment '文章类型',
    author  varchar(30)  not null comment '文章作者',
    time    mediumtext   not null comment '创建时间（时间戳）',
    content mediumtext   not null comment '文章内容',
    title   varchar(100) not null comment '标题',
    constraint text_id_uindex
        unique (id)
) comment '文章';

# 创建管理员表
create table if not exists user
(
    id         int auto_increment comment '用户ID'
        primary key,
    username   varchar(30)   not null comment '用户名',
    password   varchar(40)   not null comment '密码',
    userType   int default 2 not null comment '权限 1:Super Admin 2:Admin',
    realName   varchar(20)   not null comment '真实姓名',
    createTime mediumtext    not null comment '创建时间（时间戳）',
    lastLogin  mediumtext    null comment '上次登录时间（时间戳）',
    email      varchar(30)   not null comment '用户邮箱',
    constraint username
        unique (username)
);

# 创建Domjudge账密表
create table if not exists domjudge
(
    id       int        not null comment '报名表单编号'
        primary key,
    username char(9)    not null comment 'Domjudge账号',
    password char(8)    not null comment 'Domjudge密码',
    isQuery  tinyint(1) not null default 0 comment '是否开放该报名记录的账密查询',
    constraint id unique (id),
    constraint domjudge_p_u_check_safe_inform_id foreign key (id) references information (informationId) on delete cascade
) comment 'Domjudge账密表';


# 初始化管理员(U:root, P:123456)
insert into user(username, password, userType, realName, createTime, lastLogin, email)
VALUES ('root', 'e10adc3949ba59abbe56e057f20f883e', 1, 'root', 1664519465, null, 'gxucpc@gxuca.com');

# 报名系统比赛ID与DOMjudge比赛ID映射关系
create table if not exists mapper_contest
(
    register_contest int                  null comment '比赛编号',
    is_spider        tinyint(1) default 0 null comment '是否正在运行爬虫',
    id               int auto_increment
        primary key,
    update_time      mediumtext           null comment '上次更新时间',
    url              varchar(100)         null comment 'API URL',
    fault_time       int        default 0 null comment '失败次数',
    contest_time     mediumtext           null comment '比赛进行了多久'
);

# 榜单表
create table if not exists scoreboard
(
    contest_id int  null comment '服务平台比赛编号',
    content    text null comment '榜单JSON字符串',
    id         int auto_increment
        primary key,
    `rank`     int  null comment '排名',
    team_id    int  null comment '选手编号',
    num_solved int  null comment '过题数',
    total_time int  null comment '罚时'
);

