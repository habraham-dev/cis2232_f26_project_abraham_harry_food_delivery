DROP DATABASE IF EXISTS bjmac_squash_skills;
CREATE DATABASE bjmac_squash_skills;
use bjmac_squash_skills;


CREATE TABLE SkillsAssessmentSquashTechnical
(
    id                int(5),
    assessmentDate    varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
    createdDateTime   varchar(20) NOT NULL COMMENT 'yyyy-MM-dd hh:mm:ss',
    athleteName       varchar(50) NOT NULL COMMENT 'Athletes name',
    assessorName      varchar(50) NOT NULL COMMENT 'Athletes name',
    forehandDrives    int(5) COMMENT 'Number of forehand drives',
    backhandDrives    int(5) COMMENT 'Number of backhand drives',
    forehandVolleyMax int(5) COMMENT 'Max number of forehand volleys',
    forehandVolleySum int(5) COMMENT 'Sum of forehand volleys',
    backhandVolleyMax int(5) COMMENT 'Max number of backhand volleys',
    backhandVolleySum int(5) COMMENT 'Sum of backhand volleys',
    technicalScore    int(5) COMMENT 'Score calculated at submission'
) COMMENT 'This table holds technical skills assessment details';

CREATE TABLE Assessment
(
    id               int(5),
    organizationCode int(5),
    skillCode        int(5),
    assessmentDate   varchar(10) NOT NULL COMMENT 'yyyy-MM-dd',
    athleteName      varchar(50) NOT NULL COMMENT 'Athletes name',
    assessorName     varchar(50) NOT NULL COMMENT 'Athletes name',
    technicalScore   int(5) COMMENT 'Score calculated at submission',
    createdDateTime  varchar(20) NOT NULL COMMENT 'yyyy-MM-dd hh:mm:ss',
    createdUserId    varchar(20) NOT NULL DEFAULT '',
    updatedDateTime  varchar(20) NOT NULL COMMENT 'yyyy-MM-dd hh:mm:ss',
    updatedUserId    varchar(20) NOT NULL DEFAULT ''
) COMMENT 'This table holds assessment details';

CREATE TABLE AssessmentDetail
(
    id              int(5),
    assessmentId    int(5),
    skillCode       int(5),
    assessmentScore int(5),
    createdDateTime varchar(20) NOT NULL COMMENT 'yyyy-MM-dd hh:mm:ss',
    createdUserId   varchar(20) NOT NULL DEFAULT '',
    updatedDateTime varchar(20) NOT NULL COMMENT 'yyyy-MM-dd hh:mm:ss',
    updatedUserId   varchar(20) NOT NULL DEFAULT ''
) COMMENT 'This table holds assessment skill detail';



ALTER TABLE SkillsAssessmentSquashTechnical
    ADD PRIMARY KEY (id);
ALTER TABLE SkillsAssessmentSquashTechnical
    MODIFY id int(4) NOT NULL AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;

-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-22',	'2022-08-20 11:35:15','Maria Smith','BJ MacLean',		11,	5,	14,	78,	6,	59,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-11',	'2022-08-11 11:35:15','Rhonda Jones','BJ MacLean',		5,	7,	4,	36,	5,	38,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-07', '2022-08-07 11:35:15','Chad Collins','BJ MacLean',		8,	8,	4,	37,	5,	42,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-07', '2022-08-07 11:35:15','Rhonda Jones','BJ MacLean',		12,	8,	9,	53,	4,	42,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-07', '2022-08-05 11:35:15','Chad Collins','BJ MacLean',		8,	10,	7,	52,	3,	26,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-08',	'2022-08-08 11:35:15','Rhonda Jones','BJ MacLean',		10,	8,	8,	61,	6,	57,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-10',	'2022-08-10 11:35:15','Chad Collins','BJ MacLean',		17,	14,	8,	70,	13,	84,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-08',	'2022-08-08 11:35:15','Maria Smith','BJ MacLean',		17,	18,	12,	77,	8,	63,0);
-- insert into SkillsAssessmentSquashTechnical values(0, '2022-08-22',	'2022-08-20 11:35:15','Chad Collins','BJ MacLean',		14,	11,	10,	86,	16,	87,0);

INSERT INTO SkillsAssessmentSquashTechnical (id, assessmentDate, createdDateTime, athleteName, assessorName,
                                             forehandDrives, backhandDrives, forehandVolleyMax, forehandVolleySum,
                                             backhandVolleyMax, backhandVolleySum, technicalScore)
