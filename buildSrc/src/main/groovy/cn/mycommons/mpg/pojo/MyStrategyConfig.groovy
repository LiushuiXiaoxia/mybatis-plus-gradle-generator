package cn.mycommons.mpg.pojo

import com.baomidou.mybatisplus.generator.config.StrategyConfig

/**
 * StrategyConfig2 <br/>
 * Created by xiaqiulei on 2020-05-30.
 */
class MyStrategyConfig extends StrategyConfig {

    private String superEntityClass2

    MyStrategyConfig() {
    }

    @Override
    StrategyConfig setSuperEntityClass(String superEntityClass) {
        superEntityClass2 = superEntityClass
        return this
    }

    @Override
    String getSuperEntityClass() {
        return superEntityClass2
    }

    @Override
    String toString() {
        return "MyStrategyConfig " + super.toString()
    }
}