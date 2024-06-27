package kr.exam.file;

import java.io.FileInputStream;

public class FileInputExam02 {
	public static void main(String[] args) {

		//파일을 읽기 위한 바이트 기반 객체
		FileInputStream in = null;
		
		try {
			
			in = new FileInputStream("test.txt");
			int read = 0; // 읽어온 데이터를 담을 변수 
			
			//byte기반에 -1은 문서의 끝을 의미
			while ( (read = in.read()) != -1) {
				System.out.print((char)read);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}


	}

}
