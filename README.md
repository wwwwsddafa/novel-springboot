# 墨阅书城

一个支持 PC 与移动端阅读的原创文学平台，包含前台门户、作家专区、平台管理后台与内容采集四个子系统，覆盖作品推荐、分类检索、排行榜、在线阅读、评论互动、作家投稿、会员订阅与数据统计等完整业务闭环。

## 快速开始

### 一条命令跑起来

不需要 JDK、不需要 Maven、不需要源码编译——镜像里已经打包好了程序，MySQL 镜像里也已经烤入了 **243 本书 / 11360 章正文**的示例数据。

环境要求：Docker 20.10+ 与 Docker Compose v2（`docker compose version` 能输出版本号即可）。

```bash
docker compose up -d
```

首次启动 MySQL 需要导入约 185MB 初始数据，耗时 2~5 分钟。期间 `docker compose ps` 里 mysql 显示 `health: starting` 属正常现象，三个应用容器会等数据库健康后才启动。看到 `healthy` 就可以访问了。

| 服务 | 地址 | 账号 |
| --- | --- | --- |
| 前台门户 | <http://localhost:8083> | — |
| 管理后台 | <http://localhost:8089> | `admin` / `admin888`（登录后请立即修改） |
| 采集系统 | <http://localhost:8081> | `admin` / `admin888` |

常用命令：

```bash
docker compose ps                        # 查看状态
docker compose logs -f novel-front       # 跟踪某个服务日志
docker compose down                      # 停止（数据保留）
docker compose down -v                   # 停止并清空数据，下次启动重新导入
docker compose pull && docker compose up -d   # 更新到最新镜像
bash deploy/verify.sh                    # 部署自检（13 项）
```

`deploy/verify.sh` 会检查小说数据量、分表正文、增量脚本是否生效、三个服务可达性、章节页能否真正读出正文，以及站点品牌是否正确，最后给出「通过 N 项 / 失败 N 项」的结论。

> 如果你想改密码，复制 `.env.example` 为 `.env` 并修改其中的 `MYSQL_ROOT_PASSWORD` / `REDIS_PASSWORD`，然后 `docker compose down -v && docker compose up -d` 重新初始化。

### 从源码运行

**环境要求**：JDK 21、Maven 3.8+、MySQL 8.0、Redis 6+

**1. 初始化数据库**

```bash
mysql -uroot -p < doc/sql/novel_plus.sql
```

如需初始化小说示例数据（含正文），改用压缩包版本：解压 `doc/sql/novel_plus_data.sql.zip`，先执行其中的完整 SQL，再按日期顺序执行 `2026*.sql` 增量脚本。注意 `novel_plus.sql` 只有表结构、**不含小说数据**，两者不要混用。

**2. 修改配置**

各模块 `src/main/resources/application-dev.yml` 中调整数据库地址、账号与 Redis 配置；`application-website.yml` 配置站点名称与 SEO 信息。

**3. 启动服务**

```bash
mvn clean install -DskipTests

java -jar novel-admin/target/novel-admin.jar --spring.profiles.active=dev
java -jar novel-front/target/novel-front.jar --spring.profiles.active=dev
java -jar novel-crawl/target/novel-crawl.jar --spring.profiles.active=dev
```

也可以直接在 IDE 中分别运行三个模块的启动类。

### 自行构建镜像

改完代码想自己打包，用 build 覆盖文件：

```bash
mvn -B -DskipTests clean package
docker compose -f docker-compose.yml -f docker-compose.build.yml up -d --build
```

构建产物的 tag 与 `docker-compose.yml` 里的镜像名一致，之后单独 `docker compose up -d` 也能直接用。

### 复用宿主机已有的 MySQL / Redis

不启动容器化的数据库，只容器化三个 Java 服务，通过 `host.docker.internal` 连宿主机：

```bash
docker compose -f docker-compose.dev.yml up -d --build
```

需自行准备 `novel_plus` 库与 Redis，端口在 `docker-compose.dev.yml` 中调整；若宿主机 8083 / 8089 / 8081 已被占用，先停掉本地进程。

## 功能特性

**前台门户（novel-front）**

