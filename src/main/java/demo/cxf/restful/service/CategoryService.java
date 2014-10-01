package demo.cxf.restful.service;

import demo.cxf.restful.dao.BookShelfDAO;
import demo.cxf.restful.model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

/*
 * TODO: Category Service class - Get, update and delete a category (add category is added to /categories service)
 */

@Path("/bookshelf/categories/{id}")
@Produces({"application/xml", "application/json"})
@Consumes({"application/xml", "application/json"})
@Component(value = "categoryServiceImplementation")
public class CategoryService implements ICategoryService{

    @Autowired
	private BookShelfDAO bookShelfDAO;

    public BookShelfDAO getBookShelfDAO() {
		return bookShelfDAO;
	}

	@GET
	public Category getCategory(@PathParam("id") String categoryId) {
		System.out.println("Get Category called with category categoryId: " + categoryId);
		Category cat = getBookShelfDAO().getCategory(categoryId);
		if (cat == null) {
			ResponseBuilder builder = Response.status(Status.BAD_REQUEST);
			builder.type("application/xml");
			builder.entity("<error>Category Not Found</error>");
			throw new WebApplicationException(builder.build());
		} else {
			return cat;
		}
	}

    @PUT
    public Response updateCategory(Category category) {
        System.out.println("Update Category with category id : " + category.getCategoryId());
        Category cat = getBookShelfDAO().getCategory(category.getCategoryId());
        if (cat == null) {
            return Response.status(Status.BAD_REQUEST).build();
        } else {
            getBookShelfDAO().updateCategory(category);
            return Response.ok().build();
        }
    }

    public String modifyCategory(Category category) {
        return updateCategory(category).getStatusInfo().getReasonPhrase();
    }

	//POST - unused

	@DELETE
	public Response deleteCategory(@PathParam("id") String categoryId) {
		System.out.println("Delete Category with category categoryId : " + categoryId);
		Category cat = getBookShelfDAO().getCategory(categoryId);
		if (cat == null) {
			return Response.status(Status.BAD_REQUEST).build();
		} else {
			getBookShelfDAO().deleteCategory(categoryId);
			return Response.ok().build();
		}
	}

	public String removeCategory(@PathParam("id") String categoryId) {
		return deleteCategory(categoryId).getStatusInfo().getReasonPhrase();
	}
}
