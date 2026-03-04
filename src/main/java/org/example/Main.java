package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.json.JSONObject;
import org.json.JSONArray;

public class Main {

    public static void main(String[] args) {

        try {
            BufferedReader reader =
                    new BufferedReader(new InputStreamReader(System.in));

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue; // ignore empty lines
                }

                JSONObject request;

                try {
                    request = new JSONObject(line);
                } catch (Exception e) {
                    System.err.println("Invalid JSON received");
                    continue;
                }

                String method = request.optString("method");
                Object id = request.opt("id");

                if (method == null) continue;

                JSONObject response = new JSONObject();
                response.put("jsonrpc", "2.0");
                response.put("id", id);

                if ("initialize".equals(method)) {

                    JSONObject result = new JSONObject();
                    result.put("protocolVersion", "2025-11-25");

                    JSONObject tools = new JSONObject();
                    tools.put("listChanged", false);

                    JSONObject capabilities = new JSONObject();
                    capabilities.put("tools", tools);

                    result.put("capabilities", capabilities);

                    JSONObject serverInfo = new JSONObject();
                    serverInfo.put("name", "java-mcp-server");
                    serverInfo.put("version", "1.0.0");

                    result.put("serverInfo", serverInfo);

                    response.put("result", result);
                }

                else if ("tools/list".equals(method)) {

                    JSONObject tool = new JSONObject();
                    tool.put("name", "helloTool");
                    tool.put("description", "Returns hello message");

                    JSONObject inputSchema = new JSONObject();
                    inputSchema.put("type", "object");
                    inputSchema.put("properties", new JSONObject());
                    inputSchema.put("additionalProperties", false);

                    tool.put("inputSchema", inputSchema);

                    JSONArray toolsArray = new JSONArray();
                    toolsArray.put(tool);

                    JSONObject result = new JSONObject();
                    result.put("tools", toolsArray);

                    response.put("result", result);
                }

                else if ("tools/call".equals(method)) {

                    JSONObject params = request.getJSONObject("params");
                    String name = params.getString("name");

                    if ("helloTool".equals(name)) {

                        JSONObject textContent = new JSONObject();
                        textContent.put("type", "text");
                        textContent.put("text", "Hello from Java MCP server");

                        JSONArray contentArray = new JSONArray();
                        contentArray.put(textContent);

                        JSONObject result = new JSONObject();
                        result.put("content", contentArray);

                        response.put("result", result);
                    }
                }

                // IMPORTANT: only print if result exists
                if (response.has("result")) {
                    System.out.println(response.toString());
                    System.out.flush();
                }
            }

        } catch (Exception e) {
            System.err.println("Server error: " + e.getMessage());
        }
    }
}