VALUES (1, '2022-08-22', '2023-11-07 01:38:48', 'Maria Smith', 'BJ MacLean', 11, 5, 14, 78, 6, 59, 1085),
       (2, '2022-08-11', '2023-11-07 01:38:52', 'Rhonda Jones', 'BJ MacLean', 5, 7, 4, 36, 5, 38, 622),
       (3, '2022-08-07', '2023-11-07 01:38:56', 'Chad Collins', 'BJ MacLean', 8, 8, 4, 37, 5, 42, 707),
       (4, '2022-08-07', '2023-11-07 01:38:59', 'Rhonda Jones', 'BJ MacLean', 12, 8, 9, 53, 4, 42, 879),
       (5, '2022-08-07', '2023-11-07 01:39:01', 'Chad Collins', 'BJ MacLean', 8, 10, 7, 52, 3, 26, 740),
       (6, '2022-08-08', '2023-11-07 01:39:04', 'Rhonda Jones', 'BJ MacLean', 10, 8, 8, 61, 6, 57, 972),
       (7, '2022-08-10', '2023-11-07 01:39:07', 'Chad Collins', 'BJ MacLean', 17, 14, 8, 70, 13, 84, 1403),
       (8, '2022-08-08', '2023-11-07 01:39:10', 'Maria Smith', 'BJ MacLean', 17, 18, 12, 77, 8, 63, 1385),
       (9, '2022-08-22', '2023-11-07 01:38:44', 'Chad Collins', 'BJ MacLean', 14, 11, 10, 86, 16, 87, 1448);


# --------------------------------------------------------------------------------
# NOTE for tables below.  These are standard tables that have the same structure for all
# projects.  The code type tables can hold static lists that can be use in the program.
# Doing this can allow you to reuse code from the sample project more easily.  Note
# that I am loading the skill types into a code type below.
# Code type 1 is always user types that may exist in your projects.
# --------------------------------------------------------------------------------

CREATE TABLE CodeType
(
    id                 int(5) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    codeTypeId         int(3) COMMENT 'This is id for code types',
    englishDescription varchar(100) NOT NULL COMMENT 'English description',
    frenchDescription  varchar(100) DEFAULT NULL COMMENT 'French description',
    createdDateTime    datetime     DEFAULT NULL,
    createdUserId      varchar(20)  DEFAULT NULL,
    updatedDateTime    datetime     DEFAULT NULL,
    updatedUserId      varchar(20)  DEFAULT NULL
) COMMENT 'This tables holds the code types that are available for the application';

ALTER TABLE CodeType
    AUTO_INCREMENT = 1;

# ALTER TABLE CodeType
#     ADD PRIMARY KEY (id);



INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
                      updatedDateTime, updatedUserId)
VALUES (1, 'User Types', 'User Types FR', sysdate(), '', CURRENT_TIMESTAMP, '');
INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
                      updatedDateTime, updatedUserId)
VALUES (2, 'Status Codes', 'Status Codes FR', sysdate(), '', CURRENT_TIMESTAMP, '');

INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
                      updatedDateTime, updatedUserId)
VALUES (3, 'Organization Code', 'Organization Code FR', sysdate(), '', CURRENT_TIMESTAMP, '');

INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
                      updatedDateTime, updatedUserId)
VALUES (10, 'Organization 1 Skills', 'Organization Skills FR', sysdate(), '', CURRENT_TIMESTAMP, '');


# INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
#                       updatedDateTime, updatedUserId)
# VALUES (4, 'Organization Skill Code', 'Skill Types Technical FR', sysdate(), '', CURRENT_TIMESTAMP, '');

# INSERT INTO CodeType (CodeTypeId, englishDescription, frenchDescription, createdDateTime, createdUserId,
#                       updatedDateTime, updatedUserId)
# VALUES (3, 'Skill Types Technical Points', 'Skill Types Technical Points FR', sysdate(), '', CURRENT_TIMESTAMP, '');

CREATE TABLE CodeValue
(
    id                      int(5) COMMENT 'Primary Key',
    codeTypeId              int(3)       NOT NULL COMMENT 'see code_type table',
    codeValueSequence       int(3)       NOT NULL COMMENT 'code value identifier',
    englishDescription      varchar(100) NOT NULL COMMENT 'English description',
    englishDescriptionShort varchar(20)  NOT NULL COMMENT 'English abbreviation for description',
    frenchDescription       varchar(100) DEFAULT NULL COMMENT 'French description',
    frenchDescriptionShort  varchar(20)  DEFAULT NULL COMMENT 'French abbreviation for description',
    sortOrder               int(3)       DEFAULT NULL COMMENT 'Sort order if applicable',
    createdDateTime         datetime     DEFAULT NULL,
    createdUserId           varchar(20)  DEFAULT NULL,
    updatedDateTime         datetime     DEFAULT NULL,
    updatedUserId           varchar(20)  DEFAULT NULL
) COMMENT ='This will hold code values for the application.';


