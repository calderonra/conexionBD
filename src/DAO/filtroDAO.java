/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import com.sun.istack.internal.logging.Logger;
import conexion.conexion;
import interfaces.metodos;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import modelo.Filtro;
import sun.util.logging.PlatformLogger;

/**
 *
 * @author UCA
 */
public class filtroDAO implements metodos<Filtro> {

    private static final String SQL_INSERT ="";
    private static final String SQL_UPDATE ="UPDATE filtros_aceite SET marca=?,stock=?,existencia=? WHERE codFiltro=?";
    private static final String SQL_DELETE ="DELETE FROM filtros_aceite WHERE codFiltro=?";
    private static final String SQL_READ ="SELECT * FROM filtros_aceite WHERE codFiltro=?";
    private static final String SQL_READALL ="SELECT * FROM filtros_aceite";
    private static final conexion con=conexion.conectar();
    
    
    @Override
    public boolean create(Filtro g) {
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_INSERT);
            ps.setString(1, g.getCodigo());
            ps.setString(2,g.getMarca());
            ps.setInt(3,g.getStock());
            ps.setBoolean(4, true);
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("no furulo");
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean delete(Object key) {
        PreparedStatement ps;
        try{
            ps=con.getCnx().prepareStatement(SQL_DELETE);
            ps.setString(1,key.toString());
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("rip");
        }finally{
            con.cerrarConexion();
        }
        return false;
    }

    @Override
    public boolean update(Filtro c) {
        PreparedStatement ps;
        try{
            System.out.println(c.getCodigo());
            ps=con.getCnx().prepareStatement(SQL_UPDATE);
            ps.setString(1, c.getCodigo());
            ps.setString(2,c.getMarca());
            ps.setInt(3,c.getStock());
            if(ps.executeUpdate()>0){
                return true;
            }
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            System.out.println("rip");
        }finally{
            con.cerrarConexion();
        }
        return false;
        
    }

    @Override
    public Filtro read(Object key) {
       Filtro f=null;
       PreparedStatement ps;
       ResultSet rs;
       try{
           ps=con.getCnx().prepareStatement(SQL_READ);
           rs=ps.executeQuery();
           while(rs.next()){
               f= new Filtro(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getBoolean(5));
           }
            rs.close();
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
           System.out.println("rip");
       }finally{
           
           con.cerrarConexion();
       }
       return f;
    }

    @Override
    public ArrayList<Filtro> readAll() {
        ArrayList<Filtro> all = new ArrayList();
        Statement s;
        ResultSet rs;
         try{
           s=con.getCnx().prepareStatement(SQL_READALL);
           rs=s.executeQuery(SQL_READALL);
           while(rs.next()){
               all.add(new Filtro(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getInt(4),rs.getBoolean(5)));
           }
            rs.close();
       }catch(SQLException ex){
           System.out.println(ex.getMessage());
           System.out.println("rip");
       }finally{
           
           con.cerrarConexion();
       }
         return all;
    }

    
    
}
