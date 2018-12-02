package csk.spring.autoRegisterProxyForInterface;


import csk.spring.autoRegisterProxyForInterface.bean.BaseMapper;
import org.apache.log4j.Logger;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class CustomBaseMapper implements BaseMapper {
    private final Logger logger = Logger.getLogger(this.getClass().getName());
    private List<String> dataList = new CopyOnWriteArrayList<>();

    /**
     * @param value
     */
    @Override
    public String add(String value) {
        logger.info("添加数据:" + value);
        dataList.add(value);
        return "默认实现：" + value;
    }

    /**
     * @param key
     */
    @Override
    public void remove(String key) {
        if (dataList.isEmpty())
            throw new IllegalArgumentException("Can't remove because the list is Empty!");

    }
}
