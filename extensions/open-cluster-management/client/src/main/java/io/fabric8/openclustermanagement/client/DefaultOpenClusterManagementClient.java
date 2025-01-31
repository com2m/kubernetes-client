/**
 * Copyright (C) 2015 Red Hat, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.fabric8.openclustermanagement.client;

import io.fabric8.kubernetes.client.BaseClient;
import io.fabric8.kubernetes.client.ClientContext;
import io.fabric8.kubernetes.client.Config;
import io.fabric8.kubernetes.client.ConfigBuilder;
import io.fabric8.kubernetes.client.RequestConfig;
import io.fabric8.kubernetes.client.WithRequestCallable;
import io.fabric8.kubernetes.client.dsl.FunctionCallable;
import io.fabric8.openclustermanagement.client.dsl.OpenClusterManagementAgentAPIGroupDSL;
import io.fabric8.openclustermanagement.client.dsl.OpenClusterManagementAppsAPIGroupDSL;
import io.fabric8.openclustermanagement.client.dsl.OpenClusterManagementClustersAPIGroupDSL;
import io.fabric8.openclustermanagement.client.dsl.OpenClusterManagementDiscoveryAPIGroupDSL;
import io.fabric8.openclustermanagement.client.dsl.OpenClusterManagementOperatorAPIGroupDSL;
import io.fabric8.openclustermanagement.client.dsl.OpenClusterManagementPolicyAPIGroupDSL;
import io.fabric8.openclustermanagement.client.dsl.OpenClusterManagementSearchAPIGroupDSL;

public class DefaultOpenClusterManagementClient extends BaseClient implements NamespacedOpenClusterManagementClient {
  public DefaultOpenClusterManagementClient() {
    super();
  }

  public DefaultOpenClusterManagementClient(Config configuration) {
    super(configuration);
  }

  public DefaultOpenClusterManagementClient(ClientContext clientContext) {
    super(clientContext);
  }

  @Override
  public NamespacedOpenClusterManagementClient inAnyNamespace() {
    return inNamespace(null);
  }

  @Override
  public NamespacedOpenClusterManagementClient inNamespace(String namespace) {
    Config updated = new ConfigBuilder(getConfiguration())
      .withNamespace(namespace)
      .build();

    return new DefaultOpenClusterManagementClient(newState(updated));
  }

  @Override
  public FunctionCallable<NamespacedOpenClusterManagementClient> withRequestConfig(RequestConfig requestConfig) {
    return new WithRequestCallable<>(this, requestConfig);
  }

  @Override
  public OpenClusterManagementAppsAPIGroupDSL apps() {
    return adapt(OpenClusterManagementAppsAPIGroupClient.class);
  }

  @Override
  public OpenClusterManagementAgentAPIGroupDSL agents() {
    return adapt(OpenClusterManagementAgentAPIGroupClient.class);
  }

  @Override
  public OpenClusterManagementClustersAPIGroupDSL clusters() {
    return adapt(OpenClusterManagementClustersAPIGroupClient.class);
  }

  @Override
  public OpenClusterManagementDiscoveryAPIGroupDSL discovery() {
    return adapt(OpenClusterManagementDiscoveryAPIGroupClient.class);
  }

  @Override
  public OpenClusterManagementObservabilityAPIGroupClient observability() {
    return adapt(OpenClusterManagementObservabilityAPIGroupClient.class);
  }

  @Override
  public OpenClusterManagementOperatorAPIGroupDSL operator() {
    return adapt(OpenClusterManagementOperatorAPIGroupClient.class);
  }

  @Override
  public OpenClusterManagementPolicyAPIGroupDSL policy() {
    return adapt(OpenClusterManagementPolicyAPIGroupClient.class);
  }

  @Override
  public OpenClusterManagementSearchAPIGroupDSL search() {
    return adapt(OpenClusterManagementSearchAPIGroupClient.class);
  }
}
