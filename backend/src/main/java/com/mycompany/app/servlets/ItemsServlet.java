package com.mycompany.app.servlets;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.IterableUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;
import org.bson.types.ObjectId;

import com.mycompany.app.dao.CategoryDAO;
import com.mycompany.app.filters.GuitarFilter;
import com.mycompany.app.filters.StringsFilter;
import com.mycompany.app.models.Good;
import com.mycompany.app.models.Guitar;
import com.mycompany.app.models.Strings;

@WebServlet(name = "items-servlet", urlPatterns = { "/api/items", "/api/items/*" })
public class ItemsServlet extends BaseServlet {

	private static final String UPLOAD_DIRECTORY = "items/";

	private static final int MEMORY_THRESHOLD = 1024 * 1024 * 3; // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40; // 40MB
	private static final int MAX_REQUEST_SIZE = 1024 * 1024 * 50; // 50MB

	private DiskFileItemFactory factory;
	private ServletFileUpload upload;

	@Override
	public void init() throws ServletException {
		super.init();

		factory = new DiskFileItemFactory();
		factory.setSizeThreshold(MEMORY_THRESHOLD);
		factory.setRepository(new File(System.getProperty("java.io.tmpdir")));

		upload = new ServletFileUpload(factory);
		upload.setFileSizeMax(MAX_FILE_SIZE);
		upload.setSizeMax(MAX_REQUEST_SIZE);
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		if (pathInfo.matches("^/[0-9a-fA-F]{24}$*")) { // /api/items
			getItemById(req, resp);
		} else if (pathInfo.endsWith("")) { // /api/items/{id}
			searchItems(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getRequestURI().endsWith("/new")) { // /api/items/new
			addNewItem(req, resp);
		} else {
			resp.sendError(HttpServletResponse.SC_NOT_FOUND);
		}
	}

	private void searchItems(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String type = req.getParameter("type");
		String query = req.getParameter("q");

		if (type == null || !(type.equalsIgnoreCase("guitar") || type.equalsIgnoreCase("strings"))) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid item type");
			return;
		}

		if (type.equalsIgnoreCase("guitar")) {
			GuitarFilter filter = new GuitarFilter();
			if (query != null) {
				filter.setSearchQuery(query);
			}
			List<Guitar> guitars = IterableUtils.toList(daoFactory.createGuitarDAO().findByFilter(filter));
			writeObject(resp, HttpServletResponse.SC_OK, guitars);
		} else if (type.equalsIgnoreCase("strings")) {
			StringsFilter filter = new StringsFilter();
			if (query != null) {
				filter.setSearchQuery(query);
			}
			List<Strings> strings = IterableUtils.toList(daoFactory.createStringsDAO().findByFilter(filter));
			writeObject(resp, HttpServletResponse.SC_OK, strings);
		}
	}

	private void getItemById(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		String id = req.getPathInfo().substring(1);

		if (id == null || id.isEmpty()) {
			writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid item id");
			return;
		}

		Strings strings = daoFactory.createStringsDAO().findById(id);
		if (strings != null) {
			writeObject(resp, HttpServletResponse.SC_OK, strings);
			return;
		}

		Guitar guitar = daoFactory.createGuitarDAO().findById(id);
		if (guitar != null) {
			writeObject(resp, HttpServletResponse.SC_OK, guitar);
			return;
		}
		writeError(resp, HttpServletResponse.SC_NOT_FOUND, "Item not found");
	}

	private void addNewItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		if (req.getSession().getAttribute("role") == null || !req.getSession().getAttribute("role").equals("admin")) {
			writeError(resp, HttpServletResponse.SC_FORBIDDEN, "Users can to add new items only with role admin");
			return;
		}

		List<FileItem> formItems = null;
		try {
			formItems = upload.parseRequest(req);

			String textData = getJsonFromMultipart(formItems);

			Good newItem = gson.fromJson(textData, Good.class);

			if (newItem.getType() == null || !(newItem.getType().equalsIgnoreCase("guitar")
					|| newItem.getType().equalsIgnoreCase("string"))) {
				writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid item type");
				return;
			}

			if (newItem.getName() == null || newItem.getName().isEmpty()
					|| newItem.getDescription() == null || newItem.getDescription().isEmpty()
					|| newItem.getCost() == null || newItem.getImages() == null || newItem.getImages().isEmpty()
					|| newItem.getRemaining() == null || newItem.getCategories() == null
					|| newItem.getCategories().isEmpty()) {
				writeError(resp, HttpServletResponse.SC_BAD_REQUEST, "Invalid item data");
				return;
			}

			// FIXME спаггети-код (уже поздно...)
			if (newItem.getType().equalsIgnoreCase("guitar")) {
				Guitar newGuitar = gson.fromJson(textData, Guitar.class);
				String id = daoFactory.createGuitarDAO().save(newGuitar);
				newGuitar.setId(new ObjectId(id));
				newGuitar.setImages(saveItemImages(id, formItems));
				CategoryDAO categoryDAO = daoFactory.createCategoryDAO();
				for (String category : newItem.getCategories()) {
					categoryDAO.addGood(category, id);
				}
				daoFactory.createGuitarDAO().update(newGuitar);
				writeObject(resp, HttpServletResponse.SC_CREATED, newGuitar);
			} else if (newItem.getType().equalsIgnoreCase("string")) {
				Strings newStrings = gson.fromJson(textData, Strings.class);
				String id = daoFactory.createStringsDAO().save(newStrings);
				newStrings.setId(new ObjectId(id));
				newStrings.setImages(saveItemImages(id, formItems));
				CategoryDAO categoryDAO = daoFactory.createCategoryDAO();
				for (String category : newItem.getCategories()) {
					categoryDAO.addGood(category, id);
				}
				daoFactory.createStringsDAO().save(newStrings);
				writeObject(resp, HttpServletResponse.SC_CREATED, newStrings);
			}
		} catch (Exception e) {
			resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getLocalizedMessage());
			return;
		}

	}

	private List<String> saveItemImages(String itemId, List<FileItem> formItems) throws Exception {
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIRECTORY;
		// проверка на существование директории и ее создание, если она отсутствует
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		int imageIndex = 1;
		List<String> names = new ArrayList<String>();
		for (FileItem item : formItems) {
			if (!item.isFormField()) {
				String fileName = itemId + "_" + imageIndex + "." + FilenameUtils.getExtension(item.getName());
				File storeFile = new File(uploadPath + File.separator + fileName);
				item.write(storeFile);
				imageIndex++;
				names.add(fileName);
			}
		}
		return names;
	}

	private String getJsonFromMultipart(List<FileItem> formItems) throws IOException {
		try {
			for (FileItem fileItem : formItems) {
				if (fileItem.isFormField()) {
					return fileItem.getString();
				}
			}
		} catch (Exception e) {
			throw new IOException(e);
		}
		return null;
	}
}