/*******************************************************************************
 * Copyright (c) 2013 WPI-Suite
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: Team Rolling Thunder
 ******************************************************************************/
package edu.wpi.cs.wpisuitetng.modules.planningpoker.controller;

import java.util.ArrayList;

import edu.wpi.cs.wpisuitetng.modules.requirementmanager.models.Requirement;
import edu.wpi.cs.wpisuitetng.network.RequestObserver;
import edu.wpi.cs.wpisuitetng.network.models.IRequest;

/**
 * This observer handles responses to requests for all requirements
 *
 * @version $Revision: 1.0 $
 * @author justinhess
 */
public class GetRequirementsRequestObserver implements RequestObserver {
	
	private final GetRequirementsController controller;
	
	/**
	 * Constructs the observer given a GetRequirementsController
	 * @param controller the controller used to retrieve requirements
	 */
	public GetRequirementsRequestObserver(GetRequirementsController controller) {
		this.controller = controller;
	}

	/**
	 * Parse the requirements out of the response body and pass them to the controller
	 *
	 */
	@Override
	public void responseSuccess(IRequest iReq) {
		// Convert the JSON array of requirements to a Requirement object array
		final Requirement[] requirements = Requirement.fromJsonArray(iReq.getResponse().getBody());
		
		ArrayList<Requirement> arrayReqs = new ArrayList<Requirement>();
		
		for (Requirement r : requirements) {
		    arrayReqs.add(r);
		}
		
		// Pass these Requirements to the controller
		controller.receivedRequirements(arrayReqs);
	}

	/**
	 * 
	 */
	@Override
	public void responseError(IRequest iReq) {
		fail(iReq, null);
	}

	/**
	 * Put an error requirement in the PostBoardPanel if the request fails.
	 * 
	 */
	@Override
	public void fail(IRequest iReq, Exception exception) {
		final ArrayList<Requirement> arrayReqs = new ArrayList<Requirement>();
		arrayReqs.add(new Requirement(6, "Error", "error desc"));
		controller.receivedRequirements(arrayReqs);
	}

}
