package com.ese.util;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

public class SessionLocale {

	public static Locale main(HttpServletRequest request, String locale) {
		
		HttpSession session = request.getSession();
        Locale lo = new Locale(locale);
        
        session.setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, lo);
        // session never timeout
        session.setMaxInactiveInterval(0);
		return lo;
	}
}
