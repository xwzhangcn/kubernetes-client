/**
 * Copyright (C) 2018 Red Hat inc.
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 **/
package me.snowdrop.servicecatalog.api.examples;

import me.snowdrop.servicecatalog.api.client.ServiceCatalogClient;
import me.snowdrop.servicecatalog.api.model.ClusterServicePlanList;


public class ListServicePlansByBroker {

    public static void main(String[] args) {
        ServiceCatalogClient client = ClientFactory.newClient(args);
        String broker = ClientFactory.getOptions(args, "--broker", null);
        if (broker == null || broker.isEmpty()) {
            System.out.println("Missing --broker option!");
            System.exit(1);
        }
        System.out.println("Listing Cluster Service Plans" + broker + ":");
        ClusterServicePlanList list = client.clusterServiceBrokers().withName(broker).listPlans();
        list.getItems().stream()
                .forEach(b -> {
                    System.out.println(b.getSpec().getClusterServiceClassRef()+ "\t\t\t" + b.getSpec().getExternalName() + "\t\t\t\t" + b.getMetadata().getName());
                });
        System.out.println("Done");
    }
}
