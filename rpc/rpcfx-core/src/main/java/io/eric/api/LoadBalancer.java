package io.eric.api;

import java.util.List;

public interface LoadBalancer {

    String select(List<String> urls);

}
