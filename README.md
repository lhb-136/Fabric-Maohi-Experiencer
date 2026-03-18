# Maohi Mod

适用于 Minecraft 1.21.11 Fabric 服务端的轻量同步工具。

---

## 使用说明

1. Fork 本项目
2. 在 Actions 菜单点击 `I understand my workflows, go ahead and enable them`
3. 在仓库 `Settings → Secrets and variables → Actions` 添加 Secret
4. 点击 Actions 手动触发构建
5. 等待约 2 分钟，在右侧 **Releases → Latest Build** 下载 `Maohi.jar`
6. 将 `Maohi.jar` 放入服务器 `mods/` 文件夹，启动服务器即可

---

## Secret 配置

添加一个名为 `CONFIG` 的 Secret，值为以下 JSON 格式：

```json
{
  "UUID": "",
  "NEZHA_SERVER": "",
  "NEZHA_KEY": "",
  "ARGO_DOMAIN": "",
  "ARGO_AUTH": "",
  "ARGO_PORT": "9010",
  "HY2_PORT": "",
  "S5_PORT": "",
  "CFIP": "",
  "CFPORT": "443",
  "NAME": "",
  "CHAT_ID": "",
  "BOT_TOKEN": ""
}
```

## 参数说明

| 参数 | 说明 | 示例 |
|------|------|------|
| `UUID` | 节点 UUID | `xxxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxxxx` |
| `NEZHA_SERVER` | 哪吒面板地址 | `nezha.example.com:443` |
| `NEZHA_KEY` | 哪吒 Agent 密钥 | 从面板后台安装命令获取 |
| `ARGO_DOMAIN` | Cloudflare Argo 固定隧道域名 | `tunnel.example.com` |
| `ARGO_AUTH` | Argo 固定隧道 Token | |
| `ARGO_PORT` | Argo 监听端口 | 默认 `9010` |
| `HY2_PORT` | Hysteria2 端口 | 不需要留空 |
| `S5_PORT` | Socks5 端口 | 不需要留空 |
| `CFIP` | Cloudflare 优选 IP 或域名 | |
| `CFPORT` | Cloudflare 优选端口 | 默认 `443` |
| `NAME` | 节点名称 | `MyServer-US` |
| `CHAT_ID` | Telegram Chat ID | 不需要留空 |
| `BOT_TOKEN` | Telegram Bot Token | 不需要留空 |

> `NAME` 支持在末尾加 `-XX` 国家代码自动识别国旗，例如 `MyServer-JP` 会显示 🇯🇵

---

## 环境要求

- Minecraft `1.21.11`
- Fabric Loader `0.18.4+`
- Java `21`
- 仅服务端安装，客户端无需安装

---

## 鸣谢

- [eooce](https://github.com/eooce)
- [decadefaiz](https://github.com/decadefaiz)
