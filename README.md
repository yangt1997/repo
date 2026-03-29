# AiKnowledgeBase - 基础 Spring Boot AI 框架

已连接远程仓库：`https://github.com/yangt1997/AiKnowledgeBase.git`

## 技术栈
- Java 17
- Spring Boot 3.3.4
- Spring AI 1.0.0-M2
- OpenAI Model Starter

## 项目结构
- `controller`：提供 `/api/ai/chat` 接口
- `service`：封装 AI 调用逻辑
- `model`：请求/响应对象
- `config`：系统提示词配置

## 启动步骤
1. 配置环境变量：
   ```bash
   export OPENAI_API_KEY=你的Key
   ```
2. 启动项目：
   ```bash
   mvn spring-boot:run
   ```

## 请求示例
```bash
curl -X POST http://localhost:8080/api/ai/chat \
  -H "Content-Type: application/json" \
  -d '{"question":"请解释一下什么是知识库问答"}'
```

返回：
```json
{
  "answer": "..."
}
```
