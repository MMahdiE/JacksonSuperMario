package json.jsonparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import json.jsonparsing.pojo.SimpleTestCasePOJO;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCaseJsonSource = "{ \"title\": \"Coder From Scratch\", \"author\": \"Rui\" }";
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

    @Test
    void toJson() throws IOException {

        SimpleTestCasePOJO pojo = new SimpleTestCasePOJO();
        pojo.setTitle("Testing 123");

        JsonNode node = Json.toJson(pojo);


        assertEquals(pojo.getTitle(), "Testing 123");
    }

    @Test
    void stringify() throws IOException {

        SimpleTestCasePOJO pojo = new SimpleTestCasePOJO();
        pojo.setTitle("Testing 123");

        JsonNode node = Json.toJson(pojo);

        System.out.println(Json.stringify(node));
        System.out.println(Json.prettyPrint(node));
    }
}