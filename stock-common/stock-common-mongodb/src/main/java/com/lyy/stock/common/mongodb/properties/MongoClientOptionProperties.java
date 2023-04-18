package com.lyy.stock.common.mongodb.properties;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.boot.context.properties.ConfigurationProperties;


/**
 * @Author:
 * @createTime: 2023/04/18 10:35:51
 * @version:
 * @Description:
 */
@Data
@Accessors(chain = true)
@ConfigurationProperties(prefix = "mongodb")
public class MongoClientOptionProperties {

    /**
     * 基础连接参数
     */
    // 要连接的数据库
    public String database;
    // 用户名
    public String username;
    // 密码
    public String password;
    // IP和端口（host:port），例如127.0.0.1:27017。集群模式用,分隔开，例如host1:port1,host2:port2
    //@NotEmpty
    public String address;
    // 设置认证数据库，如果有的话
    public String authenticationDatabase;

    /**
     * 客户端连接池参数
     */
    public String clientName; // 客户端的标识，用于定位请求来源等，一般用程序名
    public int connectionTimeoutMs; // TCP（socket）连接超时时间，毫秒
    public int maxConnectionIdleTimeMs; // TCP（socket）连接闲置时间，毫秒
    public int maxConnectionLifeTimeMs; // TCP（socket）连接最多可以使用多久，毫秒
    public int readTimeoutMs; // TCP（socket）读取超时时间，毫秒
    public int maxWaitTimeMs; // 当连接池无可用连接时客户端阻塞等待的最大时长，毫秒
    public int heartbeatFrequencyMs; // 心跳检测发送频率，毫秒
    public int minHeartbeatFrequencyMs; // 最小的心跳检测发送频率，毫秒
    public int heartbeatConnectionTimeoutMs; // 心跳检测连接超时时间，毫秒
    public int heartbeatReadTimeoutMs; // 心跳检测读取超时时间，毫秒
    public int connectionsPerHost; // 线程池允许的最大连接数
    public int minConnectionsPerHost; // 线程池空闲时保持的最小连接数
    // 计算允许多少个线程阻塞等待时的乘数，算法：threadsAllowedToBlockForConnectionMultiplier*maxConnectionsPerHost
    public int threadsAllowedToBlockForConnectionMultiplier;

}
