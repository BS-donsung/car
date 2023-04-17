insert into brand values
('현대'),
('쉐보레'),
('르노'),
('벤츠'),
('폭스바겐'),
('포르쉐');





insert into engine
values
('휘발유'),
('경유'),
('LPG'),
('전기');


insert into segment (segment)
values
('경차'),
('소형차'),
('준중형차'),
('중형차'),
('준대형차'),
('대형차'),
('스포츠카'),
('SUV 경형'),
('SUV 초소형'),
('SUV 준중형'),
('SUV 중형'),
('SUV 준대형'),
('SUV 대형'),
('트럭 경형'),
('트럭 초소형'),
('트럭 준중형'),
('트럭 중형'),
('트럭 준대형'),
('트럭 대형');

insert into car(capacity, cost, fuel_efficiency, imgurl, name, brand, engine, segment, height, length, width, rearThread, frontThread, wheelBase, frontSuspension, rearSuspension) values
(1650, 27000000, 16.6, "/avantteHD.jpg", "아반떼HD", "현대", "LPG", 3, 1420, 4710, 1825, 1579, 1590, 2720, '맥퍼슨 스트럿 서스펜션', '듀얼링크 서스펜션'),
(1650, 27000000, 16.6, "/allNewK3.jpg", "올뉴k3", "기아", "휘발유", 3, 1440, 4640, 1800, 1563, 1572, 2700, '맥퍼슨 스트럿 서스펜션', '맥퍼슨 스트럿 서스펜션');

insert into user(username, createDate, email, exp, nickname, password, role, sid)
values
('banana','2022-10-10','banana@banana',0,'banana','banana','ROLE_USER',5442);

INSERT INTO store(car, user) VALUES
(1, 'banana');

insert into caroption(car, cost, option, opt) VALUES
(2, 1000000, 1, 1),
(2, 1000000, 2, 1),
(2, 1000000, 3, 1);

SELECT c.* from car c
WHERE c.segment = (SELECT sid FROM segment WHERE segment LIKE '소형차');

select * from car c
inner join segment s
on s.segment = "준중형차"
where c.brand = "현대";

select * from caroption where car = 1;