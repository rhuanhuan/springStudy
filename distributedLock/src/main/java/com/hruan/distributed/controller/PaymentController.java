package com.hruan.distributed.controller;

import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class PaymentController {
    private String key = "inventory";
    private volatile String lockKey = "lock";

    @Resource
    RedissonClient redisson;

    @GetMapping(value = "/inventory")
    public Object getInventory() {
        Jedis jedis = new Jedis(new HostAndPort("localhost", 6379));
        String value = "Not Found";
        try {
            value = jedis.get(key);
        } catch (Exception e){
            log.error("inventory not found");
        }
        finally {
            jedis.close();
        }

        return value;
    }

    /**
     * totally no lock
     * @return
     */
    @GetMapping(value = "/reduce-no-lock")
    public Object reduceInventoryNoLock() {
        Jedis jedis = new Jedis(new HostAndPort("localhost", 6379));
        String value = "Not Found";
        try {
            System.out.println("**start to reduce inventory**");
            value = jedis.get(key);
            System.out.println("current inventory: " + value);

            Thread.sleep(10000);
            int num = Integer.parseInt(value);
            num--;
            value = String.valueOf(num);
            System.out.println("Finnal inventory: " + value);
            jedis.set(key, value);
        } catch (Exception e){
            log.error("inventory not found");
        }
        finally {
            jedis.close();
        }

        return value;
    }

    /**
     * java lock
     * @return
     */
    @GetMapping(value = "/reduce-sync")
    public Object reduceInventory() {
        Jedis jedis = new Jedis(new HostAndPort("localhost", 6379));
        String value = "Not Found";
        synchronized (this) {
            try {
                System.out.println("**start to reduce inventory**");
                value = jedis.get(key);
                System.out.println("current inventory: " + value);

                Thread.sleep(10000);
                int num = Integer.parseInt(value);
                num--;
                value = String.valueOf(num);
                System.out.println("Finnal inventory: " + value);
                jedis.set(key, value);
            } catch (Exception e){
                log.error("inventory not found");
            }
            finally {
                jedis.close();
            }

            return value;
        }
    }

    /**
     * redis hand write lock
     * @return
     */
    @GetMapping(value = "/reduce-dl")
    public Object reduceInventoryDL() {
        Jedis jedis = new Jedis(new HostAndPort("localhost", 6379));
        String value = "Not Found";
        while (jedis.setnx(lockKey, "anyValue") == 0) {
            System.out.println("get lock failed");
        }

        try {
            System.out.println("**start to reduce inventory**");
            value = jedis.get(key);
            System.out.println("current inventory: " + value);

            Thread.sleep(10000);
            int num = Integer.parseInt(value);
            num--;
            value = String.valueOf(num);
            System.out.println("Finnal inventory: " + value);
            jedis.set(key, value);
        } catch (Exception e){
            log.error("inventory not found");
        }
        finally {
            System.out.println("start delete key");
            jedis.del(lockKey);
            jedis.close();
        }

        return value;
    }

    /**
     * reddision lock
     * @return
     */
    @GetMapping(value = "/reduce-redission")
    public Object reduceInventoryRedission(@RequestParam("timeout") int timeout) throws InterruptedException {
        Jedis jedis = new Jedis(new HostAndPort("localhost", 6379));
        RLock lock = redisson.getLock("anyLock");
        String value = "Not Found";

//        boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
        boolean res = lock.tryLock(100, TimeUnit.SECONDS);

        if (res) {
            try {
                System.out.println("**start to reduce inventory**");
                value = jedis.get(key);
                System.out.println("current inventory: " + value);

                Thread.sleep(timeout);
                int num = Integer.parseInt(value);
                num--;
                value = String.valueOf(num);
                System.out.println("Finnal inventory: " + value);
                jedis.set(key, value);
            } catch (Exception e){
                log.error("inventory not found");
            }
            finally {
                jedis.close();
                lock.unlock();
            }
        }

        return value;
    }

}
