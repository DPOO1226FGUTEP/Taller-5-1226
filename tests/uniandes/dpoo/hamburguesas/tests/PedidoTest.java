package uniandes.dpoo.hamburguesas.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import uniandes.dpoo.hamburguesas.mundo.Pedido;
import uniandes.dpoo.hamburguesas.mundo.Producto;
import uniandes.dpoo.hamburguesas.mundo.ProductoMenu;


public class PedidoTest {
	Pedido pedidoJuan;
	Pedido pedidoElsa;
	static int numeroPedidos;
	
	@BeforeEach
    void setUp( ) throws Exception
    {
		pedidoJuan = new Pedido("Juan ManosCortas", "Bosa central");
		pedidoElsa = new Pedido("Elsa Capuntas", "Rosales");
    }

    @AfterEach
    void tearDown( ) throws Exception
    {
        Pedido.setNumeroPedidos(0);
    }

    @Test
    void testGetId() {
    	assertEquals(numeroPedidos++,pedidoJuan.getIdPedido(),"No corresponde al Id Correcto");
    	assertEquals(numeroPedidos++,pedidoElsa.getIdPedido(),"No corresponde al Id Correcto");
    	for (int i=0; i<6; i++) {
    		String nombre = Integer.toString(i);
    		Pedido PedidoGenerico = new Pedido(nombre, "X Y Z");
    		assertEquals(numeroPedidos++,PedidoGenerico.getIdPedido(),"No corresponde al Id Correcto");
    	}
    }
    
    @Test
    void testGetNombreCLiente(){
    	assertEquals("Juan ManosCortas", pedidoJuan.getNombreCliente(), "Juan no es manosCOrtas :c");
    	assertEquals("Elsa Capuntas", pedidoElsa.getNombreCliente(), "No Elsa Capuntas ;c");
    }
    
    @Test
    void testAgregarProductoyGetProducto() {
    	ProductoMenu bist = new ProductoMenu( "misterBistBurger", 40000 );
    	pedidoJuan.agregarProducto(bist);
    	ProductoMenu papitas = new ProductoMenu( "PapitasFrancesas", 6000);
    	pedidoElsa.agregarProducto(papitas);
    	
    	ArrayList<Producto> productosJuan = pedidoJuan.consultarProductos();
    	ArrayList<Producto> productosElsa = pedidoElsa.consultarProductos();
    	assertTrue(productosJuan.contains(bist), "O el codigo esta mal o se lo trago el rappi :c");
    	assertTrue(productosElsa.contains(papitas), "La robaron de sus papas");
    	
    }
    
    @Test
    void testGetPrecioTotalPedido() {
    	/*Prueba la funcion de precio total y las auxiliares de calculo*/
    	ProductoMenu bist = new ProductoMenu( "misterBistBurger", 40000 );
    	ProductoMenu papitas = new ProductoMenu( "PapitasFrancesas", 6000);
    	pedidoJuan.agregarProducto(bist);
    	pedidoJuan.agregarProducto(papitas);
    	int precioEsperadoSinIva = 0;
    	for (Producto item : pedidoJuan.consultarProductos()) {
    		precioEsperadoSinIva += item.getPrecio();
    	}
    	int precioConIva = (int)(precioEsperadoSinIva* (1 + 0.19));
    	assertEquals(precioConIva, pedidoJuan.getPrecioTotalPedido(), "Fraude Fiscal o mal codigo");
    }


@Test
void testGenerarTextoFactura() {
	ProductoMenu papitas = new ProductoMenu( "PapitasFrancesas", 6000);
	pedidoElsa.agregarProducto(papitas);
	int precioEsperadoSinIva = 0;
	for (Producto item : pedidoElsa.consultarProductos()) {
		precioEsperadoSinIva += item.getPrecio();
	}
	int costoiva = (int)(precioEsperadoSinIva*0.19);
	
    StringBuffer sb = new StringBuffer( );

    sb.append( "Cliente: " + "Elsa Capuntas" + "\n" );
    sb.append( "Dirección: " + "Rosales" + "\n" );
    sb.append( "----------------\n" );
    
    for( Producto item : pedidoElsa.consultarProductos() )
    {
        sb.append( item.generarTextoFactura( ) );
    }

    sb.append( "----------------\n" );
    sb.append( "Precio Neto:  " + Integer.toString(precioEsperadoSinIva) + "\n" );
    sb.append( "IVA:          " + Integer.toString(costoiva)+ "\n" );
    sb.append( "Precio Total: " + pedidoElsa.getPrecioTotalPedido( ) + "\n" );

    String facturaFinal = sb.toString( );
    assertEquals(facturaFinal, pedidoElsa.generarTextoFactura(), "Factura se genera mal");
	}
}