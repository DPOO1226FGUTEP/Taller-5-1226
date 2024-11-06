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
	Ingrediente ingrediente1;
	Ingrediente ingrediente2;
	@BeforeEach
    void setUp( ) throws Exception
    {
    	ProductoMenuBase = new ProductoMenu( "amborguesa", 8000 );
    	Ajustado1 = new ProductoAjustado(ProductoMenuBase);
    	ingrediente1 = new Ingrediente("SalsaTomate", 200);
    	ingrediente2 = new Ingrediente("CrispyOnion", 1000);
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
    void testGetPrecioSimple( )
    {
        assertEquals(ProductoMenuBase.getPrecio(), Ajustado1.getPrecio(), "No corresponde al valor original");
    }
    
    @Test
    void testGetPrecioCompuesto( )
    {
    	Ajustado1.agregarIngredinete(ingrediente1);
    	Ajustado1.eliminarIngredinete(ingrediente2);
    	int calculado = ProductoMenuBase.getPrecio() + ingrediente1.getCostoAdicional() - ingrediente2.getCostoAdicional();
        assertEquals(calculado, Ajustado1.getPrecio(), "No corresponde al valor original +- Ingredientes Adicionales");
    }
    
    @Test
    void testAgregarIngrediente( )
    {
    	Ajustado1.agregarIngredinete(ingrediente1);
        assertEquals(1, Ajustado1.cantidadAgregados(), "CantidadIncorrecta");
    	Ajustado1.agregarIngredinete(ingrediente2);
        assertEquals(2, Ajustado1.cantidadAgregados(), "CantidadIncorrecta");   
    }
    
    @Test
    void testEliminarIngrediente( )
    {
    	Ajustado1.eliminarIngredinete(ingrediente1);
        assertEquals(1, Ajustado1.cantidadEliminados(), "CantidadIncorrecta");
    }
    
    
    @Test
    void testGenerarTextoFacturaSimple( )
    {
        StringBuffer sb = new StringBuffer( );
        sb.append(ProductoMenuBase);
        sb.append( "            " + Ajustado1.getPrecio( ) + "\n" );

        String concat =  sb.toString( );
        
        assertEquals( concat,Ajustado1.generarTextoFactura(), "La factura está peye" );
    }
    
    @Test
    void testGenerarTextoFacturaCompuesta( ) {
    // No se agrega debido a que sería necesario crear getters para las listas y se perdería cobertura
    }

}


