SET foreign_key_checks = 0;
DROP TABLE IF EXISTS `USER_INFO`;
DROP TABLE IF EXISTS `COMMUNITY_BOARD`;
DROP TABLE IF EXISTS `COMMUNITY_COMMENTS`;
DROP TABLE IF EXISTS `TEAM_LEADER`;
DROP TABLE IF EXISTS `COMMUNITY_SERVICE`;
DROP TABLE IF EXISTS `COMMENTS_SERVICE`;
DROP TABLE IF EXISTS `TEAM_MEMBER`;
DROP TABLE IF EXISTS `LOGIN_LOG`;
SET foreign_key_checks = 1;

CREATE TABLE `USER_INFO` (
                             `UI_student_no`	int(9)	NOT NULL,
                             `UI_name`	varchar(100)	NOT NULL,
                             `UI_birth`	varchar(100)	NOT NULL,
                             `UI_email`	varchar(100)	NOT NULL,
                             `UI_phone_no`	varchar(100)	NOT NULL,
                             `UI_pw`	varchar(100)	NOT NULL,
                             `UI_group`	varchar(30)	NULL,
                             `UI_role`	int(1)	NOT NULL,
                             `UI_cr_date`	datetime	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                             `UI_github_url`	varchar(100)	NULL
);

CREATE TABLE `COMMUNITY_BOARD` (
                                   `CB_no`	int	NOT NULL,
                                   `CB_title`	varchar(100)	NOT NULL,
                                   `CB_content`	text	NOT NULL,
                                   `CB_cr_Date`	datetime	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                                   `CB_kind`	int(1)	NOT NULL,
                                   `CB_delete_T_F`	boolean	NULL
);

CREATE TABLE `COMMUNITY_COMMENTS` (
                                      `CC_no`	int	NOT NULL,
                                      `UI_student_no`	int(9)	NOT NULL,
                                      `CB_no`	int	NOT NULL,
                                      `CC_content`	text(200)	NOT NULL,
                                      `CC_cr_date`	datetime	NOT NULL	DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `TEAM_LEADER` (
                               `TL_no`	int	NOT NULL,
                               `UI_student_no`	int(9)	NOT NULL
);

CREATE TABLE `COMMUNITY_SERVICE` (
                                     `UI_student_no`	int(9)	NOT NULL,
                                     `CB_no`	int	NOT NULL
);

CREATE TABLE `TEAM_MEMBER` (
                               `TM_no`	int	NOT NULL,
                               `UI_student_no`	int(9)	NOT NULL
);

CREATE TABLE `LOGIN_LOG` (
                             `LL_try_date`	datetime	NOT NULL	DEFAULT CURRENT_TIMESTAMP,
                             `UI_student_no`	int(9)	NOT NULL,
                             `LL_attempt_IP`	varchar(15)	NOT NULL,
                             `LL_attempt_T_F`	boolean	NOT NULL
);

ALTER TABLE `USER_INFO` ADD CONSTRAINT `PK_USER_INFO` PRIMARY KEY (
                                                                   `UI_student_no`
    );

ALTER TABLE `COMMUNITY_BOARD` ADD CONSTRAINT `PK_COMMUNITY_BOARD` PRIMARY KEY (
                                                                               `CB_no`
    );

ALTER TABLE `COMMUNITY_COMMENTS` ADD CONSTRAINT `PK_COMMUNITY_COMMENTS` PRIMARY KEY (
                                                                                     `CC_no`,
                                                                                     `UI_student_no`,
                                                                                     `CB_no`
    );

ALTER TABLE `TEAM_LEADER` ADD CONSTRAINT `PK_TEAM_LEADER` PRIMARY KEY (
                                                                       `TL_no`,
                                                                       `UI_student_no`
    );

ALTER TABLE `COMMUNITY_SERVICE` ADD CONSTRAINT `PK_COMMUNITY_SERVICE` PRIMARY KEY (
                                                                                   `UI_student_no`,
                                                                                   `CB_no`
    );

ALTER TABLE `TEAM_MEMBER` ADD CONSTRAINT `PK_TEAM_MEMBER` PRIMARY KEY (
                                                                       `TM_no`,
                                                                       `UI_student_no`
    );

ALTER TABLE `LOGIN_LOG` ADD CONSTRAINT `PK_LOGIN_LOG` PRIMARY KEY (
                                                                   `LL_try_date`,
                                                                   `UI_student_no`
    );

ALTER TABLE `COMMUNITY_COMMENTS` ADD CONSTRAINT `FK_USER_INFO_TO_COMMUNITY_COMMENTS_1` FOREIGN KEY (
                                                                                                    `UI_student_no`
    )
    REFERENCES `USER_INFO` (
                            `UI_student_no`
        );

ALTER TABLE `COMMUNITY_COMMENTS` ADD CONSTRAINT `FK_COMMUNITY_BOARD_TO_COMMUNITY_COMMENTS_1` FOREIGN KEY (
                                                                                                          `CB_no`
    )
    REFERENCES `COMMUNITY_BOARD` (
                                  `CB_no`
        );

ALTER TABLE `TEAM_LEADER` ADD CONSTRAINT `FK_USER_INFO_TO_TEAM_LEADER_1` FOREIGN KEY (
                                                                                      `UI_student_no`
    )
    REFERENCES `USER_INFO` (
                            `UI_student_no`
        );

ALTER TABLE `COMMUNITY_SERVICE` ADD CONSTRAINT `FK_USER_INFO_TO_COMMUNITY_SERVICE_1` FOREIGN KEY (
                                                                                                  `UI_student_no`
    )
    REFERENCES `USER_INFO` (
                            `UI_student_no`
        );

ALTER TABLE `COMMUNITY_SERVICE` ADD CONSTRAINT `FK_COMMUNITY_BOARD_TO_COMMUNITY_SERVICE_1` FOREIGN KEY (
                                                                                                        `CB_no`
    )
    REFERENCES `COMMUNITY_BOARD` (
                                  `CB_no`
        );

ALTER TABLE `TEAM_MEMBER` ADD CONSTRAINT `FK_TEAM_LEADER_TO_TEAM_MEMBER_1` FOREIGN KEY (
                                                                                        `TM_no`
    )
    REFERENCES `TEAM_LEADER` (
                              `TL_no`
        );

ALTER TABLE `TEAM_MEMBER` ADD CONSTRAINT `FK_USER_INFO_TO_TEAM_MEMBER_1` FOREIGN KEY (
                                                                                      `UI_student_no`
    )
    REFERENCES `USER_INFO` (
                            `UI_student_no`
        );

ALTER TABLE `LOGIN_LOG` ADD CONSTRAINT `FK_USER_INFO_TO_LOGIN_LOG_1` FOREIGN KEY (
                                                                                  `UI_student_no`
    )
    REFERENCES `USER_INFO` (
                            `UI_student_no`
        );

