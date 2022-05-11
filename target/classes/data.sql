INSERT INTO user (name,email,password) VALUES ('Kowal123','kowal@o2.pl',123456);
INSERT INTO user (name,email,password) VALUES ('Jacob11','jakub@wp.pl',123456);
INSERT INTO user (name,email,password) VALUES ('Nowak','nowak@gmail.com',123456);
INSERT INTO user (name,email,password) VALUES ('Kwiatkowski','kwiat@interia.pl',123456);
INSERT INTO user (name,email,password) VALUES ('Zielony','green@tlen.pl',123456);
INSERT INTO user (name,email,password) VALUES ('Piaty','piec@wp.pl',123456);


INSERT INTO lecture (id,lecture_name,lecturer) VALUES (1,'Introduction to Cloud Technologies','Zbigniew');
INSERT INTO lecture (id,lecture_name,lecturer) VALUES (2,'Microsoft Azure in IoT','Mariusz');
INSERT INTO lecture (id,lecture_name,lecturer) VALUES (3,'Application examples in Cloud Technology','Zbigniew');

INSERT INTO lecture (id,lecture_name,lecturer) VALUES (4,'Android essentials','Mariusz');
INSERT INTO lecture (id,lecture_name,lecturer) VALUES (5,'Creating apps in Android Studio','Zbigniew');
INSERT INTO lecture (id,lecture_name,lecturer) VALUES (6,'Modern mobile applications','Krzysztof');

INSERT INTO lecture (id,lecture_name,lecturer) VALUES (7,'What is Artificial Intelligence','Krzysztof');
INSERT INTO lecture (id,lecture_name,lecturer) VALUES (8,'Types of AI','Krzysztof');
INSERT INTO lecture (id,lecture_name,lecturer) VALUES (9,'Future with AI','Mariusz');

INSERT INTO registered_lectures (user_name,lecture_id) VALUES ('Kowal123',1);
INSERT INTO registered_lectures (user_name,lecture_id) VALUES ('Kowal123',5);
INSERT INTO registered_lectures (user_name,lecture_id) VALUES ('Kowal123',9);

INSERT INTO registered_lectures (user_name,lecture_id) VALUES ('Nowak',1);
INSERT INTO registered_lectures (user_name,lecture_id) VALUES ('Nowak',9);

INSERT INTO registered_lectures (user_name,lecture_id) VALUES ('Jacob11',3);
INSERT INTO registered_lectures (user_name,lecture_id) VALUES ('Jacob11',6);

INSERT INTO registered_lectures (user_name,lecture_id) VALUES ('Kwiatkowski',1);
INSERT INTO registered_lectures (user_name,lecture_id) VALUES ('Kwiatkowski',7);

INSERT INTO registered_lectures (user_name,lecture_id) VALUES ('Zielony',1);

INSERT INTO registered_lectures (user_name,lecture_id) VALUES ('Piaty',1);