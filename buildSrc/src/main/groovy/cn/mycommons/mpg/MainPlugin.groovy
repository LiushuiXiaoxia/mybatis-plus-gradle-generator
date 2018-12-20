package cn.mycommons.mpg

import org.gradle.api.Plugin
import org.gradle.api.Project

class MainPlugin implements Plugin<Project> {

    void apply(Project project) {
        project.plugins.apply(MpgPlugin)
    }
}