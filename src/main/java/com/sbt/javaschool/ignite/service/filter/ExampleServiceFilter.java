package com.sbt.javaschool.ignite.service.filter;

import org.apache.ignite.cluster.ClusterNode;
import org.apache.ignite.lang.IgnitePredicate;

public class ExampleServiceFilter implements IgnitePredicate<ClusterNode> {
    @Override public boolean apply(ClusterNode node) {
        return Boolean.parseBoolean(node.attribute("svc-node"));
    }
}
