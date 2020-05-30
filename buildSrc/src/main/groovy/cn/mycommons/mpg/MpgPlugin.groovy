package cn.mycommons.mpg

import cn.mycommons.mpg.pojo.MpgPluginExtension
import com.baomidou.mybatisplus.generator.AutoGenerator
import com.baomidou.mybatisplus.generator.InjectionConfig
import com.baomidou.mybatisplus.generator.config.DataSourceConfig
import com.baomidou.mybatisplus.generator.config.FileOutConfig
import com.baomidou.mybatisplus.generator.config.TemplateConfig
import com.baomidou.mybatisplus.generator.config.po.TableInfo
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import org.gradle.api.Plugin
import org.gradle.api.Project

class MpgPlugin implements Plugin<Project> {

    @Override
    void apply(Project project) {
        MpgPluginExtension extension = project.extensions.create('mpg', MpgPluginExtension, project.objects)

        if (extension != null) {
            createMpgTask(project, extension)
        }
    }

    private void createMpgTask(Project project, MpgPluginExtension extension) {
        def task = project.tasks.create("mpg")
        task.group = 'mybatis'
        task.doLast {
            project.logger.error("extension = " + extension)

            String PROJECT_PATH = project.projectDir.path
            String MODEL_PACKAGE = "cn.mycommons.springdemo"
            String TABLE_NAME = "tb_ci_app_info"
            String TABLE_PREFIX = "tb_ci"

            // 代码生成器
            AutoGenerator mpg = new AutoGenerator()

            // 全局配置
//            GlobalConfig gc = new GlobalConfig()
//            gc.setOutputDir(PROJECT_PATH + "/src/main/java")
//            gc.setAuthor("admin")
//            gc.setOpen(false)
//            gc.setBaseResultMap(true)
            mpg.setGlobalConfig(extension.globalConfig)

            // 数据源配置
            DataSourceConfig dsc = extension.dataSourceConfig
            mpg.setDataSource(dsc)

            // 包配置
//            PackageConfig pc = new PackageConfig()
//            pc.setParent(MODEL_PACKAGE)
//            pc.setEntity("task.entity")
//            pc.setMapper("task.mapper")
            mpg.setPackageInfo(extension.packageConfig)

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                void initMap() {
                    // to do nothing
                }
            }
            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>()
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig("/templates/mapper.xml.ftl") {

                @Override
                String outputFile(TableInfo tableInfo) {
                    def xmlMapperConfig = extension.xmlMapperConfig


                    if (xmlMapperConfig == null
                            || xmlMapperConfig.path == null || xmlMapperConfig.path.length() == 0
                            || xmlMapperConfig.name == null || xmlMapperConfig.name.length() == 0) {
                        def path = project.projectDir.path + "/src/main/resources/mapper/"
                        return path + tableInfo.getEntityName() + "Mapper.xml"
                    }
                    // 自定义输出文件名
                    return xmlMapperConfig.path + xmlMapperConfig.name
                }
            })

            cfg.setFileOutConfigList(focList)
            mpg.setCfg(cfg)

            // 配置模板
            TemplateConfig templateConfig = new TemplateConfig()

            // 配置自定义输出模板
            // templateConfig.setEntity()
            // templateConfig.setService()
            // templateConfig.setController()

            templateConfig.setXml(null)
            mpg.setTemplate(templateConfig)

            // 策略配置
//            StrategyConfig strategy = new StrategyConfig()
//            strategy.setNaming(NamingStrategy.underline_to_camel)
//            strategy.setColumnNaming(NamingStrategy.underline_to_camel)
//            strategy.setEntityLombokModel(true)
//            strategy.setRestControllerStyle(true)
//            // strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController")
//            strategy.setInclude(TABLE_NAME)
//            // strategy.setSuperEntityColumns("id")
//            strategy.setLogicDeleteFieldName("is_delete")
//            strategy.setSuperEntityColumns("create_by", "create_time", "update_by", "update_time", "remark", "is_delete")
//            strategy.entityTableFieldAnnotationEnable(true)
//            strategy.setControllerMappingHyphenStyle(true)
//            strategy.setTablePrefix(TABLE_PREFIX)
            extension.strategyConfig.naming = NamingStrategy.underline_to_camel
            extension.strategyConfig.columnNaming = NamingStrategy.underline_to_camel
            mpg.setStrategy(extension.strategyConfig)
            mpg.setTemplateEngine(new FreemarkerTemplateEngine())
            mpg.execute()
        }
    }
}