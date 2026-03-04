🚀 Java MCP Server (Learning Project)

This project is a simple Java-based MCP (Model Context Protocol) server built for learning and experimentation.

It demonstrates how Claude Desktop connects to a custom tool server using:

Java

Maven

JSON-RPC

STDIN / STDOUT communication

MCP protocol structure

🧠 What This Project Demonstrates

This project helps understand:

How MCP works internally

How tools are registered using tools/list

How tools are executed using tools/call

How Claude communicates with a local process

How to structure JSON-RPC responses correctly

How to package a Java application using Maven

How Claude launches a .jar file and communicates via pipes

🔄 MCP Communication Flow

Claude Desktop launches the JAR:

java -jar mcp-java-1.0.jar

Claude sends JSON-RPC messages via STDIN

The Java server:

Reads JSON

Processes method (initialize, tools/list, tools/call)

Sends JSON response via STDOUT

Claude receives the response and displays output

🛠 Technologies Used

Java (JDK 21)

Maven

org.json library

Model Context Protocol (MCP)

Claude Desktop

📦 How to Build
mvn clean package

This generates:

target/mcp-java-1.0.jar
⚙️ How to Connect with Claude Desktop

In your cloud.config.json:

{
  "mcpServers": {
    "java-mcp": {
      "command": "java",
      "args": [
        "-jar",
        "C:/path/to/target/mcp-java-1.0.jar"
      ]
    }
  }
}

Restart Claude Desktop after updating config.

🧪 Example Tool

This project includes a sample tool:

helloTool – Returns a simple text response

You can extend it to:

Call APIs

Connect databases

Perform text processing

Run OCR

Execute business logic

🎯 Purpose of This Project

This project is built purely for learning MCP architecture and understanding tool integration.

It is not production-ready but serves as a foundation to:

Build custom AI tools

Create backend integrations

Develop advanced MCP servers

📚 What I Learned

MCP strict JSON structure requirements

Importance of inputSchema

JSON-RPC formatting rules

Difference between stdout and stderr

Process-based server communication

Debugging protocol-level errors

🚀 Future Improvements

Add multiple tools

Add argument validation

Convert to HTTP-based MCP server

Add logging system

Deploy as remote MCP service

👩‍💻 Author

Built for learning and experimentation.
