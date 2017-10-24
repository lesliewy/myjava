package examples.drools.assetrule;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.Charset;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.apache.velocity.context.Context;
import org.kie.api.io.ResourceType;
import org.kie.api.runtime.StatelessKieSession;
import org.kie.internal.KnowledgeBase;
import org.kie.internal.KnowledgeBaseFactory;
import org.kie.internal.builder.KnowledgeBuilder;
import org.kie.internal.builder.KnowledgeBuilderConfiguration;
import org.kie.internal.builder.KnowledgeBuilderError;
import org.kie.internal.builder.KnowledgeBuilderFactory;
import org.kie.internal.definition.KnowledgePackage;
import org.kie.internal.io.ResourceFactory;

import examples.drools.assetrule.data.CallStrategyDroolsInput;
import examples.drools.assetrule.data.CallStrategyDroolsOutput;
import examples.drools.assetrule.data.DroolsExecuteContext;
import examples.drools.assetrule.function.BoolFunctionImpl;
import examples.drools.assetrule.function.EnumFunctionImpl;
import examples.drools.assetrule.function.ValueFunctionImpl;
import examples.drools.assetrule.function.ValueRangeFunctionImpl;
import examples.drools.assetrule.rule.*;

/**
 * Created by leslie on 2017/10/18.
 */
public class AssetRule {

    public static final void main(final String[] args) {
        StatelessKieSession session = null;

        String buildCompiledText = buildCompiledTextCommon();
        System.out.println("buildCompiledText: " + buildCompiledText);

        /*
         * String buildCompiledTextBool = buildCompiledTextBool(); String buildCompiledTextValue =
         * buildCompiledTextValue(); String buildCompiledTextRange = buildCompiledTextRange(); String
         * buildCompiledTextEnum = buildCompiledTextEnum(); System.out.println("buildCompiledTextBool: " +
         * buildCompiledTextBool); System.out.println("buildCompiledTextValue: " + buildCompiledTextValue);
         * System.out.println("buildCompiledTextRange: " + buildCompiledTextRange);
         * System.out.println("buildCompiledTextEnum: " + buildCompiledTextEnum);
         */
        KnowledgeBuilderConfiguration config = KnowledgeBuilderFactory.newKnowledgeBuilderConfiguration();
        config.setProperty("drools.dialect.default", "mvel");
        config.setProperty("drools.dialect.mvel.strict", "false");
        config.setProperty("drools.permgenThreshold", "0");
        KnowledgeBuilder kBuilder = KnowledgeBuilderFactory.newKnowledgeBuilder(config);
        kBuilder.add(ResourceFactory.newReaderResource(new StringReader(buildCompiledText)), ResourceType.DRL);
        /*
         * kBuilder.add(ResourceFactory.newReaderResource(new StringReader(buildCompiledTextBool)), ResourceType.DRL);
         * kBuilder.add(ResourceFactory.newReaderResource(new StringReader(buildCompiledTextValue)), ResourceType.DRL);
         * kBuilder.add(ResourceFactory.newReaderResource(new StringReader(buildCompiledTextRange)), ResourceType.DRL);
         * kBuilder.add(ResourceFactory.newReaderResource(new StringReader(buildCompiledTextEnum)), ResourceType.DRL);
         */
        if (kBuilder.hasErrors()) {
            for (KnowledgeBuilderError error : kBuilder.getErrors()) {
                System.out.println("Unable to compile rule, error: " + error.getMessage() + ", drl:"
                                   + buildCompiledText);
            }
            return;
        }
        Collection<KnowledgePackage> pkgs = kBuilder.getKnowledgePackages();
        KnowledgeBase kBase = KnowledgeBaseFactory.newKnowledgeBase();
        kBase.addKnowledgePackages(pkgs);
        session = kBase.newStatelessKieSession();

//         testCommon1(session);
        // testBoolRule1(session);
        // testValueRule1(session);
        // testValuesRange1(session);
        // testValuesEnum1(session);

        // 多线程

        final StatelessKieSession finalSession = session;
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        for (int i = 0; i < 1000; i++) {
            SessionTask task = new SessionTask(session);
            executorService.execute(task);
        }


    }

