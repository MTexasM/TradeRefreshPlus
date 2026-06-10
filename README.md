# Trade Refresh Plus

一个为 Minecraft 1.21.1 NeoForge 平台开发的村民交易快速刷新模组。

## 功能特性

✨ **核心功能**
- 🔑 **快捷键刷新**：按下 C 键（可自定义）可刷新村民的交易内容
- ⏱️ **长按快速刷新**：长按快捷键可连续快速刷新交易
- 🚫 **首次交易限制**：仅限于未进行过交易的村民
- 📢 **刷新提示**：刷新成功时显示通知信息

🔗 **集成特性**
- **JEI 集成**：检测 JEI 收藏夹中的物品，防止刷新
- **EMI 集成**：检测 EMI 收藏夹中的物品，防止刷新
- **REI 集成**：检测 REI 收藏夹中的物品，防止刷新

当长按快速刷新时，如果出现了收藏夹中的物品，刷新会自动停止并显示提示："无法刷新收藏夹的物品"

⚙️ **可配置选项**
- 刷新延迟时间
- 通知显示切换
- 快速刷新触发时间
- 各集成模组的开启/关闭

## 使用方法

1. 打开村民交易界面
2. 按 C 键（默认）刷新一次交易
3. 长按 C 键可快速连续刷新（除非遇到收藏夹物品）

## 配置

在 `config` 文件夹中找到 `trade_refresh_plus-client.toml`，可修改：
- `refreshDelay`：��新延迟（单位：刻）
- `showNotification`：是否显示通知
- `holdDurationTicks`：触发快速刷新的按住时间
- 各模组集成的启用/禁用状态

## 前置模组

- **必需**：NeoForge (1.21.1)
- **可选**：JEI、EMI、REI（用于收藏夹集成）

## 参考

本模组参考了以下优秀项目的实现思路：
- [Minecraft-LightLand/TradeRefresh](https://github.com/Minecraft-LightLand/TradeRefresh)
- [Mrbysco/Retraining](https://github.com/Mrbysco/Retraining)
- [henkelmax/trade-cycling](https://github.com/henkelmax/trade-cycling)

## 许可证

MIT License

## 作者

MTexasM
