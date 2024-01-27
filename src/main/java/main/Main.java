package main;

import dao.FileDao;

public class Main {

    public static void main(String[] args) {

	FileDao.deleteImage("Blog-DB.png");

	System.out.println("");
    }
}
