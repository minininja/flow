package org.dorkmaster.flow.webhook.api;

import org.dorkmaster.flow.factory.FlowFactory;
import org.dorkmaster.flow.impl.Flow;
import org.dorkmaster.flow.impl.FlowContext;
import org.dorkmaster.flow.webhook.model.FlowRequest;
import org.dorkmaster.flow.webhook.model.FlowResponse;
import org.dorkmaster.flow.webhook.model.HookContext;

import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.WebApplicationException;

@Path("/v1/flow")
public class FlowResource {
    @Inject
    protected FlowFactory factory;

    @POST
    @Path("/{flowName}")
    public FlowResponse executeFlow(@PathParam("flowName") String flowName, FlowRequest request) {

        Flow flow = factory.constructFlow(flowName);
        if (null == flow) {
            throw new NotFoundException();
        }

        HookContext context = toContext(request);
        FlowContext fc = flow.execute(context);

        if (fc instanceof HookContext) {
            return toResponse((HookContext) fc);
        } else {
            throw new WebApplicationException("Invalid result from flow", 505);
        }
    }

    /**
     * translates a request (map) into a context object
     * @param request
     * @return
     */
    HookContext toContext(FlowRequest request) {
        return new HookContext().setContext(request);
    }

    /**
     * translates a hook context into a flow response
     * @param context
     * @return
     */
    FlowResponse toResponse(HookContext context) {
        FlowResponse fr = new FlowResponse();
        fr.putAll(context.getContext());
        return fr;
    }
}
