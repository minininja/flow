package org.dorkmaster.flow.webhook;

import org.dorkmaster.flow.common.task.MarkerTask;
import org.dorkmaster.flow.factory.FlowFactory;
import org.dorkmaster.flow.factory.StandAloneFactory;
import org.dorkmaster.flow.webhook.api.FlowResource;
import org.dorkmaster.flow.webhook.model.FlowRequest;
import org.dorkmaster.flow.webhook.model.FlowResponse;
import org.junit.Assert;
import org.junit.Test;

public class HookTests {

    class TestFlowResource extends FlowResource {
        public  FlowResource setFlowFactory(FlowFactory factory) {
            super.factory = factory;
            return this;
        }
    }

    @Test
    public void happyPath() {
        FlowFactory factory = new StandAloneFactory().load("hookTestFactory");
        FlowResource fr = new TestFlowResource().setFlowFactory(factory);

        FlowResponse response = fr.executeFlow("trivial", new FlowRequest());
        Assert.assertNotNull(response.get(MarkerTask.MARKER));
    }
}
