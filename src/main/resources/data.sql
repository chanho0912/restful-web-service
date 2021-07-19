insert into account values(90001, sysdate(), 'User1', 'test111', '701010-111111');
insert into account values(90002, sysdate(), 'User2', 'test222', '801010-111111');
insert into account values(90003, sysdate(), 'User3', 'test333', '901010-111111');

insert into post values(10001, 'My first post', 90001);
insert into post values(10002, 'My second post', 90001);