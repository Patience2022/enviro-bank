insert into account_types(id,account_type_name,minimum_amount_required) values (1,'Savings',1000);
insert into account_types(id,account_type_name,maximum_overdraft_limit) values (2, 'Current', 100000);

insert into accounts (account_number,balance,account_type,amount_available, customer) values ( 'acc_001',5000,1,4000,1);
insert into accounts (account_number,balance,overdraft,amount_available,account_type,customer) values ('acc_002',3000,50000,53000,2,1);

insert into accounts (account_number,balance,account_type,amount_available,customer) values ( 'acc_003',3000,1,2000,2);
insert into accounts (account_number,balance,overdraft,amount_available,account_type,customer) values ('acc_004',4000,40000,44000,2,2);

insert into accounts (account_number,balance,account_type,amount_available,customer) values ( 'acc_005',8000,1,7000,3);
insert into accounts (account_number,balance,overdraft,amount_available,account_type,customer) values ('acc_006',3000,10000,13000,2,3);

insert into accounts (account_number,balance,account_type,amount_available,customer) values ( 'acc_007',6000,1,5000,4);
insert into accounts (account_number,balance,overdraft,amount_available,account_type,customer) values ('acc_008',7000,14000,21000,2,4);

