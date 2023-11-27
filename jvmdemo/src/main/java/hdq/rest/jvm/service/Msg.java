package hdq.rest.jvm.service;

public class Msg {
	private boolean suc = false;
	private int code = 1;
	private String msg;

	public Msg(boolean suc, int code, String msg) {
		this.suc = suc;
		this.code = code;
		this.msg = msg;
	}

	public boolean isSuc() {
		return suc;
	}

	public void setSuc(boolean suc) {
		this.suc = suc;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

}
