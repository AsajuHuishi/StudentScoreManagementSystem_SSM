USE student_score_ssm;

CREATE TABLE student_score(
	id INT PRIMARY KEY AUTO_INCREMENT,
	NO VARCHAR(10) UNIQUE NOT NULL,
	NAME VARCHAR(20) NOT NULL,
	score FLOAT(20),
	class_name INT
);

DESC student_score;


DROP TABLE student_score;


SELECT * FROM student_score;
SELECT * FROM student_score WHERE NO='201900703';
SELECT * FROM student_score WHERE score=60; 
SELECT * FROM student_score WHERE score=60 AND NO='201900703'; 

TRUNCATE TABLE student_score; 
INSERT INTO student_score(NO,NAME,score,class_name) VALUES
('201900501','Jinan',88,1),
('201900502','Qingdao',88,1),
('201900503','Zibo',83,1),
('201900504','Zaozhuang',76,1),
('201900505','Dongying',81,1),
('201900506','Yantai',85,1),
('201900601','Shijiazhuang',85,2),
('201900602','Tangshan',86,2),
('201900603','Qinhuangdao',85,2),
('201900604','Handan',80,2),
('201900605','Xingtai',80,2),
('201900701','Shenyang',85,3),
('201900702','Dalian',85,3),
('201900703','Anshan',75,3),
('201900704','FuShun',59,3);
INSERT INTO student_score(NO,NAME,score,class_name) VALUES('201900703','FuShun',59,3);