    public static void testCommon1(StatelessKieSession session) {
        long begin = System.currentTimeMillis();
        List<Object> objects = new ArrayList<>();
        DroolsExecuteContext context = new DroolsExecuteContext();
        CallStrategyDroolsOutput output = new CallStrategyDroolsOutput();
        output.setResult("this is output.");
        context.setCallStrategyDroolsOutput(output);
        CallStrategyDroolsInput input = new CallStrategyDroolsInput();
        input.setChinese(true);
        input.setAge(50);
        context.setCallStrategyDroolsInput(input);
        objects.add(context);
        // 加入bool规则
        BoolRule boolRule = new BoolRule();
        boolRule.setIsMessage(true);
        boolRule.setIsRecording(false);
        boolRule.setIsRepair(false);
        boolRule.setChinese(true);
        Map<Integer, Map<String, Boolean>> boolValues = new HashMap<>();
        Map<String, Boolean> boolValueMap = new HashMap<>();
        boolValueMap.put("isChinese", true);
        boolValueMap.put("isMale", false);
        boolValues.put(0, boolValueMap);
        boolRule.setRuleUuid("10");
        objects.add(boolRule);

        // 加入 valueRange规则.
        ValueRangeRule valueRangeRule = new ValueRangeRule();
        Map<Integer, Map<String, Range>> valueRangeMap = new HashMap<>();
        Map<String, Range> valueRange = new HashMap<>();
        Range ageRange = new Range();
        ageRange.setMin(new BigDecimal("3"));
        ageRange.setMax(new BigDecimal("20"));
        valueRange.put("age", ageRange);

        Range heightRange = new Range();
        heightRange.setMin(new BigDecimal("1.23"));
        heightRange.setMax(new BigDecimal("1.92"));
        valueRange.put("height", heightRange);

        valueRangeMap.put(1, valueRange);
        valueRangeRule.setValueRange(valueRangeMap);
        objects.add(valueRangeRule);

        // 加入value规则.
        ValueRule valueRule = new ValueRule();
        Map<Integer, Map<String, String>> valuesSeq = new HashMap<>();
        Map<String, String> valueMap = new HashMap<>();
        valueMap.put("name", "wy");
        valueMap.put("age", "20");
        valuesSeq.put(3, valueMap);
        valueRule.setValues(valuesSeq);
        objects.add(valueRule);

        // 加入enum规则
        EnumRule enumRule = new EnumRule();
        Map<Integer, Map<String, List<String>>> enumSeqMap = new HashMap<>();
        Map<String, List<String>> enumValues = new HashMap<>();
        List<String> visitPlace = new ArrayList<>();
        visitPlace.add("LYG");
        visitPlace.add("CS");
        visitPlace.add("NJ");
        visitPlace.add("HZ");
        enumValues.put("visitPlace", visitPlace);
        enumSeqMap.put(15, enumValues);
        enumRule.setValues(enumSeqMap);
        objects.add(enumRule);

        // 加入计算实例
        List list = new ArrayList();
        list.add(new BoolFunctionImpl());
        list.add(new ValueRangeFunctionImpl());
        list.add(new ValueFunctionImpl());
        list.add(new EnumFunctionImpl());
        objects.addAll(list);

        // 执行
        long begin2 = System.currentTimeMillis();
        session.execute(objects);
        long end = System.currentTimeMillis();
        System.out.println("elapse session.execute(): " + (end - begin2) + " ms");
        System.out.println("elapse all: " + (end - begin) + " ms.");
        System.out.println("output: " + output.getResult());
    }

