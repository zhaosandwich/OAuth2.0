package com.resource.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class JedisUtil {

    protected static final Log logger = LogFactory.getLog(JedisUtil.class);

    public static void setValue(String key,String value)throws Exception{
        JedisPool publicJedisPool = null;
        Jedis publicJedis = null;
        try {
            publicJedisPool = DataBase.getJedisPublicPool();
            publicJedis = publicJedisPool.getResource();
            publicJedis.setex(key, 60 * Integer.parseInt(Config.getInstance().get("redis_iface_expx")), value);
        } catch (Exception e) {
            throw new Exception("设置redis失败: key:" + key + ",value:" + value,e);
        } finally {
            if (publicJedis != null) {
                publicJedisPool.returnResource(publicJedis);
                publicJedis = null;
            }
        }
    }

    public static String getValue(String key)throws Exception{
        JedisPool publicJedisPool = null;
        Jedis publicJedis = null;
        try {
            publicJedisPool = DataBase.getJedisPublicPool();
            publicJedis = publicJedisPool.getResource();
            return publicJedis.get(key);
        } catch (Exception e) {
            throw new Exception("获取reids失败，key:"+key,e);
        } finally {
            if (publicJedis != null) {
                publicJedisPool.returnResource(publicJedis);
                publicJedis = null;
            }
        }
    }

    public static void delValue(String key)throws Exception{
        JedisPool publicJedisPool = null;
        Jedis publicJedis = null;
        try {
            publicJedisPool = DataBase.getJedisPublicPool();
            publicJedis = publicJedisPool.getResource();
            publicJedis.del(key);
        } catch (Exception e) {
            throw new Exception("删除redis失败: key:" + key ,e);
        } finally {
            if (publicJedis != null) {
                publicJedisPool.returnResource(publicJedis);
                publicJedis = null;
            }
        }
    }
}
