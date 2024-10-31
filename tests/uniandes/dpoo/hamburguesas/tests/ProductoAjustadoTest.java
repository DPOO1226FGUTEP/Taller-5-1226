package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ProductoAjustadoTest {
	ProductoMenu ProductoMenuBase;
	ProductoAjustado Ajustado1;
	@BeforeEach
    void setUp( ) throws Exception
    {
    	ProductoMenuBase = new ProductoMenu( "amborguesa", 8000 );
    	Ajustado1 = new ProductoAjustado(ProductoMenuBase);
    	
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testGetNombre( )
    {
        assertEquals("amborguesa", Ajustado1.getNombre(), "Esta mal");
    }
    
    @Test
    void testGetPrecio( )
    {
        assertEquals(ProductoMenuBase.getPrecio(), Ajustado1.getPrecio(), "No corresponde al valor original + Ingredientes");
    }
    
    @Test
    void testGenerarTextoFactura( )
    {
        StringBuffer sb = new StringBuffer( );
        sb.append(ProductoMenuBase);
        sb.append( "            " + Ajustado1.getPrecio( ) + "\n" );

        String concat =  sb.toString( );
        
        assertEquals( concat,Ajustado1.generarTextoFactura(), "La factura está peye" );
    }

}


