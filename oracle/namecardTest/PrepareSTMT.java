package com.oracle.namecardTest;

import java.sql.*; // 자바 sql 라이브러리

public class PrepareSTMT {

	public static void main(String[] args) {
		//(oracle) jdbc:oracle:thin:@localhost:1521:xe
		//(mySQL) jdbc:mysql://localhost:3306/db이름
		// 미리 입력할 오라클 주소, 계정, 비밀번호를 변수로 저장
		String DB_URL = "jdbc:oracle:thin:@127.0.0.1:1521:XE";
        String DB_USER = "scott";
        String DB_PASSWORD = "1234";

        //conn 은 오라클 연결을 위한 객체
        Connection conn = null; //연결을 만드는 객체
        // stmt는  sql 문을 실행하기 위한 객체
        PreparedStatement pstmt = null;  
        // rs는  sql문 실행 후 결과값을 받아오는 객체
       // ResultSet rs = null; // Insert는 결과값이 필요없다.
       // 데이터 입력 SQL 만들기
        String query = "UPDATE namecard SET company = ? WHERE NO = ?"; // emp 테이블에 있는 모든 데이터를 가져오는 쿼리
  
        
        try {
        	//1. 오라클 드라이버 로딩   ( ojdbc6_g.jar )    		
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException e ) { //클래스를 못찾았을때 (드라이버 없을때 )
            e.printStackTrace();
        }

        try { //DB 연결 부분은 try-catch 문으로 예외 발생시 처리
        	//2. DB 연결
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            //3. SQL 문 준비
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, "daum net"); // 첫번째 파라메타(?)
            pstmt.setInt(2, 2);				// 두번째 파라메타(?)
            //4. SQL 쿼리 실행
            pstmt.executeUpdate();
            
            conn.commit(); // 영구저장

            System.out.println("수정완료!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
              
                pstmt.close();		//State문 닫기
                conn.close();
            } catch (SQLException e) {}
        }
    }

}
