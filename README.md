# foodie
 
慕课网架构师课程后端代码（有改动）
链接：http://class.imooc.com/sale/javaarchitect?mc_marking=f684c2c3a2a40b1606bda10edc3e3775&mc_channel=banner

## 前言
foodie 项目是本人学习练手的单体电商项目，里面有些地方内容并不适合生产使用。
注：有许多地方并未完全实现。
## 项目介绍
foodie项目是电商系统，目前仅包括前台（用户使用）商城系统（后台管理系统没弄），基于SpringBoot+MyBatis实现。前台商城系统包含首页门户、商品推荐、商品搜索、商品展示、购物车、订单流程、会员中心等模块。

### 组织结构
```
foodie
├── foodie-api -- 前台商城系统接口
├── foodie-common -- 工具类及通用代码
├── foodie-service -- 服务层
├── foodie-mapper -- ORM层接口
└── foodie-pojo -- 数据库对应实体类
```
## 技术选型
### 后端技术
| 技术 | 说明 | 官网 |
| :---: | :----: | :---: |
| SpringBoot | 容器+MVC框架 | 	https://spring.io/projects/spring-boot |
| MyBatis    | ORM框架      | http://www.mybatis.org/mybatis-3/zh/index.html     |
| MyBatis-Plus | Mybatis增强 | https://mp.baomidou.com/ |
| Lombok    | 简化对象封装工具      | https://github.com/rzwitserloot/lombok     |

### 前端技术
| 技术 | 说明 | 官网 |
| :---: | :----: | :---: |
| Vue | 前端框架 | 	https://vuejs.org/ |
