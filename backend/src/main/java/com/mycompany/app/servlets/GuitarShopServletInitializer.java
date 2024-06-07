package com.mycompany.app.servlets;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import java.util.Set;

public class GuitarShopServletInitializer implements javax.servlet.ServletContainerInitializer {

	@Override
	public void onStartup(Set<Class<?>> classes, ServletContext servletContext) throws ServletException {
		// Регистрация ReviewsServlet
		WebServlet annotation = ReviewsServlet.class.getAnnotation(WebServlet.class);
		servletContext.addServlet(annotation.name(), ReviewsServlet.class)
				.addMapping(annotation.urlPatterns());

		// Регистрация CartServlet
		annotation = CartServlet.class.getAnnotation(WebServlet.class);
		servletContext.addServlet(annotation.name(), CartServlet.class)
				.addMapping(annotation.urlPatterns());

		// Регистрация ItemsServlet
		annotation = ItemsServlet.class.getAnnotation(WebServlet.class);
		servletContext.addServlet(annotation.name(), ItemsServlet.class)
				.addMapping(annotation.urlPatterns());

		// Регистрация OrdersServlet
		annotation = OrdersServlet.class.getAnnotation(WebServlet.class);
		servletContext.addServlet(annotation.name(), OrdersServlet.class)
				.addMapping(annotation.urlPatterns());

		// Регистрация UsersServlet
		annotation = UsersServlet.class.getAnnotation(WebServlet.class);
		servletContext.addServlet(annotation.name(), UsersServlet.class)
				.addMapping(annotation.urlPatterns());
	}
}
