package com.lyy.stock.common.mongodb.config;

import com.lyy.stock.common.mongodb.properties.MongoClientOptionProperties;
import com.mongodb.*;
import com.mongodb.client.MongoClient;
import com.mongodb.client.internal.MongoClientImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoClientDatabaseFactory;
import org.springframework.data.mongodb.core.convert.*;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author:
 * @createTime: 2023/04/18 10:30:50
 * @version:
 * @Description:
 */
@Configuration
// 将带有@ConfigurationProperties注解的类注入为Spring容器的Bean，
// 任何被@ConfigurationProperties注解的beans将自动被Environment属性配置。
@EnableConfigurationProperties(MongoClientOptionProperties.class)
public class MongoConfig {



    /**
     * 转换器
     * MappingMongoConverter可以自定义mongo转换器，主要自定义存取mongo数据时的一些操作，例如 mappingConverter.setTypeMapper(new
     * DefaultMongoTypeMapper(null)) 方法会将mongo数据中的_class字段去掉。
     *
     * @param factory     mongo工厂
     * @param context     上下文
     * @param beanFactory 自定义转换器
     * @return 转换器对象
     */
    @Bean
    public MappingMongoConverter mappingMongoConverter(MongoDatabaseFactory factory, MongoMappingContext context, BeanFactory beanFactory) {
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(factory);
        MappingMongoConverter mappingConverter = new MappingMongoConverter(dbRefResolver, context);
        try {
            mappingConverter.setCustomConversions(beanFactory.getBean(CustomConversions.class));
        } catch (NoSuchBeanDefinitionException ignore) {

        }

        // 保存不需要 _class to 字段 mongo
        mappingConverter.setTypeMapper(new DefaultMongoTypeMapper(null));

        return mappingConverter;
    }

    @Bean
    public MongoTypeMapper defaultMongoTypeMapper() {
        return new DefaultMongoTypeMapper(null);
    }

    /**
     * 此Bean也是可以不显示定义的，如果我们没有显示定义生成MongoTemplate实例，
     * SpringBoot利用我们配置好的MongoDbFactory在配置类中生成一个MongoTemplate，
     * 之后我们就可以在项目代码中直接@Autowired了。因为用于生成MongoTemplate
     * 的MongoDbFactory是我们自己在MongoConfig配置类中生成的，所以我们自定义的连接池参数也就生效了。
     *
     * @param mongoDbFactory mongo工厂
     * @param converter      转换器
     * @return MongoTemplate实例
     */
    @Bean
    public MongoTemplate mongoTemplate(MongoDatabaseFactory mongoDbFactory, MappingMongoConverter converter) {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory, converter);
        // 设置读从库优先
        mongoTemplate.setReadPreference(ReadPreference.secondaryPreferred());
        return mongoTemplate;
    }

    /**
     * 自定义mongo连接池
     *
     * @param properties 属性配置类
     * @return MongoDbFactory对象
     */
    @Bean
    public MongoDatabaseFactory mongoDbFactory(MongoClientOptionProperties properties) {
        // 创建客户端参数
        MongoClientSettings mongoClientOptions = mongoClientSettings(properties);

        // Mongo Client
        MongoDriverInformation info = MongoDriverInformation.builder().build();

        MongoClient mongoClient = new MongoClientImpl(mongoClientOptions, info);

        return new SimpleMongoClientDatabaseFactory(mongoClient, properties.database);
    }

    @Bean
    public MongoClientSettings mongoClientSettings(MongoClientOptionProperties properties) {
        // 创建认证
        MongoCredential mongoCredential = getCredential(properties);
        // 解析获取mongo服务地址
        List<ServerAddress> serverAddressList = getServerAddress(properties.getAddress());

        return MongoClientSettings.builder()
                //#客户端的标识，用于定位请求来源等，一般用程序名
                .applicationName(properties.clientName)
                //配置IP地址
                .applyToClusterSettings(i -> i.hosts(serverAddressList))
                //配置认证
                .credential(mongoCredential)

                .applyToSocketSettings(i -> i
                        //TCP（socket）读取超时时间，毫秒
                        .readTimeout(properties.readTimeoutMs, TimeUnit.MILLISECONDS)
                        //TCP（socket）连接超时时间，毫秒
                        .connectTimeout(properties.connectionTimeoutMs, TimeUnit.MILLISECONDS))

                .applyToConnectionPoolSettings(i -> i
                        //TCP（socket）连接闲置时间，毫秒
                        .maxConnectionIdleTime(properties.maxConnectionIdleTimeMs, TimeUnit.MILLISECONDS)
                        //TCP（socket）连接最多可以使用多久，毫秒
                        .maxConnectionLifeTime(properties.maxConnectionIdleTimeMs, TimeUnit.MILLISECONDS)
                        //当连接池无可用连接时客户端阻塞等待的最大时长，毫秒
                        .maxWaitTime(properties.maxWaitTimeMs, TimeUnit.MILLISECONDS)
                        .maxSize(properties.connectionsPerHost)
                        .minSize(properties.minConnectionsPerHost))

                .applyToServerSettings(i -> i
                        .heartbeatFrequency(properties.heartbeatFrequencyMs, TimeUnit.MILLISECONDS)
                        .minHeartbeatFrequency(properties.minHeartbeatFrequencyMs, TimeUnit.MILLISECONDS))


                .build();
    }

    /**
     * 创建认证
     *
     * @param properties 属性配置类
     * @return 认证对象
     */
    private MongoCredential getCredential(MongoClientOptionProperties properties) {
        if (!StringUtils.isEmpty(properties.getUsername()) && !StringUtils.isEmpty(properties.getPassword())) {
            // 没有专用认证数据库则取当前数据库
            String database = StringUtils.isEmpty(properties.getAuthenticationDatabase()) ?
                    properties.getDatabase() : properties.getAuthenticationDatabase();
            return MongoCredential.createCredential(properties.getUsername(), database,
                    properties.getPassword().toCharArray());
        }
        return null;
    }

    /**
     * 获取数据库服务地址
     *
     * @param mongoAddress 地址字符串
     * @return 服务地址数组
     */
    private List<ServerAddress> getServerAddress(String mongoAddress) {
        String[] mongoAddressArray = mongoAddress.trim().split(",");
        List<ServerAddress> serverAddressList = new ArrayList<>(4);
        for (String address : mongoAddressArray) {
            String[] hostAndPort = address.split(":");
            serverAddressList.add(new ServerAddress(hostAndPort[0], Integer.parseInt(hostAndPort[1])));
        }
        return serverAddressList;
    }

}
