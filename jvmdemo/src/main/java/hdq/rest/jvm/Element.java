package hdq.rest.jvm;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.UUID;

//	entity
public class Element {
	// ID 内部生成算了
	private String id;
	private String time;
	private String state;
	private String type;
	private String price;
	private String currencyType;
	private Element.User user;

	public Element() {
		this.id = UUID.randomUUID().toString();
		// 自己改用新API吧
		this.time = new Date().toLocaleString();
	}

	public Element.User getUser() {
		return user;
	}

	public void setUser(Element.User user) {
		this.user = user;
	}

	public String getId() {
		return id;
	}

	public String getTime() {
		return time;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getCurrencyType() {
		return currencyType;
	}

	public void setCurrencyType(String currencyType) {
		this.currencyType = currencyType;
	}

	class User {
		private String uid;
		private String uname;

		/**
		 * 用户得uid就直接是uname md5 之后得就可以
		 * */
		public String getUid() {
			MessageDigest digest;
			try {
				digest = java.security.MessageDigest.getInstance("MD5");
				byte[] bytes = digest.digest(this.uname.getBytes());
				return byteArrayToHex(bytes);
			} catch (NoSuchAlgorithmException e) {
				// TODO 自己处理吧;
				return null;
			}

		}

		private String byteArrayToHex(byte[] byteArray) {
			char[] hexDigits = { '0', '1', '2', '3', '4', '5', '6', '7', '8',
					'9', 'A', 'B', 'C', 'D', 'E', 'F' };
			char[] resultCharArray = new char[byteArray.length * 2];
			int index = 0;
			for (byte b : byteArray) {
				resultCharArray[index++] = hexDigits[b >>> 4 & 0xf];
				resultCharArray[index++] = hexDigits[b & 0xf];
			}
			return new String(resultCharArray);
		}

		public String getUname() {
			return uname;
		}

		public void setUname(String uname) {
			this.uname = uname;
		}
	}
}
