package org.openstack4j.model.container;

import org.openstack4j.model.ModelEntity;

/**
 * @author xx
 * @date 2025-04-10
 */
public interface ExecResponse extends ModelEntity {
    /**
     * @return The WebSocket URL for the interactive session. 交互式会话的 WebSocket URL。
     */
    String getUrl();

    String getOutput();

    String getExitCode();

    String getExecId();
}
