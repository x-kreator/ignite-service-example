package com.sbt.javaschool.ignite;

import com.sbt.javaschool.ignite.service.ExampleService;
import com.sbt.javaschool.ignite.service.impl.ExampleServiceImpl;
import java.util.Collections;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCompute;
import org.apache.ignite.IgniteServices;
import org.apache.ignite.Ignition;
import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.lang.IgnitePredicate;
import org.apache.ignite.lang.IgniteRunnable;
import org.apache.ignite.resources.IgniteInstanceResource;
import org.apache.ignite.resources.ServiceResource;
import org.apache.ignite.services.ServiceConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

public class IgniteApp {
    public static void main(String[] args) {
        Ignite ignite = Ignition.start(
            new IgniteConfiguration()
                .setUserAttributes(
                    Collections.singletonMap("svc-node", "true")
                )
                .setDiscoverySpi(new TcpDiscoverySpi()
                    .setIpFinder(new TcpDiscoveryVmIpFinder()
                        .setAddresses(Collections.singletonList("127.0.0.1"))))
                .setServiceConfiguration(
                    new ServiceConfiguration()
                        .setName("exampleSvc")
                        .setService(new ExampleServiceImpl())
                        .setTotalCount(0)
                        .setMaxPerNodeCount(1)
                        .setNodeFilter((IgnitePredicate<ClusterNode>)node
                            -> Boolean.parseBoolean(node.attribute("svc-node"))))
        );

        IgniteCompute compute = ignite.compute();

        compute.run(new IgniteRunnable() {
            @ServiceResource(serviceName = "exampleSvc")
            private ExampleService exampleSvc;
            @IgniteInstanceResource
            private Ignite ignite;

            @Override public void run() {
                final String str = "qwerty";

                ignite.log().info(str + " -> " + exampleSvc.toUpperCase(str));
            }
        });

        IgniteServices services = ignite.services();


    }
}
