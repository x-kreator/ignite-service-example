package com.sbt.javaschool.ignite.service.impl;

import com.sbt.javaschool.ignite.service.ExampleService;
import org.apache.ignite.Ignite;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.services.Service;
import org.apache.ignite.services.ServiceContext;

public class ExampleServiceImpl implements ExampleService, Service {
    @IgniteInstanceResource
    private Ignite ignite;

    @Override public String toUpperCase(String str) {
        return str == null ? null : str.toUpperCase();
    }

    @Override public void init(ServiceContext ctx) throws Exception {
        ignite.log().info(
            "Service instance " + this
                + " initialized on node "
                + ignite.cluster().localNode());
    }

    @Override public void execute(ServiceContext ctx) throws Exception {
        ignite.log().info(
            "Service instance " + this
                + " executed on node "
                + ignite.cluster().localNode());
    }

    @Override public void cancel(ServiceContext ctx) {
        ignite.log().info(
            "Service instance " + this
                + " canceled on node "
                + ignite.cluster().localNode());
    }
}
