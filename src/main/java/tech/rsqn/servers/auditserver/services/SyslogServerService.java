package tech.rsqn.servers.auditserver.services;


import org.apache.commons.lang3.StringUtils;
import org.productivity.java.syslog4j.server.SyslogServer;
import org.productivity.java.syslog4j.server.SyslogServerConfigIF;
import org.productivity.java.syslog4j.server.SyslogServerEventHandlerIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import tech.rsqn.servers.auditserver.model.AuditServerConfig;

public class SyslogServerService {
    private Logger log = LoggerFactory.getLogger(SyslogServerService.class);
    private AuditServerConfig config;

    @Required
    public void setConfig(AuditServerConfig config) {
        this.config = config;
    }

    private void err(String msg) {
        throw new RuntimeException(msg);
    }

    public void start() throws Exception {
        if (!SyslogServer.exists(config.getProtocol())) {
            err("Protocol " + config.getProtocol() + " not supported");
        }

        SyslogServerIF syslogServer = SyslogServer.getInstance(config.getProtocol());

        SyslogServerConfigIF syslogServerConfig = syslogServer.getConfig();

        if (StringUtils.isNotBlank(config.getHost())) {
            syslogServerConfig.setHost(config.getHost());
            log.info("Listening on host: " + config.getHost());
        }

        syslogServerConfig.setPort(config.getPort());
        log.info("Listening on port: " + config.getPort());

        SyslogServerEventHandlerIF eventHandler = new Slf4jSysLogEventHandler();
        syslogServerConfig.addEventHandler(eventHandler);

        SyslogServer.getThreadedInstance(config.getProtocol());
    }
}