    private static void testBoolRule1(StatelessKieSession session) {
        List<Object> objects = new ArrayList<>();
        DroolsExecuteContext context = new DroolsExecuteContext();
        context.setCallStrategyDroolsOutput(new CallStrategyDroolsOutput());
        CallStrategyDroolsInput input = new CallStrategyDroolsInput();
        input.setChinese(true);
        input.setAge(50);
        context.setCallStrategyDroolsInput(input);
        objects.add(context);
        // 加入规则对象
        BoolRule boolRule = new BoolRule();
        Map<String, Boolean> values = new HashMap<>();
        Map<Integer, Map<String, Boolean>> boolSeqMap = new HashMap<>();
        values.put("isChinese", true);
        values.put("isMale", false);
        boolSeqMap.put(23, values);
        boolRule.setValues(boolSeqMap);
        objects.add(boolRule);
        // 加入计算实例
        List list = new ArrayList();
        list.add(new BoolFunctionImpl());
        objects.addAll(list);
        // 执行
        session.execute(objects);
        CallStrategyDroolsOutput output = context.getCallStrategyDroolsOutput();
        System.out.println("output: " + output.getResult());
    }

    private static void testValueRule1(StatelessKieSession session) {
        List<Object> objects = new ArrayList<>();
        DroolsExecuteContext context = new DroolsExecuteContext();
        context.setCallStrategyDroolsOutput(new CallStrategyDroolsOutput());
        CallStrategyDroolsInput input = new CallStrategyDroolsInput();
        input.setChinese(true);
        input.setAge(50);
        context.setCallStrategyDroolsInput(input);
        objects.add(context);
        // 加入规则对象
        ValueRule vallueRule = new ValueRule();
        Map<Integer, Map<String, String>> valueSeqMap = new HashMap<>();
        Map<String, String> values = new HashMap<>();
        values.put("name", "wy");
        values.put("age", "10");
        valueSeqMap.put(232, values);
        vallueRule.setValues(valueSeqMap);
        objects.add(vallueRule);
        // 加入计算实例
        List list = new ArrayList();
        list.add(new ValueFunctionImpl());
        objects.addAll(list);
        // 执行
        session.execute(objects);
        CallStrategyDroolsOutput output = context.getCallStrategyDroolsOutput();
        System.out.println("output: " + output.getResult());
    }

    private static void testValuesRange1(StatelessKieSession session) {
        List<Object> objects = new ArrayList<>();
        DroolsExecuteContext context = new DroolsExecuteContext();
        context.setCallStrategyDroolsOutput(new CallStrategyDroolsOutput());
        CallStrategyDroolsInput input = new CallStrategyDroolsInput();
        input.setChinese(true);
        input.setAge(50);
        context.setCallStrategyDroolsInput(input);
        objects.add(context);
        // 加入规则对象
        ValueRangeRule valueRangeRule = new ValueRangeRule();
        valueRangeRule.setFieldName("age");
        Map<Integer, Map<String, Range>> valueRangeSeqMap = new HashMap<>();
        Map<String, Range> valueRange = new HashMap<>();
        Range ageRange = new Range();
        ageRange.setMin(new BigDecimal("3"));
        ageRange.setMax(new BigDecimal("20"));
        valueRange.put("age", ageRange);

        Range heightRange = new Range();
        heightRange.setMin(new BigDecimal("1.23"));
        heightRange.setMax(new BigDecimal("1.92"));
        valueRange.put("height", heightRange);
        valueRangeSeqMap.put(232, valueRange);

        valueRangeRule.setValueRange(valueRangeSeqMap);
        objects.add(valueRangeRule);
        // 加入计算实例
        List list = new ArrayList();
        list.add(new ValueRangeFunctionImpl());
        objects.addAll(list);
        // 执行
        session.execute(objects);
        CallStrategyDroolsOutput output = context.getCallStrategyDroolsOutput();
        System.out.println("output: " + output.getResult());
    }

