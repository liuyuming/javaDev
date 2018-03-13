package com.thruman.java.common.util.redisUtil;


import redis.clients.jedis.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;


public class RedisUtils {

    private ShardedJedisPool shardedJedisPool;

    public <T> T excute(Function<ShardedJedis, T> fun) {
        ShardedJedis shardedJedis = null;
        try {
            // 从连接池中获取到jedis分片对象
            shardedJedis = shardedJedisPool.getResource();

            return fun.callback(shardedJedis);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != shardedJedis) {
                shardedJedis.close();
            }
        }
        return null;
    }

    public String set( String key,  String value) {
        return excute(new Function<ShardedJedis, String>() {
            public String callback(ShardedJedis e) {
                return e.set(key, value);
            }
        });
    }

    public String get(String key) {
        return excute(new Function<ShardedJedis, String>() {
            public String callback(ShardedJedis e) {
                return e.get(key);
            }
        });
    }

    public Long del(String key) {
        return excute(new Function<ShardedJedis, Long>() {
            public Long callback(ShardedJedis e) {
                return e.del(key);
            }
        });
    }

    public Long expire( String key,  Integer seconds) {
        return excute(new Function<ShardedJedis, Long>() {
            public Long callback(ShardedJedis e) {
                return e.expire(key, seconds);
            }
        });
    }

    public Long expire( String key,  String value,  Integer seconds) {
        return excute(new Function<ShardedJedis, Long>() {
            public Long callback(ShardedJedis e) {
                e.set(key, value);
                return e.expire(key, seconds);
            }
        });
    }
    public Boolean setNX(String key, String value) {
        return excute(new Function<ShardedJedis, Boolean>() {
            public Boolean callback(ShardedJedis e) {
                e.set(key, value);
                return e.setnx(key, value) == 1 ? true : false;
            }
        });
    }


    public String getSet(String key, String expiresStr) {
        return excute(new Function<ShardedJedis, String>() {
            public String callback(ShardedJedis e) {
                return e.getSet(key, expiresStr);
            }
        });
    }

    public <T> T getObject(String key,Class<T> clazz){
        return excute((e)->{return (T)e.get(key);});
    }


    public static byte[] getBytes(Object obj){
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try{
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(obj);
            return  baos.toByteArray();
        }catch (Exception e) {
            if(baos != null) {
                try {
                    baos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if(oos != null) {
                try {
                    oos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return null;
    }


    public ShardedJedisPool getShardedJedisPool() {
        return shardedJedisPool;
    }

    public void setShardedJedisPool(ShardedJedisPool shardedJedisPool) {
        this.shardedJedisPool = shardedJedisPool;
    }

    public static void main(String[] args) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        JedisShardInfo jedisShardInfo = new JedisShardInfo("13.58.115.131",6389);
        List<JedisShardInfo> jedisShardInfos = new ArrayList<>();
        jedisShardInfos.add(jedisShardInfo);
        ShardedJedisPool shardedJedisPool = new ShardedJedisPool(jedisPoolConfig, jedisShardInfos);

        RedisUtils redisUtils = new RedisUtils();
        redisUtils.setShardedJedisPool(shardedJedisPool);
        redisUtils.set("123","123");
        System.out.println(redisUtils.get("123"));
    }

}
