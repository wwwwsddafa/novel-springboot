# ============================================================
# 墨阅书城 · 平台后台（novel-admin）
# 构建上下文：模块根目录 novel-admin/
#   由 docker-compose 指定 context: ./novel-admin
# 前置条件：先执行 mvn package 生成 target/novel-admin-*.jar
# ============================================================
FROM eclipse-temurin:21-jre

LABEL maintainer="moyue" \
      description="novel-admin 平台后台管理"

ENV TZ=Asia/Shanghai \
    LANG=C.UTF-8 \
    JAVA_OPTS="-Xms256m -Xmx512m"

WORKDIR /app

# 1) Spring Boot 可执行 jar
COPY target/novel-admin-*.jar /app/novel-admin.jar

# 2) 外部配置（端口、Redis 等）
COPY src/main/build/config/ /app/config/

# 3) Docker 环境覆盖层：ShardingSphere 数据源指向宿主机 MySQL
COPY src/main/build/docker/config/ /app/config/

EXPOSE 8089

ENTRYPOINT ["sh", "-c", "exec java $JAVA_OPTS -Duser.timezone=Asia/Shanghai -jar /app/novel-admin.jar"]
