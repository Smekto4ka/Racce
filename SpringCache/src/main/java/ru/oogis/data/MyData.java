package ru.oogis.data;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class MyData {
    private Map<Long, String> users = new HashMap<>();

    public MyData() {
        users.put(1L, "1");
        users.put(2L, "2");
        users.put(3L, "3");
        users.put(4L, "4");
        users.put(5L, "5");
    }

    @CachePut(cacheNames = "myCach", key = "#id")
    public String update(Long id, String name) {
        System.out.println("update = " + id + " //// " + name);
        users.put(id, name);
        return name;
    }
/*
    @Caching(
            cacheable = {
                    @Cacheable("users"),
                    @Cacheable("contacts")
            },
            put = {
                    @CachePut("tables"),
                    @CachePut("chairs"),
                    @CachePut(value = "meals", key = "#user.email")
            },
            evict = {
                    @CacheEvict(value = "services", key = "#user.name")
            }
    )
 */
    @CacheEvict(cacheNames = "myCach")
    public void delete(Long id) {
        System.out.println("delete , id = " + id);
        users.remove(id);
    }

    public Map<Long, String> getAll() {
        System.out.println("getAll");
        return users;
    }

    @Cacheable(cacheNames = "myCach")
    public String getName(Long id) {
        System.out.println("get Name  ; id = " + id);
        return users.get(id);
    }

}
