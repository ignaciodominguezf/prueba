package com.prueba.listas;

public class Lista {
	private Nodo nodoUno;
	private Nodo nodoDos;

	public void agregar(int valor) {
		Nodo n = new Nodo();
		n.setNum(valor);
		if (nodoUno == null) {
			nodoUno = n;
			nodoDos = nodoUno;
		} else {
			Nodo t = nodoDos;
			nodoDos = n;
			nodoDos.setAnterior(t);
			t.setSiguiente(t);
		}
	}

	public void eliminar(int valor) {
		Nodo t;
		Nodo aux;
		if (nodoUno != null) {
			if (nodoUno.getNum() == valor) {
				t = nodoUno;
				nodoUno = t.getSiguiente();
				if (nodoUno == null) {
					nodoDos = null;
				} else {
					nodoUno.setAnterior(null);
				}

			} else if (nodoDos.getNum() == valor) {
				t = nodoDos;
				nodoDos = t.getAnterior();
				nodoDos.setSiguiente(null);
			} else {
				aux = nodoUno;
				t = nodoUno.getSiguiente();
				while (t != null) {
					if (t.getNum() == valor) {
						aux.setSiguiente(t.getSiguiente());
						t.getSiguiente().setAnterior(aux);
						break;
					}
					aux = t;
					t = t.getSiguiente();
				}
			}

		}
	}
	public boolean isNull() {
        return nodoUno==null;
    }
	public Nodo consultar(int valor) {
		Nodo t = nodoUno;
		while (t != null) {
			System.out.println(t.getNum() + " =>");
			t = t.getSiguiente();
		}
		t = nodoDos;
		while (t != null) {
			System.out.println(t.getNum() + " =>");
			t = t.getAnterior();
		}

		return t; 
		
	}
	
	public class Nodo {

		private int num;
		private Nodo anterior; 
		private Nodo siguiente;
		public int getNum() {
			return num;
		}
		public void setNum(int num) {
			this.num = num;
		}
		public Nodo getAnterior() {
			return anterior;
		}
		public void setAnterior(Nodo anterior) {
			this.anterior = anterior;
		}
		public Nodo getSiguiente() {
			return siguiente;
		}
		public void setSiguiente(Nodo siguiente) {
			this.siguiente = siguiente;
		} 
	}
}
