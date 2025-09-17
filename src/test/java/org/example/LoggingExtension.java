package org.example;

import org.junit.jupiter.api.extension.AfterEachCallback;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;

public class LoggingExtension implements BeforeEachCallback, AfterEachCallback{

    @Override
    public void beforeEach(ExtensionContext context) {
        System.out.println("👉 Starting test: " + context.getDisplayName());
    }

    @Override
    public void afterEach(ExtensionContext context) {
        System.out.println("✅ Finished test: " + context.getDisplayName());
    }
}
