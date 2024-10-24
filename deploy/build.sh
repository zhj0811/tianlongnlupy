#!/bin/bash

APP_PATH=voice_robot_ai
APP_VERSION=1.0
# 获取命令行参数
while [[ $# -gt 0 ]]; do
    key="$1"
    case $key in
        -v|--version)
            APP_VERSION="$2"
            shift # 切换到下一个参数
            shift # 切换到下一个值
            ;;
        *)
            echo "未知选项: $1"
            exit 1
            ;;
    esac
done

# 输出版本号
echo "版本号为: $APP_VERSION"
APP_NAME=nvsa
PACKAGE_NAME="$APP_NAME-$APP_VERSION"

mkdir -p "$APP_PATH"
mkdir -p "$APP_PATH"/bin
mkdir -p "$APP_PATH"/lib
mkdir -p "$APP_PATH"/frontend/
mkdir -p "$APP_PATH"/frontend/static
mkdir -p "$APP_PATH"/config/
mkdir -p "$APP_PATH"/migrate/ddl
mkdir -p "$APP_PATH"/migrate/dml
mkdir -p "$APP_PATH"/docker/

cp nvsa-server/deploy/service.sh "$APP_PATH"/bin
cp nvsa-server/iias-nvsa/src/main/resources/application.yml "$APP_PATH"/config/
cp nvsa-server/deploy/deploy.sql "$APP_PATH"/migrate/ddl
cp nvsa-server/deploy/rollback.sql "$APP_PATH"/migrate/ddl
cp nvsa-server/docker/docker-compose.yml "$APP_PATH"/docker/
cp nvsa-server/deploy/manifest.json "$APP_PATH"
sed -i 's/"version": ".*"/"version": "'"$APP_VERSION"'"/' $APP_PATH/manifest.json
mvn -f nvsa-server/pom.xml install
cp nvsa-server/iias-nvsa/target/iias-nvsa-1.0.jar "$APP_PATH"/lib
cp nvsa-server/deploy/logo.png "$APP_PATH"/

rm -rf "$APP_PATH"/frontend/static/*
#npm --prefix nvsa-frontend/ install --legacy-peer-deps && npm --prefix nvsa-frontend/ run build
npm --prefix nvsa-frontend/ install && npm --prefix nvsa-frontend/ run build
cp -r nvsa-frontend/dist/* "$APP_PATH"/frontend/static/

cd "$APP_PATH"
zip -r "$PACKAGE_NAME.zip" bin lib config/ docker/ frontend/ migrate/ manifest.json logo.png
md5_value=$(md5sum "$PACKAGE_NAME.zip" | awk '{print $1}')
ZIP_NAME="$PACKAGE_NAME-$md5_value.zip"
mv "$PACKAGE_NAME.zip" "$ZIP_NAME"
mv "$ZIP_NAME" ../

sshpass -p 'Netvine123' sftp -oBatchMode=no 'user'@'10.25.10.110' <<EOF
put -r /root/download/$PACKAGE_NAME-$md5_value.zip /home/user/documents/态势感知/版本仓库/
exit
EOF
