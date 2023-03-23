package com.lyy.stock.plugin.bgg.loader;

import com.lyy.stock.plugin.common.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

/**
 * @Author:
 * @createTime: 2023/03/23 17:56:42
 * @version:
 * @Description:
 */
public abstract class LocalConfigLoader implements ConfigLoader {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public String getConfig(){
        String path = getPath();
        return FileUtil.getText(applicationContext, path);
    }

    protected abstract String getPath();
}
