/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Pedido;

/**
 *
 * @author lucia
 */
public class GerenciarPedidosController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException {
        response.setContentType("text/html;charset=UTF-8");
        String acao = request.getParameter("acao");
        if(acao.equals("listar")){
            listar(request,response);
        }
        else{
            if(acao.equals("mudarestado")){
                mudarEstado(request,response);
            }
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NoSuchMethodException ex) {
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    public void listar(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException{
        try{
            request.setAttribute("pedidos", Pedido.obterPedidos());
            RequestDispatcher view = request.getRequestDispatcher("/gerenciarPedidos.jsp");
            view.forward(request,response);
        }catch(ServletException e){
            throw e;
        }catch(IOException e){
           throw new ServletException (e); 
        }
        catch(ClassNotFoundException ex){
            
        }catch(SQLException ex){
            
        }
    }

    private void mudarEstado(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, ClassNotFoundException, NoSuchMethodException, IllegalAccessException {
        try{
            Integer numero = Integer.parseInt(request.getParameter("numero"));
            String estado = request.getParameter("estado");
            Pedido pedido = Pedido.obterPedido(numero);
            
            boolean resultado = false;
            Class classe = Class.forName("model.Pedido");
            Method metodo = classe.getMethod(estado);
            resultado = (Boolean) metodo.invoke(pedido);
            
            String mensagem ="";
            if(resultado){
                mensagem = "estado alterado com sucesso";
            }
            else{
                mensagem = "Nao foi possivel alterar estado do pedido";
            }
            response.getWriter().println(mensagem);
            
        }catch (ClassNotFoundException ex){
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SQLException ex){
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IOException ex){
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (NoSuchMethodException ex){
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (SecurityException ex){
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex){
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (IllegalArgumentException ex){
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (InvocationTargetException ex){
            Logger.getLogger(GerenciarPedidosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
