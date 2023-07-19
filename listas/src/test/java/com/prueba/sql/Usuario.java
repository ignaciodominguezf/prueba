package com.prueba.sql;
import java.sql.Date;
import java.sql.Timestamp;

public class Usuario {
	private Date fecha ;
	private Double total;
	private Double subtotal;
	private String direccion;
	private long idUser;
	
	
	public long getIdUser() {
		return idUser;
	}


	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}


	public Usuario(Date date,Double total,Double subtotal,String direccion, long idUserDes) {
		this.fecha = date;
		this.total = total;
		this.subtotal = subtotal;
		this.direccion = direccion;
		this.idUser = idUserDes;
		
	}


	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(Double subtotal) {
		this.subtotal = subtotal;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}