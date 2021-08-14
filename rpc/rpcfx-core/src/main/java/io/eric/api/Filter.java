package io.eric.api;

public interface Filter {

    boolean filter(RpcfxRequest request);

    // Filter next();

}
