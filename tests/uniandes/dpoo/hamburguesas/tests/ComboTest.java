package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Combo;
//import uniandes.dpoo.hamburguesas.mundo.Ingrediente;
//import uniandes.dpoo.hamburguesas.mundo.ProductoAjustado;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;

public class ComboTest {
	ProductoMenu ProductoUno;
	ProductoMenu ProductoDos;
	ProductoMenu ProductoTres;
	ProductoMenu papitas;
	ProductoMenu papitasBestias;
	ProductoMenu Cola;
	ArrayList<ProductoMenu> Items1 = new ArrayList<ProductoMenu>();
	Combo Combo1;
	ArrayList<ProductoMenu> Items2 = new ArrayList<ProductoMenu>();
	Combo Combo2;
	ArrayList<ProductoMenu> Items3 = new ArrayList<ProductoMenu>();
	Combo Combo3;


	@BeforeEach
    void setUp( ) throws Exception
    {
    	ProductoUno = new ProductoMenu( "amborguesaSimple", 8000 );
    	ProductoDos = new ProductoMenu( "amborguesaVegana", 12000 );
    	ProductoTres = new ProductoMenu( "misterBist", 40000 );
    	papitas = new ProductoMenu( "PapitasFrancesas", 6000);
    	papitasBestias = new ProductoMenu( "PapasAzules", 6000);
    	Cola = new ProductoMenu( "NukaCola", 5500);
    	Items1.add(ProductoUno);
    	Items1.add(papitas);
    	Items1.add(Cola);
    	Combo1 = new Combo("Estudiantil", 0.15, Items1);
    	Items2.add(ProductoDos);
    	Items2.add(papitas);
    	Combo2 = new Combo("Vegano", 0.05, Items2);
    	Items3.add(ProductoTres);
    	Items3.add(papitasBestias);
    	Items3.add(Cola);
    	Combo3 = new Combo("Brutal", 0.20, Items3);
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
    }

    @Test
    void testNombre() {
    	assertEquals("Estudiantil", Combo1.getNombre(), "No es el nombre :c");
    }
    
    @Test
    void testGetPrecio( )
    {
    	Combo3 = new Combo("Brutal", 0.20, Items3);
    	int precioBrutal = (int)((ProductoTres.getPrecio() + papitasBestias.getPrecio() + Cola.getPrecio()) * (1 - 0.2));
        assertEquals(precioBrutal, Combo3.getPrecio(), "No corresponde al valor correcto del combo");
    }
    
    @Test
    void testProductosMenu( ) {
    // Que los productos del menú sean los que obviamente son (evitar bajar conteo de pruebas)
    assertEquals(Items2, Combo2.getProductosMenu(),"Los productos somehow no son los productos");
    }
    
    @Test
    void testTextoFactura() {
        StringBuffer sb = new StringBuffer( );
        sb.append( "Combo " + Combo3.getNombre() + "\n" );
        sb.append( " Descuento: " + 0.2 + "\n" );
        sb.append( "      	      " + Combo3.getPrecio( ) + "\n" );

        String concat = sb.toString( );
        assertEquals(concat, Combo3.generarTextoFactura(),"El texto no corresponde");
    }
    
}
