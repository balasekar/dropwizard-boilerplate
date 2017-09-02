package com.product.app.health;

import com.yammer.metrics.core.HealthCheck;

public class TemplateCheck extends HealthCheck {
    private final String template;

    public TemplateCheck(String name, String template) {
        super(name);
        this.template = template;
    }

    @Override
    protected Result check() throws Exception {
        final String saying = String.format(template, "TEST");
        if (!saying.contains("TEST")) {
            return Result.unhealthy("template doesn't include a name");
        }
        return Result.healthy();
    }
}
