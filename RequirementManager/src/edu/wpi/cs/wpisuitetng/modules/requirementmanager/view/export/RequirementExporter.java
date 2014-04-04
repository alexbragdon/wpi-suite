/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.export;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * Exports requirements to a file.
 * @version Apr 4, 2014
 */
public class RequirementExporter {
    /**
     * The list of requirements to export.
     */
    private List<Requirement> requirements;
    
    /**
     * Creates a new RequirementExporter with the given requirements.
     *
     * @param requirements requirements to export
     */
    public RequirementExporter(List<Requirement> requirements) {
        this.requirements = new ArrayList<Requirement>(requirements);
    }
    
    public void exportTo(File file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        writer.println("Hello world");
        writer.close();
    }
}
