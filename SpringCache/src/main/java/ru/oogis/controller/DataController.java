package ru.oogis.controller;

import org.springframework.web.bind.annotation.*;
import ru.oogis.data.MyData;

import java.util.Map;

@RestController
public class DataController {

    private final MyData myData;


    public DataController(MyData myData) {
        this.myData = myData;
    }

    @GetMapping("/all")
    public Map<Long, String> getAll() {
        return myData.getAll();
    }

    @GetMapping("/{id}")
    public String getName(@PathVariable Long id) {
        return myData.getName(id);
    }

    @PutMapping("/up/{id}/{name}")
    public void update(@PathVariable Long id, @PathVariable String name) {
        myData.update(id, name);
    }

    @DeleteMapping("/dell/{id}")
    public void delete(@PathVariable Long id) {
        myData.delete(id);
    }

}
