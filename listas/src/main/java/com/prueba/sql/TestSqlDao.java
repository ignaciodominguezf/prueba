package com.prueba.sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Mejorar cada uno de los métodos a nivel SQL y código cuando sea necesario
 * Razonar cada una de las mejoras que se han implementado
 * No es necesario que el código implementado funcione 
 */
public class TestSqlDao {

	private static TestSqlDao instance = new TestSqlDao();
	
	private TestSqlDao() {

	}

	private static TestSqlDao getInstance() {

		return instance;
	}

	/**
	 * Obtiene el ID del último pedido para cada usuario
	 */
	public Hashtable<Long, Long> getMaxUserOrderId(long idTienda) throws Exception {
		//MODIFICACION EN LA CONSULTA PARA PODER REALIZAR LA CONSULTA PEDIDA 
		String query = String.format("SELECT ID_PEDIDO, ID_USUARIO FROM PEDIDOS AS P " + "WHERE p1.ID_PEDIDO = (SELECT MAX(ID_PEDIDO) FROM PEDIDOS "
                + "WHERE ID_USUARIO = P.ID_USUARIO)" + " AND ID_TIENDA = %s", idTienda);
        Connection connection = getConnection();
        PreparedStatement stmt = connection.prepareStatement(query);
        ResultSet rs = stmt.executeQuery();
        Hashtable<Long, Long> maxOrderUser = new Hashtable<Long, Long>();

        if (rs.first())
        {
            long idPedido = rs.getInt("ID_PEDIDO");
            long idUsuario = rs.getInt("ID_USUARIO");
            maxOrderUser.put(idUsuario, idPedido);
        }
        return maxOrderUser;
	}

	/**
	 * Copia todos los pedidos de un usuario a otro
	 */
	public void copyUserOrders(long idUserOri, long idUserDes) throws Exception {

		String query = String.format("SELECT FECHA, TOTAL, SUBTOTAL, DIRECCION FROM PEDIDOS WHERE ID_USUARIO = %s", idUserOri);
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		List<Usuario> lista = new ArrayList<Usuario>();//UTILIZACION DE UNA CLASE PARA SETEAR LOS DATOS DE LA CONSULTA Y PODER COPIARLOS
		 while(rs.next()) {
			 Usuario usu = new Usuario(rs.getDate("FECHA"),rs.getDouble("TOTAL"),rs.getDouble("SUBTOTAL"),rs.getString("DIRECCION"),idUserDes);
			 lista.add(usu);
		 }
		
		 if (lista.isEmpty())
	            return;
	        Connection connection2 = getConnection();
	        connection2.setAutoCommit(false);
	        StringBuilder insertSb = new StringBuilder();
	        insertSb.append("INSERT INTO PEDIDOS (FECHA, TOTAL, SUBTOTAL, DIRECCION, ID_USUARIO) VALUES (?,?,?,?,?)");

	        for(int i = 0;i<lista.size();i++) {
	        	stmt.setDate(1, lista.get(i).getFecha());
	        	stmt.setDouble(2, lista.get(i).getTotal());
	        	stmt.setDouble(3, lista.get(i).getSubtotal());
	        	stmt.setString(4, lista.get(i).getDireccion());
	        	stmt.setLong(5, lista.get(i).getIdUser());
	        }
	        
	        PreparedStatement stmt2 = connection2.prepareStatement(insertSb.toString());
	        stmt2.executeUpdate();
	        connection2.commit();
	}
	

	/**
	 * Obtiene los datos del usuario y pedido con el pedido de mayor importe para la tienda dada
	 */
	public void getUserMaxOrder(long idTienda, long userId, long orderId, String name, String address) throws Exception {
		// SE AÑADIÓ EN TOTAL EL METODO MAX PARA PODER CONSEGUIR EL PEDIDO CON MAYOR IMPORTE
		String query = String.format("SELECT U.ID_USUARIO, P.ID_PEDIDO, MAX(P.TOTAL), U.NOMBRE, U.DIRECCION FROM PEDIDOS AS P "
										+ "INNER JOIN USUARIOS AS U ON P.ID_USUARIO = U.ID_USUARIO WHERE P.ID_TIENDA = %", idTienda);
		Connection connection = getConnection();
		PreparedStatement stmt = connection.prepareStatement(query);
		ResultSet rs = stmt.executeQuery();
		rs.first();//REALIZAMOS EL MOVIMIENTO AL CURSOR AL PRIMER ROW PARA PODER SACAR DE LA CONSULTA LO ESPERADO
		System.out.println(rs.getString("DIRECCION")+" "+  rs.getString("NOMBRE")+" "+  rs.getInt("ID_USUARIO")+" "+  rs.getInt("ID_PEDIDO"));
	}

	private Connection getConnection() {

		// return JDBC connection
		return null;
	}
}
