
create table otc_courses
(
	id bigint null,
	title varchar(150) null,
	description text null,
	price numeric(16,2) null,
	video_times int null,
	intructorId bigint null,
	constraint otc_courses_otc_user_USER_ID_fk
		foreign key (id) references otc_user (USER_ID)
);
alter table otc_courses modify id bigint auto_increment;

create table otc_title_courses_clip
(
	id bigint null,
	id_otc_courses bigint null,
	title varchar(150) null,
	video_times int null,
	source varchar(255) null,
	constraint otc_title_courses_clip_otc_courses_id_fk
		foreign key (id) references otc_courses (id)
);
alter table otc_title_courses_clip modify id bigint auto_increment;

create table otc_course_clip
(
	id bigint null,
	id_title_courses_clip bigint null,
	title varchar(150) null,
	video_times int null,
	constraint otc_course_clip_otc_title_courses_clip_id_fk
		foreign key (id) references otc_title_courses_clip (id)
);
alter table otc_course_clip modify id bigint auto_increment;

alter table otc_courses
    add created_date date null;

alter table otc_courses
    add created_by date null;

alter table otc_courses
    add updated_date date null;

alter table otc_courses
    add updated_by date null;

alter table otc_title_courses_clip
    add created_date date null;

alter table otc_title_courses_clip
    add created_by date null;

alter table otc_title_courses_clip
    add updated_date date null;

alter table otc_title_courses_clip
    add updated_by date null;

alter table otc_course_clip
    add created_date date null;

alter table otc_course_clip
    add created_by date null;

alter table otc_course_clip
    add updated_date date null;

alter table otc_course_clip
    add updated_by date null;
alter table otc_courses
	add image varchar(500) null;
alter table otc_courses
	add review text null;
alter table otc_courses
add instructorName varchar(255) null;
alter table otc_courses modify description varchar(6000) null;
ALTER TABLE otc_landmark_db.otc_courses MODIFY COLUMN title VARCHAR(255)
CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
ALTER TABLE otc_landmark_db.otc_courses MODIFY COLUMN description VARCHAR(255)
CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
ALTER TABLE otc_landmark_db.otc_courses MODIFY COLUMN instructorName VARCHAR(255)
CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
ALTER TABLE otc_landmark_db.otc_courses MODIFY COLUMN description text
CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
