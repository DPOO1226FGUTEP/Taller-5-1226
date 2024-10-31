package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoMenuTest
{
    private ProductoMenu ProductoMenu1;

    @BeforeEach
    void setUp( ) throws Exception
    {
    	ProductoMenu1 = new ProductoMenu( "amborguesa", 8000 );
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testGetNombre( )
    {
        assertEquals( "amborguesa", ProductoMenu1.getNombre( ), "El nombre del producto no es el esperado." );
    }
    
    @Test
    void testGetPrecio( )
    {
        assertEquals( 8000, ProductoMenu1.getPrecio( ), "El precio del producto no es el esperado." );
    }
    @Test
    void testGenerarTextoFactura( )
    {
    	String nombre = ProductoMenu1.getNombre();
    	String precio = Integer.toString((ProductoMenu1.getPrecio()));
    	String concat;
        StringBuffer sb = new StringBuffer( );
        sb.append( nombre + "\n" );
        sb.append( "            " + precio + "\n" );
        concat = sb.toString( );
        assertEquals( concat, ProductoMenu1.generarTextoFactura(), "La factura está peye" );
    }

}
