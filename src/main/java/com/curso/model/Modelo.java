/**
 * 
 */
package com.curso.model;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.html.HTMLDocument.Iterator;

import com.curso.interfaces.Tienda;

/**
 * @author Admin
 *
 */
public class Modelo implements Tienda {
    private List<Modelo> inventario;
    private String nombre;
    private String seccion;
    private int Stock;
    private double precio;
    
    public Modelo(String nombre, String seccion, double precio, int Stock) {
		super();
	}
    
    public Modelo() {
        inventario = new ArrayList<>();
        inventario.add(new Modelo("Producto1", "Electrónicos", 500.0, 10));
        inventario.add(new Modelo("Producto2", "Ropa", 30.0, 50));
        inventario.add(new Modelo("Producto3", "Electrónicos", 800.0, 5));
    }

	public List<Modelo> getInventario() {
		return inventario;
	}

	public void setInventario(List<Modelo> inventario) {
		this.inventario = inventario;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}

	public int getStock() {
		return Stock;
	}

	public void setStock(int stock) {
		Stock = stock;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	 @Override
    public List<Modelo> buscarProductosPorSeccion(String seccion) {
        List<Modelo> productosEnSeccion = new ArrayList<>();

        for (Modelo producto : inventario) {
            if (producto.getSeccion().equalsIgnoreCase(seccion)) {
                productosEnSeccion.add(producto);
            }
        }

        return productosEnSeccion;
    }

	@Override
    public void insertarProducto(Modelo producto) {
        inventario.add(producto);
    }

    public void modificarProducto(String nombre, double nuevoPrecio) {
    	for (Modelo producto : inventario) {
            if (producto.getNombre().equals(nombre)) {
                producto.setPrecio(nuevoPrecio);
                return;
            }
        }
    }


    @Override
	public void eliminarProducto(String nombre) {
		 inventario.removeIf(producto -> producto.getNombre().equals(nombre));		
	}

}