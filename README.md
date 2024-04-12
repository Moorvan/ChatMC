# ChatMC Tool 

## Introduction

ChatMC harnesses LangChain and ChiselFV technologies to create a chat-based tool that enhances hardware design and formal verification by automating the generation of design code and formal verification assertions. LangChain and ChiselFV likely refer to natural language processing and hardware description language/formal verification tools respectively. Their integration with ChatMC enables seamless property verification within the Chisel environment, significantly improving verification efficiency and accessibility. Demonstrated through tasks such as multi-port memory assistance and simple pipeline verification, ChatMC provides immediate feedback and lowers the entry barriers to complex design and verification tasks, making it a promising tool for engineers and researchers striving to streamline sophisticated hardware development and verification processes.

## Demo CURL

To initiate a conversation, use the following CURL command:

```shell
curl -X WEBSOCKET "ws://localhost:8080/mc-chat"
```
This CURL command establishes a WebSocket connection to the ChatMC tool, allowing you to start interacting with the system.

## Examples

Refer to the `./examples` directory for sample conversations showcasing ChatMC's functionality.

## Deps

This project relies on two main dependencies:

### File System Server For ChatMC

[File System Server](https://github.com/Moorvan/file-system-sever) provides a file system HTTP service for the ChatMC project. The service enables users to execute various file operations through HTTP requests.

### ChiselFV Verification Framework

Using ChatMC necessitates the prior integration of the [ChiselFV](https://github.com/Moorvan/ChiselFV) verification framework into the target project. ChiselFV provides formal verification capabilities at the Chisel level, offering a suite of tools and methodologies for defining formal syntax and conducting formal verification of hardware designs. By incorporating ChiselFV into the target project, it can leverage these capabilities in conjunction with ChatMC. This integration ensures the correctness and reliability of hardware designs by employing advanced formal verification techniques.

## Configuration

To configure the ChatMC Tool, adjust the following properties in the ChatMCTool/src/main/resources/application.properties file:

```properties
quarkus.langchain4j.openai.base-url=https://xxx/
quarkus.langchain4j.openai.api-key=sk-xxx
quarkus.langchain4j.openai.chat-model.model-name=gpt-4-0125-preview

prompts.init.filepath=/path/to/you/prompts
prompts.maxResults=10
prompts.minScore=0.9

quarkus.rest-client."dev.morvan.client.MCToolsClient".url=http://xxxxxx:8081

quarkus.rest-client."dev.morvan.client.MCToolsClient".connect-timeout=5000
quarkus.rest-client."dev.morvan.client.MCToolsClient".read-timeout=100000
```

- `quarkus.langchain4j.openai.base-url`: This property specifies the base URL for the LangChain4j OpenAI integration. Provide the appropriate URL for accessing the OpenAI service.
- `quarkus.langchain4j.openai.api-key`: This property requires the API key for the LangChain4j OpenAI integration. Ensure to provide the correct API key to authenticate with the OpenAI service.
- `quarkus.langchain4j.openai.chat-model.model-name`: This property specifies the model name for the LangChain4j OpenAI chat model. Provide the name of the large model capable of providing embedded knowledge and handling function calls to utilize advanced features within the ChatMC Tool, such as 'gpt-4'.
- `prompts.init.filepath`: This property specifies the path to the directory containing prompts used for knowledge infusion.
- `quarkus.rest-client."dev.morvan.client.MCToolsClient".url`: This property requires the URL where the File System Server For ChatMC service is running. Ensure to provide the correct URL to establish communication between ChatMC and the file system server.

## Build & Run

This project uses Quarkus, the Supersonic Subatomic Java Framework.

### Running the application in dev mode

You can run your application in dev mode that enables live coding using:

```shell script
./gradlew quarkusDev
```


### Packaging and running the application

The application can be packaged using:

```shell script
./gradlew build
```

It produces the `quarkus-run.jar` file in the `build/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `build/quarkus-app/lib/` directory.

The application is now runnable using `java -jar build/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:

```shell script
./gradlew build -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar build/*-runner.jar`.
