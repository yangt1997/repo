# AiKnowledgeBase - Java 1.8 + Spring Boot + MyBatis-Plus + AI

已连接远程仓库：`https://github.com/yangt1997/AiKnowledgeBase.git`

## 技术栈
- Java 1.8
- Spring Boot 2.7.18
- MyBatis-Plus 3.5.5
- H2（内存数据库，便于本地快速启动）
- OpenAI 兼容 Chat Completions API（通过 `RestTemplate` 调用）

## 功能说明
- `POST /api/ai/chat`：基础问答接口
- 内置 `knowledge_document` 表与初始化数据
- 先从知识库做简单 `like` 检索，再拼接上下文给大模型

## 项目结构
- `controller`：接口层
- `service`：AI 调用与知识库检索逻辑
- `mapper/entity`：MyBatis-Plus 数据访问层
- `config`：AI 模型参数与提示词配置

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
  -d '{"question":"什么是RAG"}'
```
