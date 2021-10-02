package org.dorkmaster.flow.test;

import org.dorkmaster.flow.Flow;
import org.dorkmaster.flow.FlowContext;
import org.dorkmaster.flow.exception.DuplicateClassException;
import org.dorkmaster.flow.factory.SpringFlowFactory;
import org.dorkmaster.flow.test.factory.SpringMarkerTask;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations="classpath:test-application-context.xml")
public class SpringResolverTests implements InitializingBean {

    @Autowired
    SpringFlowFactory springFlowFactory;

    @Override
    public void afterPropertiesSet() throws Exception {
        try {
            springFlowFactory.load("springTestFactory");
        } catch(DuplicateClassException e) {
            // eat it, this is a work around
        }
    }

    @Test
    public void testFlow() {
        Flow flow = springFlowFactory.constructFlow("springFlow");
        FlowContext fc = flow.execute(new FlowContext());
        Assert.assertNotNull(fc);
        Assert.assertEquals(SpringMarkerTask.MARKER, fc.get(SpringMarkerTask.MARKER));
    }

    @Test
    public void testFlowWithNonSpring() {
        Flow flow = springFlowFactory.constructFlow("springFlow2");
        FlowContext fc = flow.execute(new FlowContext());
        Assert.assertNotNull(fc);
        Assert.assertEquals(SpringMarkerTask.MARKER, fc.get(SpringMarkerTask.MARKER));
    }
}
