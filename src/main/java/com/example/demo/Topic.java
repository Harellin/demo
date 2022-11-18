package com.example.demo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Topic
{
    @JsonProperty("item_name")
    private String  name;
    public Topic() { this.name="Food"; }
    public Topic(String _name) { this.name=_name; }
    String getname() { return this.name; }
    public String   convert_to_json() throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        return (mapper.writeValueAsString(this));
    }
    public void convert_from_json(String json_string) throws JsonProcessingException
    {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(json_string);
        name = jsonNode.get("item_name").asText();
    }
}