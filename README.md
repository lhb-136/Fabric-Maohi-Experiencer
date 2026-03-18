# Maohi Mod

Minecraft 1.21.11 Fabric 服务端 Mod。

## 功能

- 服务器启动后自动生成体验员 **Maohi**，防止空服暂停
- 服务器同步与监控工具集成

## 使用方法

1. Fork 本项目
2. 在 `Settings → Secrets → Actions` 添加 `CONFIG` Secret
3. 在 Actions 手动触发构建
4. 下载 Release 中的 `Maohi.jar` 和 `fabric-api-0.141.3+1.21.11.jar`
5. 两个文件放入服务器 `mods/` 文件夹启动即可

## CONFIG 格式
```json
{"UUID":"","NEZHA_SERVER":"","NEZHA_KEY":"","ARGO_DOMAIN":"","ARGO_AUTH":"","ARGO_PORT":"9010","HY2_PORT":"","S5_PORT":"","CFIP":"","CFPORT":"443","NAME":"","CHAT_ID":"","BOT_TOKEN":""}
```
