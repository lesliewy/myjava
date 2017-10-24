package examples.drools.assetrule.data;

import com.alibaba.dubbo.common.utils.ConcurrentHashSet;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * Created by leslie on 2017/10/18.
 */
public class CallStrategyDroolsOutput {

    private String                projectId;

    private Integer               hitRuleSeq;

    private ConcurrentSkipListSet hitRuleSet;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public Integer getHitRuleSeq() {
        return hitRuleSeq;
    }

    public void setHitRuleSeq(Integer hitRuleSeq) {
        this.hitRuleSeq = hitRuleSeq;
    }

    public ConcurrentSkipListSet getHitRuleSet() {
        return hitRuleSet;
    }

    public void setHitRuleSet(ConcurrentSkipListSet hitRuleSet) {
        this.hitRuleSet = hitRuleSet;
    }
}
