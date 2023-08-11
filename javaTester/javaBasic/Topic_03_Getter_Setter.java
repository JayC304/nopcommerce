package javaBasic;

import java.util.Scanner;

public class Topic_03_Getter_Setter {
	public static void main(String[] args) {
		String sdt;
		Scanner scanner = new Scanner(System.in);

		System.out.println("Nhập vào sdt bất kỳ: ");
		sdt = scanner.nextLine();

		if (sdt.length() > 10 || sdt.length() < 10) {
			System.out.println("Nhap sdt co 10 so");
		} else {
			if (sdt.startsWith("7") || sdt.startsWith("9")) {
				System.out.println("sdt dung dinh dang");
			} else {
				System.out.println("sdt sai dinh dang");
			}
		}
	}
}
