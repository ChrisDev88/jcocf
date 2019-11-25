/**
 * 
 */
package de.datatrain;

import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sap.cloud.sdk.cloudplatform.connectivity.DestinationAccessor;
import com.sap.cloud.sdk.datamodel.odata.helper.Order;
import com.sap.cloud.sdk.odatav2.connectivity.ODataException;
import com.sap.cloud.sdk.s4hana.connectivity.DefaultErpHttpDestination;
import com.sap.cloud.sdk.s4hana.connectivity.ErpHttpDestination;


import de.datatrain.vdm.namespaces.tpsrv.Tile;
import de.datatrain.vdm.services.DefaultTpsrvService;

import java.util.List;

/**
 * @author ch-Fu
 *
 */

@WebServlet("/tiles")
public class TPServlet extends HttpServlet{
	
	 	private static final long serialVersionUID = 1L;
	    private static final Logger logger = LoggerFactory.getLogger(TPServlet.class);

	    private final ErpHttpDestination destination = DestinationAccessor.getDestination("saperp").asHttp().decorate(DefaultErpHttpDestination::new);

	    @Override
	    protected void doGet(final HttpServletRequest request, final HttpServletResponse response)
	            throws ServletException, IOException {
	        try {
	            final List<Tile> tiles =
	                    new DefaultTpsrvService()
	                            .getAllTile()
	                            .orderBy(Tile.SORT, Order.ASC)
	                            .top(200)
	                            .execute(destination);

	            response.setContentType("application/json");
	            response.getWriter().write(new Gson().toJson(tiles));
	        } catch (final ODataException e) {
	            logger.error(e.getMessage(), e);
	            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	            response.getWriter().write(e.getMessage());
	        }

	    }

}