    private static void testValuesEnum1(StatelessKieSession session) {
        List<Object> objects = new ArrayList<>();
        DroolsExecuteContext context = new DroolsExecuteContext();
        context.setCallStrategyDroolsOutput(new CallStrategyDroolsOutput());
        CallStrategyDroolsInput input = new CallStrategyDroolsInput();
        input.setChinese(true);
        input.setAge(50);
        context.setCallStrategyDroolsInput(input);
        objects.add(context);
        // 加入规则对象
        EnumRule enumRule = new EnumRule();
        Map<Integer, Map<String, List<String>>> enumSeqMap = new HashMap<>();
        Map<String, List<String>> values = new HashMap<>();
        List<String> types = new ArrayList();
        types.add("a");
        types.add("b");
        values.put("a", types);
        enumSeqMap.put(998, values);
        enumRule.setValues(enumSeqMap);
        objects.add(enumRule);
        // 加入计算实例
        List list = new ArrayList();
        list.add(new EnumFunctionImpl());
        objects.addAll(list);
        // 执行
        session.execute(objects);
        CallStrategyDroolsOutput output = context.getCallStrategyDroolsOutput();
        System.out.println("output: " + output.getResult());
    }

    private static String buildCompiledTextCommon() {
        String compiledText = null;
        List<DrlContext> drlContexts = new ArrayList<>();
        DrlContext boolDrl = new DrlContext("bool", "execute");
        boolDrl.setRuleCondition("ruleCondition:BoolRule()");
        boolDrl.setVarDefine("boolRuleFunction:BoolFunctionImpl()");
        boolDrl.setExecuteString("boolRuleFunction.execute(ruleCondition, context)");
        boolDrl.setCondition("true");
        boolDrl.setDesc("布尔规则");
        boolDrl.setAlias("bool/execute");
        boolDrl.setSalience("90");
        drlContexts.add(boolDrl);

        DrlContext valueRangeRuleDrl = new DrlContext("valueRange", "execute");
        valueRangeRuleDrl.setRuleCondition("ruleCondition:ValueRangeRule()");
        valueRangeRuleDrl.setVarDefine("valueRangeRule:ValueRangeFunctionImpl()");
        valueRangeRuleDrl.setExecuteString("valueRangeRule.execute(ruleCondition, context)");
        valueRangeRuleDrl.setCondition("true");
        valueRangeRuleDrl.setDesc("范围规则");
        valueRangeRuleDrl.setAlias("valueRange/execute");
        valueRangeRuleDrl.setSalience("80");
        drlContexts.add(valueRangeRuleDrl);

        DrlContext valueRuleDrl = new DrlContext("value", "execute");
        valueRuleDrl.setRuleCondition("ruleCondition:ValueRule()");
        valueRuleDrl.setVarDefine("valueRule:ValueFunctionImpl()");
        valueRuleDrl.setExecuteString("valueRule.execute(ruleCondition, context)");
        valueRuleDrl.setCondition("true");
        valueRuleDrl.setDesc("值规则");
        valueRuleDrl.setAlias("value/execute");
        valueRuleDrl.setSalience("70");
        drlContexts.add(valueRuleDrl);

        RuleContext ruleContext = new RuleContext();
        ruleContext.setDrlContexts(drlContexts);
        String drlTemplate = loadDrlTemplate("COMMON");
        try (StringWriter writer = new StringWriter()) {
            Context ctx = new VelocityContext();
            ctx.put("ruleContext", ruleContext);
            Velocity.evaluate(ctx, writer, "velocity", drlTemplate);
            compiledText = writer.toString();
        } catch (Exception e) {
            System.out.println("[RenderEngine] assemble occur exception, details: " + e);
        }
        return compiledText;
    }

    private static String buildCompiledTextBool() {
        String compiledText = null;
        DrlContext boolRuleContext = new DrlContext("bool", "execute");
        boolRuleContext.setCondition("true");

        String drlTemplate = loadDrlTemplate("BOOL");
        try (StringWriter writer = new StringWriter()) {
            Context ctx = new VelocityContext();
            ctx.put("boolRuleContext", boolRuleContext);
            Velocity.evaluate(ctx, writer, "velocity", drlTemplate);
            compiledText = writer.toString();
        } catch (Exception e) {
            System.out.println("[RenderEngine] assemble occur exception, details: " + e);
        }
        return compiledText;
    }

