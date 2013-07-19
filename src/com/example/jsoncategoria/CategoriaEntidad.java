package com.example.jsoncategoria;

public class CategoriaEntidad {
	int Codigo;
	String Descrip;
	String Nomb;
	
	
	public CategoriaEntidad(int codigo, String descrip, String nomb) {
		super();
		Codigo = codigo;
		Descrip = descrip;
		Nomb = nomb;
	}
	
	public int getCodigo() {
		return Codigo;
	}
	public void setCodigo(int codigo) {
		Codigo = codigo;
	}
	public String getDescrip() {
		return Descrip;
	}
	public void setDescrip(String descrip) {
		Descrip = descrip;
	}
	public String getNomb() {
		return Nomb;
	}
	public void setNomb(String nomb) {
		Nomb = nomb;
	}
	
}
