package com.originalflipster.sqlpolice;

import javax.sql.DataSource;
import net.ttddyy.dsproxy.listener.logging.DefaultQueryLogEntryCreator;
import net.ttddyy.dsproxy.listener.logging.SLF4JQueryLoggingListener;
import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Profile;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class DataSourceProxyPostProcessor implements BeanPostProcessor {

  @Override
  public @NonNull Object postProcessAfterInitialization(final @NonNull Object bean, final @NonNull String beanName) throws BeansException {
    if (bean instanceof DataSource datasource) {
      SLF4JQueryLoggingListener loggingListener = new SLF4JQueryLoggingListener();
      loggingListener.setQueryLogEntryCreator(new DefaultQueryLogEntryCreator());
      return ProxyDataSourceBuilder
          .create(datasource)
          .name("SQL_POLICE")
          .listener(loggingListener)
          .countQuery()
          .build();
    }
    return bean;
  }
}