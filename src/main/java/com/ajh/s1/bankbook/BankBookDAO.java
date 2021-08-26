package com.ajh.s1.bankbook;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.ajh.s1.utill.DBConnector;

public class BankBookDAO {

	public BankBookDTO getSelect(BankBookDTO bankBookDTO) {

		DBConnector dbConnector = new DBConnector();

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		BankBookDTO bbDTO = null;

		try {

			String sql = "SELECT * FROM BANKBOOK WHERE BOOKNUMBER=?";

			con = dbConnector.getConnect();

			st = con.prepareStatement(sql);
			st.setLong(1, bankBookDTO.getBookNumber());
			rs = st.executeQuery();

			if (rs.next()) {
				bbDTO = new BankBookDTO();

				bbDTO.setBookNumber(rs.getLong("bookNumber"));
				bbDTO.setBookName(rs.getString("bookName"));
				bbDTO.setBookRate(rs.getDouble("bookRate"));
				bbDTO.setBookSale(rs.getInt("bookSale"));
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnector.disConnect(rs, st, con);
		}

		return bbDTO;

	}

	public ArrayList<BankBookDTO> getList() {
		DBConnector dbConnector = new DBConnector();

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		BankBookDTO bbDTO = null;

		ArrayList<BankBookDTO> ar = new ArrayList<BankBookDTO>();

		try {
			String sql = "SELECT * FROM BANKBOOK";

			con = dbConnector.getConnect();
			st = con.prepareStatement(sql);
			rs = st.executeQuery();

			while (rs.next()) {
				bbDTO = new BankBookDTO();

				bbDTO.setBookNumber(rs.getLong("bookNumber"));
				bbDTO.setBookName(rs.getString("bookName"));
				bbDTO.setBookRate(rs.getDouble("bookRate"));
				bbDTO.setBookSale(rs.getInt("bookSale"));

				ar.add(bbDTO);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnector.disConnect(rs, st, con);
		}

		return ar;

	}

	public int setInsert(BankBookDTO bankBookDTO) {
		DBConnector dbConnector = new DBConnector();

		Connection con = null;
		PreparedStatement st = null;

		int result = 0;

		try {
			con = dbConnector.getConnect();

			String sql = "INSERT INTO BANKBOOK VALUES(BANKBOOK_SEQ.NEXTVAL,?,?,?)";

			st = con.prepareStatement(sql);

			st.setString(1, bankBookDTO.getBookName());
			st.setDouble(2, bankBookDTO.getBookRate());
			st.setInt(3, bankBookDTO.getBookSale());

			result = st.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			dbConnector.disConnect(st, con);

		}

		return result;
	}

}
