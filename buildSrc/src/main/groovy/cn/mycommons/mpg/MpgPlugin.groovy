package cn.mycommons.mpg

import com.baomidou.mybatisplus.generator.AutoGenerator
import com.baomidou.mybatisplus.generator.InjectionConfig
import com.baomidou.mybatisplus.generator.config.*
import com.baomidou.mybatisplus.generator.config.po.TableInfo
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.plugins.MavenPlugin
import org.gradle.api.tasks.bundling.Jar

class MpgPlugin implements Plugin<Project> {

    private String nexusUsername
    private String nexusPassword

    @Override
    void apply(Project project) {
        Properties properties = new Properties()

        try {
            def file = project.rootProject.file('local.properties')
            if (file.exists()) {
                properties.load(file.newDataInputStream())
            }
            nexusUsername = properties.getProperty("nexus_username", "")
            nexusPassword = properties.getProperty("nexus_password", "")
        } catch (Exception e) {
            project.logger.error("error: " + e.message)
        }

        MpgPluginExtension extension = project.extensions.create('mpg', MpgPluginExtension)
        if (extension != null) {
            createMpgTask(project)
        }
    }

    private void createMpgTask(Project project) {
        // 生成sources.jar
        def androidSourcesJar = project.tasks.create("mybats", Jar.class)

        androidSourcesJar.doLast {

            String JDBC_URL = "jdbc:mysql://172.16.235.208:3306/ifms?useUnicode=true&characterEncoding=utf8&useSSL=false"
            String JDBC_USER = "root"
            String JDBC_PASSWORD = "admin"

            String PROJECT_PATH = project.projectDir.path
            String MODEL_PACKAGE = "cn.mycommons.springdemo"
            String TABLE_NAME = "tb_ci_app_info"
            String TABLE_PREFIX = "tb_ci"

            // 代码生成器
            AutoGenerator mpg = new AutoGenerator()

            // 全局配置
            GlobalConfig gc = new GlobalConfig()
            gc.setOutputDir(PROJECT_PATH + "/src/main/java")
            gc.setAuthor("admin")
            gc.setOpen(false)
            gc.setBaseResultMap(true)
            mpg.setGlobalConfig(gc)

            // 数据源配置
            DataSourceConfig dsc = new DataSourceConfig()
            dsc.setUrl(JDBC_URL)
            dsc.setDriverName("com.mysql.jdbc.Driver")
            dsc.setUsername(JDBC_USER)
            dsc.setPassword(JDBC_PASSWORD)
            mpg.setDataSource(dsc)

            // 包配置
            PackageConfig pc = new PackageConfig()
            pc.setParent(MODEL_PACKAGE)
            pc.setEntity("mybatis.entity")
            pc.setMapper("mybatis.mapper")
            mpg.setPackageInfo(pc)

            // 自定义配置
            InjectionConfig cfg = new InjectionConfig() {
                @Override
                public void initMap() {
                    // to do nothing
                }
            }

            // 如果模板引擎是 freemarker
            String templatePath = "/templates/mapper.xml.ftl"
            // 如果模板引擎是 velocity
            // String templatePath = "/templates/mapper.xml.vm"

            // 自定义输出配置
            List<FileOutConfig> focList = new ArrayList<>()
            // 自定义配置会被优先输出
            focList.add(new FileOutConfig(templatePath) {
                @Override
                public String outputFile(TableInfo tableInfo) {
                    // 自定义输出文件名
                    return PROJECT_PATH + "/src/main/resources/mapper/" + tableInfo.getEntityName() + "Mapper.xml"
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
            StrategyConfig strategy = new StrategyConfig()
            strategy.setNaming(NamingStrategy.underline_to_camel)
            strategy.setColumnNaming(NamingStrategy.underline_to_camel)
            strategy.setEntityLombokModel(true)
            strategy.setRestControllerStyle(true)
            // strategy.setSuperControllerClass("com.baomidou.ant.common.BaseController")
            strategy.setInclude(TABLE_NAME)
            // strategy.setSuperEntityColumns("id")
            strategy.setLogicDeleteFieldName("is_delete")
            strategy.setSuperEntityColumns("create_by", "create_time", "update_by", "update_time", "remark", "is_delete")
            strategy.entityTableFieldAnnotationEnable(true)
            strategy.setControllerMappingHyphenStyle(true)
            strategy.setTablePrefix(TABLE_PREFIX)
            mpg.setStrategy(strategy)
            mpg.setTemplateEngine(new FreemarkerTemplateEngine())
            mpg.execute()
        }
    }
}