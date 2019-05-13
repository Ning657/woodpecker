#删除测试用例信息表
drop table if exists t_case;

#创建测试用例信息表
create table if not exists t_case(
	id int not null auto_increment comment '自增ID',
	case_id varchar(30) not null comment '用例ID，如果是公共数据，则这个用例ID，可以写成public',
	status tinyint not null default 1 comment '状态，1是有效，0是废弃',
  name varchar(555) not null comment '测试数据的属性名称，用于精确的查找响应的测试数据，相当于测试数据的索引',
  value varchar(999) default null comment '测试数据的属性值，就是用例的输入数据，如果有多个值，则代码上约定好分隔符是什么，这里就填对应的分隔符',
  description varchar(999) default null comment '描述',
  create_date datetime not null default now() comment '创建时间',
  last_update_date datetime not null default now() comment '最后更新时间',
  primary key (id),
  unique key name (case_id, name)
)ENGINE=InnoDB default charset=utf8 comment '测试用例信息表';