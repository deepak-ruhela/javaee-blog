package helper;

public class StringFormat {

	public static String get10Words(String desc) {

		String[] str = desc.split(" ");
		if (str.length > 10) {
			String res = "";
			for (int i = 0; i < 10; i++) {

				res = res + str[i] + " ";

			}

		} else {

			return (desc + "...");
		}

		return null;

	}

}
