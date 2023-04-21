# car

add readme
test webhook


#
INSERT INTO brand
VALUES
('기아'),
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

insert into option (oname)
VALUES
('통풍시트'),
('열선시트'),
('후방센서');

INSERT INTO car (capacity, fuel_efficiency, imgurl, name, brand, engine, segment, cost)
VALUES
(1591,15.6,'/avantte.jpg','아반떼HD','현대','휘발유',2, 25000000);

insert into caroption(car, option, isopt, cost)
values
(1, 1, 1,1000000),
(1, 2, 1,500000),
(1, 3, 0,0);

insert into user(username, createDate, email, exp, nickname, password, role, sid)
values
('banana','2022-10-10','banana@banana',0,'banana','banana','ROLE_USER',5442);


## frontend install pack
```bash
# vue awesome
npm i --save-dev @fortawesome/vue-fontawesome

# awesome icon
npm i --save-dev @fortawesome/free-solid-svg-icons
npm i --save-dev @fortawesome/free-brands-svg-icons
```

```js
# awesome icon main.js 설정
// awsome icon import
import { library } from '@fortawesome/fontawesome-svg-core'
import { fas } from '@fortawesome/free-solid-svg-icons'
import { fab } from '@fortawesome/free-brands-svg-icons'
import { FontAwesomeIcon } from '@fortawesome/vue-fontawesome'

library.add(fas, fab)
```
