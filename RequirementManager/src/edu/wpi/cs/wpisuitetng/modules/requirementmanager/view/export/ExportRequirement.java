/*******************************************************************************
 * Copyright (c) 2012-2014 -- WPI Suite
 *
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.requirementmanager.view.export;

import java.util.ArrayList;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.AcceptanceTest;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.NoteList;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementPriority;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.RequirementType;
import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.characteristics.TransactionHistory;

/**
 * A version of Requirement that stores only the fields that get imported and exported.
 *
 * @version Apr 4, 2014
 */
public class ExportRequirement {
    /**
     * The requirement's name.
     */
    private final String name;
    
    /**
     * The requirement's description.
     */
    private final String description;
    
    /**
     * The type of the requirement.
     */
    private final RequirementType type;
    
    /**
     * The requirement's priority.
     */
    private final RequirementPriority priority;
    
    /**
     * The notes associated with this requirement.
     */
    private final NoteList notes;
    
    /**
     * The transaction history for this requirement.
     */
    private final TransactionHistory transactions;
    
    /**
     * The requirement's acceptance tests.
     */
    private final ArrayList<AcceptanceTest> tests;
    
    /**
     * Creates an ExportRequirement based on the given requirement.
     *
     * @param requirement the requirement to copy
     */
    public ExportRequirement(Requirement requirement) {
        name = requirement.getName();
        description = requirement.getDescription();
        type = requirement.getType();
        priority = requirement.getPriority();
        notes = requirement.getNotes();
        transactions = requirement.getHistory();
        tests = requirement.getTests();
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public RequirementType getType() {
        return type;
    }

    public RequirementPriority getPriority() {
        return priority;
    }

    public NoteList getNotes() {
        return notes;
    }

    public TransactionHistory getTransactions() {
        return transactions;
    }

    public ArrayList<AcceptanceTest> getTests() {
        return tests;
    }
}
