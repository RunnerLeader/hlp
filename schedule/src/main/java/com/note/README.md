### 实现定时任务方案

- while循环 + Thread.sleep()
- TimerTask
- 任务线程池ScheduledExecutorService
- spring的Task
- SpringBoot 中的定时任务注解@Schedule
- 三方调度框架 quartz xxljob等

### 分布式集群情况下 单机任务存在什么问题
- 1 幂等问题  怎么保证一个任务只被一台机器执行
- 2 如何动态调整任务执行时间(不重启服务)
- 3 部署任务的机器发生故障怎么转移
- 4 怎么对任务进行监控
- 5 怎么实现分片 解决单机性能瓶颈
- 6 动态扩展怎么实现(不停服务)

##### 分布式任务调度解决方案
- 1 幂等问题
  > 假设 有个邮件服务, 部署了A B 两台机器, 每天凌晨执行任务`跑批发送邮件`
  如何做到只能有一台机器执行任务
  - 分布式锁实现调度控制
  - 配置文件 redis mysql 做调度开关
  - 数据库表唯一约束
  - 分布式调度平台TBSchedule (当当)Elastic-job (唯品会)Saturn (大众点评)xxl-job google-corn等
  - 自研









### 传统任务缺陷
容错 分片  幂等 集群  告警  





