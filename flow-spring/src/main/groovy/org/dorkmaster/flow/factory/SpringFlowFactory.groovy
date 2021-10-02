package org.dorkmaster.flow.factory

import org.dorkmaster.flow.Flow
import org.springframework.beans.BeansException
import org.springframework.beans.factory.BeanFactory
import org.springframework.beans.factory.BeanFactoryAware
import org.springframework.beans.factory.InitializingBean
import org.springframework.stereotype.Service

@Service
class SpringFlowFactory extends Factory implements FlowFactory, BeanFactoryAware, InitializingBean {

    BeanFactory beanFactory

    @Override
    void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory
    }

    @Override
    void afterPropertiesSet() throws Exception {
        // we just need to override the resolver, the rest should work as is
        super.resolver =  new SpringBeanResolver(beanFactory: beanFactory)
    }
}
