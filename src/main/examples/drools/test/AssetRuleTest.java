package examples.drools.test;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.junit.Test;

import examples.drools.assetrule.AssetRule;
import examples.drools.assetrule.data.RulesInput;
import examples.drools.assetrule.data.RulesOutput;

/**
 * Created by leslie on 2017/10/24.
 */
public class AssetRuleTest {

    @Test
    public void testExecute() {
        AssetRule assetRule = new AssetRule();
        RulesInput input = new RulesInput();
        RulesOutput output = assetRule.execute(input);
        System.out.println("output: " + ToStringBuilder.reflectionToString(output));
    }
}
