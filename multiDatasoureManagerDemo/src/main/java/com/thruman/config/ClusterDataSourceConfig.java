package com.thruman.config;

import com.atomikos.jdbc.AtomikosDataSourceBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


@Configuration
// 扫描 Mapper 接口并容器管理
@MapperScan(basePackages = ClusterDataSourceConfig.PACKAGE, sqlSessionFactoryRef = "clusterSqlSessionFactory")
public class ClusterDataSourceConfig {

    // dao目录 精确到 cluster 目录，以便跟其他数据源隔离
    static final String PACKAGE = "com.thruman.dao.cluster";
    // mapper目录 精确到 cluster 目录，以便跟其他数据源隔离
    static final String MAPPER_LOCATION = "classpath:mapper/cluster/*.xml";

    @Bean(name = "clusterDataSource")
    @ConfigurationProperties(prefix = "spring.jta.atomikos.datasource.clusterDataSource")
    public DataSource clusterDataSource() {
        return new AtomikosDataSourceBean();
    }


    @Bean(name = "clusterSqlSessionFactory")
    public SqlSessionFactory clusterSqlSessionFactory(@Qualifier("clusterDataSource") DataSource clusterDataSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(clusterDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(ClusterDataSourceConfig.MAPPER_LOCATION));
        return sessionFactory.getObject();
    }

    @Bean(name = "clusterSqlSessionTemplate")
    public SqlSessionTemplate testSqlSessionTemplate(
            @Qualifier("clusterSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}