package kr.exam.file;

import java.io.FileInputStream;

public class FileInputExam01 {
	public static void main(String[] args) {

		//파일을 읽기 위한 바이트 기반 객체
		FileInputStream in = null;
		
		try {
			
			in = new FileInputStream("test.txt");
			int read = 0; // 읽어온 데이터를 담을 변수 
			
			// byte 기반이기 때문에 읽어올 때 byte를 사용함
			byte[] buffer = new byte[100];
			
			//byte기반에 -1은 문서의 끝을 의미
			while ( (read = in.read(buffer)) != -1) {
				System.out.write(buffer, 0, read);
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}


	}

}
