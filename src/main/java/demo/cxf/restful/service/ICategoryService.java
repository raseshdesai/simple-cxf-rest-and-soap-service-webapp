package demo.cxf.restful.service;

import demo.cxf.restful.model.Category;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 *  Created by Rasesh Desai on 3/17/14.
 */

@WebService
@Path("/bookshelf/categorysoapservice")
public interface ICategoryService {

    @WebResult
    Category getCategory(@WebParam String id);

    @WebResult
    String modifyCategory(@WebParam Category category);

    @WebResult
    String removeCategory(@WebParam String categoryId);
}
