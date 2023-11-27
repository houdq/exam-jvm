package hdq.rest.jvm.service;

import hdq.rest.jvm.App;
import hdq.rest.jvm.User;
import hdq.rest.jvm.db.DB;
import hdq.rest.jvm.db.Element;
import hdq.rest.jvm.db.Table;
import org.eclipse.jetty.util.MultiPartInputStreamParser;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Map;

/**
 * 创建交易得接口
 */
public class Create extends HttpServlet {

    private static final long serialVersionUID = 7413080107741116510L;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json; charset=UTF-8");
        PrintWriter out = response.getWriter();
        Msg msg = new Msg(false, 400, "不允许的请求方式");
        String msgStr = App.gson.toJson(msg);
        out.println(msgStr);
        out.flush();
        out.close();
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream reqIs = request.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(reqIs));
        String line;
        String queryString = "";
        while ((line = reader.readLine()) != null) {
            queryString += line;
        }
        MultipartConfigElement mce = new MultipartConfigElement(
                "yourTempLocation", 1048576, 1048576, 262144);
        MultiPartInputStreamParser mpip = new MultiPartInputStreamParser(reqIs,
                "multipart-* ", mce, new File("tmp"));
        System.out.println("参数: " + queryString);
        System.out.println("part: " + mpip.getParts().size());
        // 可以获取所有参数
        Map<String, String[]> params = request.getParameterMap();
        for (String key : params.keySet()) {
            String[] values = params.get(key);
            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                System.out.println("参数: " + key);
                System.out.println("值: " + value);
            }
        }
        String state = request.getParameter("state");
        String type = request.getParameter("type");
        String price = request.getParameter("price");
        String currencyType = request.getParameter("currencyType");
        String uname = request.getParameter("uname");
        System.out.println("交易状态[" + state);
        System.out.println("交易类型[" + type);
        System.out.println("金额[" + price);
        System.out.println("货币类型[" + currencyType);
        System.out.println("用户信息[" + uname);
        // 「交易
        // ID」、「创建时间」、「交易状态（例如是否交易成功）」、「交易类型（例如支付还是退款）」、「交易金额」、「交易币种（例如美元还是人民币）」、「客户信息」
        // TODO 可以做一个参数验证器,那些是是必填,哪些是可选
        // 必填的没填,就抛出异常,不往下走了 .可选得没填,给上默认值,并且返回详细信息

        try {
            DB db = DB.getInstance();
            Table table = db.createTable("history");
            Element ele = new Element();
            ele.setCurrencyType(currencyType);
            ele.setPrice(price);
            ele.setState(state);
            ele.setType(type);
            User user = new User();
            user.setUname(uname);
            ele.setUser(user);
            table.add(ele);
            response.setContentType("application/json; charset=UTF-8");
            PrintWriter out = response.getWriter();
            String id = ele.getId();
            out.println(id);
            out.flush();
            out.close();
        } catch (Exception e) {
            // TODO 需要回应异常信息
        }

    }
}
