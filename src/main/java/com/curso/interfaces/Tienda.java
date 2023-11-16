/**
 * 
 */
package com.curso.interfaces;

import java.util.List;

import com.curso.model.Modelo;

/**
 * @author Admin
 *
 */
public interface Tienda {
	
	void insertarProducto(Modelo producto);
    void eliminarProducto(String nombre);
    void modificarProducto(String nombre, double nuevoPrecio);
    List<Modelo> buscarProductosPorSeccion(String seccion);
    

}
