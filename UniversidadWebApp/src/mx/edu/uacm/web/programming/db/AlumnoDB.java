package mx.edu.uacm.web.programming.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import mx.edu.uacm.web.programming.bean.AlumnoBean;
import mx.edu.uacm.web.programming.bean.CarreraBean;
import mx.edu.uacm.web.programming.bean.MateriaBean;

public class AlumnoDB {

public boolean storeMateriasAlumno(String matricula, String [] cves_materias) throws SQLException {

	boolean toRet = false;
	
	Connection conn= null;
	
	try {
		
		conn = DbUtil.getConnection();
		conn.setAutoCommit(false);
		
		String sql_statement = "insert into materia_x_alumno values (?, ? , ? )";
		
		PreparedStatement sql_stmt = conn.prepareStatement(sql_statement);
		
		
		for (String str : cves_materias) {

			sql_stmt.setString(1, matricula);
			sql_stmt.setString(2, str);
			sql_stmt.setString(3, "reg");
			
			sql_stmt.executeUpdate();
		}
		conn.commit();
		toRet = true;
	}catch(SQLException sqle ) {
				conn.rollback();
				sqle.printStackTrace();
	} finally {
		conn.close();
	}
	
	return toRet;
}
	
public List<MateriaBean> loadMateriasBySemestre( String semestre ) throws SQLException{
		
		List<MateriaBean> list = new ArrayList<MateriaBean>();
		
		Connection conn = null;
		
		try {
		
			conn = DbUtil.getConnection();
			
			String sql_statement = "Select * from materias where semestre = ?";
			
			PreparedStatement sql_stmt = conn.prepareStatement(sql_statement);
			
			sql_stmt.setString(1, semestre);
			
			ResultSet rs = sql_stmt.executeQuery( );
			
			
			while (rs.next()) {
				
				String cve_materia = rs.getString("cve_materia");
				semestre  = rs.getString("semestre");
				String nombre  = rs.getString("nombre");
				String carrera  = rs.getString("carrera");
				  
				MateriaBean materiaBean = new MateriaBean ( cve_materia, semestre, nombre, carrera);
				list.add(materiaBean);
			}
		}finally {
			conn.close();
		}
		
		return list;
	}

	
	public AlumnoBean consultaAlumno(String matricula) throws SQLException {
		AlumnoBean alumnoBean = null;
		
		Connection conn =null;
		
		try {
			String sql = " select a.matricula, a.nombre, a.ap1, a.ap2, a.semestre, " +
						 " c.nombre as nombre_carrera from alumno a, carrera c where a.matricula = ? and c.carrera =  a.carrera";
			
			conn = DbUtil.getConnection();
			
			PreparedStatement pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, matricula);
			
			ResultSet rs = pstmt.executeQuery();
			
			while ( rs.next() ) {
				matricula = rs.getString("matricula");
				String nombre = rs.getString("nombre");
				String ap1 = rs.getString("ap1");
				String ap2 = rs.getString("ap2");
				String semestre = rs.getString("semestre");
				String carrera = rs.getString("nombre_carrera");
				
				alumnoBean = new AlumnoBean(matricula, nombre, ap1, ap2, semestre, carrera);
				
			}
			
		} finally {
			conn.close();
		}
		
		return alumnoBean;
	}
	
	public boolean storeAlumno( AlumnoBean alumnoBean) throws SQLException {
		boolean toRet = false;
		Connection conn = null;
		
				
		try {
			conn = DbUtil.getConnection();
			
			String sql = "insert into alumno values ( ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement pstmt = conn.prepareStatement( sql );
			
			pstmt.setString( 1, alumnoBean.getMatricula() );
			pstmt.setString( 2, alumnoBean.getNombre() );
			pstmt.setString( 3, alumnoBean.getAp1() );
			pstmt.setString( 4, alumnoBean.getAp2() );
			pstmt.setString( 5, alumnoBean.getSemestre() );
			pstmt.setString( 6, alumnoBean.getCarrera() );
			
			int reg = pstmt.executeUpdate();
			
			if (reg > 0)
				toRet = true;

		}finally {
			conn.close();
		}
		
		return toRet;
	}
	
	public List<CarreraBean> loadCarreras() throws SQLException{
		
		List<CarreraBean> list = new ArrayList<CarreraBean>();
		
		Connection conn = null;
		
		try {
		
			conn = DbUtil.getConnection();
			
			String sql_statement = "Select * from carrera";
			
			Statement sql_stmt = conn.createStatement( );
			
			ResultSet rs = sql_stmt.executeQuery( sql_statement );
			
			while (rs.next()) {
				String carrera = rs.getString("carrera");
				String nombre  = rs.getString("nombre");
				
				CarreraBean carreraBean = new CarreraBean(carrera, nombre);
				list.add(carreraBean);
			}
		}finally {
			conn.close();
		}
		
		return list;
	}
	
}
