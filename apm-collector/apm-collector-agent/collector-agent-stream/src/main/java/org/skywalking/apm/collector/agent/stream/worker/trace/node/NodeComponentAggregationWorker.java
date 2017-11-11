/*
 * Copyright 2017, OpenSkywalking Organization All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * Project repository: https://github.com/OpenSkywalking/skywalking
 */

package org.skywalking.apm.collector.agent.stream.worker.trace.node;

import org.skywalking.apm.collector.cache.CacheServiceManager;
import org.skywalking.apm.collector.queue.service.QueueCreatorService;
import org.skywalking.apm.collector.storage.service.DAOService;
import org.skywalking.apm.collector.storage.table.node.NodeComponent;
import org.skywalking.apm.collector.stream.worker.base.AbstractLocalAsyncWorkerProvider;
import org.skywalking.apm.collector.stream.worker.impl.AggregationWorker;

/**
 * @author peng-yongsheng
 */
public class NodeComponentAggregationWorker extends AggregationWorker<NodeComponent, NodeComponent> {

    public NodeComponentAggregationWorker(DAOService daoService, CacheServiceManager cacheServiceManager) {
        super(daoService, cacheServiceManager);
    }

    @Override public int id() {
        return 0;
    }

    public static class Factory extends AbstractLocalAsyncWorkerProvider<NodeComponent, NodeComponent, NodeComponentAggregationWorker> {
        public Factory(DAOService daoService, CacheServiceManager cacheServiceManager,
            QueueCreatorService<NodeComponent> queueCreatorService) {
            super(daoService, cacheServiceManager, queueCreatorService);
        }

        @Override public NodeComponentAggregationWorker workerInstance(DAOService daoService,
            CacheServiceManager cacheServiceManager) {
            return new NodeComponentAggregationWorker(getDaoService(), getCacheServiceManager());
        }

        @Override
        public int queueSize() {
            return 1024;
        }
    }
}
