package org.openstack4j.api.container;

import java.util.List;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.api.Builders;
import org.openstack4j.model.common.ActionResponse;
import org.openstack4j.model.container.*;
import org.openstack4j.openstack.container.domain.ZunContainerUpdate;
import org.openstack4j.openstack.container.domain.ZunExecParameters;
import org.openstack4j.openstack.container.domain.ZunLogsParameters;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author xx
 * @date 2025-04-25
 */
@Test(suiteName = "Container/containerv1", enabled = true)
public class ZunContainerV1Tests extends AbstractTest {

    @Override
    protected Service service() {
        return Service.CONTAINER;
    }

    @Test
    public void testListContainers() {
        respondWith(200, "{\"containers\": []}");
        List<? extends Container> list = osv3().containers().list();
        assertEquals(list.size(), 1);
    }

    @Test
    public void testGetContainer() {
        Container container = osv3().containers().get("1");
        assertEquals(container.getName(), "test");
    }

    @Test
    public void testCreateContainer() {
        ContainerCreate container = Builders.zunContainer()
                .name("test")
                .interactive(true)
//                .addNet("2eda3028-8c71-40af-b634-226c912b7a72")
                .image("docker.io/nginx:latest")
                .build();
        Container ct = osv3().containers().create(container);
        assertEquals(ct.getName(), "test");
    }


    @Test
    public void testDeleteContainer() {
        ActionResponse resp = osv3().containers().delete("c860dc7c-70db-4f45-b865-a2eb3838c88c", false, true);
        System.out.println(resp.toString());
        assertTrue(resp.isSuccess());
    }


    @Test
    public void testStartContainer() {
        ActionResponse resp = osv3().containers().start("test");
        assertTrue(resp.isSuccess());
    }

    @Test
    public void testStopContainer() {
        ActionResponse resp = osv3().containers().stop("test", null);
        assertTrue(resp.isSuccess());
    }

    @Test
    public void testRestartContainer() {
        ActionResponse resp = osv3().containers().restart("test", null);
        assertTrue(resp.isSuccess());
    }

    @Test
    public void testPauseContainer() {
        ActionResponse resp = osv3().containers().pause("test");
        assertTrue(resp.isSuccess());
    }

    @Test
    public void testUnPauseContainer() {
        ActionResponse resp = osv3().containers().unpause("test");
        assertTrue(resp.isSuccess());
    }

    @Test
    public void testContainerExecute() {
        ExecParameters params = ZunExecParameters.builder().command("pwd").interactive(true).build();
        ExecResponse resp = osv3().containers().execute("test", params);
        System.out.printf((resp.getAttachToken()) + "\n", resp.getUrl());
        assertNotNull(resp.getUrl());
    }


    @Test
    public void testLogsContainer() {
        LogsParameters logsParams = ZunLogsParameters.builder().tail(200).build();
        String logs = osv3().containers().logs("docker-php", logsParams);
        System.out.println(logs);
    }

    @Test
    public void testRenameContainer() {
        Container ct = osv3().containers().rename("test", "test2");
        assertEquals(ct.getName(), "test2");

    }

    @Test
    public void testUpdateContainer() {
        ContainerUpdate toBeUpdate = ZunContainerUpdate.builder().memory(1024).cpu(2.0f).build();
        Container ct = osv3().containers().update("test2", toBeUpdate);
        assertEquals(ct.getCpu(), 2.0f);
        assertEquals(ct.getMemory(), 1024);

    }


}
