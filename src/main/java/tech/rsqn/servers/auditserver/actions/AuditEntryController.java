package tech.rsqn.servers.auditserver.actions;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import tech.rsqn.servers.auditserver.model.AuditEntry;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Controller
public class AuditEntryController {

    private List<AuditEntry> entries = new ArrayList();

    @RequestMapping("/submit")
    @ResponseBody
    public String submit(HttpServletRequest req
            , @RequestParam(value = "msg", required = false, defaultValue = "") String message
            , @RequestParam(value = "lvl", required = false, defaultValue = "") String level
            , @RequestParam(value = "src", required = false, defaultValue = "") String src) throws Exception {

        AuditEntry entry = new AuditEntry();
        entry.setTs(new Date());
        entry.setSrc("DEFAULT");
        entry.setLvl("TRACE");
        entry.setMsg("No Message");


        if (StringUtils.isNotBlank(message)) {
            entry.setMsg(message);
        }

        if (StringUtils.isNotBlank(src)) {
            entry.setSrc(src);
        }

        if (StringUtils.isNotBlank(level)) {
            entry.setLvl(level);
        }

        entries.add(entry);
        Collections.sort(entries, new Comparator<AuditEntry>() {
            @Override
            public int compare(AuditEntry o1, AuditEntry o2) {
                return o1.getTs().compareTo(o2.getTs());
            }
        });

        return query();
    }

    @RequestMapping("/query")
    @ResponseBody
    public String query() {

        String ret = "<html>";

        for (AuditEntry entry : entries) {
            ret += entry.toString();
            ret += "<br/>";
        }

        ret += "<br/>";

        return ret;
    }
}
