/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Serves the planning poker web app.
 *
 * @author Team Romulus
 * @version Apr 30, 2014
 */
public class WPIPlanningPokerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
 
    /*
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,
                    IOException {
        String[] parts = req.getRequestURI().split("/");
        String filename = "planningpoker" + File.separator + parts[parts.length - 1];
        String contextPath = getServletContext().getRealPath(File.separator);
        File file = new File(contextPath + filename);
        
        resp.setContentType("text/html");
        resp.setContentLength((int) file.length());
        
        FileInputStream input = new FileInputStream(file);
        ServletOutputStream output = resp.getOutputStream();
        int bytes;
        while ((bytes = input.read()) != -1) {
            output.write(bytes);
        }
    }
}
