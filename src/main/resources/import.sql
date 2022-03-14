/*-- This file allow to write SQL commands that will be emitted in test and dev.
-- The commands are commented as their support depends of the database
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-1');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-2');
-- insert into myentity (id, field) values(nextval('hibernate_sequence'), 'field-3');

insert into t_column (id, checked, name, title)
values (1, true, 'name', 'Name'),
       (2, true, 'age', 'Age'),
       (3, true, 'phone', 'Phone');


insert into t_dataset(id) values (1);

insert into t_row (id, dataset_id) values (1, 1), (2, 1), (3, 1);

insert into row_values (row_id, column_id, value)
values (1, 1, 'John'),
       (1, 2, '23'),
       (1, 3, '9199'),

       (2, 1, 'Chris'),
       (2, 2, '19'),
       (2, 3, '9199'),

       (3, 1, 'Mathew'),
       (3, 2, '30'),
       (3, 3, '9199');
*/