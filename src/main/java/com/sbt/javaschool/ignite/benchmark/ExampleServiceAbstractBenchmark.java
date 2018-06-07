package com.sbt.javaschool.ignite.benchmark;

import com.sbt.javaschool.ignite.service.ExampleService;
import org.apache.ignite.Ignite;
import org.apache.ignite.yardstick.IgniteAbstractBenchmark;
import org.yardstickframework.BenchmarkConfiguration;

public abstract class ExampleServiceAbstractBenchmark extends IgniteAbstractBenchmark {

    private ExampleService svc;

    /** {@inheritDoc} */
    @Override public void setUp(BenchmarkConfiguration cfg) throws Exception {
        super.setUp(cfg);

        svc = ignite().services().serviceProxy(
            "exampleSvc",
            ExampleService.class,
            false);
    }

    protected ExampleService service() {
        return svc;
    }
}
