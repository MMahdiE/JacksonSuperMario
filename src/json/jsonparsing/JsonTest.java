package json.jsonparsing;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import json.jsonparsing.pojo.AuthorPOJO;
import json.jsonparsing.pojo.BookPOJO;
import json.jsonparsing.pojo.DayPOJO;
import json.jsonparsing.pojo.SimpleTestCasePOJO;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class JsonTest {

    private String simpleTestCaseJsonSource = "{\n" +
            "  \"title\": \"Coder From Scratch\",\n" +
            "  \"author\": \"Rui\"\n" +
            "}";
    private String dayScenario1 = "{\n" +
            "  \"date\": \"2019-12-25\",\n" +
            "  \"name\": \"Christmas Day\"\n" +
            "}";
    private String authorBooksScenario = "{\n" +
            "  \"authorName\": \"Rui\",\n" +
            "  \"books\": [\n" +
            "    {\n" +
            "      \"title\": \"title1\",\n" +
            "      \"isPrint\": true,\n" +
            "      \"publishDate\": \"2019-12-25\"\n" +
            "    },\n" +
            "    {\n" +
            "      \"title\": \"title2\",\n" +
            "      \"isPrint\": false,\n" +
            "      \"publishDate\": \"2019-01-01\"\n" +
            "    }\n" +
            "  ]\n" +
            "}";

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

    @Test
    void dayTestScenario1() throws IOException {

        JsonNode node = Json.parse(dayScenario1);
        DayPOJO pojo = Json.fromJson(node, DayPOJO.class);

        assertEquals("2019-12-25", pojo.getDate().toString());
    }

    @Test
    void authorBooksScenario() throws IOException {

        JsonNode node = Json.parse(authorBooksScenario);
        AuthorPOJO pojo = Json.fromJson(node, AuthorPOJO.class);

        System.out.println("Author: " + pojo.getAuthorName());
        for(BookPOJO bp: pojo.getBooks()) {
            System.out.println("Book: " + bp.getTitle());
            System.out.println("Is In Print: " + bp.isPrint());
            System.out.println("Date: " + bp.getPublishDate());
        }
    }
}