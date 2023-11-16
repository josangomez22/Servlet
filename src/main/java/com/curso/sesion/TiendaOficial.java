package com.curso.sesion;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import com.curso.model.Modelo;

/**
 * Servlet implementation class TiendaOficial
 */
@WebServlet("/miTienda")
public class TiendaOficial extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Modelo tienda;

    @Override
    public void init() throws ServletException {
        tienda = new Modelo();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Insertar un nuevo producto
        if (request.getParameter("nombreProducto") != null) {
            insertarProducto(request, response);
            return;
        }

        // Buscar productos por sección
        if (request.getParameter("seccion") != null) {
            buscarProductosPorSeccion(request, response);
            return;
        }

        // Eliminar un producto
        if (request.getParameter("nombreProductoEliminar") != null) {
            eliminarProducto(request, response);
            return;
        }

        // Modificar el precio de un producto
        if (request.getParameter("nombreProductoModificar") != null) {
            modificarPrecioProducto(request, response);
            return;
        }

        // Si ninguna operación coincide, redirigir a una página de error
        response.sendRedirect("JSP/Error.jsp");
    }

    private void insertarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombre = request.getParameter("nombreProducto");
            String seccion = request.getParameter("seccionProducto");
            double precio = Double.parseDouble(request.getParameter("precioProducto"));
            int stock = Integer.parseInt(request.getParameter("stockProducto"));

            Modelo nuevoProducto = new Modelo(nombre, seccion, precio, stock);

            tienda.insertarProducto(nuevoProducto);

            // Redirigir respuesta
            response.sendRedirect("JSP/Exito.jsp");
        } catch (NumberFormatException e) {
            // redirigir a una página de error
            response.sendRedirect("JSP/Error.jsp");
        }
    }

    private void buscarProductosPorSeccion(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String seccionABuscar = request.getParameter("seccion");

        List<Modelo> productosEnSeccion = tienda.buscarProductosPorSeccion(seccionABuscar);

        request.setAttribute("productosEnSeccion", productosEnSeccion);

        // Redirigir a la página JSP que mostrará los resultados
        RequestDispatcher dispatcher = request.getRequestDispatcher("JSP/ExitoBusqueda.jsp");
        dispatcher.forward(request, response);
    }

    private void eliminarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreProductoAEliminar = request.getParameter("nombreProductoEliminar");

        if (nombreProductoAEliminar != null && !nombreProductoAEliminar.isEmpty()) {
            tienda.eliminarProducto(nombreProductoAEliminar);
            response.sendRedirect("JSP/Exito.jsp");
        } else {
            // mostrar un mensaje de error
            response.sendRedirect("JSP/Error.jsp");
        }
    }

    private void modificarPrecioProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String nombreProductoAModificar = request.getParameter("nombreProductoModificar");
            double nuevoPrecio = Double.parseDouble(request.getParameter("precioProductoModificar"));

            if (nombreProductoAModificar != null && !nombreProductoAModificar.isEmpty()) {
                tienda.modificarProducto(nombreProductoAModificar, nuevoPrecio);
                response.sendRedirect("JSP/Exito.jsp");
            } else {
                // Si no se proporciona un nombre o un nuevo precio, redirigir a otra página o mostrar un mensaje de error
                response.sendRedirect("JSP/Error.jsp");
            }
        } catch (NumberFormatException e) {
            // Manejo de la excepción, redirigir a una página de error
            response.sendRedirect("JSP/Error.jsp");
        }
    }
}