- 作品推荐位、分类浏览、关键词检索、榜单排行
- 在线阅读器：多种阅读主题、字号与背景切换、阅读进度与书架收藏
- 章节评论、评论回复、举报反馈
- 用户注册登录、会员中心、充值订阅、阅读记录
- 作家专区：作品创建与上下架、章节发布与修改、收益明细
- AI 辅助写作：章节内容扩写、缩写、续写与润色，作品封面图生成

**管理后台（novel-admin）**

- 作品、章节、分类、作家、会员的完整管理
- 评论与举报审核、新闻公告发布、友情链接维护
- 站点信息配置（站名、域名、SEO、Logo）
- 权限体系：用户、角色、菜单、部门、数据权限
- 数据统计看板与实时报表

**采集系统（novel-crawl）**

- 采集源规则配置与在线调试
- 单本/批量采集、定时增量更新、采集进度监控
- 采集异常告警邮件通知

## 技术选型

| 分类 | 技术 |
| --- | --- |
| 基础框架 | Spring Boot 3.4 / JDK 21 |
| 持久层 | MyBatis + MyBatis Dynamic SQL + PageHelper |
| 数据存储 | MySQL 8.0（ShardingSphere-JDBC 分表）+ Redis |
| 安全 | Spring Security + Apache Shiro + JJWT |
| 模板引擎 | Thymeleaf |
| 前端 | Layui + ECharts + wangEditor |
| AI 能力 | Spring AI（对话模型 + 文生图模型） |
| 内容解析 | Jsoup + 自定义规则引擎 |
| 部署 | Docker / Docker Compose / GitHub Actions |

## 项目结构

```
.
├── novel-common              公共模块：实体、工具、异常处理、通用配置
├── novel-front               前台门户 + 作家专区（端口 8083）
├── novel-admin               平台管理后台（端口 8089）
├── novel-crawl               内容采集系统（端口 8081）
├── templates                 前台模板主题（green / orange / blue / dark）
├── deploy                    发布环境配置（ShardingSphere 配置、部署自检脚本）
├── doc/sql                   数据库脚本、示例数据压缩包、带数据的 MySQL 镜像定义
├── docker-compose.yml        一键部署（拉取已构建镜像）
├── docker-compose.build.yml  本地构建覆盖文件
└── docker-compose.dev.yml    复用宿主机数据库的开发版
```

## 配置说明

- 小说内容支持两种存储方式：数据库分表存储、TXT 文本存储，在 `application-common.yml` 中切换。
- 图片存储支持本地磁盘与阿里云 OSS，可在 `application-oss.yml` 中配置。
- AI 写作功能需要在配置文件中填写兼容 OpenAI 协议的大模型服务地址与密钥。
- 采集系统的邮件告警通过环境变量 `MAIL_PASSWORD` 注入邮箱授权码，请勿将密钥提交到仓库。

> ⚠ `deploy/shardingsphere-jdbc.yml` 中数据源的 `password` 必须与 `.env` 里的 `MYSQL_ROOT_PASSWORD` 一致。该文件由 ShardingSphere 直接读取，不经过 Spring 占位符解析，无法写成 `${...}`。

## 关于镜像发布（维护者）

仓库配置了 GitHub Actions（`.github/workflows/docker-publish.yml`），推送到 `main` 分支或打 `v*` 标签时会自动构建四个镜像并推送到 GitHub Container Registry：

```
ghcr.io/<owner>/<repo>/novel-front
ghcr.io/<owner>/<repo>/novel-admin
ghcr.io/<owner>/<repo>/novel-crawl
ghcr.io/<owner>/<repo>/mysql-novel
```

首次发布后 GitHub Packages 默认把包设为 private，需要到仓库右侧 **Packages** 里把每个包的可见性改成 **Public**，否则别人 `docker pull` 会报 401。想换成自己的 registry，设置环境变量即可：

```bash
IMAGE_PREFIX=your.registry/your-namespace docker compose up -d
```

## 说明

本项目仅供学习与技术交流使用，采集模块仅用于演示数据处理流程，请遵守目标站点的使用条款与相关法律法规。