    private static String buildCompiledTextValue() {
        String compiledText = null;
        DrlContext valueRuleContext = new DrlContext("value", "execute");
        valueRuleContext.setCondition("true");

        String drlTemplate = loadDrlTemplate("VALUE");
        try (StringWriter writer = new StringWriter()) {
            Context ctx = new VelocityContext();
            ctx.put("valueRuleContext", valueRuleContext);
            Velocity.evaluate(ctx, writer, "velocity", drlTemplate);
            compiledText = writer.toString();
        } catch (Exception e) {
            System.out.println("[RenderEngine] assemble occur exception, details: " + e);
        }
        return compiledText;
    }

    private static String buildCompiledTextRange() {
        String compiledText = null;
        DrlContext rangeRuleContext = new DrlContext("range", "execute");
        rangeRuleContext.setCondition("true");

        String drlTemplate = loadDrlTemplate("RANGE");
        try (StringWriter writer = new StringWriter()) {
            Context ctx = new VelocityContext();
            ctx.put("rangeRuleContext", rangeRuleContext);
            Velocity.evaluate(ctx, writer, "velocity", drlTemplate);
            compiledText = writer.toString();
        } catch (Exception e) {
            System.out.println("[RenderEngine] assemble occur exception, details: " + e);
        }
        return compiledText;
    }

    private static String buildCompiledTextEnum() {
        String compiledText = null;
        DrlContext enumRuleContext = new DrlContext("enum", "execute");
        enumRuleContext.setCondition("true");

        String drlTemplate = loadDrlTemplate("ENUM");
        try (StringWriter writer = new StringWriter()) {
            Context ctx = new VelocityContext();
            ctx.put("enumRuleContext", enumRuleContext);
            Velocity.evaluate(ctx, writer, "velocity", drlTemplate);
            compiledText = writer.toString();
        } catch (Exception e) {
            System.out.println("[RenderEngine] assemble occur exception, details: " + e);
        }
        return compiledText;
    }

    public static String loadDrlTemplate(String ruleType) {
        String filePath = null;
        switch (ruleType) {
            case "BOOL":
                filePath = "/Users/leslie/MyProjects/GitHub/myjava/src/main/examples/drools/assetrule/drl/bool_rule.drl.vm";
                break;
            case "VALUE":
                filePath = "/Users/leslie/MyProjects/GitHub/myjava/src/main/examples/drools/assetrule/drl/value_rule.drl.vm";
                break;
            case "RANGE":
                filePath = "/Users/leslie/MyProjects/GitHub/myjava/src/main/examples/drools/assetrule/drl/range_rule.drl.vm";
                break;
            case "ENUM":
                filePath = "/Users/leslie/MyProjects/GitHub/myjava/src/main/examples/drools/assetrule/drl/enum_rule.drl.vm";
                break;
            case "COMMON":
                filePath = "/Users/leslie/MyProjects/GitHub/myjava/src/main/examples/drools/assetrule/drl/common.drl.vm";
                break;
            default:
                filePath = "/Users/leslie/MyProjects/GitHub/myjava/src/main/examples/drools/assetrule/drl/common.drl.vm";
        }

        InputStream in = null;
        try {
            in = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        StringBuffer sb = new StringBuffer();
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(in, Charset.defaultCharset().name()));
            String line = null;
            while ((line = br.readLine()) != null) {
                sb.append(line).append("\n");
            }
        } catch (IOException e) {
            System.out.println(e);
        } finally {
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    System.out.println(e);
                }
            }
        }
        return sb.toString();
    }
}

class SessionTask implements Runnable {

    private StatelessKieSession session;

    SessionTask(StatelessKieSession session){
        this.session = session;
    }

    @Override
    public void run() {
        AssetRule.testCommon1(session);
    }
}
