package cn.mycommons.mpg.pojo

import com.baomidou.mybatisplus.generator.config.DataSourceConfig
import com.baomidou.mybatisplus.generator.config.GlobalConfig
import com.baomidou.mybatisplus.generator.config.PackageConfig
import com.baomidou.mybatisplus.generator.config.StrategyConfig
import org.gradle.api.Action
import org.gradle.api.model.ObjectFactory

import javax.inject.Inject

class MpgPluginExtension {

    boolean enable = true
    GlobalConfig globalConfig
    DataSourceConfig dataSourceConfig
    PackageConfig packageConfig
    XmlMapperConfig xmlMapperConfig
    StrategyConfig strategyConfig

    @Inject
    MpgPluginExtension(ObjectFactory objectFactory) {
        // Create a Person instance
        globalConfig = objectFactory.newInstance(GlobalConfig)
        dataSourceConfig = objectFactory.newInstance(DataSourceConfig)
        packageConfig = objectFactory.newInstance(PackageConfig)
        xmlMapperConfig = objectFactory.newInstance(XmlMapperConfig)
        strategyConfig = objectFactory.newInstance(StrategyConfig)
    }

    void globalConfig(Action<? super GlobalConfig> action) {
        action.execute(globalConfig)
    }

    void dataSourceConfig(Action<? super DataSourceConfig> action) {
        action.execute(dataSourceConfig)
    }

    void packageConfig(Action<? super PackageConfig> action) {
        action.execute(packageConfig)
    }

    void xmlMapperConfig(Action<? super XmlMapperConfig> action) {
        action.execute(xmlMapperConfig)
    }

    void strategyConfig(Action<? super StrategyConfig> action) {
        action.execute(strategyConfig)
    }


    @Override
    String toString() {
        return "MpgPluginExtension{" +
                "enable=" + enable +
                ", globalConfig=" + globalConfig +
                ", dataSourceConfig=" + dataSourceConfig +
                ", packageConfig=" + packageConfig +
                ", xmlMapperConfig=" + xmlMapperConfig +
                ", strategyConfig=" + strategyConfig +
                '}'
    }
}