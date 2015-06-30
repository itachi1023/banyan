package com.messagebus.client.api;

import com.google.common.eventbus.Subscribe;
import com.messagebus.client.MessagebusSinglePool;
import com.messagebus.client.core.BaseTestCase;
import com.messagebus.client.event.component.ClientDestroyEvent;
import com.messagebus.client.event.component.ClientInitedEvent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * Created by yanghua on 6/25/15.
 */
public class ComponentEventListener extends BaseTestCase {

    private static final Log logger = LogFactory.getLog(ComponentEventListener.class);

    @Override
    public void setUp() throws Exception {

    }

    @Override
    public void tearDown() throws Exception {

    }

    @Test
    public void testComponentEvent() throws Exception {
        singlePool = new MessagebusSinglePool(host, port);
        singlePool.registerComponentEventListener(new EventListener());
        client = singlePool.getResource();

        singlePool.returnResource(client);
        singlePool.destroy();
    }

    public class EventListener {

        @Subscribe
        public void onClientInited(ClientInitedEvent event) {
            logger.debug(" outer of component event : onClientInited");
        }

        @Subscribe
        public void onClientDestroy(ClientDestroyEvent event) {
            logger.debug(" outer of component event : onClientDestroy");
        }

    }
}
