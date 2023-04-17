package json.jsonparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import json.jsonparsing.pojo.SimpleTestCasePOJO;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCaseJsonSource = "{ \"title\": \"Coder From Scratch\" }";
    @Test
    void parse() throws JsonProcessingException {

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        assertEquals(node.get("title").asText(), "Coder From Scratch");

    }

    @Test
    void fromJson() throws IOException {

        JsonNode node = Json.parse(simpleTestCaseJsonSource);
        SimpleTestCasePOJO pojo = Json.fromJson(node, SimpleTestCasePOJO.class);

        assertEquals(pojo.getTitle(), "Coder From Scratch");
    }
}