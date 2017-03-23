package tech.rsqn.servers.auditserver.services;

import org.productivity.java.syslog4j.server.SyslogServerEventHandlerIF;
import org.productivity.java.syslog4j.server.SyslogServerEventIF;
import org.productivity.java.syslog4j.server.SyslogServerIF;
import org.productivity.java.syslog4j.util.SyslogUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Slf4jSysLogEventHandler implements SyslogServerEventHandlerIF {
    private static final Logger log = LoggerFactory.getLogger(Slf4jSysLogEventHandler.class);

    public void event(SyslogServerIF syslogServer, SyslogServerEventIF event) {
        String date = (event.getDate() == null ? new Date() : event.getDate()).toString();
        String facility = SyslogUtility.getFacilityString(event.getFacility());
        String level = SyslogUtility.getLevelString(event.getLevel());

        String s = ("{" + facility + "} " + date + " " + level + " " + event.getMessage());

        System.out.println("##### " + s);
        log.info(s);
    }
}
