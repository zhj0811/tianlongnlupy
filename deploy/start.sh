#!/bin/bash

# 后端
mvn install
mkdir -p docker/package
cp iias-nvsa/target/iias-nvsa-1.0.jar /data/iias/app/nvsa/current/lib
# 前端
cd frontend&&pnpm install --no-frozen-lockfile&&pnpm run build&&cd ..
rm -rf frontend/static
mv frontend/dist frontend/static
cp -r frontend/static /data/iias/app/nvsa/current/frontend/
#平台后端打包
# shellcheck disable=SC2164
cd ../iias/
git pull
mvn install
cp iias-gate/target/iias-gate.jar /home/workspace/3DPrison/docker/local/admin
cp iias-system/target/iias-system-1.0-SNAPSHOT.jar /home/workspace/3DPrison/docker/local/admin
#平台前端
cd iias-frontend&&pnpm install --no-frozen-lockfile&&pnpm run build&&cd ..
cp -r iias-frontend/dist ../3DPrison/docker/
#重启容器
docker-compose -f /data/iias/app/nvsa/current/docker/docker-compose.yml restart nvsa
docker restart system gate local-nginx-1