ALTER TABLE CodeValue
    MODIFY id int(5) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'This is the primary key',
    AUTO_INCREMENT = 1;


INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (1, 1, 'General', 'General', 'GeneralFR', 'GeneralFR', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP,
        'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (1, 2, 'Admin', 'Admin', 'Admin', 'Admin', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (2, 1, 'Active', 'Active', 'A', 'A', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (2, 2, 'Inactive', 'Inactive', 'I', 'I', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (3, 1, 'Squash PEI', 'Squash PEI', 'SPEI', 'SPEI', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (100, 1, 'Forehand Drives', 'Forehand Drives', 'FD', 'FD', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (100, 2, 'Backhand Drives', 'Backhand Drives', 'BD', 'BD', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (100, 3, 'Forehand Volley Max', 'Forehand Volley Max', 'FVM', 'FVM', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');
INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (100, 4, 'Forehand Volley Sum', 'Forehand Volley Sum', 'FVS', 'FVS', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (100, 5, 'Backhand Volley Max', 'Backhand Volley Max', 'BVM', 'BVM', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (100, 6, 'Backhand Volley Sum', 'Backhand Volley Sum', 'BVS', 'BVS', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (100, 7, 'Figure 8 Volley Max', 'Figure 8 Volley Max', 'F8VM', 'F8VM', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');

INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
                       frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
VALUES (100, 8, 'Figure 8 Volley Sum', 'Figure 8 Volley Sum', 'F8VS', 'F8VS', CURRENT_TIMESTAMP, 'admin', CURRENT_TIMESTAMP, 'admin');


# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (2, 3, 'Number Forehand Drives', 'NFD', 'Number Forehand DrivesFR', 'NFD', '2023-10-25 18:44:37', 'admin',
#         '2023-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (2, 4, 'Number Backhand Drives', 'NBD', 'Number Backhand DrivesFR', 'NBD', '2023-10-25 18:44:37', 'admin',
#         '2023-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (2, 5, 'Forehand Volley Max', 'FVM', 'Forehand Volley MaxFR', 'FVM', '2023-10-25 18:44:37', 'admin',
#         '2023-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (2, 6, 'Forehand Volley Sum', 'FVS', 'Forehand Volley SumFR', 'FVS', '2023-10-25 18:44:37', 'admin',
#         '2023-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (2, 7, 'Backhand Volley Max', 'BVM', 'Backhand Volley MaxFR', 'BVM', '2023-10-25 18:44:37', 'admin',
#         '2023-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (2, 8, 'Backhand Volley Sum', 'BVS', 'Backhand Volley SumFR', 'BVS', '2023-10-25 18:44:37', 'admin',
#         '2023-10-25 18:44:37', 'admin');
#
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (3, 9, '15', '15', '15', '15', '2023-10-25 18:44:37', 'admin', '2023-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (3, 10, '15', '15', '15', '15', '2023-10-25 18:44:37', 'admin', '2023-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (3, 11, '8', '8', '8', '8', '2023-10-25 18:44:37', 'admin', '2023-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (3, 12, '5', '5', '5', '5', '2023-10-25 18:44:37', 'admin', '2023-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (3, 13, '8', '8', '8', '8', '2023-10-25 18:44:37', 'admin', '2023-10-25 18:44:37', 'admin');
# INSERT INTO CodeValue (codeTypeId, codeValueSequence, englishDescription, englishDescriptionShort, frenchDescription,
#                        frenchDescriptionShort, createdDateTime, createdUserId, updatedDateTime, updatedUserId)
# VALUES (3, 14, '5', '5', '5', '5', '2023-10-25 18:44:37', 'admin', '2023-10-25 18:44:37', 'admin');


CREATE TABLE UserAccess
(
    userAccessId         int(3)       NOT NULL,
    username             varchar(100) NOT NULL COMMENT 'Unique user name for app',
    password             varchar(128) NOT NULL,
    name                 varchar(128),
    userAccessStatusCode int(3)       NOT NULL DEFAULT '1' COMMENT 'Code type #2',
    userTypeCode         int(3)       NOT NULL DEFAULT '1' COMMENT 'Code type #1',
    createdDateTime      datetime              DEFAULT NULL COMMENT 'When user was created.'
);


ALTER TABLE UserAccess
    ADD PRIMARY KEY (userAccessId),
    ADD KEY userTypeCode (userTypeCode);

ALTER TABLE UserAccess
    MODIFY userAccessId int(3) NOT NULL AUTO_INCREMENT;


