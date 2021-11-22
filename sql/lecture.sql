
CREATE TABLE "LECTURE_CLASSIFICATION" (
	"LC_NO"	NUMBER		NOT NULL,
	"LC_NAME"	VARCHAR2(40)		NOT NULL
);

ALTER TABLE "LECTURE_CLASSIFICATION" ADD CONSTRAINT "PK_LECTURE_CLASSIFICATION" PRIMARY KEY (
	"LC_NO"
);

insert into lecture_classification values (1, '정기강좌');
insert into lecture_classification values (2, '단기강좌');
insert into lecture_classification values (3, '무료강좌');

CREATE TABLE "LECTURE_TARGET" (
	"LT_NO"	NUMBER		NOT NULL,
	"LT_NAME"	VARCHAR2(40)		NOT NULL
);

ALTER TABLE "LECTURE_TARGET" ADD CONSTRAINT "PK_LECTURE_TARGET" PRIMARY KEY (
	"LT_NO"
);

insert into lecture_target values (1, '성인강좌');
insert into lecture_target values (2, '아동강좌');
insert into lecture_target values (3, '영유아강좌');

CREATE TABLE "LECTURE" (
	"L_NO"	NUMBER		NOT NULL,
	"L_TITLE"	VARCHAR2(100)		NOT NULL,
	"L_THUMBNAIL"	VARCHAR2(1000)		NOT NULL,
	"L_ENROLL_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"L_MODIFY_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"L_STATUS"	VARCHAR2(1)	DEFAULT 'N'	NOT NULL,
	"LC_NO"	NUMBER		NOT NULL,
	"LT_NO"	NUMBER		NOT NULL,
    "L_CONTENT"	CLOB		NOT NULL
);

ALTER TABLE "LECTURE" ADD CONSTRAINT "PK_LECTURE" PRIMARY KEY (
	"L_NO"
);

ALTER TABLE "LECTURE" ADD CONSTRAINT "FK_LECTURE_CLASSIFICATION_TO_LECTURE_1" FOREIGN KEY (
	"LC_NO"
)
REFERENCES "LECTURE_CLASSIFICATION" (
	"LC_NO"
);

ALTER TABLE "LECTURE" ADD CONSTRAINT "FK_LECTURE_TARGET_TO_LECTURE_1" FOREIGN KEY (
	"LT_NO"
)
REFERENCES "LECTURE_TARGET" (
	"LT_NO"
);

insert into lecture values (1
, '도자기 공예'
, '20181129114054.jpg'
, '21/11/30'
, '21/11/30'
, 'N'
, 2
, 2
, '와아아아아ㅏ아앙아ㅏ아아아아아아아ㅏ아아아아ㅏ아아ㅏ아아아아ㅏ아아아ㅏ아아아아ㅏ아아아ㅏ아아아아아아ㅏ아앙');

CREATE TABLE "INSTRUCTOR" (
	"INSTRUCTOR_NO"	NUMBER		NOT NULL,
	"INSTRUCTOR_NAME"	VARCHAR2(30)		NULL,
	"PROFILE_PHOTO"	VARCHAR2(1500)		NULL,
	"BIRTH_DATE"	VARCHAR2(20)		NULL,
	"INSTRUCTOR_PHONE"	VARCHAR2(15)		NULL,
	"CAREER"	VARCHAR2(5000)		NULL
);

ALTER TABLE "INSTRUCTOR" ADD CONSTRAINT "PK_INSTRUCTOR" PRIMARY KEY (
	"INSTRUCTOR_NO"
);

insert into instructor values (1
, '우별림'
, '20181129114054.jpg'
, '1991/11/30'
, '01012341234'
, 'KH정보교육원 강사 (2019~현재)'
);

--
--ㅇㅇㅇ

CREATE TABLE "LECTURE_REGISTRATION" (
	"LR_NO"	NUMBER		NOT NULL,
	"LR_CAPACITY"	NUMBER		NOT NULL,
	"LR_COUNT"	NUMBER	DEFAULT 0	NOT NULL,
	"LR_START_DATE"	  Date		NOT NULL,
	"LR_END_DATE"	Date		NOT NULL,
	"LR_NUMBER"	NUMBER		NOT NULL,
	"LR_DAY"	VARCHAR2(40)		NOT NULL,
	"LR_START_TIME"	VARCHAR2(40)		NOT NULL,
	"LR_END_TIME"	VARCHAR2(40)		NOT NULL,
	"LR_ENROLL_DATE"	DATE	DEFAULT SYSDATE	NOT NULL,
	"LR_MODIFY_DATE"	DATE		NOT NULL,
	"LR_STATUS"	VARCHAR2(1)	DEFAULT 'N'	NOT NULL,
	"INSTRUCTOR_NO"	NUMBER		NOT NULL,
	"L_NO"	NUMBER		NOT NULL,
    "LR_FEE"	NUMBER		NOT NULL
);

ALTER TABLE "LECTURE_REGISTRATION" ADD CONSTRAINT "PK_LECTURE_REGISTRATION" PRIMARY KEY (
	"LR_NO"
);

ALTER TABLE "LECTURE_REGISTRATION" ADD CONSTRAINT "FK_INSTRUCTOR_TO_LECTURE_REGISTRATION_1" FOREIGN KEY (
	"INSTRUCTOR_NO"
)
REFERENCES "INSTRUCTOR" (
	"INSTRUCTOR_NO"
);

ALTER TABLE "LECTURE_REGISTRATION" ADD CONSTRAINT "FK_LECTURE_TO_LECTURE_REGISTRATION_1" FOREIGN KEY (
	"L_NO"
)
REFERENCES "LECTURE" (
	"L_NO"
);




insert into lecture_registration values (
1 
,20
, 2
, '2021-11-19'
, '2021-11-26'
, 2
, '월'
, '13:00'
, '15:00'
, sysdate
, sysdate
, DEFAULT
, 1
, 2
, to_char(77000, '99,999')
);


--select 
--substr(lr_start_time, 1, instr(lr_start_time, ':')-1)
--from lecture_registration
--join lecture using (l_no)
--join instructor using (instructor_no);


--select
--DISTINCT  EXTRACT(year FROM LR_START_DATE)
--from lecture_registration
--order by EXTRACT(year FROM LR_START_DATE) desc;
--
--select 
--		* 
--		from lecture_registration
--		join lecture using (l_no)
--		join instructor using (instructor_no)
--		where lr_status = 'N';

commit;
--
--alter table lecture_registration add column LR_FEE not null;
--
--alter table lecture_registration drop column LR_FEE;
--alter table lecture_registration modify LR_FEE not null;
--update lecture_registration set LR_FEE = null;
