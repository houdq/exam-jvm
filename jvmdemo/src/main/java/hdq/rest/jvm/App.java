package hdq.rest.jvm;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;

import javax.servlet.MultipartConfigElement;
import javax.servlet.Servlet;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Hello world! jetty 参考这个链接
 * http://www.cnblogs.com/dyllove98/archive/2013/07/11/3184794.html
 */
public class App {

	private static final Logger log = Logger.getLogger("hdq.jvm");
	private static GsonBuilder gsonBuilder = new GsonBuilder();;
	public static Gson gson;
	// 可用用来修改配置文件

	private static final Properties config = new Properties();

	public static void main(String[] args) {
		gson = gsonBuilder.create();
		Map<Class<? extends Servlet>, String> servletMap = new HashMap<Class<? extends Servlet>, String>();
		servletMap.put(Query.class, "/query");
		servletMap.put(Create.class, "/create");
		startServer(7777, "/", servletMap);
	}

	private static void startServer(int port, String contextPath,
			Map<Class<? extends Servlet>, String> servletMap) {
		ServletHolder sh = new ServletHolder(Create.class);
		MultipartConfigElement mce = new MultipartConfigElement(
				"yourTempLocation", 1048576, 1048576, 262144);
		sh.getRegistration().setMultipartConfig(mce);
		// 绑定端口
		Server server = new Server(port);
		// 可以使用ServletContextHandler处理Servlet
		ServletContextHandler context = new ServletContextHandler();
		// 添加Servlet并指定映射url-pattern
		for (Map.Entry<Class<? extends Servlet>, String> servletEntry : servletMap
				.entrySet()) {
			context.addServlet(servletEntry.getKey(), servletEntry.getValue());
		}
		// 此时访问路径就是http://127.0.0.1:port/contextPath/urlPattern
		context.setContextPath(contextPath);
		// 绑定Handler
		server.setHandler(context);
		// 启动服务
		try {
			server.start();
		} catch (Exception e) {
			log.info("shit! 启动Jetty时发生异常,堆栈轨迹如下");
			e.printStackTrace();
		}
		if (server.isStarted()) {
			log.info("ok!Servlet服务启动成功");
		}
	}
}
