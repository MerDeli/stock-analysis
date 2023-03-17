package com.lyy.ums.mbg.gen;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.fill.Column;
import com.baomidou.mybatisplus.generator.fill.Property;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

// 演示例子，执行 main 方法控制台输入模块表名回车自动生成对应项目目录中
public class CodeGenerator {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotBlank(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }


    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/stock-analy?serverTimezone=GMT%2B8", "root", "123456")
                .globalConfig(builder -> {
                    builder.author("lyy") // 设置作者 baomidou 默认值:作者
                            .enableSwagger() // 开启 swagger 模式 默认值:false
                            .fileOverride() // 覆盖已生成文件 默认值:false
                            .disableOpenDir()//禁止打开输出目录 默认值:true
                            .commentDate("yyyy-MM-dd")// 注释日期
                            .dateType(DateType.ONLY_DATE)//定义生成的实体类中日期类型 DateType.ONLY_DATE 默认值: DateType.TIME_PACK
                            .outputDir(System.getProperty("user.dir") + "/stock-analysis/stock-admin-ums/ums-mbg/src/main/java"); // 指定输出目录 /opt/baomidou/ 默认值: windows:D:// linux or mac : /tmp
                    //System.getProperty("user.dir")为你当前模块的绝对路径
                })

                .packageConfig(builder -> {
                    builder.parent("com.lyy.ums.mbg") // 父包模块名 默认值:com.baomidou
                            .controller("controller")//Controller 包名 默认值:controller
                            .entity("entity")//Entity 包名 默认值:entity
                            .service("service")//Service 包名 默认值:service
                            .mapper("mapper")//Mapper 包名 默认值:mapper
//                            .moduleName("ums-mbg") // 设置父包模块名 默认值:无
                            .pathInfo(Collections.singletonMap(OutputFile.xml,System.getProperty("user.dir")+ "/stock-analysis/stock-admin-ums/ums-mbg/src/main/resources/mapper")); // 设置mapperXml生成路径
                    //默认存放在mapper的xml下
                })

                .strategyConfig(builder -> {
                    builder.addInclude("stock_user","stock_role","stock_user_role_rel") // 设置需要生成的表名 可边长参数“user”, “user1”
                            .addTablePrefix("tb_", "c_") // 设置过滤表前缀
                            .serviceBuilder()//service策略配置
                            .formatServiceFileName("%sService")
                            .formatServiceImplFileName("%sServiceImpl")
                            .entityBuilder()// 实体类策略配置
                            .idType(IdType.ASSIGN_ID)//主键策略  雪花算法自动生成的id
                            .addTableFills(new Column("created_time", FieldFill.INSERT)) // 自动填充配置
                            .addTableFills(new Property("updated_time", FieldFill.INSERT_UPDATE))
                            .enableLombok() //开启lombok
                            .logicDeleteColumnName("delete_flag")// 说明逻辑删除是哪个字段
                            .enableTableFieldAnnotation()// 属性加上注解说明
                            .controllerBuilder() //controller 策略配置
                            .formatFileName("%sController")
                            .enableRestStyle() // 开启RestController注解
                            .mapperBuilder()// mapper策略配置
                            .formatMapperFileName("%sMapper")
                            .enableMapperAnnotation()//@mapper注解开启
                            .formatXmlFileName("%sMapper");
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}