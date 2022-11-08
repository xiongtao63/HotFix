package com.enjoy.plugin;

import org.gradle.api.Action;
import org.gradle.api.Plugin;
import org.gradle.api.Project;

public class HotfixPlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {

        final PatchExt patch = project.getExtensions().create("patch", PatchExt.class);

        project.afterEvaluate(new Action<Project>() {
            @Override
            public void execute(Project project) {
                String applicationName = patch.applicationName;
                System.out.println(applicationName);
            }
        });

    }
}
