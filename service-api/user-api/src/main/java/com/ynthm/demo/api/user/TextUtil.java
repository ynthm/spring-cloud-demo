package com.ynthm.demo.api.user;

import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.SimpleEvaluationContext;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ethan Wang
 */
public class TextUtil {

  public static void main(String[] args) {
    String smsTemplate = "验证码:#{[code]},您正在登录管理后台，#{[minutes]}分钟内输入有效。";
    Map<String, Object> params = new HashMap<>();
    params.put("code", "12345");
    params.put("minutes", 5);

    ExpressionParser parser = new SpelExpressionParser();
    TemplateParserContext parserContext = new TemplateParserContext();
    String content =
        parser.parseExpression(smsTemplate, parserContext).getValue(params, String.class);

    System.out.println(content);

    // 测试SpringEL解析器
    String template = "#{#name}，早上好"; // 设置文字模板,其中#{}表示表达式的起止，#user是表达式字符串，表示引用一个变量。
    ExpressionParser paser = new SpelExpressionParser(); // 创建表达式解析器

    // 通过evaluationContext.setVariable可以在上下文中设定变量。
    EvaluationContext context = new StandardEvaluationContext();
    context.setVariable("name", "Alex");
    //
    SimpleEvaluationContext simpleEvaluationContext =
        SimpleEvaluationContext.forReadOnlyDataBinding().build();
    simpleEvaluationContext.setVariable("name", "Alex");

    // 解析表达式，如果表达式是一个模板表达式，需要为解析传入模板解析器上下文。
    Expression expression = paser.parseExpression(template, new TemplateParserContext());

    // 使用Expression.getValue()获取表达式的值，这里传入了Evalution上下文，第二个参数是类型参数，表示返回值的类型。
    System.out.println(expression.getValue(simpleEvaluationContext, String.class));
  }
}
