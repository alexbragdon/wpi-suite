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

import com.google.gson.Gson;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;

/**
 * Exports requirements to a file.
 * @version Apr 4, 2014
 */
public class RequirementExporter {
    /**
     * The list of requirements to export.
     */
    private List<ExportRequirement> requirements = new ArrayList<ExportRequirement>();
    
    /**
     * Creates a new RequirementExporter with the given requirements.
     *
     * @param requirements requirements to export
     */
    public RequirementExporter(List<Requirement> requirements) {
        for (Requirement requirement : requirements) {
            this.requirements.add(new ExportRequirement(requirement));
        }
    }
    
    public void exportTo(File file) throws FileNotFoundException {
        PrintWriter writer = new PrintWriter(file);
        String output = new Gson().toJson(requirements, requirements.getClass());
        writer.write(output);
        writer.close();
    }
}
