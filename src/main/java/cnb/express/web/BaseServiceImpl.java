package cnb.express.web;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by tom on 16-12-30.
 */
public abstract class BaseServiceImpl {

    protected JSONObject buildResponse(String key, Object value) {
        JSONObject res = new JSONObject();
        res.put(key, value);
        return res;
    }

}
