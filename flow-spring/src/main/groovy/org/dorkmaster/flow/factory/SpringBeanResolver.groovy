package org.dorkmaster.flow.factory

import org.dorkmaster.flow.Decider
import org.dorkmaster.flow.Flow
import org.dorkmaster.flow.Task
import org.dorkmaster.flow.exception.UndefinedBeanException
import org.springframework.beans.factory.BeanFactory

class SpringBeanResolver extends SimpleResolver implements Resolver {
    BeanFactory beanFactory

    @Override
    Resolver add(Type type, String name, String clazz) {
        if (!beanFactory.containsBean(name)) {
            try {
                super.add(type, name, clazz)
            } catch (ClassNotFoundException e) {
                throw new UndefinedBeanException("'${name}' is not defined")
            }
        }
    }

    @Override
    Object create(Type type, String name) {
        if (beanFactory.containsBean(name)) {
            Object bean = beanFactory.getBean(name)
            if (bean instanceof Flow || bean instanceof Decider || bean instanceof Task) {
                return bean
            }
        }
        super.create(type, name)
    }
}
