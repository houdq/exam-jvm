package hdq.rest.jvm.service;

import hdq.rest.jvm.App;
import hdq.rest.jvm.db.Element;
import hdq.rest.jvm.db.DB;
import hdq.rest.jvm.db.Table;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 查询交易得接口
 */
public class Query extends HttpServlet {

	private static final long serialVersionUID = -544394743270194711L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = response.getWriter();
		String state = request.getParameter("state");
		if (state != null) {
			System.out.println("按照状态查询: " + state);
			DB db = DB.getInstance();
			Table table = db.createTable("history");
			ArrayList<Element> ret = table.queryByState(state);
			String retStr = App.gson.toJson(ret, ret.getClass());
			System.out.println(retStr);
			out.println(retStr);
			out.flush();
			out.close();
		} else {

			DB db = DB.getInstance();
			Table table = db.createTable("history");
			ArrayList<Element> ret = table.getAll();
			String retStr = App.gson.toJson(ret, ret.getClass());
			System.out.println(retStr);
			out.println(retStr);
			out.flush();
			out.close();
			// Todo 应该列出所有吧..先不再返回吧 回头补个接口
			// Msg msg = new Msg(false, 400, "未知查询条件");
			// String retStr = App.gson.toJson(msg);
			// out.println(retStr);
			// out.flush();
			// out.close();
		}

	}
}
