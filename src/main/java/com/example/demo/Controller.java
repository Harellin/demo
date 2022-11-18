package com.example.demo;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController
public class Controller
{
    private List<Topic>  items = new ArrayList<>();
    @PostMapping("/topic/new")
    public ResponseEntity<Void> additem(@RequestBody String text)
    {
        Topic topic = new Topic(text); items.add(topic);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("/topic")
    public ResponseEntity<String> getItems() throws JsonProcessingException
    {
        String value = "";
        for (int i = 0; i < items.size(); i++)
        {
            if (i == items.size()-1) { value = value + items.get(i).getname() + "\n"; continue; }
            value = value + items.get(i).getname() + ",\n";
        }
        return ResponseEntity.ok(value);
    }
    @DeleteMapping("topic/{index}")
    public ResponseEntity<Void> deleteitem(@PathVariable("index") Integer index)
    {
        items.remove((int) index);
        return ResponseEntity.noContent().build();
    }
    @PutMapping("/topic/update/{index}")
    public ResponseEntity<Void> updateitem(@PathVariable("index") Integer index, @RequestBody String json_user) throws JsonProcessingException
    {
        Topic topic = new Topic(); topic.convert_from_json(json_user); items.set(index, topic);
        return ResponseEntity.accepted().build();
    }
    @GetMapping("topic/count")
    public ResponseEntity<String> getItemscount() { return ResponseEntity.ok(Integer.toString(items.size())); }
    @DeleteMapping("topic/all")
    public ResponseEntity<Void> deleteAllItems()
    {
        items = new ArrayList<>();
        return ResponseEntity.noContent().build();
    }
    @GetMapping("/topic/show/{index}")
    public ResponseEntity<String> getItemByIndex(@PathVariable("index") Integer index) throws JsonProcessingException
    {
        String  value = items.get(index).convert_to_json();
        return ResponseEntity.ok(value);
    }
}